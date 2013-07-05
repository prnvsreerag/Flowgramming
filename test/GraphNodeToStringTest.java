/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.missouri.isocial.foundation.AbstractGraphNode;
import edu.missouri.isocial.foundation.components.core.model.DefaultDraggableComponentModel;
import edu.missouri.isocial.foundation.components.core.model.DraggableComponentModel;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Ryan
 */
public class GraphNodeToStringTest {

    @Test
    public void nothing() {
        assertTrue(true);
    }

    @Test
    public void shouldSerializeToCorrectFormat() {
        //given a node of with model type Example and issue 3
        GraphNode node = new GraphNode(new DefaultDraggableComponentModel());


        //when the node is serialized and a string is returned
        String id = node.getSerializedID();

        //then the string should equal Example_3
        assertTrue(id.equals("example_0"));
    }

    @Test
    public void shouldDeserializeToCorrectNameAndIssue() {
        //given a string of format: "example_0"
        String serializedNode = "example_0";

        //when the string is deserialized, and a node is returned
        GraphNode node = GraphNode.fromString(serializedNode);

        //then the node's objName should equal "example"
        //and the issue should equal 0
        assertTrue(node.getValue().getObjName().equals("example"));
        assertTrue(node.getValue().getIssue() == 0);
    }

    @Test
    public void shouldDeserializeToCorrectNameIssueAndClass() {
        //given a string of format: example_0(Class=name)
        String serializedNode = "example_0(class=name)";

        //when the string is deserialized, and node is returned
        GraphNode node = GraphNode.fromString(serializedNode);

        //then the node's objName should equal "example",
        //and the issue should equal 0,
        //and the the class should equal "name"
        assertTrue(node.getValue().getObjName().equals("example"));
        assertTrue(node.getValue().getIssue() == 0);
        assertTrue(node.getValue().getClassName().equals("name"));

    }

    @Test
    public void shouldDeserializeToCorrectNameIssueClassWithLink() {
        //given a string of format: example_0(Class=name):output-
        String serializedNode = "example_0(class=name):output";

        //when the string is deserialized and the node is returned
        GraphNode node = GraphNode.fromString(serializedNode);

        //then the node's fields should be correct
        assertTrue(node.getValue().getObjName().equals("example"));
        assertTrue(node.getValue().getIssue() == 0);
        assertTrue(node.getValue().getClassName().equals("name"));

    }

    static class GraphNode extends AbstractGraphNode<DraggableComponentModel> {

        public GraphNode(DraggableComponentModel model) {
            super(model.getObjName() + model.getIssue());
        }

        @Override
        public DraggableComponentModel getValue() {
            return null;
        }
        public String getSerializedID() {
            return getValue().getObjName() + "_" + getValue().getIssue();
        }

        public static GraphNode fromString(String serializedNode) {
            GraphNode node = mock(GraphNode.class);
            DraggableComponentModel dcm = mock(DraggableComponentModel.class);

            String tokens[] = serializedNode.split("_");
            String objName = tokens[0];
            int issue = getIssueFromString(tokens[1]);
            String className = getClassFromString(tokens[1]);

            when(node.getValue()).thenReturn(dcm);
            when(dcm.getObjName()).thenReturn(objName);
            when(dcm.getIssue()).thenReturn(issue);
            when(dcm.getClassName()).thenReturn(className);
            
            return node;

        }

        private static String getClassFromString(String secondToken) {

            //given string, get substring of ( and )
            int startIndex = secondToken.indexOf("(");
            if (startIndex < 0) {
                //if no class exists, return "null"
                return "null";
            }

            int endIndex = secondToken.indexOf(")");


            String expression = secondToken.substring(startIndex + 1, endIndex);
            return expression.split("=")[1];

        }

        private static int getIssueFromString(String secondToken) {
            int endIndex = secondToken.indexOf("(");
            if (endIndex == -1) {
                return Integer.valueOf(secondToken);
            }
            return Integer.valueOf(secondToken.substring(0, endIndex));
        }
    }
}