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
public abstract class AbstractParameterizable {
    
    private Map<String, Object> parameters;
    
    public AbstractParameterizable() {
        parameters = new HashMap<String, Object>();
    }
    
    protected Map<String, Object> parameters() {
        return parameters;
    }
    
    public void setParameter(String key, Object value) {
        parameters.put(key, value);
    }
    
    public Object getParameter(String key) {
        return parameters.get(key);
    }

}
