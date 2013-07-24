/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import edu.missouri.isocial.foundation.components.core.Connection;
import edu.missouri.isocial.foundation.components.core.Link;
import edu.missouri.isocial.foundation.components.core.DraggableComponent;
import edu.missouri.isocial.foundation.components.core.model.DraggableComponentModel;
import edu.missouri.isocial.foundation.contextmenu.ContextMenu;
import java.awt.Component;
import java.awt.Point;

/**
 *
 * @author Ryan
 */
public interface Editor {
    
    public Connection addConnection(Link start, Link end);
        
    public void addDraggable(DraggableComponentModel model, DraggableComponent draggable);
    
    public void showContextMenu(int xOnScreen, int yOnScreen);
    
    public void removeDraggable(DraggableComponent draggable);
    
    public Point getLocationOnScreen();
    
    public Component findComponentAt(Point location);

    public Component getComponentAt(Point location);

    public void executeProgram();

    public void removeConnection(String id);
    
    public ContextMenu getContextMenu();

    public ConnectionRepository getConnectionRepository();

    public DraggableComponent getDraggableWithID(String ID);
}
