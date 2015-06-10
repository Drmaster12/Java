package coreservlets;

import java.awt.*;
import javax.swing.*;

/** A JPanel that displays six JRadioButtons.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class SixChoicePanel extends JPanel {
  public SixChoicePanel(String title, String[] buttonLabels) {
    super(new GridLayout(3, 2));
    setBackground(Color.lightGray);
    setBorder(BorderFactory.createTitledBorder(title));
    ButtonGroup group = new ButtonGroup();
    JRadioButton option;
    int halfLength = buttonLabels.length/2;  // Assumes even length
    for(int i=0; i<halfLength; i++) {
      option = new JRadioButton(buttonLabels[i]);
      group.add(option);
      add(option);
      option = new JRadioButton(buttonLabels[i+halfLength]);
      group.add(option);
      add(option);
    }
  }
}
