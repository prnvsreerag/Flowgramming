package edu.missouri.isocial.basic.components.variables;

import edu.missouri.isocial.foundation.annotations.In;
import edu.missouri.isocial.foundation.annotations.Out;
import edu.missouri.isocial.foundation.annotations.SequenceVariable;

/**
 *
 * @author Ryan
 */
@SequenceVariable(category="Sequence Objects", caption="Integer")
public class DraggableInteger {
    private Object value;
    
    
    @In(type=Integer.class)
    public void setValue(Object value) {
        this.value = value;
    }
    
    @Out(type=Integer.class)
    public Object getValue() {
        return this.value;
    }
}
