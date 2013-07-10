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
        float x = retrieve("X", Float.class);
        float y = retrieve("Y", Float.class);
        float z = retrieve("Z", Float.class);

        return new Vector3f(x, y, z);
    }

    @Override
    public void fromString(String input) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <K> K defaultInputValue(Class<K> clazz) {

        if (clazz.getName().contains("Float")) {
            return (K) new Float(0);
        } else {
            return null;
        }
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

        @Override
        public String toString() {
            return "{" + x + "," + y + "," + z + "}";

        }
    }
}
