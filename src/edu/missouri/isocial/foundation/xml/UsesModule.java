/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ryan
 */
@XmlRootElement(name = "uses-module")
public class UsesModule {
    private String name = "core";

    @XmlAttribute(name = "with-name")
    public String getName() {
        return name;
    }

    //@XmlTransient
    public static UsesModule withName(String name) {
        UsesModule um = new UsesModule();
        um.setName(name);

        return um;
    }

    public void setName(String name) {
        this.name = name;
    }
}
