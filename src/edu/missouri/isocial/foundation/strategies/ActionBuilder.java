/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.strategies;

import edu.missouri.isocial.foundation.Actionable;
import edu.missouri.isocial.foundation.Editor;
import edu.missouri.isocial.foundation.components.sequence.DraggableAction;

/**
 *
 * @author Ryan
 */
public class ActionBuilder {
    private final Editor editor;
    private final Actionable obj;
   public ActionBuilder(Editor editor, Actionable obj) {
       this.editor = editor;
       this.obj = obj;
   } 
   
   public DraggableAction build(String[] parameters) {
       DraggableAction<Actionable> actionable = new DraggableAction<Actionable>(editor, obj, parameters);       
       
       return actionable;
   }
}
