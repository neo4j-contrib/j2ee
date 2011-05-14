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
package org.neo4j.servlet.examples.client;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;

/**
 * @author tbaum
 * @since 14.05.11 16:22
 */
public class ClientNode implements IsSerializable {
// ------------------------------ FIELDS ------------------------------

    private long id;
    private ArrayList<ClientRelationship> relationships;

// --------------------------- CONSTRUCTORS ---------------------------

    protected ClientNode() {
    }

    public ClientNode(final long id, final ArrayList<ClientRelationship> relationships) {
        this.id = id;
        this.relationships = relationships;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public long getId() {
        return id;
    }

    public ArrayList<ClientRelationship> getRelationships() {
        return relationships;
    }
}
