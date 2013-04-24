/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.contextmenu.menuitems;

import edu.missouri.isocial.foundation.components.sequence.DraggableCondition;
import edu.missouri.isocial.foundation.components.sequence.DraggableAction;
import edu.missouri.isocial.foundation.contextmenu.MenuItem;
import edu.missouri.isocial.foundation.contextmenu.MenuItemWithEditor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;

/**
 *
 * @author Ryan
 */
@MenuItem(category="Sequence Conditions", caption="Add Condition")
public class AddSequenceCondition extends MenuItemWithEditor {

    @Override
    public void actionPerformed(ActionEvent event) {
//        DraggableCondition condition = new DraggableCondition(editor);
//        Point mouseLocationOnScreen = MouseInfo.getPointerInfo().getLocation();
//        Point editorLocationOnScreen = editor.getLocationOnScreen();
//        
//        int newX = mouseLocationOnScreen.x - editorLocationOnScreen.x;
//        int newY = mouseLocationOnScreen.y - editorLocationOnScreen.y;
//        
//        Point location = new Point(newX, newY);
//        
//        condition.setLocation(location);
//        condition.setSize(200, 200);
//        editor.addDraggable(condition);
//        condition.setVisible(true);
//        condition.repaint();
        
    }
    
}
