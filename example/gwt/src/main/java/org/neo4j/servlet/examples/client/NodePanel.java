package org.neo4j.servlet.examples.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import org.neo4j.servlet.examples.client.event.AddNode;
import org.neo4j.servlet.examples.client.event.ShowNode;
import org.neo4j.servlet.examples.client.event.ShowNodeHandler;

/**
 * @author tbaum
 * @since 14.05.11 15:44
 */
public class NodePanel extends Composite implements ShowNodeHandler {
// ------------------------------ FIELDS ------------------------------

    private static NodePanelUiBinder ourUiBinder = GWT.create(NodePanelUiBinder.class);
    @UiField Button add;
    @UiField TextBox from;
    @UiField TextBox relation;
    @UiField Label title;
    @UiField TextBox to;
    @UiField FlowPanel outgoing;
    @UiField FlowPanel incoming;
    private final EventBus eventBus;


    @UiHandler("add") void onAdd(ClickEvent click) {
        String toValue = to.getValue();
        eventBus.fireEvent(new AddNode(from.getValue(), relation.getValue(), toValue.equals("(create new)") ? "" : toValue));
    }

// --------------------------- CONSTRUCTORS ---------------------------

    public NodePanel(EventBus eventBus) {
        this.eventBus = eventBus;
        initWidget(ourUiBinder.createAndBindUi(this));

        relation.setValue("relates_to");
        to.setValue("(create new)");

        this.eventBus.addHandler(ShowNode.TYPE, this);

    }

    @Override public void onShowNode(final ShowNode event) {
        ClientNode clientNode = event.getNode();
        from.setValue(String.valueOf(clientNode.getId()));
        title.setText("Node[" + clientNode.getId() + "]");
        incoming.clear();
        for (ClientRelationship relationship : clientNode.getRelationships()) {
            if (relationship.getEndNode() == clientNode.getId()) {
                incoming.add(new IncomingRelationshipPanel(relationship, eventBus));
            }
        }

        outgoing.clear();
        for (ClientRelationship relationship : clientNode.getRelationships()) {
            if (relationship.getStartNode() == clientNode.getId()) {
                outgoing.add(new OutgoingRelationshipPanel(relationship, eventBus));
            }
        }
    }


// -------------------------- INNER CLASSES --------------------------

    interface NodePanelUiBinder extends UiBinder<HTMLPanel, NodePanel> {
    }
}