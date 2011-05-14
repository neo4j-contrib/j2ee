package org.neo4j.servlet.examples.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author tbaum
 * @since 14.05.11 17:58
 */
public class RemoveNode extends GwtEvent<RemoveNodeHandler> {
// ------------------------------ FIELDS ------------------------------

    public static Type<RemoveNodeHandler> TYPE = new Type<RemoveNodeHandler>();
    private final long id;
    private final long showNode;

// --------------------------- CONSTRUCTORS ---------------------------

    public RemoveNode(final long id, final long showNode) {
        this.id = id;
        this.showNode = showNode;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public long getId() {
        return id;
    }

    public long getShowNode() {
        return showNode;
    }

// -------------------------- OTHER METHODS --------------------------

    protected void dispatch(RemoveNodeHandler handler) {
        handler.onRemoveNode(this);
    }

    public Type<RemoveNodeHandler> getAssociatedType() {
        return TYPE;
    }
}
