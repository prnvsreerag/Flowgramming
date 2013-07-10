/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.basic.components.variables;

import edu.missouri.isocial.foundation.DataGraphNode;

/**
 *
 * @author Ryan
 */
public class FloatDataNode extends DataGraphNode<Float> {

    private float configured;

    public FloatDataNode(String ID) {
        super(ID);
    }

    @Override
    public Float getValue() {
        return configured;
    }

    @Override
    public void fromString(String input) {
        configured = Float.valueOf(input);
    }
}
