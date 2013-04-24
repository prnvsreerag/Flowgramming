/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

/**
 *
 * @author Ryan
 */
public interface StrategyCollection {
    
    public <T> T get(Class<T> c);
}
