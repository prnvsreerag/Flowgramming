/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.io;

import edu.missouri.isocial.foundation.xml.FlowDTO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Ryan
 */
public class JAXBFlowImporter implements FlowImporterSPI {

    private JAXBContext context;
    private Unmarshaller unmarshaller;

    public JAXBFlowImporter() {
        try {
            context = JAXBContext.newInstance(edu.missouri.isocial.foundation.xml.FlowDTO.class);
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBFlowImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Object importFromString(String s) {
        Object obj = null;
        try {
            obj = unmarshaller.unmarshal(new ByteArrayInputStream(s.getBytes()));
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBFlowImporter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return obj;
        }
    }

    @Override
    public FlowDTO importFromFile(File f) {

        FlowDTO obj = null;
        try {
            obj = (FlowDTO) unmarshaller.unmarshal(f);
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBFlowImporter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return obj;
        }

    }
}
