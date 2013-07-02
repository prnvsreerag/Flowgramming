/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core;

import edu.missouri.isocial.foundation.Editor;
import edu.missouri.isocial.foundation.EditorApplication;
import edu.missouri.isocial.foundation.components.core.brushes.LinkBrush;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ryan
 */
public class Link extends JComponent {
    private final LinkBrush brush;
    private Class expectedType;
    private final LinkController linkController;

    public enum POSITION {

        LEFT, RIGHT, BOTTOM
    };
    
    private final Editor editor;
    private final DraggableComponent draggableParent;
    private Color currentColor; 
    public static final int SIDE_SIZE = 10;  
    private static Integer nextConnectorID = 0;
    //Connectors that this connector instance connects to.
    private Set<Link> endPoints;
    private final int ID;
    private POSITION defaultPosition = POSITION.LEFT;
    private String caption;

    private Link(Editor editor,
            DraggableComponent parent,
            POSITION position,
            String caption,
            Class expectedType) {

        this(editor, parent, position);
        this.caption = caption;
        this.expectedType = expectedType;

    }

    public int getID() {
        return ID;
    }

    public Editor getEditor() {
        return this.editor;
    }
    private Link(final Editor editor, DraggableComponent draggableParent, POSITION defaultPosition) {
        this.editor = editor;
        this.draggableParent = draggableParent;
        this.endPoints = new LinkedHashSet<Link>();
        this.setSize(SIDE_SIZE + 100, SIDE_SIZE + 20);
        this.defaultPosition = defaultPosition;
        this.setOpaque(false);
        this.currentColor = Color.BLACK;
        this.brush = new LinkBrush(this);
        this.linkController = new LinkController(this);
        
        synchronized(nextConnectorID) {
            this.ID = nextConnectorID.intValue();
            
            System.out.println("CREATING CONNECTOR: "+ID);
            nextConnectorID += 1;
        }
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public DraggableComponent getDraggableParent() {
        return draggableParent;
    }

    @Override
    protected void paintComponent(Graphics g1) {
        brush.paintComponent(g1);
    }

    public POSITION getDefaultPosition() {
        return defaultPosition;
    }

    public String getCaption() {
        return caption;
    }

    public Class getExpectedType() {
        return this.expectedType;
    }

    public Color getCurrentColor() {
        return this.currentColor;
    }
    
    public Set<Link> getEndPoints() {
        return endPoints;
    }
    
    public void detachConnector(Link endPoint) {
        endPoints.remove(endPoint);
    }
    
    public void addEndPoint(Link endPoint) {
        System.out.println("ADDING ENDPOINT: "+endPoint.getID()+" TO CONNECTOR: "+getID());
        endPoints.add(endPoint);
    }

    public static LinkBuilder builder() {
        return new LinkBuilder();
    }

    public static class LinkBuilder {

        private Editor editor = null;
        private DraggableComponent parent = null;
        private POSITION position = POSITION.LEFT;
        private String caption = "n/a";
        private Class expectedType = Object.class;

        public LinkBuilder withEditor(Editor editor) {
            this.editor = editor;
            return this;
        }

        public LinkBuilder withParent(DraggableComponent parent) {
            this.parent = parent;
            return this;
        }

        public LinkBuilder withPosition(POSITION position) {
            this.position = position;
            return this;
        }

        public LinkBuilder withCaption(String caption) {
            this.caption = caption;
            return this;
        }

        public LinkBuilder withExpectedType(Class expectedType) {
            this.expectedType = expectedType;
            return this;
        }

        public Link build() {
            return new Link(editor, parent, position, caption, expectedType);
        }
    }
}
