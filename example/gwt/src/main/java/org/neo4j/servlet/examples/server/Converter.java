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
package org.neo4j.servlet.examples.server;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.servlet.examples.client.ClientNode;
import org.neo4j.servlet.examples.client.ClientRelationship;

import java.util.ArrayList;

/**
 * @author tbaum
 * @since 14.05.11 16:30
 */
public class Converter {
// -------------------------- STATIC METHODS --------------------------

    public static ClientNode toClientNode(final Node nodeById) {
        final ArrayList<ClientRelationship> rels = new ArrayList<ClientRelationship>();
        for (Relationship relationship : nodeById.getRelationships()) {
            rels.add(toClientRelationship(relationship));
        }

        return new ClientNode(nodeById.getId(), rels);
    }

    private static ClientRelationship toClientRelationship(final Relationship relationship) {
        return new ClientRelationship(relationship.getId(), relationship.getType().name(), relationship.getStartNode().getId(), relationship.getEndNode().getId());
    }
}
