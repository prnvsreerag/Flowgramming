/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ryan
 */
public class DraggableIDInConnectionTest {

    public DraggableIDInConnectionTest() {
    }

    @Test
    public void nothing() {
        assertTrue(true);
    }

    @Test
    public void shouldSplitStringCorrectly() {
        String input = "#Float_0:output";

        String expectedDraggableID = "Float_0";
        String expectedLinkID = "output";

        input = input.replace("#", "");
        String[] tokens = input.split(":");

        assertTrue(expectedDraggableID.equals(tokens[0]));
        assertTrue(expectedLinkID.equals(tokens[1]));

    }
}