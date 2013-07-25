/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Ryan
 */
public class JAXBFlowExporter implements FlowExporterSPI {

    private JAXBContext context;
    private Marshaller marshaller;

    public JAXBFlowExporter() {
        try {
            context = JAXBContext.newInstance(edu.missouri.isocial.foundation.xml.FlowDTO.class);
            marshaller = context.createMarshaller();
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBFlowExporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String exportToString(Object obj) {
        String output = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(obj, baos);
            output = baos.toString();
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBFlowExporter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return output;
        }
    }


    @Override
    public File exportToFile(Object obj, File output) {
        try {

            marshaller.marshal(obj, output);

        } catch (JAXBException ex) {
            Logger.getLogger(JAXBFlowExporter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return output;
        }
    }
}
