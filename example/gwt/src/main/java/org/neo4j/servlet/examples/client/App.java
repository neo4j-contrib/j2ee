package org.neo4j.servlet.examples.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import org.neo4j.servlet.examples.client.event.*;
import org.neo4j.servlet.examples.client.service.NodeService;
import org.neo4j.servlet.examples.client.service.NodeServiceAsync;

/**
 * @author tbaum
 * @since 14.05.11 16:04
 */
public class App implements EntryPoint, ValueChangeHandler<String> {
// ------------------------------ FIELDS ------------------------------

    private static final NodeServiceAsync service = GWT.create(NodeService.class);
    private final EventBus eventbus = GWT.create(SimpleEventBus.class);
    private NodePanel panel;

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface EntryPoint ---------------------

    public void onModuleLoad() {
        panel = new NodePanel(eventbus);
        RootPanel.get().add(panel);

        eventbus.addHandler(AddNode.TYPE, new AddNodeHandler() {
            @Override public void onAddNode(final AddNode event) {
                service.addNode(event.getFrom(), event.getRelation(), event.getTo(),
                        new NodeCallback("add node", eventbus));
            }
        });

        eventbus.addHandler(RemoveNode.TYPE, new RemoveNodeHandler() {
            @Override public void onRemoveNode(final RemoveNode event) {
                service.removeNode(event.getId(), event.getShowNode(), new NodeCallback("removing node", eventbus));
            }
        });

        eventbus.addHandler(RemoveRelationship.TYPE, new RemoveRelationshipHandler() {
            @Override public void onRemoveRelationship(final RemoveRelationship event) {
                service.removeRelation(event.getId(), event.getShowNode(), new NodeCallback("removing relationship", eventbus));
            }
        });


        History.addValueChangeHandler(this);
        History.fireCurrentHistoryState();
    }

// --------------------- Interface ValueChangeHandler ---------------------


    @Override public void onValueChange(final ValueChangeEvent<String> nodeId) {
        String value = nodeId.getValue();
        Long id = value.isEmpty() ? null : Long.parseLong(value);
        service.getNode(id, new NodeCallback("Loading node", eventbus));
    }
}
