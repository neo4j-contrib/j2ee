package org.neo4j.servlet.examples.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import org.neo4j.servlet.examples.client.event.RemoveNode;
import org.neo4j.servlet.examples.client.event.RemoveRelationship;

/**
 * @author tbaum
 * @since 14.05.11 17:24
 */
public class OutgoingRelationshipPanel extends Composite {
// ------------------------------ FIELDS ------------------------------

    private static RelationshipPanelUiBinder ourUiBinder = GWT.create(RelationshipPanelUiBinder.class);
    @UiField Label endNode;
    @UiField Label relationship;
    @UiField Button removeRelationship;
    @UiField Button removeEndNode;

    private final EventBus eventBus;
    private final ClientRelationship current;


    @UiHandler("endNode") void goToEndNode(ClickEvent event) {
        History.newItem(String.valueOf(current.getEndNode()));
    }


    @UiHandler("removeEndNode") void removeEndNode(ClickEvent event) {
        eventBus.fireEvent(new RemoveNode(current.getEndNode(), current.getStartNode()));
    }

    @UiHandler("removeRelationship") void removeRelationship(ClickEvent event) {
        eventBus.fireEvent(new RemoveRelationship(current.getId(), current.getStartNode()));
    }
// --------------------------- CONSTRUCTORS ---------------------------

    public OutgoingRelationshipPanel(final ClientRelationship relationship, final EventBus eventBus) {
        this.eventBus = eventBus;
        this.current = relationship;
        initWidget(ourUiBinder.createAndBindUi(this));

        this.relationship.setText(relationship.getName() + "[" + relationship.getId() + "]");
        this.endNode.setText("Node [" + relationship.getEndNode() + "]");
    }

// -------------------------- INNER CLASSES --------------------------

    interface RelationshipPanelUiBinder extends UiBinder<HTMLPanel, OutgoingRelationshipPanel> {
    }
}