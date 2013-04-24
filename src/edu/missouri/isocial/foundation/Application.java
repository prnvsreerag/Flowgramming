/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

/**
 *
 * @author Ryan
 */
public interface Application {
    public void addToStartItems(Startable item);
    public void start(Object input);
}
