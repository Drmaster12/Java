import java.awt.*;
import java.applet.Applet;

/** Insert three circles into an Applet using FlowLayout.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class CircleTest extends Applet {
  @Override
  public void init() {
    setBackground(Color.LIGHT_GRAY);
    add(new Circle(Color.WHITE, 30));
    add(new Circle(Color.GRAY, 40));
    add(new Circle(Color.BLACK, 50));
  }
}