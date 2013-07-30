/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.basic.components.actions;

import edu.missouri.isocial.foundation.FunctionalGraphNode;

/**
 *
 * @author Ryan
 */
public class AddToOneNode extends FunctionalGraphNode {

    public AddToOneNode(String ID) {
        super(ID);
    }

    @Override
    public Object evaluated(Object obj) {
        Integer x = null;
        if (obj instanceof Integer) {
            x = (Integer) obj;
        } else {
            x = 0;
        }

        return evaluate("output", x + 1);
    }

    @Override
    public <K> K defaultInputValue(Class<K> clazz) {
        if (clazz.getName().contains("Integer")) {
            return (K) new Integer(1);
        }

        return null;
    }

    @Override
    public void fromString(String input) {
        //do nothing
    }
}
