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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryan
 */
public class EditorCanvas extends javax.swing.JPanel implements Editor {
    
    private ContextMenu contextMenu;
    private EditorCanvasController controller;
    private ConnectionRepository repository;
    
    private ApplicationContext context() {
        return ApplicationContext.INSTANCE;//injectStrategy(Editor.class, this);
    }

    private ApplicationGraph graph() {
        return context().getGraph();
    }
    public EditorCanvas() {
        initComponents();
        
        context().injectStrategy(Editor.class, this);
        
        //controller used for Swing presentation logic
        controller = new EditorCanvasController(this);                
        contextMenu = new ContextMenuBuilder().build();
        repository = new MappedConnectionRepository();
    }

    @Override
    public Connection addConnection(Link start, Link end) {

//        context().getGraph().getNode(start.getDraggableParent().getModel().getClassName()).
        ApplicationGraph graph = graph();
        AbstractGraphNode startNode = graph.getNode(start.getDraggableParent().getID());
        AbstractGraphNode endNode = graph.getNode(end.getDraggableParent().getID());

        startNode.addAdjacentNode(start.getCaption(), endNode);
        Connection connection = new Connection(start, end);
        start.addEndPoint(end);
        end.addEndPoint(start);
        
        add(connection);
        connection.setVisible(true);
        ConnectionController cc = new ConnectionController(this, start, end, connection);
        cc.setVisible(true);
        
        
        String connectionID = start.getID()+"->"+end.getID();
        repository.addConnection(connectionID, connection);
        
        return connection;
    }
    
    @Override
    public void removeConnection(String connectionID) {
        Connection connection = repository.getConnection(connectionID);
        repository.removeConnection(connectionID);
        if(connection == null) {
            return;
        }
        connection.cleanup();
        connection.setVisible(false);
        remove(connection);
 
        System.out.println("REMOVING CONNECTION: "+connectionID);
        
    }

    private <T extends AbstractGraphNode> T newNode(Class<T> clazz, String ID) {
        T obj = null;
        try {
            obj = clazz.getConstructor(String.class).newInstance(ID);

        } catch (InstantiationException ex) {
            Logger.getLogger(EditorCanvas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EditorCanvas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(EditorCanvas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(EditorCanvas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(EditorCanvas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(EditorCanvas.class.getName()).log(Level.SEVERE, null, ex);
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

}
