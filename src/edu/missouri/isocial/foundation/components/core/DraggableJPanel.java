/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core;

import edu.missouri.isocial.foundation.EditorApplication;
import edu.missouri.isocial.foundation.components.ConnectionInfo;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryan
 */
public class DraggableJPanel extends javax.swing.JPanel {

    /**
     * Creates new form DraggableJPanel
     */
    private volatile int screenX = 0;
    private volatile int screenY = 0;
    private volatile int myX = 0;
    private volatile int myY = 0;
    private final EditorApplication editor;

    private final List<ConnectionInfo> connections;
    
    public DraggableJPanel(EditorApplication editor) {
        this.editor = editor;
        this.connections = new ArrayList<ConnectionInfo>();
        initComponents();

        this.setOpaque(false);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
                screenX = me.getXOnScreen();
                screenY = me.getYOnScreen();
                myX = getX();
                myY = getY();
            }

            @Override
            public void mouseReleased(MouseEvent me) {

            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent me) {
                int deltaX = me.getXOnScreen() - screenX;
                int deltaY = me.getYOnScreen() - screenY;

                setLocation(myX + deltaX, myY + deltaY);
                
                repaintConnections();
            }

            @Override
            public void mouseMoved(MouseEvent me) {
            }
        });
        
    }

    @Override
    protected void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;

        Color borderColor = Color.BLACK;

        //if this component is selected change the border color to white.
        //TODO: check EditorApplication to see if this is selected component

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color[] colors = new Color[]{new Color(0.6f, 0.6f, 1.0f, 0.9f), new Color(0.6f, 0.6f, 1.0f, 0.5f)};

        float[] factors = {0f, 1f};

        //draw drop shadow
        g.setPaint(new RadialGradientPaint(getWidth() / 2, getHeight() / 2, getWidth() / 2, factors, colors));
        g.fillRoundRect(8, 3, getWidth() - 10, getHeight() - 6, 15, 15);

        //draw frame body
        g.setColor(new Color(170, 170, 170));
        g.fillRoundRect(5, 1, getWidth() - 9, getHeight() - 6, 15, 15);

        //draw frame border
        g.setColor(borderColor);
        g.drawRoundRect(4, 0, getWidth() - 9, getHeight() - 6, 15, 15);

        //Remove artifact in upper right corner
        g.setColor(new Color(170, 170, 170));
        g.fillRect(4, 1, 10, 10);
        g.setColor(borderColor);
        g.drawLine(4, 0, 14, 0);
        g.drawLine(4, 0, 4, 10);

        //draw separator between title and body.
        g.setColor(Color.BLACK);
        g.drawLine(5, 15, getWidth() - 6, 15);

        //draw lighter line to give 3d effect to separator
        g.setColor(new Color(190, 190, 190));
        g.drawLine(5, 16, getWidth() - 6, 16);


        //TODO: Parameterize these colors
        Color c1 = Color.MAGENTA;
        Color c2 = Color.YELLOW;

        //fill background of title area
        g.setPaint(new GradientPaint(0, 15, c1, getWidth(), 15, c2));
        g.fillRoundRect(5, 1, getWidth() - 15, 14, 10, 15);
        repaintConnections();
        

    }
    
    public void repaintConnections() {
        for(ConnectionInfo connection: connections) {
            
            Point startPoint = connection.getStartConnector().getLocationOnScreen();
            Point endPoint = connection.getEndConnector().getLocationOnScreen();
            this.getGraphics().setColor(Color.green);
           this.getGraphics().drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
           System.out.println("DRAWING CONNECTION: "
                   + "\nSTART->("+startPoint.x+","+startPoint.y+")\n"
                   + "END->("+endPoint.x+","+endPoint.y+")");
           
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 0, 51));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
