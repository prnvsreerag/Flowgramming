/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.missouri.isocial.foundation;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import jsyntaxpane.DefaultSyntaxKit;

/**
 *
 * @author Ryan
 */
public class SyntaxTester {

    public SyntaxTester() {
        JFrame f = new JFrame();
        final Container c = f.getContentPane();
        c.setLayout(new BorderLayout());

        DefaultSyntaxKit.initKit();
        final JEditorPane codeEditor = new JEditorPane();
        JScrollPane scrPane = new JScrollPane(codeEditor);
        c.add(scrPane, BorderLayout.CENTER);
        c.doLayout();
        codeEditor.setContentType("text/java");
        codeEditor.setText("public static void main(String args[]) {\n}");

        f.setSize(800, 600);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    public static void main(String args[]) {
        new SyntaxTester();
    }
}
