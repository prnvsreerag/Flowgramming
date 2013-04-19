/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import edu.missouri.isocial.foundation.annotations.SequenceVariable;
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
    private final Editor editor;

    public ContextMenuBuilder(Lookup lookup, Editor editor) {
                
        //get discovery mechanism        
        this.lookup = lookup;
        
        this.editor = editor;
        
        //set lookup to search for all classes annotated by MenuItem
        lookup.setAnnotation(MenuItem.class);
    }
    
    public ContextMenu build() {
        
        ContextMenu menu = setupMenuItems();
        
        lookup.setAnnotation(SequenceVariable.class);
        Set<Object> objects = lookup.lookUp(Object.class);
        
        for(Object object: objects) {
            //get main annotation
            
            //get in annotation
            
            //get out 
        }
        
        
        
        
        

        //return menu
        return menu;
    }

    private ContextMenu setupMenuItems() {
        //create ContextMenu
        ContextMenu menu = new ContextMenu(editor);
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
        return menu;
    }
}
