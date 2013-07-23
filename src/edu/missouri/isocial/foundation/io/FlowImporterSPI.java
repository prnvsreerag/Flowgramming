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
public interface FlowImporterSPI {

    public Object importFromString(String s);

    public Object importFromFile(File f);
}
