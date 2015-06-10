package coreservlets;

import java.awt.*;
import javax.swing.*;

/** Simple example illustrating the use of JButton, especially
 *  the new constructors that permit you to add an image.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class JButtons extends JFrame {
  public static void main(String[] args) {
    JFrame frame = new JButtons();
    frame.setVisible(true);
  }

  public JButtons() {
    super("Using JButton");
    WindowUtilities.setNimbusLookAndFeel();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Container content = getContentPane();
    content.setBackground(Color.white);
    content.setLayout(new FlowLayout());
    JButton button1 = new JButton("Java");
    content.add(button1);
    ImageIcon cup = new ImageIcon("images/cup.gif");
    JButton button2 = new JButton(cup);
    content.add(button2);
    JButton button3 = new JButton("Java", cup);
    content.add(button3);
    JButton button4 = new JButton("Java", cup);
    button4.setHorizontalTextPosition(SwingConstants.LEFT);
    content.add(button4);
    pack();
  }
}