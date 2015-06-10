import java.awt.*;
import java.applet.Applet;

/** Position circles down the diagonal so that their borders
 *  just touch. Illustrates that Java lightweight
 *  components can be partially transparent.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class BetterCircleTest extends Applet {
  @Override
  public void init() {
    setBackground(Color.LIGHT_GRAY);
    setLayout(null);
    BetterCircle circle;
    int radius = getSize().width/6;
    int deltaX = round(2.0 * radius / Math.sqrt(2.0));
    for (int x=radius; x<6*radius; x=x+deltaX) {
      circle = new BetterCircle(Color.BLACK, radius);
      add(circle);
      circle.setCenter(x, x);
    }
  }

  private int round(double num) {
    return((int)Math.round(num));
  }
}