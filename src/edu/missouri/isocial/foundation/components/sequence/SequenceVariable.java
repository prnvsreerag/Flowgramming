/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.sequence;

import edu.missouri.isocial.foundation.EditorApplication;
import edu.missouri.isocial.foundation.components.core.DraggableJPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Ryan
 */
public class SequenceVariable extends DraggableJPanel {
 
    
    private static final int SCALE = 100;
    public SequenceVariable(EditorApplication editor) {
        super(editor);
    }
    
    @Override
    protected void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        
       g.setColor(Color.GREEN);
       g.fill(new Ellipse2D.Double(0, 0, SCALE, SCALE));
        
        
    }
}
