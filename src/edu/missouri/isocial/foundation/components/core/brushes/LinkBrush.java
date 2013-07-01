/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.core.brushes;

import edu.missouri.isocial.foundation.components.core.Link;
import static edu.missouri.isocial.foundation.components.core.Link.POSITION.BOTTOM;
import static edu.missouri.isocial.foundation.components.core.Link.POSITION.LEFT;
import static edu.missouri.isocial.foundation.components.core.Link.POSITION.RIGHT;
import static edu.missouri.isocial.foundation.components.core.Link.SIDE_SIZE;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Ryan
 */
public class LinkBrush {

    private final Link link;

    public LinkBrush(Link link) {
        this.link = link;
    }

    public void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        double stringWidth = g.getFontMetrics().stringWidth(link.getCaption());
        double boxWidth = SIDE_SIZE;
        double padding = 4;
        double totalWidth = stringWidth + padding + boxWidth;
        link.setSize(new Double(totalWidth).intValue(),
                g.getFontMetrics().getHeight());

        g.setColor(link.getCurrentColor());
        switch (link.getDefaultPosition()) {
            case LEFT:
                g.fillRect(0, 0, SIDE_SIZE, SIDE_SIZE);
                g.drawString("Test", SIDE_SIZE + 2, SIDE_SIZE + 2);//, TOP_ALIGNMENT, TOP_ALIGNMENT);\
                break;
            case RIGHT:
                g.drawString(link.getCaption(), 0, 0);
                g.fillRect(new Double(stringWidth + padding).intValue(), 0, SIDE_SIZE + 2,
                        SIDE_SIZE + 2);
                break;
            case BOTTOM:
                //this.setSize(50, 25);
                //g.drawString("Test", 0, 0);
                g.fillRect(0, 0, SIDE_SIZE, SIDE_SIZE);
                break;
            default:
                throw new AssertionError(link.getDefaultPosition().name());
        }
    }
}
