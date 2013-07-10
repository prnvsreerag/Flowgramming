/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core.model;

import edu.missouri.isocial.foundation.annotations.Configurable;
import edu.missouri.isocial.foundation.annotations.Controller;
import edu.missouri.isocial.foundation.annotations.PropertiesView;
import edu.missouri.isocial.foundation.components.core.DraggableComponent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Ryan
 */
public class DraggableProperties<T> {

    private final DraggableComponent component;
    private JComponent view;

    public DraggableProperties(DraggableComponent component) {
        this.component = component;

        //create view
        initializeView();

        //create controller for view
        //initializeController(view);
    }

    public JComponent getView() {
        return view;
    }

    private void initializeView() {
        PropertiesView annotation = getClass().getAnnotation(PropertiesView.class);

        Class clazzToInstantiate = annotation.value();
        try {
            instantiatePropertiesView(clazzToInstantiate);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(DraggableProperties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DraggableProperties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DraggableProperties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(DraggableProperties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(DraggableProperties.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void intializeController() {
        Controller annotation = getClass().getAnnotation(Controller.class);
        Class clazzToInstantiate = annotation.value();

        instantiatePropertiesViewController(clazzToInstantiate);
    }

    private JPanel instantiatePropertiesView(Class clazzToInstantiate)
            throws NoSuchMethodException,
            InstantiationException,
            IllegalAccessException,
            IllegalArgumentException,
            InvocationTargetException {
        Constructor constructor = clazzToInstantiate.getConstructor();
        return (JPanel) constructor.newInstance();
    }

    private void instantiatePropertiesViewController(Class clazzToInstantiate) {
        //TODO: figure this part out.
    }
}
