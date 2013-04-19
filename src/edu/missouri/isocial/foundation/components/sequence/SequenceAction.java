/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.sequence;

import edu.missouri.isocial.foundation.Editor;
import edu.missouri.isocial.foundation.components.core.DraggableJPanel;
import edu.missouri.isocial.foundation.components.core.Connector;
import edu.missouri.isocial.foundation.EditorApplication;
import edu.missouri.isocial.foundation.components.core.ParameterConnector;

/**
 *
 * @author Ryan
 */
public class SequenceAction extends DraggableJPanel {

    private Connector inConnector;
    private Connector outConnector;
    public SequenceAction(Editor editor) {
        super(editor);

        inConnector = new Connector(editor, this);
        add(inConnector);
        inConnector.setLocation(0, 20);
//        c.validate();
        inConnector.setVisible(true);

        outConnector = new Connector(editor, this);
        add(outConnector);
        System.out.println("NODE WIDTH:" + getWidth());
        outConnector.setLocation(190, 20);
        outConnector.setVisible(true);
        
        
        ParameterConnector pc = new ParameterConnector(editor, this);
        add(pc);
        pc.setLocation(190/2, 190);
        pc.setVisible(true);
    }
}
