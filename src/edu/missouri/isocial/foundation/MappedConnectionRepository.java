/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import edu.missouri.isocial.foundation.components.core.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ryan
 */
public class MappedConnectionRepository implements ConnectionRepository {
    private Map<String, Connection> _internal;
    
    public MappedConnectionRepository() {
        _internal = new HashMap<String, Connection>();
    }

    public void listConnections() {
        for (String key : _internal.keySet()) {
            System.out.println(key);
        }
    }

    @Override
    public void addConnection(String connectionId, Connection connection) {
        if(idIsValid(connectionId)) {
            _internal.put(connectionId, connection);
        }
    }

    @Override
    public Connection getConnection(String connectionId) {
        if(idIsValid(connectionId)) {
           return  _internal.get(connectionId);
        } else {
            return null;
        }
    }

    @Override
    public void removeConnection(String connectionId) {
        if(idIsValid(connectionId)) {
            _internal.remove(connectionId);
        }
    }    
    
    private boolean idIsValid(String connectionId) {
        
        if (!connectionId.contains("<->")) {
            return false;
        }
        
        String[] tokens = connectionId.split("<->");
        if(tokens.length != 2) {
            return false;
        }
        
        try {
            Integer.valueOf(tokens[0]);
            Integer.valueOf(tokens[1]);
        } catch(NumberFormatException nfe) {
            return false;
        }
        
        
        return true;
    }

    public boolean hasConnection(String connectionId) {
        if (!idIsValid(connectionId)) {
            return false;
        }

        if (_internal.containsKey(connectionId)) {
            return true;
        }

        return false;
    }
}
