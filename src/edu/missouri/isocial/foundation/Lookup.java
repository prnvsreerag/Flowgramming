/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import java.util.Set;

/**
 *
 * @author Ryan
 */
public interface Lookup {
    public void setAnnotation(Class annotation);
    
    public<T> Set<T> lookUp(Class<T> spi);
}
