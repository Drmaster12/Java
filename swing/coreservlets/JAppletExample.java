package coreservlets;

import java.awt.*;
import javax.swing.*;

/** Tiny example showing the main differences in using 
 *  JApplet instead of Applet: using the content pane,
 *  getting Java (Metal) look and feel by default, and
 *  having BorderLayout be the default instead of FlowLayout.
 *
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */
 
public class JAppletExample extends JApplet {
  @Override
  public void init() {
    WindowUtilities.setNativeLookAndFeel();
    Container content = getContentPane();
    content.setBackground(Color.WHITE);
    content.setLayout(new FlowLayout()); 
    content.add(new JButton("Button 1"));
    content.add(new JButton("Button 2"));
    content.add(new JButton("Button 3"));
  }
}