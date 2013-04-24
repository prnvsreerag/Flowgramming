/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.components.sequence;

import edu.missouri.isocial.foundation.Editor;
import edu.missouri.isocial.foundation.components.core.DraggableJPanel;
import edu.missouri.isocial.foundation.components.core.Connector;
import edu.missouri.isocial.foundation.EditorApplication;
import edu.missouri.isocial.foundation.components.core.Connection;
import edu.missouri.isocial.foundation.components.core.ParameterConnector;

/**
 *
 * @author Ryan
 */
public class SequenceAction<T> extends DraggableJPanel<T> {

    private Connector inConnector;
    private Connector outConnector;
    public SequenceAction(Editor editor, T model) {
        super(editor, model);

        inConnector = new Connector(editor, this);
        add(inConnector);
        inConnector.setLocation(0, 20);
//        c.validate();
        inConnector.setVisible(true);

        outConnector = new Connector(editor, this);
        add(outConnector);
        System.out.println("NODE WIDTH:" + getWidth());
        outConnector.setLocation(190, 20);
        outConnector.setVisible(true);
        
        
        ParameterConnector pc = new ParameterConnector(editor, this);
        add(pc);
        pc.setLocation(190/2, 190-5);
        pc.setVisible(true);
    }
    
    public Connector getInConnector() {
        return inConnector;
    }
    
    public Connector getOutConnector() {
        return outConnector;
    }
    
    @Override
    protected void cleanup() {
        //get any output connectors connecting to this inConnector and set their
        //end point to null.
        
        for(Connector endPoint: inConnector.getEndPoints()) {
            endPoint.detachConnector(inConnector);
            
            String id = endPoint.getID()+"->"+inConnector.getID();
            System.out.println("REMOVING CONNECTION: "+id);
            getEditor().removeConnection(id);
        }
        
        //get any input connectors connected to this outConnector and set their
        //end point to null.
        for(Connector endPoint: outConnector.getEndPoints()) {
            endPoint.detachConnector(outConnector);
            String id = outConnector.getID()+"->"+endPoint.getID();
            
            
            System.out.println("REMOVING CONNECTION: "+id);
            getEditor().removeConnection(id);
            
        }
        
    }
}
