/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core.model;

import edu.missouri.isocial.foundation.components.core.model.LinkModel.LinkModelBuilder;
import java.util.ArrayList;

/**
 *
 * @author Ryan
 */
//@DraggableItem
public class DefaultDraggableComponentModel extends DraggableComponentModel {

    public DefaultDraggableComponentModel() {
        super();
    }
    @Override
    public void default_properties() {
        displayText = "example";
        ObjName = "example";
        ObjCategory = "examples";

        left(0, link().withCaption("input").expectingType(Object.class).build());
        left(1, link().withCaption("input").expectingType(Object.class).build());

        right(0, link().withCaption("output").expectingType(Object.class).build());
        bottom(0, link().withCaption("parameter1").expectingType(Integer.class).build());
        bottom(1, link().withCaption("parameter22").expectingType(Integer.class).build());
        bottom(2, link().withCaption("parameter333").expectingType(Integer.class).build());
    }
}
