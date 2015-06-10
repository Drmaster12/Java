import java.awt.*;
import java.awt.event.*;
import java.io.*;

/** A Panel that draws circles centered wherever the user clicks
 *  the mouse. Uses a null layout manager.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class CirclePanel extends Panel {
  private class ClickAdapter extends MouseAdapter
                             implements Serializable {
    @Override
    public void mousePressed(MouseEvent event) {
      BetterCircle circle = new BetterCircle(Color.BLACK, 25);
      add(circle);
      circle.setCenter(event.getX(), event.getY());
    }
  }

  public CirclePanel() {
    setLayout(null);
    addMouseListener(new ClickAdapter());
  }
}