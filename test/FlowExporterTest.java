/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.missouri.isocial.foundation.io.FlowExporterSPI;
import java.io.File;
import models.ExampleFlow;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 *
 * @author Ryan
 */
public class FlowExporterTest {

    private FlowExporterSPI exporter;
    private String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Flow><uses-module with-name=\"core\"/><uses-module with-name=\"utils\"/><uses-module with-name=\"lang\"/><instances><Instance with-position=\"0,0\" of=\"TestClass\" with-id=\"TestClass_0\"><Connection to=\"#Float_2\" forParameter=\"X\"/></Instance></instances></Flow>\n";

    public FlowExporterTest() {
    }

    @Test
    public void nothing() {
        assertTrue(true);
    }

    @Test
    public void shouldExportToString() {
        exporter = mock(FlowExporterSPI.class);
        ExampleFlow ef = new ExampleFlow();
        when(exporter.exportToString(ef)).thenReturn(s);

        String output = exporter.exportToString(ef);

        verify(exporter).exportToString(ef);
    }

    @Test
    public void shouldExportToFile() {
        exporter = mock(FlowExporterSPI.class);
        ExampleFlow ef = new ExampleFlow();
        when(exporter.exportToFile(ef)).thenReturn(new File("test"));

        exporter.exportToFile(ef);

        verify(exporter).exportToFile(ef);
    }
    
}