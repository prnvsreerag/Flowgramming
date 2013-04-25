/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 *
 * @author Ryan
 */
public class EasyCompiler {

    private JavaCompiler compiler;
    private Map<String, String> codes;
    private StandardJavaFileManager fileManager;

    public EasyCompiler() {

        this.codes = new HashMap<String, String>();
        this.compiler = ToolProvider.getSystemJavaCompiler();
        this.fileManager = compiler.getStandardFileManager(null, null, null);
    }

    public void addSourceCode(String classNameWithoutExtension, String javaCode) {
        this.codes.put(classNameWithoutExtension, javaCode);
    }

    public File compileClassesIntoJar(String name, String mainClassName) throws IOException {
        Manifest manifest = new Manifest();
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
        manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, mainClassName);

        JarOutputStream target = new JarOutputStream(new FileOutputStream(name), manifest);

        for (Map.Entry<String, String> entry : codes.entrySet()) {
            writeJarEntry(target, entry.getKey(), entry.getValue());
        }

        target.close();

        return new File(name);
//             return new FileWriter(target);
    }

    private void writeJarEntry(JarOutputStream target, String name, String code) throws IOException {
        JarEntry entry = new JarEntry(name + ".class");
        target.putNextEntry(entry);


        File file = compileClass(name, code);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
//        new FileInputStream(file).
        byte[] buffer = new byte[1024];
        while(true) {
            int count = in.read(buffer);
            if(count == -1) {
                break;
            }
            target.write(buffer, 0, count);
        }
        
        target.closeEntry();
//             target.write(code, off, len);
    }

    public Set<File> compileClasses() {
        Set<File> files = new HashSet<File>();

        for (Map.Entry<String, String> entry : codes.entrySet()) {
            JavaSourceFromString source = new JavaSourceFromString(entry.getKey(), entry.getValue());

            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, Collections.singleton(source));
            task.call();


            files.add(new File(source.getName().replace(".java", ".class")));

        }
        return files;

    }

    public File compileClass(String name, String code) {
        JavaSourceFromString source = new JavaSourceFromString(name, code);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, Collections.singleton(source));
        task.call();

        System.out.println("COMPILED CLASS: "+source.getName().replace(".java", ".class").replace("/", ""));
        return new File(source.getName().replace(".java", ".class").replace("/", ""));

    }

    private static class JavaSourceFromString extends SimpleJavaFileObject {

        private final String code;

        public JavaSourceFromString(String name, String code) {
            super(URI.create("string:///" + name.replace('.', '/') + JavaFileObject.Kind.SOURCE.extension), JavaFileObject.Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }
}
