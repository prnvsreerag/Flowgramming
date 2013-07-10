/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ryan
 */
public abstract class AbstractGraphNode<T> {

    private Map<String, AbstractGraphNode<T>> adjacentNodes;
    private final String id;

    public AbstractGraphNode(String ID) {
        this.id = ID;

        adjacentNodes = new HashMap<String, AbstractGraphNode<T>>();
    }

    public String getID() {
        return id;
    }

    public abstract T getValue();

    public abstract <K> K defaultInputValue(Class<K> clazz);

    public abstract void fromString(String input);

    public Map<String, AbstractGraphNode<T>> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void addAdjacentNode(String fieldName, AbstractGraphNode<T> adjacentNode) {
        adjacentNodes.put(fieldName, adjacentNode);
    }

    public <K> K retrieve(String key, Class<K> clazz) {
        if (adjacentNodes.containsKey(key)) {
            K value = (K) adjacentNodes.get(key).getValue();
            return value != null ? value : defaultInputValue(clazz);
        } else {
            return defaultInputValue(clazz);
        }

    }
}
