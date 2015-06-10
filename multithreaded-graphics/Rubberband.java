import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/** Draw "rubberband" rectangles when the user drags
 *  the mouse.
 *
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class Rubberband extends Applet {
  private int startX, startY, lastX, lastY;

  @Override
  public void init() {
    addMouseListener(new RectRecorder());
    addMouseMotionListener(new RectDrawer());
    setBackground(Color.WHITE);
  }

  /** Draw the rectangle, adjusting the x, y, w, h
   *  to correctly accommodate for the opposite corner of the
   *  rubberband box relative to the start position.
   */

  private void drawRectangle(Graphics g, int startX, int startY,
                             int stopX, int stopY ) {
    int x, y, w, h;
    x = Math.min(startX, stopX);
    y = Math.min(startY, stopY);
    w = Math.abs(startX - stopX);
    h = Math.abs(startY - stopY);
    g.drawRect(x, y, w, h);
  }

  private class RectRecorder extends MouseAdapter {

    /** When the user presses the mouse, record the
     *  location of the top-left corner of rectangle.
     */

    @Override
    public void mousePressed(MouseEvent event) {
      startX = event.getX();
      startY = event.getY();
      lastX = startX;
      lastY = startY;
    }

    /** Draw blue rectangle when the user releases
     *  the mouse.
     */

    @Override
    public void mouseReleased(MouseEvent event) {
      Graphics g = getGraphics();
      g.setColor(Color.BLUE);
      drawRectangle(g, startX, startY, lastX, lastY);
    }
  }

  private class RectDrawer extends MouseMotionAdapter {

    /** This draws a rubberband rectangle, from the location
     *  where the mouse was first clicked to the location
     *  where the mouse is dragged.
     */

    @Override
    public void mouseDragged(MouseEvent event) {
      int x = event.getX();
      int y = event.getY();
      Graphics g = getGraphics();
      g.setXORMode(Color.LIGHT_GRAY);
      drawRectangle(g, startX, startY, lastX, lastY);
      drawRectangle(g, startX, startY, x, y);
      lastX = x;
      lastY = y;
    }
  }
}