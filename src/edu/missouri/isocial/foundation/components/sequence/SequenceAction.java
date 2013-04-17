/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.sequence;

import edu.missouri.isocial.foundation.components.core.DraggableJPanel;
import edu.missouri.isocial.foundation.components.core.Connector;
import edu.missouri.isocial.foundation.EditorApplication;

/**
 *
 * @author Ryan
 */
public class SequenceAction extends DraggableJPanel {

    private Connector inConnector;
    private Connector outConnector;
    public SequenceAction(EditorApplication editor) {
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
    }
}
