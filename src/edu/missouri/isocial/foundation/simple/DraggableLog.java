/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.simple;

import edu.missouri.isocial.basic.components.actions.LogSequenceNode;
import edu.missouri.isocial.foundation.components.core.model.DraggableComponentModel;
import edu.missouri.isocial.foundation.components.core.model.DraggableItem;
//import edu.missouri.isocial.foundation.simple.DraggableLog.LogSequenceNode;

/**
 *
 * @author Ryan
 */
@DraggableItem(LogSequenceNode.class)
public class DraggableLog extends DraggableComponentModel {

    @Override
    public void default_properties() {
        displayText = "Log";
        ObjName = "Log";
        ObjCategory = "Misc";

        left(0, link().withCaption("previous").expectingType(Void.class).build());
        right(0, link().withCaption("next").expectingType(Void.class).build());
        bottom(0, link().withCaption("msg").expectingType(String.class).build());

    }
}
