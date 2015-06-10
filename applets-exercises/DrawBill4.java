import java.applet.*;
import java.awt.*;

/** Uses an HTML parameter to determine whether or not to draw the mustache.
 *  Handles missing or misspelled parameters.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class DrawBill4 extends Applet {
  private Image bill;
  private AudioClip laughter;
  private boolean isUsingMustache = false;

  @Override
  public void init() {
    bill = getImage(getCodeBase(),
                    "images/gates-blue-screen.jpg");
    laughter = getAudioClip(getCodeBase(),
                            "images/laughter.au");
    String mustacheFlag = getParameter("MUSTACHE");
    if ((mustacheFlag != null) &&
        (mustacheFlag.equals("YES"))) {
      isUsingMustache = true;
    }
  }

  @Override
  public void paint(Graphics g) {
    g.drawImage(bill, 0, 0, this);
    if (isUsingMustache) {
      g.fillRect(455, 212, 50, 5);
    }
    laughter.play();
  }
}