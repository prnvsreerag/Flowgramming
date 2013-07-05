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
public class ApplicationGraph {

    private Map<String, AbstractGraphNode> nodes;

    public ApplicationGraph() {
        nodes = new HashMap<String, AbstractGraphNode>();
    }

    public AbstractGraphNode getNode(String key) {
        return nodes.get(key);
    }

    public void addNode(String key, AbstractGraphNode node) {
        nodes.put(key, node);
    }
}
