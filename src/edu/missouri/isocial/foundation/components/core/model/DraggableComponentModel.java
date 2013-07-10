/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core.model;

import edu.missouri.isocial.foundation.ObservableObserver;
import edu.missouri.isocial.foundation.annotations.Configurable;
import edu.missouri.isocial.foundation.components.core.DraggableComponent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    //private static int issues = 0;
    //private int issue = 0;
    private DraggableProperties propertiesModel;

    public DraggableComponentModel() {
        
        left = new ArrayList<LinkModel>();
        right = new ArrayList<LinkModel>();
        bottom = new ArrayList<LinkModel>();

        //issue = issues;
        //incrementIssue();

        //handleConfigurable();

        default_properties();
    }

//    public String getID() {
//        return ObjName + "_" + issue;
//    }

//    private static void incrementIssue() {
//        issues += 1;
//    }

//    public int getIssue() {
//        return issue;
//    }

    public abstract void default_properties();

    private void handleConfigurable() {
        try {

            Configurable annotation = getClass().getAnnotation(Configurable.class);
            if (annotation == null) {
                return;
            }
            Class propertiesClass = annotation.value();
            Constructor constructor = propertiesClass.getConstructor(getClass());
            DraggableProperties propertiesModel = (DraggableProperties) constructor.newInstance(this);
            this.propertiesModel = propertiesModel;
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(DraggableProperties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(DraggableProperties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DraggableComponentModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DraggableComponentModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(DraggableComponentModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(DraggableComponentModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
}
