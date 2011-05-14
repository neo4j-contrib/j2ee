/**
 * Copyright (c) 2002-2011 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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