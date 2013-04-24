/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.basic.components.variables;

import edu.missouri.isocial.foundation.annotations.In;
import edu.missouri.isocial.foundation.annotations.Out;
import edu.missouri.isocial.foundation.annotations.SequenceVariable;

/**
 *
 * @author Ryan
 */
@SequenceVariable(category="Sequence Objects", caption="String")
public class DraggableString {
    private String value;
    
    @In(type=String.class)
    public void setValue(Object value) {
        this.value = (String)value;
    }
    
    @Out(type=String.class)
    public Object getValue() {
        return this.value;
    }
}
