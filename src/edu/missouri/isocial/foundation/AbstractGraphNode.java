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
    private final T value;
    private final String id;

    public AbstractGraphNode(String ID, T value) {
        this.id = ID;
        this.value = value;
        adjacentNodes = new HashMap<String, AbstractGraphNode<T>>();
    }

    public String getID() {
        return id;
    }

    public T getValue() {
        return this.value;
    }

    public Map<String, AbstractGraphNode<T>> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void addAdjacentNode(AbstractGraphNode<T> adjacentNode) {
        adjacentNodes.put(adjacentNode.getID(), adjacentNode);
    }
}
