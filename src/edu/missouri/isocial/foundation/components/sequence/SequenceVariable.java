/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor
 */
package edu.missouri.isocial.foundation.components.sequence;

import edu.missouri.isocial.foundation.Editor;
import edu.missouri.isocial.foundation.EditorApplication;
import edu.missouri.isocial.foundation.components.core.DraggableJPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Ryan
 */
public class SequenceVariable extends DraggableJPanel {

    private static final int SCALE = 100;
    private String caption = "???";
    
    public SequenceVariable(Editor editor) {
        super(editor);
    }
    
    public SequenceVariable(Editor editor, String caption) {
        super(editor);
        this.caption = caption;
    }
    
    @Override
    protected void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        
        //set background color
       g.setColor(Color.GREEN);
       //draw background
       g.fill(new Ellipse2D.Double(0, 0, SCALE, SCALE));
       //set border color
       g.setColor(Color.BLACK);
       //draw border
       g.draw(new Ellipse2D.Double(0,0, SCALE, SCALE));
    
       //set font
       g.setFont(Font.getFont("Arial"));
       //get font information
       FontMetrics metric = g.getFontMetrics();
       
       //get the width of the caption so we can center it on the object.
       int width = metric.stringWidth(caption);
 
       //draw the caption
       g.drawString(caption, SCALE/2 - width/2, SCALE/2);
    }
    
    public void setCaption(String caption) {
        this.caption = caption;
    }
}
