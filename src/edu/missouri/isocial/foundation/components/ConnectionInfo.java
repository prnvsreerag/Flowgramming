/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components;

import edu.missouri.isocial.foundation.components.core.DraggableComponent;
import edu.missouri.isocial.foundation.components.core.Link;

/**
 *
 * @author Ryan
 */
public class ConnectionInfo {
    private DraggableComponent startNode;
    private DraggableComponent endNode;
    
    private Link startConnector;
    private Link endConnector;
    
    private Class connectionClass;
    
    public ConnectionInfo(DraggableComponent startNode, DraggableComponent endNode, Link startConnector, Link endConnector, Class connectionClass) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.startConnector = startConnector;
        this.endConnector = endConnector;
        this.connectionClass = connectionClass;
    }

    public DraggableComponent getStartNode() {
        return startNode;
    }

    public DraggableComponent getEndNode() {
        return endNode;
    }

    public Link getStartConnector() {
        return startConnector;
    }

    public Link getEndConnector() {
        return endConnector;
    }

    public Class getConnectionClass() {
        return connectionClass;
    }
}
