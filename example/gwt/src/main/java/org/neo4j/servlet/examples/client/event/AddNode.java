package org.neo4j.servlet.examples.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author tbaum
 * @since 14.05.11 16:55
 */
public class AddNode extends GwtEvent<AddNodeHandler> {
// ------------------------------ FIELDS ------------------------------

    public static Type<AddNodeHandler> TYPE = new Type<AddNodeHandler>();
    private final Long from;
    private final Long to;
    private final String relation;

// --------------------------- CONSTRUCTORS ---------------------------

    public AddNode(final String from, final String relation, final String toValue) {
        this.from = Long.parseLong(from);
        this.to = toValue.isEmpty() ? null : Long.parseLong(toValue);
        this.relation = relation;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public Long getFrom() {
        return from;
    }

    public String getRelation() {
        return relation;
    }

    public Long getTo() {
        return to;
    }

// -------------------------- OTHER METHODS --------------------------

    protected void dispatch(AddNodeHandler handler) {
        handler.onAddNode(this);
    }

    public Type<AddNodeHandler> getAssociatedType() {
        return TYPE;
    }
}
