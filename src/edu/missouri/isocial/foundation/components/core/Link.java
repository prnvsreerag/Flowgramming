/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core;

import edu.missouri.isocial.foundation.Editor;
import edu.missouri.isocial.foundation.EditorApplication;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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

    public enum POSITION {

        LEFT, RIGHT, BOTTOM
    };
    private Class valueClassType = Object.class;
    private final Editor editor;
//    private List<Connection> connections;
    private final DraggableComponent draggableParent;
    private Color currentColor;
    private boolean mouseIsPressed = false;
    private static final Color DEFAULT_COLOR = Color.BLACK;
    private static final Color ACTIVE_COLOR = Color.WHITE;
    public static final int SIDE_SIZE = 10;

    
    private static Integer nextConnectorID = 0;
    //Connectors that this connector instance connects to.
    private Set<Link> endPoints;
    private final int ID;
    private POSITION defaultPosition = POSITION.LEFT;
    private String caption;

    private Link(Editor editor, DraggableComponent parent, POSITION position, String caption) {

        this(editor, parent, position);
        this.caption = caption;

    }

    private Link(final Editor editor, DraggableComponent draggableParent, POSITION defaultPosition) {
        this.editor = editor;
        this.draggableParent = draggableParent;
        this.endPoints = new LinkedHashSet<Link>();
        this.setSize(SIDE_SIZE + 100, SIDE_SIZE + 2);
//        this.connections = new ArrayList<Connection>();
        this.defaultPosition = defaultPosition;
        this.setOpaque(false);
        this.currentColor = DEFAULT_COLOR;
        this.addMouseListener(new MouseListener() {
            Link parentConnector = Link.this;

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

                if (c instanceof DraggableComponent) {
                    DraggableComponent d = (DraggableComponent) c;
                    final Component c2 = d.findComponentAt(subtract(e.getLocationOnScreen(),d.getLocationOnScreen()));
                    if(c2 == null) {
                        System.out.println("NULL COMPONENT IN DRAGGABLE");
                        return;
                    }
                    
                    handleQueryForConnector(c2);

                } else {
                    handleQueryForConnector(c);
                }
                //check if we dropped on a connector
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                currentColor = ACTIVE_COLOR;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!mouseIsPressed) {
                    currentColor = DEFAULT_COLOR;
                }
                repaint();
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        synchronized(nextConnectorID) {
            this.ID = nextConnectorID.intValue();
            
            System.out.println("CREATING CONNECTOR: "+ID);
            nextConnectorID += 1;
        }
    }

    private void handleQueryForConnector(final Component potentialConnector) {
        if (potentialConnector instanceof Link) {

            if (potentialConnector.equals(Link.this)) {
                System.out.println("CANNOT MAKE CONNECTION TO SELF!");
                return;
            }
            Link target = (Link) potentialConnector;

            Class type = target.getValueClass();
            //check that we're meeting the right type
            if (type.equals(getValueClass())) {
                //add connection

                makeConnection(getDraggableParent(), target.getDraggableParent(), target);

                System.out.println("CONNECTION MADE!");
            } else {
                System.out.println("TYPES DON'T MATCH!");
            }
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("DID NOT RELEASE ON A CONNECTOR!");
                    System.out.println("RELEASED ON A: " + potentialConnector.getClass().getName());
                }
            });

        }
    }

    private Point subtract(Point one, Point two) {
        int x = one.x - two.x;
        int y = one.y - two.y;
        
        return new Point(x, y);
    }
    public DraggableComponent getDraggableParent() {
        return draggableParent;
    }

    @Override
    protected void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;

        g.setColor(currentColor);
        switch (defaultPosition) {
            case LEFT:
                g.fillRect(0, 0, SIDE_SIZE, SIDE_SIZE);
                g.drawString("Test", SIDE_SIZE + 2, SIDE_SIZE + 2);//, TOP_ALIGNMENT, TOP_ALIGNMENT);\
                break;
            case RIGHT:
                g.drawString("Test", 0, 0);
                g.fillRect(20, 0, SIDE_SIZE + 2, SIDE_SIZE + 2);
                break;
            case BOTTOM:
                //this.setSize(50, 25);
                //g.drawString("Test", 0, 0);
                g.fillRect(0, 0, SIDE_SIZE, SIDE_SIZE);
                break;
        }
    }

    private Class getValueClass() {
        return this.valueClassType;
    }

    private void makeConnection(DraggableComponent start, DraggableComponent end, Link target) {
//        ConnectionInfo connectionInfo = new ConnectionInfo(start, end, this, target, target.getValueClass());
//        start.addConnectionStart(connectionInfo);
//        end.addConnectionEnd(connectionInfo);

        String id = getID() +"->"+target.getID();
                System.out.println("MAKING CONNECTION: "+id);
        editor.addConnection(this, target);
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
    
    public int getID() {
        return ID;
    }
    
    public void addEndPoint(Link endPoint) {
        System.out.println("ADDING ENDPOINT: "+endPoint.getID()+" TO CONNECTOR: "+getID());
        endPoints.add(endPoint);
    }

    public static LinkBuilder builder() {
        return new LinkBuilder();
    }

    public static class LinkBuilder {

        private Editor editor;
        private DraggableComponent parent;
        private POSITION position;
        private String caption;

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

        public Link build() {
            return new Link(editor, parent, position, caption);
        }
    }
}
