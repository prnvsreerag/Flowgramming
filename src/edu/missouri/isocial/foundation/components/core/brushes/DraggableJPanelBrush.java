/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core.brushes;

import edu.missouri.isocial.foundation.components.core.DraggableJPanel;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;

/**
 *
 * @author Ryan
 */
public class DraggableJPanelBrush {
    private final DraggableJPanel draggable;
    
    /**
     *
     * @param draggable
     */
    public DraggableJPanelBrush(DraggableJPanel draggable) {
        this.draggable = draggable;
    }
    
    
    private int getWidth() {
        return draggable.getWidth();
    }
    
    private int getHeight() {
        return draggable.getHeight();
    }
    
    private Color getBorderColor() {
        return draggable.getBorderColor();
    }
    
    public void paint(Graphics2D g) {
//        borderColor = Color.BLACK;

        //if this component is selected change the border color to white.
        //TODO: check EditorApplication to see if this is selected component

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color[] colors = new Color[]{new Color(0.6f, 0.6f, 1.0f, 0.9f), new Color(0.6f, 0.6f, 1.0f, 0.5f)};

        float[] factors = {0f, 1f};

        //draw drop shadow
        g.setPaint(new RadialGradientPaint(getWidth() / 2, getHeight() / 2, getWidth() / 2, factors, colors));
        g.fillRoundRect(8, 3, getWidth() - 10, getHeight() - 6, 15, 15);

        //draw frame body
        g.setColor(new Color(170, 170, 170));
        g.fillRoundRect(5, 1, getWidth() - 9, getHeight() - 6, 15, 15);

        //draw frame border
        g.setColor(getBorderColor());
        g.drawRoundRect(4, 0, getWidth() - 9, getHeight() - 6, 15, 15);

        //Remove artifact in upper right corner
        g.setColor(new Color(170, 170, 170));
        g.fillRect(4, 1, 10, 10);
        g.setColor(getBorderColor());
        g.drawLine(4, 0, 14, 0);
        g.drawLine(4, 0, 4, 10);

        //draw separator between title and body.
        g.setColor(Color.BLACK);
        g.drawLine(5, 15, getWidth() - 6, 15);

        //draw lighter line to give 3d effect to separator
        g.setColor(new Color(190, 190, 190));
        g.drawLine(5, 16, getWidth() - 6, 16);


        //TODO: Parameterize these colors
        Color c1 = Color.MAGENTA;
        Color c2 = Color.YELLOW;

        //fill background of title area
        g.setPaint(new GradientPaint(0, 15, c1, getWidth(), 15, c2));
        g.fillRoundRect(5, 1, getWidth() - 15, 14, 10, 15);
    }
}
