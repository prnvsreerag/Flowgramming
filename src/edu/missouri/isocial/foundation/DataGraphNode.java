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
public abstract class DataGraphNode<T> extends AbstractGraphNode<T> {

    public DataGraphNode(String ID) {
        super(ID);
    }

    public <K> K retrieve(String key, Class<K> clazz) {
        if (adjacentNodes().containsKey(key)) {
            K value = (K) adjacentNodes().get(key).call(null);
            return value != null ? value : defaultInputValue(clazz);
        } else {
            return defaultInputValue(clazz);
        }

    }

    public T call(Object obj) {
        return getValue();
    }

    public abstract T getValue();

    private Map<String, AbstractGraphNode<T>> adjacentNodes() {
        return getAdjacentNodes();
    }
}
