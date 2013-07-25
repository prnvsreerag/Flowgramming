/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Ryan
 */
public class FlowGraph {

    private Map<String, AbstractGraphNode> nodes;

    public FlowGraph() {
        nodes = new HashMap<String, AbstractGraphNode>();
    }

    public AbstractGraphNode getNode(String key) {
        return nodes.get(key);
    }

    public void addNode(String key, AbstractGraphNode node) {
        nodes.put(key, node);
    }

    public void listValues() {
        for (Map.Entry<String, AbstractGraphNode> entry : nodes.entrySet()) {
            System.out.println("[" + entry.getKey() + "] ->" + entry.getValue().getValue());
        }
    }

    public void removeNodeWithKey(String id) {

        if (nodes.containsKey(id)) {
            //get node                   
            AbstractGraphNode node = getNode(id);

            //get adjacent nodes to this node
            for (Object adjacent : node.getAdjacentNodes().values()) {
                //remove us as adjacent to them.
                ((AbstractGraphNode) adjacent).removeAdjacentNodeWithKey(id);
            }

            //remove node from the graph
            nodes.remove(id);

        } else {
            //nothing to do here.
        }
    }

    public int getNumberOfNodes() {
        return nodes.size();
    }

    public Set<String> keyValues() {
        return nodes.keySet();
    }
}
