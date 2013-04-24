/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.sequence;

import edu.missouri.isocial.foundation.Editor;
import edu.missouri.isocial.foundation.EditorApplication;
import edu.missouri.isocial.foundation.components.core.Connector;
import edu.missouri.isocial.foundation.components.core.DraggableJPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;

/**
 *
 * @author Ryan
 */
public class DraggableCondition<T> extends DraggableJPanel<T> {

    private final static int SCALE = 150;
    private Connector bottom;
    private Connector top;
    private Connector right;

    public DraggableCondition(Editor editor, T model) {
        super(editor, model);

        bottom = new Connector(editor, this);
        add(bottom);
        bottom.setLocation(SCALE / 2 - (bottom.getWidth() / 2), SCALE - (bottom.getHeight() / 2));
        bottom.setVisible(true);

        top = new Connector(editor, this);
        add(top);
        top.setLocation(SCALE / 2 - (top.getWidth()) / 2, 0);
        top.setVisible(true);

        right = new Connector(editor, this);
        add(right);
        right.setLocation(SCALE - right.getWidth() / 2, SCALE / 2 - (right.getWidth() / 2));
        right.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;

        GeneralPath path = new GeneralPath();
        path.moveTo(SCALE / 2, 0);
        path.lineTo(SCALE, SCALE / 2);
        path.lineTo(SCALE / 2, SCALE);
        path.lineTo(0, SCALE / 2);
        path.lineTo(SCALE / 2, 0);

        g.setColor(Color.RED);
        g.fill(path);
        g.setColor(Color.BLACK);
        g.draw(path);

    }
}
