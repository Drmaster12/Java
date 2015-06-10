import java.applet.Applet;
import java.awt.*;

/** Draws a circle. Illustrates that you can inherit init, since it is a lifecycle method,
 *  not an abstract method.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class DrawCircle extends Applet {
  @Override
  public void paint(Graphics g) {
    g.fillOval(50, 50, 100, 100);
  }
}