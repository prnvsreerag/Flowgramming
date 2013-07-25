/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ryan
 */
//@XmlRootElement(name = "Instance")
public class DraggableInstance {
    private String model;
    private String position;
    private InstanceConnection[] connections;
    private String id;

    /**
     * The test class of the model to use to reproduce the draggable view.
     * Typically a subclass of DraggableModelComponent
     *
     * @return A string consisting of a classname
     */
    @XmlAttribute(name = "of")
    public String getModel() {
        return this.model;
    }

    @XmlAttribute(name = "with-id")
    public String getID() {
        return id;
    }

    @XmlAttribute(name = "with-position")
    public String getPosition() {
        return this.position;
    }

    @XmlElement(name = "Connection")
    public InstanceConnection[] getConnection() {
        return this.connections;
    }

    public void setModel(String className) {
        this.model = className;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setConnections(InstanceConnection[] connections) {
        this.connections = connections;
    }

    public void setID(String id) {
        this.id = id;
    }
}
