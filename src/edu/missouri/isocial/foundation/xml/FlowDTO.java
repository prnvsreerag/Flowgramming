/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.xml;

import edu.missouri.isocial.foundation.AbstractGraphNode;
import edu.missouri.isocial.foundation.GraphView;
import edu.missouri.isocial.foundation.components.core.DraggableComponent;
import edu.missouri.isocial.foundation.components.core.model.DraggableComponentModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public static FlowDTO fromGraphView(GraphView view) {
        FlowDTO flow = new FlowDTO();
        if (view.getKeysOfDraggables().isEmpty()) {
            flow.setInstances(DraggableInstances.emptyList());
        } else {
            DraggableInstances instances = new DraggableInstances();
            List<DraggableInstance> _instances = new ArrayList<DraggableInstance>();
            //for every key in graph (representing IDs)
            for (String nodeID : view.getKeysOfDraggables()) {

                //get component from editor
                DraggableComponent component = view.getDraggableWithID(nodeID);

                //get position from component
                String position = positionToString(component);

                //get model from component
                DraggableComponentModel model = component.getModel();

                //process connections
                InstanceConnection[] connections = processConnections(view.getNodeFromFlowGraph(nodeID));

                //construct DraggableInstance
                DraggableInstance di = new DraggableInstance();
                di.setID(nodeID);
                di.setPosition(position);
                di.setModel(model.getClassName());
                di.setConnections(connections);

                //add instance to list
                _instances.add(di);

            }
            instances.setInstances(_instances);
            flow.setInstances(instances);
        }

        return flow;
    }

    private static String positionToString(DraggableComponent component) {
        int x = component.getX();
        int y = component.getY();

        return x + "," + y;
    }

    private static <T> InstanceConnection[] processConnections(AbstractGraphNode<T> node) {

        ArrayList<InstanceConnection> connections = new ArrayList<InstanceConnection>();

        for (Map.Entry<String, AbstractGraphNode<T>> entry : node.getAdjacentNodes().entrySet()) {
            String forParameter = entry.getKey();
            String toID = entry.getValue().getID();
            String linkID = entry.getValue().getKeyForNode(node);

            InstanceConnection connection = new InstanceConnection();
            connection.setTo(toID + ":" + linkID);
            connection.setForParameter(forParameter);

            connections.add(connection);
        }


        return connections.toArray(new InstanceConnection[]{});
    }
}
