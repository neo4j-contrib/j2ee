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
public class IncomingRelationshipPanel extends Composite {
// ------------------------------ FIELDS ------------------------------

    private static RelationshipPanelUiBinder ourUiBinder = GWT.create(RelationshipPanelUiBinder.class);
    @UiField Label startNode;
    @UiField Label relationship;
    @UiField Button removeStartNode;
    @UiField Button removeRelationship;

    private final EventBus eventBus;
    private final ClientRelationship current;

    @UiHandler("startNode") void goToStartNode(ClickEvent event) {
        History.newItem(String.valueOf(current.getStartNode()));
    }


    @UiHandler("removeStartNode") void removeStartNode(ClickEvent event) {
        eventBus.fireEvent(new RemoveNode(current.getStartNode(), current.getEndNode()));
    }


    @UiHandler("removeRelationship") void removeRelationship(ClickEvent event) {
        eventBus.fireEvent(new RemoveRelationship(current.getId(), current.getEndNode()));
    }
// --------------------------- CONSTRUCTORS ---------------------------

    public IncomingRelationshipPanel(final ClientRelationship relationship, final EventBus eventBus) {
        this.eventBus = eventBus;
        this.current = relationship;
        initWidget(ourUiBinder.createAndBindUi(this));


        this.startNode.setText("Node [" + relationship.getStartNode() + "]");
        this.relationship.setText(relationship.getName() + "[" + relationship.getId() + "]");
    }

// -------------------------- INNER CLASSES --------------------------

    interface RelationshipPanelUiBinder extends UiBinder<HTMLPanel, IncomingRelationshipPanel> {
    }
}