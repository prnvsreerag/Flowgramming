/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.model;

/**
 *
 * @author Ryan
 */
public interface FunctionItemSPI {
    public void retrieveInput(Object input);
    
    public void runFunction();
    
    public void setOutput();
}
