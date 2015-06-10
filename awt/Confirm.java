import java.awt.*;
import java.awt.event.*;

/** A modal dialog box with two buttons: Yes and No.
 *  Clicking Yes exits Java. Clicking No exits the
 *  dialog. Used for confirmed quits from frames.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class Confirm extends Dialog implements ActionListener {
  private Button yes, no;

  public Confirm(Frame parent) {
    super(parent, "Confirmation", true);
    setLayout(new FlowLayout());
    add(new Label("Really quit?"));
    yes = new Button("Yes");
    yes.addActionListener(this);
    no  = new Button("No");
    no.addActionListener(this);
    add(yes);
    add(no);
    pack();
    setVisible(true);
  }

  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == yes) {
      System.exit(0);
    } else {
      dispose();
    }
  }
}