package coreservlets;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
                
/** Simple example illustrating the use of JColorChooser. 
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class JColorChooserTest extends JFrame
                               implements ActionListener {
  public static void main(String[] args) {
    new JColorChooserTest();
  }

  public JColorChooserTest() {
    super("Using JColorChooser");
    WindowUtilities.setNimbusLookAndFeel();
    addWindowListener(new ExitListener());
    Container content = getContentPane();
    content.setBackground(Color.white);
    content.setLayout(new FlowLayout());
    JButton colorButton
      = new JButton("Choose Background Color");
    colorButton.addActionListener(this);
    content.add(colorButton);
    setSize(300, 100);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Args are parent component, title, initial color
    Color bgColor
      = JColorChooser.showDialog(this,
                                 "Choose Background Color",
                                 getContentPane().getBackground());
    if (bgColor != null)
      getContentPane().setBackground(bgColor);
  }
}
