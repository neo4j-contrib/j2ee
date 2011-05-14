package org.neo4j.servlet.examples.client;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author tbaum
 * @since 14.05.11 16:43
 */
public class ClientRelationship implements IsSerializable {
// ------------------------------ FIELDS ------------------------------

    private long id;
    private String name;
    private long startNode;
    private long endNode;

// --------------------------- CONSTRUCTORS ---------------------------

    protected ClientRelationship() {
    }

    public ClientRelationship(final long id, final String name, final long startNode, final long endNode) {
        this.id = id;
        this.name = name;
        this.startNode = startNode;
        this.endNode = endNode;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public long getEndNode() {
        return endNode;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getStartNode() {
        return startNode;
    }
}
