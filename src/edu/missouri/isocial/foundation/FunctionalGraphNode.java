/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import java.util.Map;

/**
 *
 * @author Ryan
 */
public abstract class FunctionalGraphNode extends AbstractGraphNode<Object> {

    public FunctionalGraphNode(String ID) {
        super(ID);
    }

    public Object evaluate(String key, Object parameter) {
        if (adjacentNodes().containsKey(key)) {
            return adjacentNodes().get(key).call(parameter);
            
        }
        return parameter;
    }

    private Map<String, AbstractGraphNode<Object>> adjacentNodes() {
        return getAdjacentNodes();
    }

    public Object call(Object obj) {
        return evaluated(obj);
        
    }

    public abstract Object evaluated(Object obj);

    public Object testEval(Object obj) {
        Integer x = (Integer) obj;

        return evaluate("output", x + 1);

    }
}
