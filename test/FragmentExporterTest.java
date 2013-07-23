/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.missouri.isocial.foundation.io.FragmentExporterSPI;
import java.io.File;
import models.ExampleFragment;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 *
 * @author Ryan
 */
public class FragmentExporterTest {

    private FragmentExporterSPI exporter;
    private String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Fragment><uses-module with-name=\"core\"/><uses-module with-name=\"utils\"/><uses-module with-name=\"lang\"/><instances><Instance with-position=\"0,0\" of=\"TestClass\" with-id=\"TestClass_0\"><Connection to=\"#Float_2\" forParameter=\"X\"/></Instance></instances></Fragment>\n";

    public FragmentExporterTest() {
    }

    @Test
    public void nothing() {
        assertTrue(true);
    }

    @Test
    public void shouldExportToString() {
        exporter = mock(FragmentExporterSPI.class);
        ExampleFragment ef = new ExampleFragment();
        when(exporter.exportToString(ef)).thenReturn(s);

        String output = exporter.exportToString(ef);

        verify(exporter).exportToString(ef);
    }

    @Test
    public void shouldExportToFile() {
        exporter = mock(FragmentExporterSPI.class);
        ExampleFragment ef = new ExampleFragment();
        when(exporter.exportToFile(ef)).thenReturn(new File("test"));

        exporter.exportToFile(ef);

        verify(exporter).exportToFile(ef);
    }
    
}