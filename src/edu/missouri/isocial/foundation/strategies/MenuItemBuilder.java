/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.strategies;

import edu.missouri.isocial.foundation.Actionable;
import edu.missouri.isocial.foundation.Editor;
import edu.missouri.isocial.foundation.annotations.SequenceAction;
//import edu.missouri.isocial.foundation.components.core.DraggableAction;
import edu.missouri.isocial.foundation.contextmenu.MenuItemWithEditor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;

/**
 *
 * @author Ryan
 */
public class MenuItemBuilder {

    private final Editor editorFromBuilder;

    public MenuItemBuilder(Editor editor) {
        this.editorFromBuilder = editor;
    }

    public MenuItemWithEditor buildAction(final Actionable action) {
        SequenceAction annot = action.getClass().getAnnotation(SequenceAction.class);
        final String[] parameters = annot.parameters();
        
        MenuItemWithEditor item = new MenuItemWithEditor() {            
            { 
                editor = editorFromBuilder;
            }
            
            @Override
            public void actionPerformed(ActionEvent event) {
                
//                DraggableAction draggable = DraggableAction.newInstance(editor, action, parameters);
//
//
//                Point mouseLocationOnScreen = MouseInfo.getPointerInfo().getLocation();
//                Point editorLocationOnScreen = editorFromBuilder.getLocationOnScreen();
//
//                int newX = mouseLocationOnScreen.x - editorLocationOnScreen.x;
//                int newY = mouseLocationOnScreen.y - editorLocationOnScreen.y;
//
//                Point location = new Point(newX, newY);
//
//                draggable.setLocation(location);
//                draggable.setSize(200, 200);
//                editorFromBuilder.addDraggable(draggable);
//                draggable.setVisible(true);
//                draggable.repaint();


            }
        };
        return item;
    }
}
