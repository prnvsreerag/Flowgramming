/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.contextmenu;

import edu.missouri.isocial.foundation.EditorApplication;

/**
 *
 * @author Ryan
 */
public abstract class MenuItemWithEditor implements MenuItemSPI {
    protected EditorApplication editor;
    
    public void setContext(EditorApplication editor) {
        this.editor = editor;
    }
    
}
