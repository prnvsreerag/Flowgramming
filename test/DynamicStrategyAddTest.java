/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.missouri.isocial.foundation.ScannedClassLoader;
import edu.missouri.isocial.foundation.annotations.Strategy;
import edu.missouri.isocial.foundation.utils.EasyCompiler;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class DynamicStrategyAddTest {

    private static final String code = ""
            + "import edu.missouri.isocial.foundation.annotations.Strategy;\n"
            + "\n"
            + "@Strategy\n"
            + "public class MyObject { \n"
            + "     private final int x;\n"
            + "     public MyObject(int x) {\n"
            + "         this.x = x;\n"
            + "     }\n"
            + "public static void main(String args[]) {\n"
            + "     System.out.println(\"WEEEEE\");\n"
            + "}\n"
            + "}";

    public DynamicStrategyAddTest() {
    }

// TODO add test methods here.
// The methods must be annotated with annotation @Test. For example:
//
//    @Test
    public void shouldBeAbleToInstantiateClass() {
        //arrange
        EasyCompiler compiler = new EasyCompiler();
        compiler.addSourceCode("MyObject", code);
        compiler.compileClasses();

        File file = new File("MyObject.class");
        assertTrue(file.exists());

        String pathName = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(File.separator));
        System.out.println("PARENT: " + pathName);
        File path = new File(pathName);
        try {
            ScannedClassLoader.INSTANCE().addURL(path.toURI().toURL());
        } catch (MalformedURLException ex) {
            Logger.getLogger(DynamicStrategyAddTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        Class c = null;
        try {
            c = ScannedClassLoader.INSTANCE().loadClass("MyObject");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DynamicStrategyAddTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(c);
        Constructor[] consts = c.getConstructors();

        assertTrue(consts.length > 0);
        System.out.println("# OF CONSTRUCTORS: " + consts.length);
        Constructor constructor = consts[0];

        assertTrue(constructor.getParameterTypes().length == 1);
        System.out.println(constructor.getParameterTypes()[0]);
//       c.newInstance(this);

        //act
        Object obj = null;
        try {
            obj = constructor.newInstance(10);

        } catch (InstantiationException ex) {
            Logger.getLogger(DynamicStrategyAddTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DynamicStrategyAddTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(DynamicStrategyAddTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(DynamicStrategyAddTest.class.getName()).log(Level.SEVERE, null, ex);

        }

        //assert
        assertNotNull(obj);
        Set<String> names = ScannedClassLoader.INSTANCE().getClasses(Strategy.class);
        assertTrue(names.contains(c.getName()));
    }

    @Test
    public void hello() {
        //arrange
        EasyCompiler compiler = new EasyCompiler();
        compiler.addSourceCode("MyObject", code);

        File file = null;
        //act
        try {
            file = compiler.compileClassesIntoJar("TestStrategy.jar", "MyObject");

        } catch (IOException ex) {
            Logger.getLogger(DynamicStrategyAddTest.class.getName()).log(Level.SEVERE, null, ex);
        }


        //assert
        assertNotNull(file);
        assertTrue(file.exists());
        

    }

    private File makeJar(String name, String mainClass) {
        //arrange
        EasyCompiler compiler = new EasyCompiler();
        compiler.addSourceCode("MyObject", code);

        File file = null;
        //act
        try {
            file = compiler.compileClassesIntoJar(name, mainClass);

        } catch (IOException ex) {
            Logger.getLogger(DynamicStrategyAddTest.class.getName()).log(Level.SEVERE, null, ex);
        }


        //assert
        assertNotNull(file);
        assertTrue(file.exists());
        return file;
    }

    @Test
    public void testClassLoading() {
        //arrange
        File jar = makeJar("JagWireOutput.jar", "MyObject");
        ScannedClassLoader loader = ScannedClassLoader.INSTANCE();
        try {
            //act
            System.out.println("ADDING JAR: " + jar.toURI().toURL().toString());
            loader.addURL(parentDirectory(jar).toURI().toURL());
        } catch (MalformedURLException ex) {
            Logger.getLogger(DynamicStrategyAddTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        //assert
        Set<String> names = loader.getClasses(Strategy.class);
        assertTrue(names.size() >= 1);

        boolean good = false;
        for (String name : names) {
            if (name.contains("MyObject")) {
                System.out.println("WE GOT ONE!");
                good = true;
            }
        }
        assertTrue(good);

    }
    
    public File parentDirectory(File file) {
        int firstIndex = 0;
       
        int lastIndex = file.getAbsolutePath().lastIndexOf(File.separator);
        System.out.println("PATH: "+file.getAbsolutePath().substring(firstIndex, lastIndex));
        return new File(file.getAbsolutePath().substring(firstIndex, lastIndex));
        
        
    }
}