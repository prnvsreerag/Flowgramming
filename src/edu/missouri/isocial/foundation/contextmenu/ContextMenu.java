/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.contextmenu;

import edu.missouri.isocial.foundation.EditorApplication;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Ryan
 */
public class ContextMenu {
    private JPopupMenu internalMenu;
    
    private Map<String, JMenu> categories;
    private final EditorApplication editor;
    public ContextMenu(EditorApplication editor) {
        internalMenu = new JPopupMenu();
        categories = new HashMap<String, JMenu>();
    
        this.editor = editor;
        
        
        addDefaultMenuItems();
    }
    
    public boolean hasCategory(String category) {
        return categories.containsKey(category);
    }
    
    public void addMenuItem(String category, String caption, final MenuItemSPI spi) {
        if(!hasCategory(category)) {
            //create menu
            categories.put(category, new JMenu(category));
            
            //add submenu to context menu
            internalMenu.add(categories.get(category));
        } 
        
        spi.setContext(editor);
        
        
        //retrieve our menu
        JMenu menu = categories.get(category);
        
        //create jmenuitem
        JMenuItem item = new JMenuItem(caption);
        
        //use spi as actionlistener
        item.addActionListener(spi);
        
        //add the item to the relevant menu
        menu.add(item);
  
    }

    public void show(Component component, int x, int y) {
       internalMenu.show(component, x, y);
    }

    public void setVisible(boolean b) {
        internalMenu.setVisible(b);
    }

    private void addDefaultMenuItems() {
       //create exit item
        JMenuItem exit = new JMenuItem("Exit");
        
        //add action listener
        
        exit.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        
        });
        //add exit item to menu
        internalMenu.add(exit);
    }
}
