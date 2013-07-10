/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ryan
 */
public class DraggableComponentController {

    private final DraggableComponent draggable;
    private volatile int screenX = 0;
    private volatile int screenY = 0;
    private volatile int myX = 0;
    private volatile int myY = 0;
    private boolean selected;
    private MouseListener _mouseListener;
    private MouseMotionListener _mouseMotionListener;
    private KeyListener _keyListener;

    public DraggableComponentController(DraggableComponent draggable) {
        this.draggable = draggable;

//        Class.forName(null).getMethod(null, null).in
//        in.setValue(out.getValue());
        initializeListeners();
    }

    private void initializeListeners() {

        _mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userClickedMouseButton(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                userPressedMouseButton(e);
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

        _mouseMotionListener = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                userDraggedMouse(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        };

        _keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                userPressedAKey(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };

        draggable.addMouseListener(_mouseListener);
        draggable.addMouseMotionListener(_mouseMotionListener);
        draggable.addKeyListener(_keyListener);

    }

    protected void userPressedMouseButton(MouseEvent e) {
        screenX = e.getXOnScreen();
        screenY = e.getYOnScreen();
        myX = draggable.getX();
        myY = draggable.getY();
    }

    protected void userClickedMouseButton(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            selected = !selected;
            if (selected) {
                draggable.setBorderColor(Color.WHITE);
                draggable.requestFocusInWindow();


                int x = getCorrectXCoordinate(e);//e.getXOnScreen() - draggable.getLocationOnScreen().x - draggable.getEditor().getLocationOnScreen().x;
                int y = getCorrectYCoordinate(e);
                //e.getYOnScreen() - draggable.getLocationOnScreen().y - draggable.getEditor().getLocationOnScreen().y;

                draggable.showContextMenu(x, y);
            } else {
                draggable.setBorderColor(Color.BLACK);
            }
            draggable.repaint();
        }
    }

    private int getCorrectXCoordinate(MouseEvent e) {
        return e.getXOnScreen() - draggable.getLocationOnScreen().x;
    }

    private int getCorrectYCoordinate(MouseEvent e) {
        return e.getYOnScreen() - draggable.getLocationOnScreen().y;

    }

    protected void userDraggedMouse(MouseEvent e) {
        int deltaX = e.getXOnScreen() - screenX;
        int deltaY = e.getYOnScreen() - screenY;

        draggable.setLocation(myX + deltaX, myY + deltaY);
        //draggable.repaintConnections();
    }

    protected void userPressedAKey(KeyEvent e) {
        System.out.println("KEY TYPED!");
        
        if (selected) {
            
            if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                cleanup();
                
                draggable.setVisible(false);

                draggable.getEditor().removeDraggable(draggable);  
                
            }
        }
    }

    public void cleanup() {
        draggable.removeMouseListener(_mouseListener);
        draggable.removeMouseMotionListener(_mouseMotionListener);
        draggable.removeKeyListener(_keyListener);
        
        draggable.cleanup();
    }
}
