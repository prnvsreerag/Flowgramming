/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core;

import edu.missouri.isocial.foundation.Editor;
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

    private Link startConnector;
    private Link endConnector;
    private Connection connection;
    private boolean selected = false;
    private static final Color SELECTED_COLOR = Color.WHITE;
    private static final Color DEFAULT_COLOR = Color.BLACK;
    private final Editor editor;
    private MouseListener _mouseListener;
    private ComponentListener _startConnectorParentListener;
    private ComponentListener _endConnectorParentListener;

    public ConnectionController(Editor editor, Link parentConnector, Link targetConnector, Connection connection) {
        this.startConnector = parentConnector;
        this.endConnector = targetConnector;
        this.connection = connection;
        this.editor = editor;
        connection.setController(this);
        connection.setOpaque(false);
               
        //add mouse listener to connection
        addMouseListenerToConnection();

        //add component changed listener to start connector
        addComponentListenerToStartConnector();

        //add component changed listener to end connector
        addComponentListenerToEndConnector();
        
//        connection.setVisible(true);
//        System.out.println("Z-ORDER: "+editor.getComponentZOrder(connection));
        
        
        //TODO: figure out something better to do with this rather
        //than comment it out. Possibly introduce it to the interface.
//        editor.setComponentZOrder(connection, 0);
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
    }

    private void addMouseListenerToConnection() {
        _mouseListener = new MouseListener() {
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
        };
        
        connection.addMouseListener(_mouseListener);
    }

    private void addComponentListenerToStartConnector() {
        if(startConnector.getParent() == null) {
            System.out.println("CONNECTOR HAS NULL PARENT!");
            return;
        }
        
        _startConnectorParentListener=new ComponentListener() {
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
        };
        startConnector.getParent().addComponentListener(_startConnectorParentListener);
    }

    private void startConnectorResized() {
        update();
    }

    private void startConnectorMoved() {
        update();
    }

    private void addComponentListenerToEndConnector() {
        _endConnectorParentListener = new ComponentListener() {
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
        };
        
        endConnector.getParent().addComponentListener(_endConnectorParentListener);
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
    
    private int determineHighestY() {
        
        int startY = startConnector.getLocationOnScreen().y - editor.getLocationOnScreen().y;
        int endY = endConnector.getLocationOnScreen().y - editor.getLocationOnScreen().y;
        
        //we use min here because of swing's coordinate system.
        //y = 0 at the top left corner, ergo the "highest" y, visually, will
        //be the lowest value.
        return Math.min(startY, endY);
    }
    
    private int determineLowestX() {
        int startX = startConnector.getLocationOnScreen().x - editor.getLocationOnScreen().x;
        int endX = endConnector.getLocationOnScreen().x - editor.getLocationOnScreen().x;
        
        return Math.min(startX, endX);
    }
    
    private void relocateConnection() {
        
        //the location needs to be the highest y and lowest x;
        int highestY = determineHighestY();
        int lowestX = determineLowestX();
        
        //set location in editor coordinates
        connection.setLocation(lowestX, highestY);

        //the start point now needs to cohere to the start connector in our new coordinate space
        int startPointX = startConnector.getLocationOnScreen().x - connection.getLocationOnScreen().x;
        int startPointY = startConnector.getLocationOnScreen().y - connection.getLocationOnScreen().y;
        
        int endPointX = endConnector.getLocationOnScreen().x - connection.getLocationOnScreen().x;
        int endPointY = endConnector.getLocationOnScreen().y - connection.getLocationOnScreen().y;

        connection.setStartPoint(new Point(startPointX+(Link.SIDE_SIZE/2), startPointY+(Link.SIDE_SIZE/2)));
        connection.setEndPoint(new Point(endPointX+(Link.SIDE_SIZE/2), endPointY+(Link.SIDE_SIZE/2)));

//        System.out.println("NEW START POINT: "+connection.getStartPoint());
//        System.out.println("NEW END POINT: "+connection.getEndPoint());



        //get the larger of the two Y's
        int newX = startConnector.getLocationOnScreen().x - editor.getLocationOnScreen().x + startConnector.getWidth()/2;
        int newY = startConnector.getLocationOnScreen().y - editor.getLocationOnScreen().y + startConnector.getHeight()/2;
        //get the lowers of the two X's
//        int newX = Math.min(startX, endX);
//
//        connection.setLocation(newX, newY);
//        System.out.println("NEW LOCATION: ("+newX+","+newY+")");
        
//        System.out.println("CONNECTOR ON SCREEN: "+startConnector.getLocationOnScreen()+"\n"
//                + "CONNECTION ON SCREEN: "+connection.getLocationOnScreen());
    }

    private void resizeConnection() {
        /*
         * We need to get the absolute value of the difference of each component.
         */

        int startY = this.startConnector.getLocationOnScreen().y;
        int startX = this.startConnector.getLocationOnScreen().x;

        int endY = this.endConnector.getLocationOnScreen().y;
        int endX = this.endConnector.getLocationOnScreen().x;

        int newX = Math.abs(endX - startX);
        int newY = Math.abs(endY - startY);

//        System.out.println("NEW SIZE: ("+newX+","+newY+")");
        connection.setSize(newX, newY);
//        connection.setEndPoint(new Point(newX, newY));
    }

    public void cleanup() {
       endConnector.getParent().removeComponentListener(_endConnectorParentListener);
       startConnector.getParent().removeComponentListener(_startConnectorParentListener);
       connection.removeMouseListener(_mouseListener);
       
       _startConnectorParentListener = null;
       _endConnectorParentListener = null;
       _mouseListener = null;
    }
}
