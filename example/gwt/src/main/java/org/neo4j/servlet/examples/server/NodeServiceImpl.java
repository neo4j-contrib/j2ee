package org.neo4j.servlet.examples.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.neo4j.graphdb.*;
import org.neo4j.servlet.Util;
import org.neo4j.servlet.examples.client.ClientNode;
import org.neo4j.servlet.examples.client.service.NodeService;

/**
 * @author tbaum
 * @since 14.05.11 15:56
 */
public class NodeServiceImpl extends RemoteServiceServlet implements NodeService {
// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface NodeServiceAsync ---------------------

    @Override public ClientNode getNode(final Long id) {
        GraphDatabaseService db = Util.instance(getServletContext());
        if (id == null) {
            return Converter.toClientNode(db.getReferenceNode());
        } else {
            return Converter.toClientNode(db.getNodeById(id));
        }
    }

    @Override public ClientNode addNode(final Long from, final String relation, final Long to) {
        GraphDatabaseService db = Util.instance(getServletContext());

        Transaction tx = db.beginTx();
        try {
            Node fromNode = db.getNodeById(from);
            Node toNode = to == null ? db.createNode() : db.getNodeById(to);
            fromNode.createRelationshipTo(toNode, DynamicRelationshipType.withName(relation));

            tx.success();
            return Converter.toClientNode(fromNode);
        } finally {
            tx.finish();
        }
    }

    @Override public ClientNode removeNode(final Long id, Long nodeId) {
        GraphDatabaseService db = Util.instance(getServletContext());

        Transaction tx = db.beginTx();
        try {
            Node node = db.getNodeById(id);
            for (Relationship relationship : node.getRelationships()) {
                relationship.delete();
            }
            node.delete();
            tx.success();

            return getNode(nodeId);
        } finally {
            tx.finish();
        }
    }

    @Override public ClientNode removeRelation(final Long id, Long nodeId) {
        GraphDatabaseService db = Util.instance(getServletContext());

        Transaction tx = db.beginTx();
        try {
            Relationship fromNode = db.getRelationshipById(id);

            fromNode.delete();
            tx.success();

            return getNode(nodeId);
        } finally {
            tx.finish();
        }
    }
}