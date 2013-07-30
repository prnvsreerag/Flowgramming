/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.io;

import edu.missouri.isocial.foundation.GraphView;
import edu.missouri.isocial.foundation.xml.FlowDTO;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Ryan
 */
public class FlowGraphReader {

    private final GraphView graphView;

    public FlowGraphReader(GraphView graphView) {
        this.graphView = graphView;
    }

    public void readFromFile() {
        JFileChooser chooser = new JFileChooser();
        int returnValue = chooser.showOpenDialog(null);
        File fileToOpen = null;
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            fileToOpen = chooser.getSelectedFile();
        } else {
            return;
        }

        FlowDTO flow = FlowIO.JAXBImporter().importFromFile(fileToOpen);
        FlowDataProcessor processor = new FlowDataProcessor(graphView);
        processor.process(flow);
    }
}
