/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ryan
 */
public abstract class AbstractConfigurable {
    
    private Map<String, Object> properties;
    
    public AbstractConfigurable() {
        properties = new HashMap<String, Object>();
    }
    
    protected Map<String, Object> properties() {
       return properties;
    }
    
    public void setParameter(String key, Object value) {
        properties.put(key, value);
    }
    
    public Object getParameter(String key) {
        return properties.get(key);
    }

}
