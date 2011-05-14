package org.neo4j.servlet.examples.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author tbaum
 * @since 14.05.11 17:00
 */
public interface ShowNodeHandler extends EventHandler {
// -------------------------- OTHER METHODS --------------------------

    void onShowNode(ShowNode event);
}
