/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryan
 */
public abstract class DraggableComponentModel {

    protected String displayText;
    protected String ObjName;
    protected String ObjCategory;
    protected List<LinkModel> left;
    protected List<LinkModel> right;
    protected List<LinkModel> bottom;

    public DraggableComponentModel() {
        left = new ArrayList<LinkModel>();
        right = new ArrayList<LinkModel>();
        bottom = new ArrayList<LinkModel>();

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

    public List<LinkModel> getLeft() {
        return left;
    }

    public List<LinkModel> getRight() {
        return right;
    }

    public List<LinkModel> getBottom() {
        return bottom;
    }

    protected LinkModel.LinkModelBuilder link() {
        return LinkModel.builder();
    }

    protected void left(int index, LinkModel model) {
        left.add(index, model);
    }

    protected void right(int index, LinkModel model) {
        right.add(index, model);
    }

    protected void bottom(int index, LinkModel model) {
        bottom.add(index, model);
    }
}
