package org.neo4j.servlet.examples.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author tbaum
 * @since 14.05.11 17:58
 */
public interface RemoveNodeHandler extends EventHandler {
// -------------------------- OTHER METHODS --------------------------

    void onRemoveNode(RemoveNode event);
}
