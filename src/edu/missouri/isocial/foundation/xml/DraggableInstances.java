/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ryan
 */
@XmlRootElement(name = "Instances")
public class DraggableInstances {

    @XmlElement(name = "Instance")
    public List<DraggableInstance> getInstances() {
        List<DraggableInstance> l = new ArrayList<DraggableInstance>();

        l.add(new DraggableInstance());

        return l;
    }

    public static DraggableInstances defaultInstance() {

        return new DraggableInstances();
    }

}
