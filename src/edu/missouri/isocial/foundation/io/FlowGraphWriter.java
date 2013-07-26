/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.io;

import edu.missouri.isocial.foundation.AbstractGraphNode;
import edu.missouri.isocial.foundation.ApplicationContext;
import edu.missouri.isocial.foundation.GraphView;
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
    private final GraphView view;

    public FlowGraphWriter(FlowGraph graph, GraphView view) {
        this.graph = graph;
        this.view = view;
    }

    public String writeToString() {
        FlowExporterSPI exporter = FlowIO.JAXBExporter();
        FlowDTO flow = FlowDTO.fromGraphView(view);//constructFlowDTO();

        return exporter.exportToString(flow);
    }

    public File writeToFile() {
        FlowExporterSPI exporter = FlowIO.JAXBExporter();
        FlowDTO flow = FlowDTO.fromGraphView(view);//constructFlowDTO();

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
        FlowDTO flow = FlowDTO.fromGraphView(view);//constructFlowDTO();

        exporter.exportToFile(flow, file);
    }
}
