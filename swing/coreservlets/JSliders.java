package coreservlets;

import java.awt.*;
import javax.swing.*;
                
/** Simple example illustrating the use of JSliders, especially
 *  the ability to specify tick marks and labels.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class JSliders extends JFrame {
  public static void main(String[] args) {
    new JSliders();
  }

  public JSliders() {
    super("Using JSlider");
    WindowUtilities.setNimbusLookAndFeel();
    addWindowListener(new ExitListener());
    Container content = getContentPane();
    content.setBackground(Color.white);
    
    JSlider slider1 = new JSlider();
    slider1.setBorder(BorderFactory.createTitledBorder
                        ("JSlider without Tick Marks"));
    content.add(slider1, BorderLayout.NORTH);
    
    JSlider slider2 = new JSlider();
    slider2.setBorder(BorderFactory.createTitledBorder
                        ("JSlider with Tick Marks"));
    slider2.setMajorTickSpacing(20);
    slider2.setMinorTickSpacing(5);
    slider2.setPaintTicks(true);
    content.add(slider2, BorderLayout.CENTER);

    JSlider slider3 = new JSlider();
    slider3.setBorder(BorderFactory.createTitledBorder
                        ("JSlider with Tick Marks & Labels"));
    slider3.setMajorTickSpacing(20);
    slider3.setMinorTickSpacing(5);
    slider3.setPaintTicks(true);
    slider3.setPaintLabels(true);
    content.add(slider3, BorderLayout.SOUTH);
    
    pack();
    setVisible(true);
  }
}
