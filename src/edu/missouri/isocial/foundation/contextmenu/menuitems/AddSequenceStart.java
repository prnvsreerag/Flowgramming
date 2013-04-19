/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.contextmenu.menuitems;

import edu.missouri.isocial.foundation.components.sequence.SequenceStart;
import edu.missouri.isocial.foundation.contextmenu.MenuItem;
import edu.missouri.isocial.foundation.contextmenu.MenuItemWithEditor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;

/**
 *
 * @author Ryan
 */
@MenuItem(category="Sequence Actions", caption="Add Start")
public class AddSequenceStart extends MenuItemWithEditor {
    
    @Override
    public void actionPerformed(ActionEvent event) {
        SequenceStart start = new SequenceStart(editor);
        Point mouseLocationOnScreen = MouseInfo.getPointerInfo().getLocation();
        Point editorLocationOnScreen = editor.getLocationOnScreen();
        
        int newX = mouseLocationOnScreen.x - editorLocationOnScreen.x;
        int newY = mouseLocationOnScreen.y - editorLocationOnScreen.y;
        
        Point location = new Point(newX, newY);
        
        start.setLocation(location);
        start.setSize(100, 100);
        editor.addDraggable(start);
        start.setVisible(true);
        start.repaint();
    }
}
