import java.awt.*;

/** An improved variation of the Circle class that uses
 *  lightweight components instead of Canvas.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class BetterCircle extends Component {
  private Dimension preferredDimension;
  private int width, height;

  public BetterCircle(Color foreground, int radius) {
    setForeground(foreground);
    width = 2*radius;
    height = 2*radius;
    preferredDimension = new Dimension(width, height);
    setSize(preferredDimension);
  }

  @Override
  public void paint(Graphics g) {
    g.setColor(getForeground());
    g.fillOval(0, 0, width, height);
  }

  public void setCenter(int x, int y) {
    setLocation(x - width/2, y - height/2);
  }

  /** Report the original size as the preferred size.
   *  That way, the BetterCircle doesn't get
   *  shrunk by layout managers.
   */

  @Override
  public Dimension getPreferredSize() {
    return(preferredDimension);
  }

  /** Report same thing for minimum size as
   *  preferred size.
   */

  @Override
  public Dimension getMinimumSize() {
    return(preferredDimension);
  }
}