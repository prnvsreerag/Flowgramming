/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ryan
 */
//@XmlRootElement(name = "Instance")
public class DraggableInstance {

    @XmlAttribute(name = "of")
    public String getModel() {
        return "TestClass";
    }

    @XmlAttribute(name = "with-id")
    public String getID() {
        return "TestClass_0";
    }

    @XmlAttribute(name = "with-position")
    public String getPosition() {
        return "0,0";
    }

    @XmlElement(name = "Connection")
    public InstanceConnection[] getConnection() {
        return new InstanceConnection[]{InstanceConnection.defaultInstance()};
    }
}
