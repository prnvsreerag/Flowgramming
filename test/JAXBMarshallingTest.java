/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import models.ExampleFragment;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ryan
 */
public class JAXBMarshallingTest {
    private JAXBContext context;
    private Unmarshaller unmarshaller;
    private Marshaller marshaller;

    public JAXBMarshallingTest() {
        try {
            context = JAXBContext.newInstance(models.ExampleFragment.class);
            marshaller = context.createMarshaller();
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBMarshallingTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void nothing() {

        assertTrue(true);
    }

    @Test
    public void shouldPrintOutExampleFragment() {
        try {
            File file = new File("test-file");
            marshaller.marshal(new ExampleFragment(), file);

            printFile(file);


        } catch (JAXBException ex) {
            Logger.getLogger(JAXBMarshallingTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }

    @Test
    public void shouldReadExampleFragmentCorrectly() {
        try {
            String input = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Fragment><uses-module with-name=\"core\"/><uses-module with-name=\"utils\"/><uses-module with-name=\"lang\"/><instances><Instance with-position=\"0,0\" of=\"TestClass\" with-id=\"TestClass_0\"><Connection to=\"#Float_2\" forParameter=\"X\"/></Instance></instances></Fragment>\n"
                    + "";
            unmarshaller = context.createUnmarshaller();
            ExampleFragment frag = (ExampleFragment) unmarshaller.unmarshal(new ByteArrayInputStream(input.getBytes()));
            assertTrue(frag.getModule() != null);
            assertTrue(frag.getModule().length == 3);

            assertTrue(frag.getInstances() != null);
            assertTrue(frag.getInstances().getInstances().size() == 1);

            assertTrue(frag.getInstances().getInstances().get(0).getConnection().length == 1);
            assertTrue(frag.getInstances().getInstances().get(0).getConnection()[0].getTo().equals("#Float_2"));

        } catch (JAXBException ex) {
            Logger.getLogger(JAXBMarshallingTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void printFile(File file) {
        BufferedReader reader = null;
        try {
            

            String currentLine;
            reader = new BufferedReader(new FileReader(file));
            while ((currentLine = reader.readLine()) != null) {
                System.out.println(currentLine);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JAXBMarshallingTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JAXBMarshallingTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(JAXBMarshallingTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
}