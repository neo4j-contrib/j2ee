package org.neo4j.servlet.examples.client.event;

import com.google.gwt.event.shared.GwtEvent;
import org.neo4j.servlet.examples.client.ClientNode;

/**
 * @author tbaum
 * @since 14.05.11 17:00
 */
public class ShowNode extends GwtEvent<ShowNodeHandler> {
// ------------------------------ FIELDS ------------------------------

    public static Type<ShowNodeHandler> TYPE = new Type<ShowNodeHandler>();
    private final ClientNode node;

// --------------------------- CONSTRUCTORS ---------------------------

    public ShowNode(final ClientNode node) {
        this.node = node;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public ClientNode getNode() {
        return node;
    }

// -------------------------- OTHER METHODS --------------------------

    protected void dispatch(ShowNodeHandler handler) {
        handler.onShowNode(this);
    }

    public Type<ShowNodeHandler> getAssociatedType() {
        return TYPE;
    }
}
