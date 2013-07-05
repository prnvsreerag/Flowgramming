/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core.model;

import edu.missouri.isocial.foundation.components.core.Link;

/**
 *
 * @author Ryan
 */
public abstract class DraggableDataComponentModel extends DraggableComponentModel {

    protected void input(int index, LinkModel link) {
        left(0, link);
    }

    protected void output(int index, LinkModel link) {
        right(0, link);
    }
}
