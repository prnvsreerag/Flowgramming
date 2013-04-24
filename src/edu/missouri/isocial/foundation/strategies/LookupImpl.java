/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.strategies;

import edu.missouri.isocial.foundation.Lookup;
import edu.missouri.isocial.foundation.ScannedClassLoader;
import edu.missouri.isocial.foundation.annotations.Strategy;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryan
 */
@Strategy
public class LookupImpl implements Lookup {
    private Class annotation;

    @Override
    public void setAnnotation(Class annotation) {
        this.annotation = annotation;
    }

    @Override
    public <T> Set<T> lookUp(Class<T> spi) {
        Iterator<T> iterator = ScannedClassLoader.INSTANCE().getInstances(annotation, spi);
        Set<T> output = new HashSet<T>();
        while(iterator.hasNext()) {
            T t = iterator.next();
            output.add(t);
        }
        
        return output;
    }
    
    @Override
    public Set<Object> getAll() {
        Set<String> names = ScannedClassLoader.INSTANCE().getClasses(annotation);
        Set<Object> instances = new HashSet<Object>();
        for(String name: names) {
            Object obj;
            try {
                obj = Class.forName(name).newInstance();
                instances.add(obj);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LookupImpl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                continue;
            }
        }
        
        return instances;
    }
    
    
    
}
