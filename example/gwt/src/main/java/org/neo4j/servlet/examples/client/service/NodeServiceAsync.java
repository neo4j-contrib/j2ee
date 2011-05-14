package org.neo4j.servlet.examples.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.neo4j.servlet.examples.client.ClientNode;

/**
 * @author tbaum
 * @since 14.05.11 15:56
 */
public interface NodeServiceAsync {
// -------------------------- OTHER METHODS --------------------------

    void addNode(Long from, String relation, Long to, AsyncCallback<ClientNode> async);

    void getNode(Long id, final AsyncCallback<ClientNode> async);

    void removeNode(Long id, Long nodeId, final AsyncCallback<ClientNode> async);

    void removeRelation(Long id, Long nodeId, final AsyncCallback<ClientNode> async);
}
