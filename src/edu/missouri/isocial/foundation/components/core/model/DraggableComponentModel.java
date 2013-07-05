/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core.model;

import edu.missouri.isocial.foundation.ObservableObserver;
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
    private static int issues = 0;
    private int issue = 0;

    public DraggableComponentModel() {
        
        left = new ArrayList<LinkModel>();
        right = new ArrayList<LinkModel>();
        bottom = new ArrayList<LinkModel>();

        issue = issues;
        incrementIssue();

        default_properties();
    }

    private static void incrementIssue() {
        issues += 1;
    }

    public int getIssue() {
        return issue;
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

    public String getClassName() {
        return getClass().getName();
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

    private <T> void meh(T t) {
    }

    protected void meh2() {
        meh(5);

        meh("equals");
    }

}
