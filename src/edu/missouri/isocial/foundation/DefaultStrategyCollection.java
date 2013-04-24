/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import edu.missouri.isocial.foundation.strategies.LookupImpl;
import edu.missouri.isocial.foundation.annotations.Strategy;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Ryan
 */
public class DefaultStrategyCollection implements StrategyCollection {

    private Map<Class, Object> internal;
    public DefaultStrategyCollection() {
        internal = new HashMap<Class, Object>();
                
        Lookup lookup = new LookupImpl();
        lookup.setAnnotation(Strategy.class);
        Set<Object> objs = lookup.getAll();
        
        for(Object obj: objs) {
           Class[] interfaces =  obj.getClass().getInterfaces();
           for(Class interfaze: interfaces) {
               internal.put(interfaze, obj);
           }
        }
        
        
    }

    @Override
    public <T> T get(Class<T> c) {
        return (T)internal.get(c);
    }
    
}
