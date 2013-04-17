/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.contextmenu.menuitems;

import edu.missouri.isocial.foundation.components.core.DraggableJPanel;
import edu.missouri.isocial.foundation.contextmenu.MenuItem;
import edu.missouri.isocial.foundation.contextmenu.MenuItemSPI;
import java.awt.event.ActionEvent;

/**
 *
 * @author Ryan
 */
@MenuItem(category="Sequence Objects", caption="Add Draggable")
public class AddSequenceObjectItem implements MenuItemSPI {

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("ACTION PERFORMED #1");
    }
    
}
