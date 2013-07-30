/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.basic.components.actions;

import edu.missouri.isocial.foundation.SequenceGraphNode;
import java.util.logging.Logger;

/**
 *
 * @author Ryan
 */
public class LogSequenceNode extends SequenceGraphNode {
    private String logMessage;

    public LogSequenceNode(String ID) {
        super(ID);
    }

    @Override
    public Void activated() {
        Logger.getLogger(LogSequenceNode.class.getName()).warning(logMessage);
        activate("next");
        return null;
    }

    @Override
    public <K> K defaultInputValue(Class<K> clazz) {
        return null;
    }

    @Override
    public void fromString(String input) {
        this.logMessage = input;
    }
}
