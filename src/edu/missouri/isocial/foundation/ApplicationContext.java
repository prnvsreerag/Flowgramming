/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import java.util.concurrent.Callable;

/**
 *
 * @author Ryan
 */
public enum ApplicationContext {
    INSTANCE;
    
    private Application currentApplication;
    private StrategyCollection strategies;
    private FlowGraph graph;
    
    public Application getApplication() {
        if(currentApplication == null) {
            currentApplication = new DefaultSequenceApplication();
        }
        
        return currentApplication;       
    }
    
    public void setApplication(Application application) {
        this.currentApplication = application;
    }
    
    public StrategyCollection getStrategies() {
        if(strategies == null) {
            strategies = new DefaultStrategyCollection();
        }
        
        
        return strategies;
    }
    void injectStrategy(Class key, Object value) {
       getStrategies().put(key, value);
    }

    public FlowGraph getGraph() {
        if (graph == null) {
            graph = new FlowGraph();
        }
        return graph;
    }
}
