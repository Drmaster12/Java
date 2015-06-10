import java.applet.*;
import java.awt.*;

/** Draws a picture from a relative URL.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class DrawBill extends Applet {
  private Image bill;

  @Override
  public void init() {
    bill = getImage(getCodeBase(),
                    "images/gates-blue-screen.jpg");
  }

  @Override
  public void paint(Graphics g) {
    g.drawImage(bill, 0, 0, this);
  }
}