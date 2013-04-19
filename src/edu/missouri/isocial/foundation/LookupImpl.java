/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Ryan
 */
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
}
