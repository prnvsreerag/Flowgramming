/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.io;

import edu.missouri.isocial.foundation.AbstractGraphNode;
import edu.missouri.isocial.foundation.ApplicationContext;
import edu.missouri.isocial.foundation.Editor;
import edu.missouri.isocial.foundation.FlowGraph;
import edu.missouri.isocial.foundation.components.core.DraggableComponent;
import edu.missouri.isocial.foundation.components.core.model.DraggableComponentModel;
import edu.missouri.isocial.foundation.xml.DraggableInstance;
import edu.missouri.isocial.foundation.xml.DraggableInstances;
import edu.missouri.isocial.foundation.xml.FlowDTO;
import edu.missouri.isocial.foundation.xml.InstanceConnection;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;

/**
 *
 * @author Ryan
 */
public class FlowGraphWriter {

    private final FlowGraph graph;
    //editor is needed to retrieve positions and models of draggable components
    private final Editor editor;

    public FlowGraphWriter(FlowGraph graph, Editor editor) {
        this.graph = graph;
        this.editor = editor;
    }

    public String writeToString() {
        FlowExporterSPI exporter = FlowIO.JAXBExporter();
        FlowDTO flow = constructFlowDTO();

        return exporter.exportToString(flow);
    }

    public File writeToFile() {
        FlowExporterSPI exporter = FlowIO.JAXBExporter();
        FlowDTO flow = constructFlowDTO();

        //use JFileChooser to get a file object
        JFileChooser chooser = new JFileChooser();
        int returnValue = chooser.showSaveDialog(null);
        File fileToSaveTo = null;
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            fileToSaveTo = chooser.getSelectedFile();
        } else {
            fileToSaveTo = new File("Temp.flow");
        }

        return exporter.exportToFile(flow, fileToSaveTo);
    }

    public void writeToFile(File file) {
        //use JAXBExporter for now
        FlowExporterSPI exporter = FlowIO.JAXBExporter();
        FlowDTO flow = constructFlowDTO();

        exporter.exportToFile(flow, file);
    }

    private InstanceConnection[] processConnections(AbstractGraphNode node) {

        ArrayList<InstanceConnection> connections = new ArrayList<InstanceConnection>();


        for (Object _node : node.getAdjacentNodes().entrySet()) {
            AbstractGraphNode adjacentNode = ((Map.Entry<String, AbstractGraphNode>) _node).getValue();
            String forParameter = ((Map.Entry<String, AbstractGraphNode>) _node).getKey();

            //get ID minus the :link
            String toID = adjacentNode.getID();

            //do something about the link id
            String linkID = adjacentNode.getKeyForNode(node);

            //create InstanceConnection
            InstanceConnection connection = new InstanceConnection();
            connection.setTo(toID + ":" + linkID);
            connection.setForParameter(forParameter);

            //add InstanceConnection to list
            connections.add(connection);

        }


        return connections.toArray(new InstanceConnection[]{});
    }

    private String positionToString(DraggableComponent component) {
        int x = component.getX();
        int y = component.getY();

        return x + "," + y;
    }

    private FlowDTO constructFlowDTO() {
        //construct FlowDTO from graph nodes
        FlowDTO flow = new FlowDTO();
        if (graph.getNumberOfNodes() == 0) {
            flow.setInstances(DraggableInstances.emptyList());
        } else {
            DraggableInstances instances = new DraggableInstances();
            List<DraggableInstance> _instances = new ArrayList<DraggableInstance>();
            //for every key in graph (representing IDs)
            for (String nodeID : graph.keyValues()) {

                //get component from editor
                DraggableComponent component = editor.getDraggableWithID(nodeID);

                //get position from component
                String position = positionToString(component);

                //get model from component
                DraggableComponentModel model = component.getModel();

                //process connections
                InstanceConnection[] connections = processConnections(graph.getNode(nodeID));

                //construct DraggableInstance
                DraggableInstance di = new DraggableInstance();
                di.setID(nodeID);
                di.setPosition(position);
                di.setModel(model.getClassName());
                di.setConnections(connections);

                //add instance to list
                _instances.add(di);

            }
            instances.setInstances(_instances);
            flow.setInstances(instances);
        }

        return flow;
    }
}
