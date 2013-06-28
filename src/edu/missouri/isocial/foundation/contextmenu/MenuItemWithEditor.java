/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.contextmenu;

import edu.missouri.isocial.foundation.Actionable;
import edu.missouri.isocial.foundation.ApplicationContext;
import edu.missouri.isocial.foundation.Editor;
import edu.missouri.isocial.foundation.EditorApplication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Ryan
 */
public abstract class MenuItemWithEditor implements MenuItemSPI {

    protected Editor editor;

    public void setContext(Editor editor) {
        this.editor = editor;
    }

    public static MenuItemWithEditor fromActionable(Actionable actionable) {
        return fromActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //DraggableAction.fromActionable(actionable);
            }
        });
    }

    private static MenuItemWithEditor fromActionListener(final ActionListener listener) {

        return new MenuItemWithEditor() {
            public void actionPerformed(ActionEvent event) {
                listener.actionPerformed(event);

            }
        };
    }
}
