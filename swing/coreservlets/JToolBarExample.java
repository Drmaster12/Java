package coreservlets;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** Small example showing basic use of JToolBar. 
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class JToolBarExample extends JFrame
                             implements ItemListener {
  private BrowserToolBar toolbar;
  private JCheckBox labelBox;

  public static void main(String[] args) {
    new JToolBarExample();
  }

  public JToolBarExample() {
    super("JToolBar Example");
    WindowUtilities.setNimbusLookAndFeel();
    addWindowListener(new ExitListener());
    Container content = getContentPane();
    content.setBackground(Color.white);
    
    JPanel panel = new JPanel(new BorderLayout());
    labelBox = new JCheckBox("Show Text Labels?");
    labelBox.setHorizontalAlignment(SwingConstants.CENTER);
    labelBox.addItemListener(this);
    panel.add(new JTextArea(10,30), BorderLayout.CENTER);
    panel.add(labelBox, BorderLayout.SOUTH);

    toolbar = new BrowserToolBar();
    content.add(toolbar, BorderLayout.NORTH);
    content.add(panel, BorderLayout.CENTER);

    pack();
    setVisible(true);
  }

  public void itemStateChanged(ItemEvent event) {
    toolbar.setTextLabels(labelBox.isSelected());
    pack();
  }
}