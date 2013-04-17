/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import edu.missouri.isocial.foundation.contextmenu.ContextMenu;
import edu.missouri.isocial.foundation.contextmenu.MenuItem;
import edu.missouri.isocial.foundation.contextmenu.MenuItemSPI;
import java.util.Set;
import javax.swing.JPopupMenu;

/**
 *
 * @author Ryan
 */
public class ContextMenuBuilder {
    private final Lookup lookup;

    public ContextMenuBuilder(Lookup lookup) {
                
        //get discovery mechanism        
        this.lookup = lookup;
        
        //set lookup to search for all classes annotated by MenuItem
        lookup.setAnnotation(MenuItem.class);
    }
    
    public ContextMenu build() {

        //create ContextMenu
        
        ContextMenu menu = new ContextMenu();
        
        //discover annoated SPIs
        Set<MenuItemSPI> spis = lookup.lookUp(MenuItemSPI.class);
        
        //for every SPI
        for(MenuItemSPI spi: spis) {
        
        //get annotation
            MenuItem annotation = spi.getClass().getAnnotation(MenuItem.class);
        //get category from annotation
            String category = annotation.category();
        
        //get caption from annotation
            String caption = annotation.caption();

            //add menu item
            menu.addMenuItem(category, caption, spi);
        }

        //return menu
        return menu;
    }
}
