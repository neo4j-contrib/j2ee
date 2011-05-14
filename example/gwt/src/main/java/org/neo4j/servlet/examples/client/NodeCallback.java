package org.neo4j.servlet.examples.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.neo4j.servlet.examples.client.event.ShowNode;

/**
* @author tbaum
* @since 14.05.11 18:04
*/
class NodeCallback implements AsyncCallback<ClientNode> {
// ------------------------------ FIELDS ------------------------------

    private final String message;
    private EventBus eventbus;

// --------------------------- CONSTRUCTORS ---------------------------

    public NodeCallback(final String message, final EventBus eventbus) {
        this.eventbus = eventbus;
        this.message = message;
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface AsyncCallback ---------------------

    @Override public void onFailure(final Throwable throwable) {
        String msg = message;
        Window.alert("Error: " + msg + "\n" + throwable.getMessage());
    }

    @Override public void onSuccess(final ClientNode result) {
        eventbus.fireEvent(new ShowNode(result));
    }
}
