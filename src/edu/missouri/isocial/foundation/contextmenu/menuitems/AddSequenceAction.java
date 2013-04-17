/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.contextmenu.menuitems;

import edu.missouri.isocial.foundation.contextmenu.MenuItem;
import edu.missouri.isocial.foundation.contextmenu.MenuItemSPI;
import java.awt.event.ActionEvent;

/**
 *
 * @author Ryan
 */
@MenuItem(category="Sequence Actions", caption="Add Draggable")
public class AddSequenceAction implements MenuItemSPI {

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("ADDED SEQUENCE ACTION!");
    }
    
}
