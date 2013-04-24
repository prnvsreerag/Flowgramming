/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.contextmenu.menuitems;

import edu.missouri.isocial.foundation.EditorApplication;
import edu.missouri.isocial.foundation.components.sequence.DraggableAction;
import edu.missouri.isocial.foundation.contextmenu.MenuItem;
import edu.missouri.isocial.foundation.contextmenu.MenuItemSPI;
import edu.missouri.isocial.foundation.contextmenu.MenuItemWithEditor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;

/**
 *
 * @author Ryan
 */
@MenuItem(category="Sequence Actions", caption="Add Action")
public class AddSequenceAction extends MenuItemWithEditor {
    


    @Override
    public void actionPerformed(ActionEvent event) {
//        DraggableAction action = new DraggableAction(editor);
//        Point mouseLocationOnScreen = MouseInfo.getPointerInfo().getLocation();
//        Point editorLocationOnScreen = editor.getLocationOnScreen();
//        
//        int newX = mouseLocationOnScreen.x - editorLocationOnScreen.x;
//        int newY = mouseLocationOnScreen.y - editorLocationOnScreen.y;
//        
//        Point location = new Point(newX, newY);
//        
//        action.setLocation(location);
//        action.setSize(200, 200);
//        editor.addDraggable(action);
//        action.setVisible(true);
//        action.repaint();
        
    }
    
}
