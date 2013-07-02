/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

/**
 *
 * @author Ryan
 */
public interface Observable {

    public void addObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void update();
}
