/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.contextmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Ryan
 */
public interface MenuItemSPI extends ActionListener {

    @Override
    public void actionPerformed(ActionEvent event);
}
