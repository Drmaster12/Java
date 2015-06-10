package coreservlets;

import javax.swing.*;
import java.awt.*;

/** A JPanel that combines a JLabel and a JTextField. 
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class JLabeledTextField extends JPanel {
  private JLabel label;
  private JTextField textField;

  public JLabeledTextField(String labelString, 
                           String textFieldString,
			                  LayoutManager layout) {
    super();
    setLayout(layout);
    label = new JLabel(labelString);
    textField = new JTextField(textFieldString);
    add(label);
    add(textField);
  }
 
  public JLabeledTextField(String labelString, 
                           String textFieldString) {
    this(labelString, textFieldString, 
         new FlowLayout(FlowLayout.LEFT));
  }

  public JLabel getJLabel() {
    return(label); 
  }

  public JTextField getJTextField() { 
    return(textField);
  }

  public String getText() {
    return(getJTextField().getText());
  }

  public void setText(String textFieldString) {
    getJTextField().setText(textFieldString);
  }

  public void setFonts(Font f) {
    getJLabel().setFont(f);
    getJTextField().setFont(f);
  }

  @Override
  public void setEnabled(boolean status) {
   getJLabel().setEnabled(status);
   getJTextField().setEnabled(status);
   super.setEnabled(status);
  }
}
