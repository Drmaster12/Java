import java.awt.*;
import java.awt.event.*;

/** A Frame that uses the Confirm dialog to verify that
 *  users really want to quit.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class ConfirmTest extends Frame {
  public static void main(String[] args) {
    new ConfirmTest();
  }

  public ConfirmTest() {
    super("Confirming QUIT");
    setSize(300, 200);
    addWindowListener(new ConfirmListener());
    setVisible(true);
  }

  public ConfirmTest(String title) {
    super(title);
  }

  private class ConfirmListener extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent event) {
      new Confirm(ConfirmTest.this);
    }
  }
}