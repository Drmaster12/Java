import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** An applet that draws a small circle where you click.
 *
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class DrawCircles extends Applet {
  private ArrayList<SimpleCircle> circles;

  @Override
  public void init() {
    circles = new ArrayList<SimpleCircle>();
    addMouseListener(new CircleDrawer());
    setBackground(Color.WHITE);
  }

  /** When you click the mouse, create a SimpleCircle,
   *  put it in the list of circles, and tell the system
   *  to repaint (which calls update, which clears
   *  the screen and calls paint).
   */

  private class CircleDrawer extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent event) {
      circles.add(new SimpleCircle(event.getX(),
                                   event.getY(), 25));
      repaint();
    }
  }

  /** This loops down the available SimpleCircle objects,
   *  drawing each one.
   */

  @Override
  public void paint(Graphics g) {
    for(SimpleCircle circle: circles) {
      circle.draw(g);
    }
  }
}