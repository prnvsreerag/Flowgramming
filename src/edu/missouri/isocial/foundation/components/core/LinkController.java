/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ryan
 */
public class LinkController {

    private final Link link;
    private MouseListener _mouseListener;
    private boolean mouseIsPressed;
    private static final Color DEFAULT_COLOR = Color.BLACK;
    private static final Color ACTIVE_COLOR = Color.WHITE;

    public LinkController(Link link) {
        this.link = link;

        initializeListeners();
    }

    private void initializeListeners() {
        _mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                userPressedMouseButton(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                userReleasedMouseButton(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                userEnteredMouse(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                userExitedMouse(e);
            }
        };

        link.addMouseListener(_mouseListener);
    }

    public void userPressedMouseButton(MouseEvent e) {
        mouseIsPressed = true;
    }

    public void userReleasedMouseButton(MouseEvent e) {
        //get the draggable from the editor based on where the mouse was released.
        final Component c = link.getEditor().findComponentAt(e.getLocationOnScreen());

        mouseIsPressed = false;
        link.repaint();

        //check edge cases
        if (c == null) {
            //fail
            return;
        }

        if (c instanceof DraggableComponent) {
            //pass
            DraggableComponent d = (DraggableComponent) c;
            final Component c2 = d.findComponentAt(subtract(e.getLocationOnScreen(), d.getLocationOnScreen()));
            if (c2 == null) {
                //fail
                System.out.println("NULL COMPONENT IN DRAGGABLE");
                return;
            }

            handleQueryForLink(c2);
        } else {
            handleQueryForLink(c);
        }
    }

    public void userEnteredMouse(MouseEvent e) {
//        currentColor = Link.ACTIVE_COLOR;
        link.setCurrentColor(ACTIVE_COLOR);
        link.repaint();
    }

    public void userExitedMouse(MouseEvent e) {
        if (!mouseIsPressed) {
            link.setCurrentColor(DEFAULT_COLOR);
        }
        link.repaint();
    }

    private void handleQueryForLink(final Component potentialLink) {
        //make sure we're working with the correct object here.
        if (potentialLink instanceof Link) {
            //also make sure we're not just connecting with ourselves.
            if (potentialLink.equals(link)) {
                link.setCurrentColor(DEFAULT_COLOR);
                System.out.println("CANNOT MAKE CONNECTION TO SELF!");
                return;
            }

            //make sure we're not connecting two connectors on the same node
            if (potentialLink.getParent().equals(link.getParent())) {
                link.setCurrentColor(DEFAULT_COLOR);
                System.out.println("CANNOT MAKE CONNECTION TO NODE SELF.");
                return;
            }

            Link target = (Link) potentialLink;
            Class type = target.getExpectedType();
            //check that the two expected types for the links match
            if (type.equals(link.getExpectedType())) {
                //make the connection if the types match
                makeConnection(link.getDraggableParent(), target.getDraggableParent(), target);
                System.out.println("TYPES: " + link.getExpectedType() + " MATCHED SUCCESSFULLY!");
            } else {
                link.setCurrentColor(DEFAULT_COLOR);
                System.out.println("TYPE: " + type + " DOES NOT MATCH: " + link.getExpectedType());
            }
        } else {
            link.setCurrentColor(DEFAULT_COLOR);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("DID NOT RELEASE ON A LINK!");
                    System.out.println("RELEASED ON A: " + potentialLink.getClass().getName());
                }
            });
        }
    }

    private Point subtract(Point one, Point two) {
        int x = one.x - two.x;
        int y = one.y - two.y;

        return new Point(x, y);
    }

    private void makeConnection(DraggableComponent start, DraggableComponent end, Link target) {
        String id = link.getID() + "->" + target.getID();
        System.out.println("MAKING CONNECTION: " + id);
        link.getEditor().addConnection(link, target);
    }
}
