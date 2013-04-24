/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import edu.missouri.isocial.foundation.model.SequenceItemSPI;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Ryan
 */
public class DefaultSequenceApplication implements Application {

    public Set<Startable> startables;
    
    public DefaultSequenceApplication() {
        startables = new HashSet<Startable>();
    }

    @Override
    public void addToStartItems(Startable item) {
        startables.add(item);
    }

    @Override
    public void start(Object input) {
        for(Startable startable: startables) {
            startable.start(input);
            
            SequenceItemSPI item = startable;
            
            while(item.getNextItem() != null) {
                item = item.getNextItem();
                
                item.retrieveParameters();
                item.run();
                item.updateParameters();
            }
        }
    }
}
