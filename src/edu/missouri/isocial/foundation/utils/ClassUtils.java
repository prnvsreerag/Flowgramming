/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryan
 */
public class ClassUtils {

    public static <T> T newInstanceFromClassName(String name) {
        T obj = null;
        try {
            Class<T> clazz = (Class<T>) Class.forName(name);
            obj = newInstanceOfClass(clazz);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClassUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ClassUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ClassUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return obj;
        }
    }

    public static <T> T newInstanceOfClass(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }
}
