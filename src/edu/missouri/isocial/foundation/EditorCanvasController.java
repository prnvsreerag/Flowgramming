/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ryan
 */
public class EditorCanvasController {

    private final EditorCanvas editor;

    public EditorCanvasController(EditorCanvas editor) {
        this.editor = editor;

        initializeListeners();
    }

    private void initializeListeners() {
        editor.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                userMovedMouse(e);
            }
        });

        editor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userClickedMouseOnCanvas(e);
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

    protected void userMovedMouse(MouseEvent me) {
        //do nothing for now
    }

    protected void userClickedMouseOnCanvas(MouseEvent me) {
        if (SwingUtilities.isRightMouseButton(me)) {
            
            int x = me.getXOnScreen() - editor.getLocationOnScreen().x;
            int y = me.getYOnScreen() - editor.getLocationOnScreen().y;
            
            editor.showContextMenu(x, y);
        }
    }
}
