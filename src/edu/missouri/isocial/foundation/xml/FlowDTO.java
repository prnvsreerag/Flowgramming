/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Ryan
 */
@XmlRootElement(name = "Flow")
@XmlType(propOrder = {"module", "instances"})
public class FlowDTO {

    private UsesModule[] modules;
    private DraggableInstances instances;

    @XmlElement(name = "uses-module")
    public UsesModule[] getModule() {
        return modules;
    }

    @XmlElement
    public DraggableInstances getInstances() {
        return instances;
    }

    public void setModule(UsesModule[] modules) {
        this.modules = modules;
    }

    public void setInstances(DraggableInstances instances) {
        this.instances = instances;
    }
}
