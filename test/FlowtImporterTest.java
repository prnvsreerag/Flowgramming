/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.missouri.isocial.foundation.io.FlowImporterSPI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Ryan
 */
public class FlowtImporterTest {

    private FlowImporterSPI importer;
    private String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Flow><uses-module with-name=\"core\"/><uses-module with-name=\"utils\"/><uses-module with-name=\"lang\"/><instances><Instance with-position=\"0,0\" of=\"TestClass\" with-id=\"TestClass_0\"><Connection to=\"#Float_2\" forParameter=\"X\"/></Instance></instances></Flow>\n";

    public FlowtImporterTest() {
    }

    @Test
    public void nothing() {
        assertTrue(true);
    }

    @Test
    public void shouldImportFromString() {
        importer = mock(FlowImporterSPI.class);
        when(importer.importFromString(s)).thenReturn(new Object());

        importer.importFromString(s);

        verify(importer).importFromString(s);
    }

    @Test
    public void shouldImportFromFile() {
        importer = mock(FlowImporterSPI.class);
        when(importer.importFromFile(writeStringToFile())).thenReturn(new Object());

        importer.importFromFile(writeStringToFile());

        verify(importer).importFromFile(writeStringToFile());
    }

    private File writeStringToFile() {
        File f = null;
        PrintWriter writer = null;

        try {
            f = new File("Example.xml");
            writer = new PrintWriter(f);
            writer.write(s);
            return f;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlowtImporterTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
            return f;
        }
    }
}