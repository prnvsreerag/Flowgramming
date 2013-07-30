/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core;

import edu.missouri.isocial.foundation.ApplicationContext;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Line2D;
import javax.swing.JComponent;


/**
 *
 * @author Ryan
 */
public class Connection extends JComponent {
    private Point startPoint;
    private Point endPoint;
    private  Link start;
    private  Link end;
    private Color currentColor = Color.BLACK;

    private ConnectionController controller;
    
    public Connection(Link start, Link end) {
        this.start = start;
        this.end = end;

        this.setOpaque(false);
        this.setBackground(Color.green);
    }
    
    public void cleanup() {
        controller.cleanup();
    }
    
    public void setController(ConnectionController controller) {
        this.controller = controller;
    }
    
    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }
    public Link getStart() {
        return start;
    }
    
    public Link getEnd() {
        return end;
    } 
    
    public Point getStartPoint() {
        return this.startPoint;
    }
    
    public Point getEndPoint() {
        return this.endPoint;
    }
    
    public void setStartPoint(Point start) {
        this.startPoint = start;
    }
    
    public void setEndPoint(Point end) {
        this.endPoint = end;        
    }
    
    @Override
    protected void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        
        g.setColor(currentColor);              
        Line2D line = new Line2D.Double(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        g.draw(line);

        //draw bounds of this component.
        if (ApplicationContext.INSTANCE.isInDebugMode()) {
            g.setColor(Color.MAGENTA);
            g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        }
    }
}
