/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core;

import edu.missouri.isocial.foundation.GraphView;
import edu.missouri.isocial.foundation.components.core.brushes.LinkBrush;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.JComponent;

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
    
    private final GraphView editor;
    private final DraggableComponent draggableParent;
    private Color currentColor; 
    public static final int SIDE_SIZE = 10;  
    private static Integer nextConnectorID = 0;
    //Connectors that this connector instance connects to.
    private Set<Link> endPoints;
    private final int ID;
    private POSITION defaultPosition = POSITION.LEFT;
    private String caption;

    private Link(GraphView editor,
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

    public GraphView getEditor() {
        return this.editor;
    }
    private Link(final GraphView editor, DraggableComponent draggableParent, POSITION defaultPosition) {
        this.editor = editor;
        this.draggableParent = draggableParent;
        this.endPoints = new LinkedHashSet<Link>();
        this.setSize(SIDE_SIZE + 100, SIDE_SIZE + 5);

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

    public void cleanup() {
        linkController.cleanup();

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

    public void removeConnections() {
        for (Link endPoint : endPoints) {
            String first = endPoint.ID + "<->" + ID;
            String second = ID + "<->" + endPoint.ID;
            this.getEditor().removeConnection(first);
            this.getEditor().removeConnection(second);
        }
    }


    public static LinkBuilder builder() {
        return new LinkBuilder();
    }

    public static class LinkBuilder {

        private GraphView editor = null;
        private DraggableComponent parent = null;
        private POSITION position = POSITION.LEFT;
        private String caption = "n/a";
        private Class expectedType = Object.class;

        public LinkBuilder withEditor(GraphView editor) {
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
