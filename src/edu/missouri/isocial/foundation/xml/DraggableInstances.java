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
    private List<DraggableInstance> values;

    public DraggableInstances() {
        this.values = new ArrayList<DraggableInstance>();
    }
    public void setInstances(List<DraggableInstance> instances) {
        this.values.clear();
        this.values.addAll(instances);
    }

    @XmlElement(name = "Instance")
    public List<DraggableInstance> getInstances() {


        return values;
    }

    public static DraggableInstances defaultInstance() {

        DraggableInstances vs = new DraggableInstances();
        List<DraggableInstance> l = new ArrayList<DraggableInstance>();

        l.add(new DraggableInstance());
        vs.setInstances(l);
        return vs;
    }

    public static DraggableInstances emptyList() {
        return new DraggableInstances();
    }

}
