import javax.swing.*;

/** From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class LabeledTextField extends JPanel {
  public LabeledTextField(String label) {
    label =
      "<html><H3>" + label + "</H3></html>";
    add(new JLabel(label));
    add(new JTextField(15));
  }
}