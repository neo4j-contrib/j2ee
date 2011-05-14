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
