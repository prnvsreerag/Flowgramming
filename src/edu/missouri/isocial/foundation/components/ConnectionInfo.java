/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components;

import edu.missouri.isocial.foundation.components.core.DraggableJPanel;
import edu.missouri.isocial.foundation.components.core.Connector;

/**
 *
 * @author Ryan
 */
public class ConnectionInfo {
    private DraggableJPanel startNode;
    private DraggableJPanel endNode;
    
    private Connector startConnector;
    private Connector endConnector;
    
    private Class connectionClass;
    
    public ConnectionInfo(DraggableJPanel startNode, DraggableJPanel endNode, Connector startConnector, Connector endConnector, Class connectionClass) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.startConnector = startConnector;
        this.endConnector = endConnector;
        this.connectionClass = connectionClass;
    }

    public DraggableJPanel getStartNode() {
        return startNode;
    }

    public DraggableJPanel getEndNode() {
        return endNode;
    }

    public Connector getStartConnector() {
        return startConnector;
    }

    public Connector getEndConnector() {
        return endConnector;
    }

    public Class getConnectionClass() {
        return connectionClass;
    }
}
