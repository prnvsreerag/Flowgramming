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

    public SequenceAction(EditorApplication editor) {
        super(editor);

        Connector left = new Connector(editor, this);
        add(left);
        left.setLocation(0, 20);
//        c.validate();
        left.setVisible(true);

        Connector right = new Connector(editor, this);
        add(right);
        System.out.println("NODE WIDTH:" + getWidth());
        right.setLocation(190, 20);
        right.setVisible(true);
    }
}
