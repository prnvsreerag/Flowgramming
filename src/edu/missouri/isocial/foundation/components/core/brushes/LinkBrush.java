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
import java.awt.Color;
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
        link.setSize(Int(totalWidth), g.getFontMetrics().getHeight() + 10);

        g.setColor(link.getCurrentColor());
        switch (link.getDefaultPosition()) {
            case LEFT:
                g.fillRect(0, 0, SIDE_SIZE, SIDE_SIZE);
                g.drawString(link.getCaption(), SIDE_SIZE + 2, SIDE_SIZE + 2);
                break;
            case RIGHT:
                link.setLocation(link.getParent().getWidth() - link.getWidth(), link.getLocation().y);

                g.drawString(link.getCaption(), 0, 10);
                g.fillRect(new Double(stringWidth + padding).intValue(), 0, SIDE_SIZE + 2,
                SIDE_SIZE + 2);
                break;
            case BOTTOM:
                link.setSize(new Double(stringWidth).intValue(),
                        new Double(SIDE_SIZE + padding + 10).intValue());

                link.setLocation(link.getLocation().x, link.getParent().getHeight() - link.getHeight());
                g.drawString(link.getCaption(), 0, 10);

                double xPos = link.getWidth() / 2.0 - SIDE_SIZE / 2;

                g.fillRect(Int(xPos), Int(10 + padding), SIDE_SIZE, SIDE_SIZE);
                break;
            default:
                throw new AssertionError(link.getDefaultPosition().name());
        }

        g.setColor(Color.RED);
        g.drawRect(0, 0, link.getWidth() - 1, link.getHeight() - 1);
    }

    /**
     * Syntactic sugar for retrieving an int value from a double value
     *
     * @param value the double value to transform into int
     * @return the int representation of the given double
     */
    private int Int(double value) {
        return new Double(value).intValue();
    }
}
