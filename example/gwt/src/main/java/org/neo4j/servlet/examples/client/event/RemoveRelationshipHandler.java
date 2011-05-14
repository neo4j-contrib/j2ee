package org.neo4j.servlet.examples.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author tbaum
 * @since 14.05.11 17:58
 */
public interface RemoveRelationshipHandler extends EventHandler {
// -------------------------- OTHER METHODS --------------------------

    void onRemoveRelationship(RemoveRelationship event);
}
