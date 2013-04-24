/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.model.basic;

import edu.missouri.isocial.foundation.Startable;
import edu.missouri.isocial.foundation.model.SequenceItemSPI;

/**
 *
 * @author Ryan
 */
public class BasicSequenceItem implements SequenceItemSPI {
    private final SequenceItemSPI previousItem;

    
    public BasicSequenceItem(SequenceItemSPI previousItem) {
        this.previousItem = previousItem;
    } 
    @Override
    public void retrieveParameters() {
//        previousItem.getParameters();
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateParameters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public void setPreviousItem(SequenceItemSPI previous) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void setPreviousItem(Startable start) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void setNextItem(SequenceItemSPI next) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SequenceItemSPI getNextItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
