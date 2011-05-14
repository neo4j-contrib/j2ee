package org.neo4j.servlet.examples.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.neo4j.servlet.examples.client.ClientNode;

/**
 * @author tbaum
 * @since 14.05.11 15:56
 */
@RemoteServiceRelativePath("NodeService")
public interface NodeService extends RemoteService {
    ClientNode getNode(Long id);

    ClientNode addNode(Long from, String relation, Long to);

    ClientNode removeNode(Long id, Long nodeId);

    ClientNode removeRelation(Long id, Long nodeId);
}
