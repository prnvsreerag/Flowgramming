/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import edu.missouri.isocial.foundation.components.core.Connection;

/**
 *
 * @author Ryan
 */
public interface ConnectionRepository {
    /**
     * 
     * @param connectionId representation of connection in the format of: #->#
     * @param connection value object
     */
    public void addConnection(String connectionId, Connection connection);
    
    public Connection getConnection(String connectionId);
    
    public void removeConnection(String connectionId);

    public void listConnections();

    public boolean hasConnection(String connectionId);
}
