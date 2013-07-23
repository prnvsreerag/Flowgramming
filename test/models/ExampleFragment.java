/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Ryan
 */
@XmlRootElement(name = "Fragment")
@XmlType(propOrder = {"module", "instances"})
public class ExampleFragment {

    @XmlElement(name = "uses-module")
    public UsesModule[] getModule() {
        return new UsesModule[]{UsesModule.withName("core"),
            UsesModule.withName("utils"),
            UsesModule.withName("lang")};
    }

    @XmlElement
    public DraggableInstances getInstances() {
        return DraggableInstances.defaultInstance();

    }
}
