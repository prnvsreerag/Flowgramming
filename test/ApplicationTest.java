/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.missouri.isocial.foundation.Application;
import edu.missouri.isocial.foundation.ApplicationContext;
import edu.missouri.isocial.foundation.Startable;
import edu.missouri.isocial.foundation.model.SequenceItemSPI;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InOrder;


import static org.mockito.Mockito.*;
/**
 *
 * @author Ryan
 */
public class ApplicationTest {
    

    @Test
    public void scratchPad() {
        Application app = ApplicationContext.INSTANCE.getApplication();
        
        //create start sequence item
        Startable start = mock(Startable.class);//new StartItem();
        
        //create first sequence item after start
        SequenceItemSPI first = mock(SequenceItemSPI.class);
        when(start.getNextItem()).thenReturn(first);
//        start.setNextItem(first);
//        first.setPreviousItem(start);
//        
//        //create second sequence item after first
        SequenceItemSPI second = mock(SequenceItemSPI.class);
        when(first.getNextItem()).thenReturn(second);
//        //create third sequence item after second
        SequenceItemSPI third = mock(SequenceItemSPI.class);
        when(second.getNextItem()).thenReturn(third);
//        third.setPreviousItem(second);
//        //add start as a root item
        
        app.addToStartItems(start);
        
        //execute sequence
        app.start(null);
        InOrder inOrder = inOrder(start, first, second, third);
                
        //verify start executed first, then first, then second, then third.
        inOrder.verify(start).start(null);
        inOrder.verify(first).retrieveParameters();
        inOrder.verify(first).run();
        inOrder.verify(first).updateParameters();
        inOrder.verify(second).retrieveParameters();
        inOrder.verify(second).run();
        inOrder.verify(second).updateParameters();
        inOrder.verify(third).retrieveParameters();
        inOrder.verify(third).run();
        inOrder.verify(third).updateParameters();
        
        
    }
}