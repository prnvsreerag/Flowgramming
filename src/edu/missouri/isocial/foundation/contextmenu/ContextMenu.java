/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.contextmenu;

import edu.missouri.isocial.foundation.Actionable;
import edu.missouri.isocial.foundation.ApplicationContext;
import edu.missouri.isocial.foundation.Editor;
import edu.missouri.isocial.foundation.EditorApplication;
import edu.missouri.isocial.foundation.Lookup;
import edu.missouri.isocial.foundation.StrategyCollection;
import edu.missouri.isocial.foundation.annotations.SequenceAction;
import edu.missouri.isocial.foundation.strategies.MenuItemBuilder;
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
    private final Editor editor;

    public ContextMenu(Editor editor) {
        internalMenu = new JPopupMenu();
        categories = new HashMap<String, JMenu>();

        this.editor = editor;

        addDefaultMenuItems();

        addSequenceActions();
    }

    public boolean hasCategory(String category) {
        return categories.containsKey(category);
    }

    public void addMenuItem(String category, String caption, final MenuItemSPI spi) {
        if (!hasCategory(category)) {
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

    private void addSequenceActions() {
        Lookup lookup = strategies().get(Lookup.class);

        lookup.setAnnotation(SequenceAction.class);
        Set<Actionable> actionables = lookup.getAll(Actionable.class);

        MenuItemBuilder builder = new MenuItemBuilder(editor);

        for (Actionable actionable : actionables) {

            SequenceAction annot = actionable.getClass().getAnnotation(SequenceAction.class);
            final String menuCategory = annot.category();
            final String menuCaption = annot.caption();
            

            MenuItemWithEditor item = builder.buildAction(actionable);

            addMenuItem(menuCategory, menuCaption, item);
        }


    }

    private StrategyCollection strategies() {
        return ApplicationContext.INSTANCE.getStrategies();
    }
}
