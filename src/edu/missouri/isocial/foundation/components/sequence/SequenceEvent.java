/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.sequence;

import edu.missouri.isocial.foundation.Editor;
import edu.missouri.isocial.foundation.components.core.DraggableJPanel;
import edu.missouri.isocial.foundation.components.core.Connector;
import edu.missouri.isocial.foundation.EditorApplication;

/**
 *
 * @author Ryan
 */
public class SequenceEvent extends DraggableJPanel {
    
    private Connector outConnector;
    public SequenceEvent(Editor editor) {
        super(editor);       
        
        outConnector = new Connector(editor, this);
        add(outConnector);
        System.out.println("NODE WIDTH:"+getWidth());
        outConnector.setLocation(190, 20);
        outConnector.setVisible(true);
    }
}
