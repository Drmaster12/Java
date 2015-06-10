import java.awt.*;

/** A class to store an x, y, and radius, plus a draw method.
 *
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class SimpleCircle {
  private int x, y, radius;

  public SimpleCircle(int x, int y, int radius) {
    setX(x);
    setY(y);
    setRadius(radius);
  }

  /** Given a Graphics, draw the SimpleCircle
   *  centered around its current position.
   */

  public void draw(Graphics g) {
    g.fillOval(x - radius, y - radius,
               radius * 2, radius * 2);
  }

  public int getX() { return(x); }

  public void setX(int x) { this.x = x; }

  public int getY() { return(y); }

  public void setY(int y) { this.y = y; }

  public int getRadius() { return(radius); }

  public void setRadius(int radius) {
    this.radius = radius;
  }
}