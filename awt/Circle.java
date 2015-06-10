import java.awt.*;

/** A Circle component built using a Canvas.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class Circle extends Canvas {
  private int width, height;

  public Circle(Color foreground, int radius) {
    setForeground(foreground);
    width = 2*radius;
    height = 2*radius;
    setSize(width, height);
  }

  @Override
  public void paint(Graphics g) {
    g.fillOval(0, 0, width, height);
  }

  public void setCenter(int x, int y) {
    setLocation(x - width/2, y - height/2);
  }
}