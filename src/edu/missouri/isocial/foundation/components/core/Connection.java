/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Line2D;
import javax.swing.JComponent;
import javax.swing.border.Border;

/**
 *
 * @author Ryan
 */
public class Connection extends JComponent {
    private Point startPoint;
    private Point endPoint;
    private  Connector start;
    private  Connector end;

    private Color currentColor = Color.BLACK;
    
    public Connection(Connector start, Connector end) {
        this.start = start;
        this.end = end;

        this.setOpaque(false);
        this.setBackground(Color.green);
    }
     
    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }
    public Connector getStart() {
        return start;
    }
    
    public Connector getEnd() {
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
        
        g.drawLine(0, 0, endPoint.x, endPoint.y);
        System.out.println("CONNECTION START->("+startPoint.x+","+startPoint.y+")");
        

    }
}
