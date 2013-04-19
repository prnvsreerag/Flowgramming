/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.contextmenu.menuitems;

import edu.missouri.isocial.foundation.components.sequence.SequenceAction;
import edu.missouri.isocial.foundation.components.sequence.SequenceEvent;
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
@MenuItem(category = "Sequence Events", caption = "Add Event")
public class AddSequenceEventItem extends MenuItemWithEditor {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        SequenceEvent event = new SequenceEvent(editor);
        Point mouseLocationOnScreen = MouseInfo.getPointerInfo().getLocation();
        Point editorLocationOnScreen = editor.getLocationOnScreen();

        int newX = mouseLocationOnScreen.x - editorLocationOnScreen.x;
        int newY = mouseLocationOnScreen.y - editorLocationOnScreen.y;

        Point location = new Point(newX, newY);

        event.setLocation(location);
        event.setSize(200, 200);
        editor.addDraggable(event);
        event.setVisible(true);
        event.repaint();

    }
}
