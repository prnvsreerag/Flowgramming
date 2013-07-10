/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.simple;

import edu.missouri.isocial.basic.components.variables.VectorDataNode;
import edu.missouri.isocial.foundation.components.core.model.DraggableComponentModel;
import edu.missouri.isocial.foundation.components.core.model.DraggableItem;

/**
 *
 * @author Ryan
 */

@DraggableItem(VectorDataNode.class)
public class DraggableVector extends DraggableComponentModel {

    @Override
    public void default_properties() {
        displayText = "Vector3f";
        ObjName = "Vector3f";
        ObjCategory = "Geometry Primitives";


        left(0, link().withCaption("Vector").expectingType(Vector3f.class).build());
        right(0, link().withCaption("X").expectingType(Float.class).build());
        right(1, link().withCaption("Y").expectingType(Float.class).build());
        right(2, link().withCaption("Z").expectingType(Float.class).build());
    }

    class Vector3f {
    }
}
