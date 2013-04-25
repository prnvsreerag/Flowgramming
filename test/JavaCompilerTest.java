
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.missouri.isocial.foundation.ScannedClassLoader;
import edu.missouri.isocial.foundation.utils.EasyCompiler;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
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
public class JavaCompilerTest {

    private static final String clazz = ""
            + "import edu.missouri.isocial.foundation.annotations.Strategy;\n"
            + ""
            + "@Strategy\n"
            + "public class MyObject { "
            + "     private final int x;"
            + "     public MyObject(int x) {"
            + "         this.x = x;"
            + "     }"
            + "}";

    public JavaCompilerTest() {
    }

    @Test
    public void compileJavaFile() {
        JavaSourceFromString source = new JavaSourceFromString("MyObject", clazz);
        assertNotNull(source);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        CompilationTask task = compiler.getTask(null, fileManager, null, null, null, Collections.singleton(source));
        assertTrue(task.call());

    }

    @Test
    public void testMyCompiler() {
        MyCompiler c = new MyCompiler();
        c.addSourceCode("MyObject", clazz);
        c.compileClasses();
        System.out.println("COMPILING!");
        assertTrue(true);
        System.out.println("SUCCESS!");
    }

    @Test
    public void testMyJarWriter() {
        MyCompiler c = new MyCompiler();
        c.addSourceCode("MyObject", clazz);
        try {
            c.compileClassesIntoJar("Test.jar");
        } catch (IOException ex) {
            Logger.getLogger(JavaCompilerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class JavaSourceFromString extends SimpleJavaFileObject {

        private final String code;

        public JavaSourceFromString(String name, String code) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }

    private static class MyCompiler {

        private JavaCompiler compiler;
        private Map<String, String> codes;
        private StandardJavaFileManager fileManager;

        public MyCompiler() {

            this.codes = new HashMap<String, String>();
            this.compiler = ToolProvider.getSystemJavaCompiler();
            this.fileManager = compiler.getStandardFileManager(null, null, null);
        }

        public void addSourceCode(String classNameWithoutExtension, String javaCode) {
            this.codes.put(classNameWithoutExtension, javaCode);
        }

        public File compileClassesIntoJar(String name) throws IOException {
            Manifest manifest = new Manifest();
            manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");

            JarOutputStream target = new JarOutputStream(new FileOutputStream(name), manifest);

            for (Map.Entry<String, String> entry : codes.entrySet()) {
                writeJarEntry(target, entry.getKey(), entry.getValue());
            }

            target.close();

            return null;
//             return new FileWriter(target);
        }

        private void writeJarEntry(JarOutputStream target, String name, String code) throws IOException {
            JarEntry entry = new JarEntry(name + ".class");
            target.putNextEntry(entry);

//             BufferedInputStream in = new BufferedInputStream(new StringReader());

            target.write(code.getBytes());
            target.closeEntry();
//             target.write(code, off, len);
        }

        public void compileClasses() {
            for (Map.Entry<String, String> entry : codes.entrySet()) {
                JavaSourceFromString source = new JavaSourceFromString(entry.getKey(), entry.getValue());
                JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, Collections.singleton(source));
                task.call();
            }
        }
    }
}