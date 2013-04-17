/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.sequence;

import edu.missouri.isocial.foundation.EditorApplication;
import edu.missouri.isocial.foundation.components.core.Connector;
import edu.missouri.isocial.foundation.components.core.DraggableJPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;

/**
 *
 * @author Ryan
 */
public class DraggableCondition extends DraggableJPanel {
    
    private final static int SCALE = 150;
    public DraggableCondition(EditorApplication editor) {
        super(editor);
        
        Connector bottom = new Connector(editor, this);
        add(bottom);
        bottom.setLocation(SCALE/2 - (bottom.getWidth()/2), SCALE - (bottom.getHeight()/2));
        bottom.setVisible(true);
        
        Connector top = new Connector(editor, this);
        add(top);
        top.setLocation(SCALE/2 -(top.getWidth())/2, 0);
        top.setVisible(true);
        
        Connector right = new Connector(editor, this);
        add(right);
        right.setLocation(SCALE - right.getWidth()/2, SCALE/2 - (right.getWidth()/2));
        right.setVisible(true);
    }
    
    @Override
    protected void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
//        Rectangle r = new Rectangle(0, 0, 100, 100);

        g.setColor(Color.red);
//        g.translate(r.x+(r.width/2), r.y+(r.height/2));
//        g.rotate(Math.toRadians(90));
//        g.drawRect(0,0, 199, 199);
        
        GeneralPath path = new GeneralPath();
        path.moveTo(SCALE/2, 0);
        path.lineTo(SCALE, SCALE/2);
        path.lineTo(SCALE/2, SCALE);
        path.lineTo(0, SCALE/2);
        path.lineTo(SCALE/2, 0);
//        g.scale(2, 2);
        g.draw(path);      
        g.fill(path);
//        g.dispose();
//        
//        g = (Graphics2D)g.create();
        
        

        
    }
}
