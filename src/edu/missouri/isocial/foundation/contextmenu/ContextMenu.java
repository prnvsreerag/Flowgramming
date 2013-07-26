/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.contextmenu;

import edu.missouri.isocial.foundation.Actionable;
import edu.missouri.isocial.foundation.ApplicationContext;
import edu.missouri.isocial.foundation.GraphView;
import edu.missouri.isocial.foundation.EditorApplication;
import edu.missouri.isocial.foundation.Lookup;
import edu.missouri.isocial.foundation.StrategyCollection;
import edu.missouri.isocial.foundation.annotations.SequenceAction;
import edu.missouri.isocial.foundation.components.core.DraggableComponent;
import edu.missouri.isocial.foundation.components.core.model.DraggableComponentModel;
import edu.missouri.isocial.foundation.components.core.model.DraggableItem;
import edu.missouri.isocial.foundation.io.FlowGraphWriter;
import edu.missouri.isocial.foundation.strategies.MenuItemBuilder;
import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
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
    private final GraphView editor;

    public ContextMenu(GraphView editor) {
        internalMenu = new JPopupMenu();
        categories = new HashMap<String, JMenu>();

        this.editor = editor;

        addDefaultMenuItems();
        addDraggableItems();
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

        JMenuItem listValues = new JMenuItem("List Values");

        listValues.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                context().getGraph().listValues();
            }
        });

        internalMenu.add(listValues);


        JMenuItem listConnections = new JMenuItem("List Connections");

        listConnections.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editor.getConnectionRepository().listConnections();
            }
        });
        internalMenu.add(listConnections);


        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FlowGraphWriter writer = new FlowGraphWriter(context().getGraph(), editor);
                System.out.println(writer.writeToString());
            }
        });
        internalMenu.add(saveMenuItem);

    }

    private ApplicationContext context() {
        return ApplicationContext.INSTANCE;
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

    private void addDraggableItems() {
        Lookup lookup = strategies().get(Lookup.class);
        lookup.setAnnotation(DraggableItem.class);
        Set<DraggableComponentModel> draggables = lookup.getAll(DraggableComponentModel.class);

        for (final DraggableComponentModel draggable : draggables) {
            //build menu item 
            MenuItemSPI item = new MenuItemSPI() {
                private GraphView editor;

                @Override
                public void actionPerformed(ActionEvent event) {
                    DraggableComponent _component = new DraggableComponent(editor, draggable);


                    Point mouseLocationOnScreen = MouseInfo.getPointerInfo().getLocation();
                    Point editorLocationOnScreen = editor.getLocationOnScreen();

                    int newX = mouseLocationOnScreen.x - editorLocationOnScreen.x;
                    int newY = mouseLocationOnScreen.y - editorLocationOnScreen.y;

                    Point location = new Point(newX, newY);

                    _component.setLocation(location);

                    editor.addDraggable(draggable, _component);
                    //_component.setSize(200, 200);
                    _component.setVisible(true);
                    _component.repaint();

                }

                @Override
                public void setContext(GraphView editor) {
                    this.editor = editor;
                }
            };

            addMenuItem(draggable.getObjCategory(),
                    draggable.getObjName(),
                    item);
        }
    }
}
