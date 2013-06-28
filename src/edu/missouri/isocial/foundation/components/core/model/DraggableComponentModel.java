/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core.model;

/**
 *
 * @author Ryan
 */
public abstract class DraggableComponentModel {

    protected String displayText;
    protected String ObjName;
    protected String ObjCategory;
    protected LinkModel[] left;
    protected LinkModel[] right;
    protected LinkModel[] bottom;

    public DraggableComponentModel() {
        left = new LinkModel[10];
        right = new LinkModel[10];
        bottom = new LinkModel[10];

        default_properties();
    }

    public abstract void default_properties();

    public String getDisplayText() {
        return displayText;
    }

    public String getObjName() {
        return ObjName;
    }

    public String getObjCategory() {
        return ObjCategory;
    }

    public LinkModel[] getLeft() {
        return left;
    }

    public LinkModel[] getRight() {
        return right;
    }

    public LinkModel[] getBottom() {
        return bottom;
    }

    protected LinkModel.LinkModelBuilder link() {
        return LinkModel.builder();
    }
}
