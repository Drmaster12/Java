package coreservlets;

import java.awt.*;
import javax.swing.*;

/** Tiny example showing the main difference in using 
 *  JFrame instead of Frame: using the content pane
 *  and getting the Java look and feel by default
 *  instead of the native look and feel.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class JFrameExample extends JFrame {
  public static void main(String[] args) {
    JFrame frame = new JFrameExample("This is a test");
    frame.setVisible(true);
  }
  
  public JFrameExample(String title) {
    super(title);
    WindowUtilities.setNimbusLookAndFeel();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(300, 100);
    Container content = getContentPane();
    content.setBackground(Color.WHITE);
    content.setLayout(new FlowLayout()); 
    content.add(new JButton("Button 1"));
    content.add(new JButton("Button 2"));
    content.add(new JButton("Button 3"));
  }
}