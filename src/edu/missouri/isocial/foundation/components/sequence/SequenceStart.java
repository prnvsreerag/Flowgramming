/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.sequence;

import edu.missouri.isocial.foundation.Editor;
import edu.missouri.isocial.foundation.components.core.Connector;
import edu.missouri.isocial.foundation.components.core.DraggableJPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;

/**
 *
 * @author Ryan
 */
public class SequenceStart extends DraggableJPanel {
    
    private static final int SCALE = 75;
    private static final String caption = "Start";
    private final Connector startConnector;
    public SequenceStart(Editor editor) {
        super(editor);
        
        startConnector = new Connector(editor, this);
        add(startConnector);
        startConnector.setLocation(SCALE*3/4 -5, SCALE/2-5);
        startConnector.setVisible(true);
        
    }
    
    @Override
    public void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        
        GeneralPath path = new GeneralPath();
        path.moveTo(0, 0);
        path.lineTo(SCALE *3/4, SCALE/2);
        path.lineTo(0, SCALE);
        path.lineTo(0, 0);
        
        g.setColor(Color.GREEN);
        g.fill(path);
        g.setColor(Color.BLACK);
        g.draw(path);
        
        g.setFont(Font.getFont("Arial"));
       
        FontMetrics metrics = g.getFontMetrics();
        int captionWidth = metrics.stringWidth(caption);
        
        int xMiddle = (SCALE*3/4)/2;
        int yMiddle = (SCALE/2);
        
//        Point textLocation = new Point(xMiddle-captionWidth/2, yMiddle);
        g.drawString(caption, xMiddle-captionWidth/2 - 5, yMiddle+5);
        
    }
}
