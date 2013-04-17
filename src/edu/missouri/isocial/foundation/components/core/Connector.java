/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core;

import edu.missouri.isocial.foundation.EditorApplication;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ryan
 */
public class Connector extends JComponent {

    private Class valueClassType = Object.class;
    private final EditorApplication editor;
//    private List<Connection> connections;
    private final DraggableJPanel draggableParent;
    private Color currentColor;
    private boolean mouseIsPressed = false;
    
    private static final Color DEFAULT_COLOR = Color.BLACK;
    private static final Color ACTIVE_COLOR = Color.WHITE;
    
    
    public Connector(final EditorApplication editor, DraggableJPanel draggableParent) {
        this.editor = editor;
        this.draggableParent = draggableParent;
        this.setSize(10, 10);
//        this.connections = new ArrayList<Connection>();
        
        this.setOpaque(false);
        this.currentColor = DEFAULT_COLOR;
        this.addMouseListener(new MouseListener() {
            Connector parentConnector = Connector.this;

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("PRESSED!");
                mouseIsPressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                final Component c = editor.findComponentAt(e.getLocationOnScreen());
                mouseIsPressed = false;
                repaint();
                
                if (c == null) {
                    System.out.println("NULL COMPONENT!");
                    return;
                }
                //check if we dropped on a connector
                if (c instanceof Connector) {

                    if (c.equals(parentConnector)) {
                        System.out.println("CANNOT MAKE CONNECTION TO SELF!");
                        return;
                    }
                    Connector target = (Connector) c;

                    Class type = target.getValueClass();
                    //check that we're meeting the right type
                    if (type.equals(getValueClass())) {
                        //add connection

//                        Connection conn = editor.addConnection(parentConnector, target);
                        
                        makeConnection(getDraggableParent(), target.getDraggableParent(), target);
                        
//                        connections.add(conn);
                        
                        System.out.println("CONNECTION MADE!");
                    } else {
                        System.out.println("TYPES DON'T MATCH!");
                    }
                } else {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("DID NOT RELEASE ON A CONNECTOR!");
                            System.out.println("RELEASED ON A: " + c.getClass().getName());
                        }
                    });

                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                currentColor = ACTIVE_COLOR;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(!mouseIsPressed) { 
                    currentColor = DEFAULT_COLOR;
                }
                repaint();
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
    }
    
    
    public DraggableJPanel getDraggableParent() {
        return draggableParent;
    }
    @Override
    protected void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;

        g.setColor(currentColor);
        g.fillRect(0, 0, 10, 10);

    }

    private Class getValueClass() {
        return this.valueClassType;
    }
    
    private void makeConnection(DraggableJPanel start, DraggableJPanel end, Connector target) {
//        ConnectionInfo connectionInfo = new ConnectionInfo(start, end, this, target, target.getValueClass());
//        start.addConnectionStart(connectionInfo);
//        end.addConnectionEnd(connectionInfo);
        
        
        editor.addConnection(this, target);
    }
}
