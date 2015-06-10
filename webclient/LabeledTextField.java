import java.awt.*; // For FlowLayout, Font.
import javax.swing.*;

/** A TextField with an associated Label.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages Volume II
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://volume2.coreservlets.com/.
 *  (C) 2007 Marty Hall, Larry Brown, and Yaakov Chaikin;
 *  may be freely used or adapted.
 */

public class LabeledTextField extends JPanel {
  private JLabel label;
  private JTextField textField;

  public LabeledTextField(String labelString,
                          Font labelFont,
                          int textFieldSize,
                          Font textFont) {
    setLayout(new FlowLayout(FlowLayout.LEFT));
    label = new JLabel(labelString, JLabel.RIGHT);
    if (labelFont != null) {
      label.setFont(labelFont);
    }
    add(label);
    textField = new JTextField(textFieldSize);
    if (textFont != null) {
      textField.setFont(textFont);
    }
    add(textField);
  }

  public LabeledTextField(String labelString,
                          String textFieldString) {
    this(labelString, null, textFieldString,
         textFieldString.length(), null);
  }

  public LabeledTextField(String labelString,
                          int textFieldSize) {
    this(labelString, null, textFieldSize, null);
  }

  public LabeledTextField(String labelString,
                          Font labelFont,
                          String textFieldString,
                          int textFieldSize,
                          Font textFont) {
    this(labelString, labelFont,
         textFieldSize, textFont);
    textField.setText(textFieldString);
  }

  /** The Label at the left side of the LabeledTextField.
   *  To manipulate the Label, do:
   *  <PRE>
   *    LabeledTextField ltf = new LabeledTextField(...);
   *    ltf.getLabel().someLabelMethod(...);
   *  </PRE>
   */

  public JLabel getLabel() {
    return(label);
  }

  /** The TextField at the right side of the
   *  LabeledTextField.
   */

  public JTextField getTextField() {
    return(textField);
  }

  public void setText(String textFieldString) {
    textField.setText(textFieldString);
  }
}