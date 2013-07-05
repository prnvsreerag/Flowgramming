/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.basic.components.variables;

import edu.missouri.isocial.basic.components.variables.VectorDataNode.Vector3f;
import edu.missouri.isocial.foundation.DataGraphNode;

/**
 *
 * @author Ryan
 */
public class VectorDataNode extends DataGraphNode<Vector3f> {

    public VectorDataNode(String ID) {
        super(ID);
    }


    @Override
    public Vector3f getValue() {
        float x = retrieve("x", Float.class);
        float y = retrieve("y", Float.class);
        float z = retrieve("z", Float.class);

        return new Vector3f(x, y, z);
    }

    class Vector3f {

        float x;
        float y;
        float z;

        Vector3f(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
