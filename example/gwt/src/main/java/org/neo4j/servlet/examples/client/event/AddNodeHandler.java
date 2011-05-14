package org.neo4j.servlet.examples.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author tbaum
 * @since 14.05.11 16:55
 */
public interface AddNodeHandler extends EventHandler {
// -------------------------- OTHER METHODS --------------------------

    void onAddNode(AddNode event);
}
