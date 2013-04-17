/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.contextmenu.menuitems;

import edu.missouri.isocial.foundation.components.sequence.DraggableCondition;
import edu.missouri.isocial.foundation.components.core.DraggableJPanel;
import edu.missouri.isocial.foundation.components.sequence.SequenceEvent;
import edu.missouri.isocial.foundation.components.sequence.SequenceVariable;
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
@MenuItem(category="Sequence Objects", caption="Add Object")
public class AddSequenceObjectItem extends MenuItemWithEditor {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
//        SequenceEvent event = new SequenceEvent(editor);
        SequenceVariable variable = new SequenceVariable(editor);
        Point mouseLocationOnScreen = MouseInfo.getPointerInfo().getLocation();
        Point editorLocationOnScreen = editor.getLocationOnScreen();

        int newX = mouseLocationOnScreen.x - editorLocationOnScreen.x;
        int newY = mouseLocationOnScreen.y - editorLocationOnScreen.y;

        Point location = new Point(newX, newY);
        
        variable.setLocation(location);
        variable.setSize(200, 200);
        editor.add(variable);
        variable.setVisible(true);
        variable.repaint();
    }
    
}
