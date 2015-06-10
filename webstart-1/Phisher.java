import java.awt.*;
import javax.swing.*;

/** From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class Phisher extends JFrame {
  public Phisher(String company) {
    WindowUtilities.setNativeLookAndFeel();
    addWindowListener(new ExitListener());
    Container content = getContentPane();
    String title = company + " Security Verification";
    setTitle(title);
    String imageURL =
      "http://images.encarta.msn.com/xrefmedia/" +
      "sharemed/targets/images/pho/000a5/000a5038.jpg";
    String labelText =
      "<html><CENTER><H1>" + title + "</H1>" +
      "<IMG SRC=" + imageURL + "><BR>" +
      "<H2>Your " + company + " account may have been " +
      "compromised.<BR>To avoid cancellation, please " +
      "reenter your account information.<BR>Sorry for " +
      "the inconvenience, but security is our " +
      "priority.</H2>";
    JLabel label = new JLabel(labelText);
    content.add(label, BorderLayout.NORTH);
    JPanel accountInfoPanel = new JPanel();
    accountInfoPanel.setLayout(new GridLayout(4, 1));
    accountInfoPanel.add
      (new LabeledTextField("Account name:"));
    accountInfoPanel.add
      (new LabeledTextField("Password:"));
    accountInfoPanel.add
      (new LabeledTextField("Credit card:"));
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(new JButton("Confirm Account"));
    accountInfoPanel.add(buttonPanel);
    content.add(accountInfoPanel, BorderLayout.CENTER);
    pack();
    setVisible(true);
  }
}