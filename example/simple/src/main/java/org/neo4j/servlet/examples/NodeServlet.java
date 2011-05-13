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
package org.neo4j.servlet.examples;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.servlet.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.neo4j.graphdb.DynamicRelationshipType.withName;

/**
 * @author tbaum
 * @since 13.05.11 16:51
 */
public class NodeServlet extends HttpServlet {

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        final GraphDatabaseService db = Util.instance(getServletContext());

        final String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            response.sendRedirect("node/" + db.getReferenceNode().getId());
        } else {
            final long id = Long.parseLong(pathInfo.substring(1));
            request.setAttribute("node", db.getNodeById(id));
            request.getRequestDispatcher("/node.jspx").forward(request, response);
        }

    }

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        if (request.getParameter("add-relation") != null) {
            addReleation(request, response);
        } else if (request.getParameter("remove-relation") != null) {
            removeReleation(request, response);
        } else if (request.getParameter("remove-node") != null) {
            removeNode(request, response);
        } else {
            response.sendRedirect("../node");
        }
    }

    private void addReleation(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String releation = request.getParameter("relation");
        final Long from = parseId(request, "from");
        final Long to = parseId(request, "to");


        final GraphDatabaseService db = Util.instance(getServletContext());
        final Transaction tx = db.beginTx();

        try {

            final Node fromNode = db.getNodeById(from);
            final Node toNode = to == null ? db.createNode() : db.getNodeById(to);
            fromNode.createRelationshipTo(toNode, withName(releation));

            tx.success();
        } finally {
            tx.finish();
        }


        response.sendRedirect("../node/" + from);
    }

    private void removeReleation(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final Long relation = parseId(request, "id");

        final GraphDatabaseService db = Util.instance(getServletContext());
        final Transaction tx = db.beginTx();
        final Long from;
        try {

            final Relationship relationship = db.getRelationshipById(relation);
            from = relationship.getStartNode().getId();

            relationship.delete();

            tx.success();
        } finally {
            tx.finish();
        }


        response.sendRedirect("../node/" + from);
    }

    private void removeNode(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final Long nodeId = parseId(request, "id");

        final GraphDatabaseService db = Util.instance(getServletContext());
        final Transaction tx = db.beginTx();
        try {

            final Node node = db.getNodeById(nodeId);
            for (final Relationship relationship : node.getRelationships()) {
                relationship.delete();
            }
            node.delete();

            tx.success();
        } finally {
            tx.finish();
        }

        response.sendRedirect("../node");
    }

    private Long parseId(final HttpServletRequest request, final String f) {
        try {
            return Long.parseLong(request.getParameter(f));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
