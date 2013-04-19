/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import edu.missouri.isocial.foundation.components.core.Connection;
import edu.missouri.isocial.foundation.components.core.Connector;
import edu.missouri.isocial.foundation.components.core.DraggableJPanel;
import java.awt.Component;
import java.awt.Point;

/**
 *
 * @author Ryan
 */
public interface Editor {
    
    public Connection addConnection(Connector start, Connector end);
        
    public void addDraggable(DraggableJPanel draggable);
    
    public void showContextMenu(int xOnScreen, int yOnScreen);
    
    public void removeDraggable(DraggableJPanel draggable);
    
    public Point getLocationOnScreen();
    
    public Component findComponentAt(Point location);
}
