/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.missouri.isocial.foundation.simple.SimpleAnnotation;
import edu.missouri.isocial.foundation.ScannedClassLoader;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ryan
 */
public class ScannedClassLoaderTest {
    
    private ScannedClassLoader loader;
    public ScannedClassLoaderTest() {
        loader = ScannedClassLoader.INSTANCE();
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testDiscovery() {

        //arrange
        loader = ScannedClassLoader.INSTANCE();
        
        //act
        Set<String> classnames = loader.getClasses(SimpleAnnotation.class);
        System.out.println("LENGTH OF SET: "+classnames.size());
        String[] array = new String[1];
        classnames.toArray(array);
        
        //assert
        assertTrue(array[0].contains("SimpleAnnotatedClass"));
        
    }
    
    
}