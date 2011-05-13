/**
 * Copyright (c) 2002-2011 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.servlet;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.EmbeddedGraphDatabase;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author tbaum
 * @since 13.05.11 14:49
 */
public class Neo4JContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger("neo4jdb");
    private static final String PREFIX = "neo4j:";

    @Override public void contextInitialized(final ServletContextEvent sce) {
        final ServletContext servletContext = sce.getServletContext();

        initDb(servletContext);
    }

    @Override public void contextDestroyed(final ServletContextEvent sce) {
        final ServletContext servletContext = sce.getServletContext();

        shutdownDb(servletContext);
    }

    void initDb(final ServletContext servletContext) {
        LOGGER.info("initializing neo4j-database");

        final String storeDir = getStorageDir(servletContext);
        LOGGER.info("neo4j-storage dir: " + storeDir);

        final Map<String, String> parameter = getParameter(servletContext);
        LOGGER.info("neo4j:parameter :" + parameter);

        final EmbeddedGraphDatabase graphDatabase = new EmbeddedGraphDatabase(storeDir, parameter);

        servletContext.setAttribute(Util.CONTEXT_KEY, graphDatabase);
    }

    private String getStorageDir(final ServletContext servletContext) {
        final String initParameter = servletContext.getInitParameter("neo4j-storage-dir");

        final String storage = (initParameter == null ? "${context}WEB-INF/db/neo4j" : initParameter);

        return storage.replaceAll("\\$\\{context\\}", servletContext.getRealPath("/"));
    }

    private Map<String, String> getParameter(final ServletContext servletContext) {
        final HashMap<String, String> parameter = new HashMap<String, String>();

        @SuppressWarnings({"unchecked"})
        final Enumeration<String> initParameterNames = servletContext.getInitParameterNames();

        while (initParameterNames.hasMoreElements()) {
            final String param = initParameterNames.nextElement();
            if (param.startsWith(PREFIX)) {
                parameter.put(param.substring(PREFIX.length()), servletContext.getInitParameter(param));
            }
        }

        return parameter;
    }

    void shutdownDb(final ServletContext servletContext) {
        try {
            LOGGER.info("stopping neo4j-database");
            final GraphDatabaseService graphDatabaseService = Util.instance(servletContext);
            graphDatabaseService.shutdown();
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
