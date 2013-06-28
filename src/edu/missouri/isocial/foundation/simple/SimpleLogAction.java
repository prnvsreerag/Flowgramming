package edu.missouri.isocial.foundation.simple;

import edu.missouri.isocial.foundation.AbstractParameterizable;
import edu.missouri.isocial.foundation.Actionable;
import edu.missouri.isocial.foundation.annotations.SequenceAction;
import java.util.logging.Logger;

/**
 *
 * @author Ryan
 */
@SequenceAction(parameters={"Message"}, category="Sequence Actions", caption="Log")
public class SimpleLogAction extends AbstractParameterizable implements Actionable {
   
    @Override
    public void takeAction() {       
        String msg = (String) parameters().get("Message");
        Logger.getLogger(SimpleLogAction.class.getName()).warning(msg);
    }
    
}
