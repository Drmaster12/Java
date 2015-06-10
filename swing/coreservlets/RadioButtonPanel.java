package coreservlets;

import javax.swing.*;
import java.awt.*;

/** A simple way to group several radio buttons in a single row, 
 *  with a label on the left.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class RadioButtonPanel extends JPanel {
  public RadioButtonPanel(String labelString, 
                          JRadioButton[] radioButtons,  
			                 ButtonGroup buttonGroup) {
    setLayout(new FlowLayout(FlowLayout.LEFT));
    add(new JLabel(labelString));
    for(int i=0; i<radioButtons.length; i++) {
      buttonGroup.add(radioButtons[i]);
      add(radioButtons[i]);
    }
  }

  @Override
  public void setEnabled(boolean state) {
    Component[] components = getComponents();
    for(int i=0; i<components.length; i++)
      components[i].setEnabled(state);
  }
}
