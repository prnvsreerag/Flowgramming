/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import edu.missouri.isocial.foundation.model.SequenceItemSPI;

/**
 * Just a marking interface to signify the class implementing this interface
 * should be executed as the start of a sequence of execution.
 * 
 * @author Ryan
 */
public interface Startable extends SequenceItemSPI {
    public void start(/* optional input*/ Object input);
}
