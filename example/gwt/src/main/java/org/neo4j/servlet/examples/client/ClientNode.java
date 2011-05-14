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
