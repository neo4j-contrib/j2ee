package org.neo4j.servlet.examples.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author tbaum
 * @since 14.05.11 17:58
 */
public class RemoveRelationship extends GwtEvent<RemoveRelationshipHandler> {
// ------------------------------ FIELDS ------------------------------

    public static Type<RemoveRelationshipHandler> TYPE = new Type<RemoveRelationshipHandler>();

    private final long id;
    private final long showNode;

// --------------------------- CONSTRUCTORS ---------------------------

    public RemoveRelationship(final long id, final long showNode) {
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

    protected void dispatch(RemoveRelationshipHandler handler) {
        handler.onRemoveRelationship(this);
    }

    public Type<RemoveRelationshipHandler> getAssociatedType() {
        return TYPE;
    }
}
