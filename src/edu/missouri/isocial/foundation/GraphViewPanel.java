/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import edu.missouri.isocial.foundation.contextmenu.ContextMenuBuilder;
import edu.missouri.isocial.foundation.components.core.Connection;
import edu.missouri.isocial.foundation.components.core.ConnectionController;
import edu.missouri.isocial.foundation.components.core.Link;
import edu.missouri.isocial.foundation.components.core.DraggableComponent;
import edu.missouri.isocial.foundation.components.core.model.DraggableComponentModel;
import edu.missouri.isocial.foundation.components.core.model.DraggableItem;
//import edu.missouri.isocial.foundation.components.sequence.SequenceStart;
import edu.missouri.isocial.foundation.contextmenu.ContextMenu;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryan
 */
public class GraphViewPanel extends javax.swing.JPanel implements GraphView {
    
    private ContextMenu contextMenu;
    private EditorCanvasController controller;
    private ConnectionRepository repository;
    private Map<String, DraggableComponent> draggables;

    private ApplicationContext context() {
        return ApplicationContext.INSTANCE;//injectStrategy(Editor.class, this);
    }

    private FlowGraph graph() {
        return context().getGraph();
    }

    @Override
    public <T> AbstractGraphNode<T> getNodeFromFlowGraph(String nodeID) {
        return graph().getNode(nodeID);
    }
    public GraphViewPanel() {
        initComponents();
        
        context().injectStrategy(GraphView.class, this);
        
        //controller used for Swing presentation logic
        controller = new EditorCanvasController(this);                
        contextMenu = new ContextMenuBuilder().build();
        repository = new MappedConnectionRepository();
        draggables = new HashMap<String, DraggableComponent>();
    }

    public Set<String> getKeysOfDraggables() {
        return draggables.keySet();
    }

    @Override
    public Connection addConnection(Link startLink, Link endLink) {

        //add connection to graph
        FlowGraph graph = graph();
        AbstractGraphNode startNode = graph.getNode(startLink.getDraggableParent().getID());
        AbstractGraphNode endNode = graph.getNode(endLink.getDraggableParent().getID());
        startNode.addAdjacentNode(startLink.getCaption(), endNode);
        endNode.addAdjacentNode(endLink.getCaption(), startNode);

        //create connection view
        Connection connection = new Connection(startLink, endLink);
        startLink.addEndPoint(endLink);
        endLink.addEndPoint(startLink);
        
        add(connection);
        connection.setVisible(true);
        ConnectionController cc = new ConnectionController(this, startLink, endLink, connection);
        cc.setVisible(true);
        
        //store connection in repository
        String connectionID = startLink.getID() + "<->" + endLink.getID();
        repository.addConnection(connectionID, connection);

        connectionID = endLink.getID() + "<->" + startLink.getID();        
        repository.addConnection(connectionID, connection);

        return connection;
    }
    
    @Override
    public void removeConnection(String connectionID) {

        Connection connection = repository.getConnection(connectionID);

        //remove connection from repository
        repository.removeConnection(connectionID);
        if(connection == null) {
            return;
        }
        //remove connection view
        connection.setVisible(false);
        connection.cleanup();



        remove(connection);
 
        System.out.println("REMOVING CONNECTION: "+connectionID);
        
    }

    private <T extends AbstractGraphNode> T newNode(Class<T> clazz, String ID) {
        T obj = null;
        try {
            obj = clazz.getConstructor(String.class).newInstance(ID);

        } catch (InstantiationException ex) {
            Logger.getLogger(GraphViewPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GraphViewPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(GraphViewPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(GraphViewPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(GraphViewPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(GraphViewPanel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return obj;
        }
    }

    @Override
    public void addDraggable(DraggableComponentModel model, DraggableComponent draggable) {
        
//        if(draggable instanceof SequenceStart) {
//            application().addToStartItems((Startable)draggable.getModel());
//        }
        DraggableItem itemType = model.getClass().getAnnotation(DraggableItem.class);

        graph().addNode(draggable.getID(), newNode(itemType.value(), draggable.getID()));
        add(draggable);

        draggables.put(draggable.getID(), draggable);
    }

    @Override
    public DraggableComponent getDraggableWithID(String ID) {
        return draggables.get(ID);
    }

    @Override
    public void showContextMenu(int xOnScreen, int yOnScreen) {
        contextMenu.show(this, xOnScreen, yOnScreen);
        contextMenu.setVisible(true);
    }
    
    @Override
    public ContextMenu getContextMenu() {
        return contextMenu;
    }
    
    @Override
    public void removeDraggable(DraggableComponent draggable) {
        remove(draggable);
        draggables.remove(draggable.getID());
        repaint();
    }

    public void executeProgram() {
    }

    protected void paintComponent(Graphics g1) {
        super.paintComponent(g1);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private Application application() {
        return ApplicationContext.INSTANCE.getApplication();
    }

    public ConnectionRepository getConnectionRepository() {
        return repository;
    }

}
