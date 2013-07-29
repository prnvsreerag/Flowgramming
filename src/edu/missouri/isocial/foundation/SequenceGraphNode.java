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
public abstract class SequenceGraphNode extends AbstractGraphNode<Void> {
    public SequenceGraphNode(String ID) {
        super(ID);
    }

    public void activate(String key) {
        if (adjacentNodes().containsKey(key)) {

            //we pass null here because we want sequence, not functional
            //behavior here.
            adjacentNodes().get(key).call(null);
        } else {
            //if nothing is connected to our output, do nothing
        }
    }

//    public AbstractGraphNode<T> retrieve(String key, Class<T> clazz) {
//        if (adjacentNodes().containsKey(key)) {
//        }
//    }

    public Void call(Object obj) {
        activated();
        return null;
    }

    public abstract Void activated();

    private Map<String, AbstractGraphNode<Void>> adjacentNodes() {
        return getAdjacentNodes();
    }

    public void activated(int x) {
        //do stuff and then..

        activate("done");
    }

//    public <K> K defaultInputValue(Class<K> clazz) {
//        return null;
//    }
//
//    public void fromString(String input) {
//        //do nothing
//    }
}
