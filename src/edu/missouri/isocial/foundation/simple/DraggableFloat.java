/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.simple;

import edu.missouri.isocial.basic.components.variables.FloatDataNode;
import edu.missouri.isocial.foundation.annotations.Configurable;
import edu.missouri.isocial.foundation.components.core.model.DraggableComponentModel;
import edu.missouri.isocial.foundation.components.core.model.DraggableItem;

/**
 *
 * @author Ryan
 */
@DraggableItem(FloatDataNode.class)
public class DraggableFloat extends DraggableComponentModel {

    @Override
    public void default_properties() {
        displayText = "Float";
        ObjName = "Float";
        ObjCategory = "Primitives";

        left(0, link().withCaption("Float").expectingType(Float.class).build());
    }
}
