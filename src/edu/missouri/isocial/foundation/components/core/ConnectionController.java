/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core;

import edu.missouri.isocial.foundation.components.core.Connection;
import edu.missouri.isocial.foundation.EditorApplication;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Ryan
 */
public class ConnectionController {

    private Connector startConnector;
    private Connector endConnector;
    private Connection connection;
    private boolean selected = false;
    private static final Color SELECTED_COLOR = Color.WHITE;
    private static final Color DEFAULT_COLOR = Color.BLACK;
    private final EditorApplication editor;

    public ConnectionController(EditorApplication editor, Connector parentConnector, Connector targetConnector, Connection connection) {
        this.startConnector = parentConnector;
        this.endConnector = targetConnector;
        this.connection = connection;
        this.editor = editor;
        connection.setOpaque(false);
               
        //add mouse listener to connection
        addMouseListenerToConnection();

        //add component changed listener to start connector
        addComponentListenerToStartConnector();

        //add component changed listener to end connector
        addComponentListenerToEndConnector();
        
//        connection.setVisible(true);
        System.out.println("Z-ORDER: "+editor.getComponentZOrder(connection));
        editor.setComponentZOrder(connection, 0);
        update();
    }

    public void setVisible(boolean visible) {
        connection.setVisible(visible);
    }
    private void mouseClickedOnConnection() {
        selected = !selected;
        update();
    }

    private void update() {

        if (selected) {
            connection.setCurrentColor(SELECTED_COLOR);
        } else {
            connection.setCurrentColor(DEFAULT_COLOR);
        }

        //set location
        relocateConnection();

        //resize component
        resizeConnection();

        //lastly repaint component
        connection.repaint();
        System.out.println("CONNECTION UPDATED!");
    }

    private void addMouseListenerToConnection() {
        connection.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClickedOnConnection();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    private void addComponentListenerToStartConnector() {
        if(startConnector.getParent() == null) {
            System.out.println("CONNECTOR HAS NULL PARENT!");
            return;
        }
        startConnector.getParent().addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                startConnectorResized();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                startConnectorMoved();
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });
    }

    private void startConnectorResized() {
        update();
    }

    private void startConnectorMoved() {
        update();
    }

    private void addComponentListenerToEndConnector() {
        endConnector.getParent().addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                endConnectorResized();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                endConnectorMoved();
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });
    }

    private void endConnectorResized() {
        update();
    }

    private void endConnectorMoved() {
        update();
    }

    private Point subtractPoints(Point start, Point end) {
        
        int x = start.x - end.x;
        int y = start.y - end.y;
        return new Point(x, y);
    }
    
    private void relocateConnection() {
        /*
         * First we need to update the connector points.
         */
        
        connection.setStartPoint(new Point(0, 0));
        
        connection.setEndPoint(subtractPoints(endConnector.getLocationOnScreen(), editor.getLocationOnScreen()));
        
        /*
         * We need to get the new upper left corner
         * so let's get the highest Y and the lowest X.
         */

        int endY = this.endConnector.getLocationOnScreen().y - editor.getLocationOnScreen().y;
        int endX = this.endConnector.getLocationOnScreen().x - editor.getLocationOnScreen().x;

        //get the larger of the two Y's
        int newX = startConnector.getLocationOnScreen().x - editor.getLocationOnScreen().x + startConnector.getWidth()/2;
        int newY = startConnector.getLocationOnScreen().y - editor.getLocationOnScreen().y + startConnector.getHeight()/2;
        //get the lowers of the two X's
//        int newX = Math.min(startX, endX);
//
        connection.setLocation(newX, newY);
        System.out.println("NEW LOCATION: ("+newX+","+newY+")");
    }

    private void resizeConnection() {
        /*
         * We need to get the absolute value of the difference of each component.
         */

        int startY = this.startConnector.getLocationOnScreen().y;
        int startX = this.startConnector.getLocationOnScreen().x;

        int endY = this.endConnector.getLocationOnScreen().y;
        int endX = this.endConnector.getLocationOnScreen().x;

        int newX = endX - startX;
        int newY = endY - startY;

        System.out.println("NEW SIZE: ("+newX+","+newY+")");
        connection.setSize(newX, newY);
        connection.setEndPoint(new Point(newX, newY));
    }
}
