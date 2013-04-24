/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.model;

import edu.missouri.isocial.foundation.Startable;

/**
 *
 * @author Ryan
 */
public interface SequenceItemSPI {
    
//    public void setPreviousItem(SequenceItemSPI previous);
//    
//    public void setPreviousItem(Startable start);
    
    public void setNextItem(SequenceItemSPI next);
    
    public void retrieveParameters();
   
    public void run();
    
    public void updateParameters();

    public SequenceItemSPI getNextItem();
}
