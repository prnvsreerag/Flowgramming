/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.io;

/**
 *
 * @author Ryan
 */
public class FlowIO {
    public FlowExporterSPI JAXBExporter() {
        return new JAXBFlowExporter();
    }

    public FlowImporterSPI JAXBImporter() {
        return new JAXBFlowImporter();
    }
}
