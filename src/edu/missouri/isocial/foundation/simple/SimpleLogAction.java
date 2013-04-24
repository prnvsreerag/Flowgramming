/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.simple;

import edu.missouri.isocial.foundation.AbstractConfigurable;
import edu.missouri.isocial.foundation.Actionable;
import edu.missouri.isocial.foundation.annotations.SequenceAction;
import java.util.logging.Logger;

/**
 *
 * @author Ryan
 */
@SequenceAction(parameters={"Message"})
public class SimpleLogAction extends AbstractConfigurable implements Actionable {

    @Override
    public void takeAction() {
        
        String msg = (String)properties().get("Message");
        Logger.getLogger(SimpleLogAction.class.getName()).warning(msg);
    }
    
}
