/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.simple;

import edu.missouri.isocial.basic.components.actions.AddToOneNode;
import edu.missouri.isocial.foundation.components.core.model.DraggableComponentModel;
import edu.missouri.isocial.foundation.components.core.model.DraggableItem;

/**
 *
 * @author Ryan
 */
@DraggableItem(AddToOneNode.class)
public class DraggableAddToOne extends DraggableComponentModel {

    public void default_properties() {
        displayText = "n+1";
        ObjName = "N Plus 1";
        ObjCategory = "Math";

        left(0, link().withCaption("input").expectingType(Integer.class).build());
        right(0, link().withCaption("output").expectingType(Integer.class).build());
    }
}
