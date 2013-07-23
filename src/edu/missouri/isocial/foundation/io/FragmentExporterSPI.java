/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.io;

import java.io.File;

/**
 *
 * @author Ryan
 */
public interface FragmentExporterSPI {
    public String exportToString(Object obj);

    public File exportToFile(Object obj);
}
