import java.applet.*;
import java.awt.*;

/** Draws over top of the image.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class DrawBill3 extends Applet {
  private Image bill;
  private AudioClip laughter;

  @Override
  public void init() {
    bill = getImage(getCodeBase(),
                    "images/gates-blue-screen.jpg");
    laughter = getAudioClip(getCodeBase(),
                            "images/laughter.au");
  }

  @Override
  public void paint(Graphics g) {
    g.drawImage(bill, 0, 0, this);
    g.fillRect(455, 212, 50, 5);
    laughter.play();
  }
}