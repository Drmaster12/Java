import java.awt.*;

/** A Frame that you can actually quit. Used as the starting
 *  point for most AWT-based graphical applications.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class CloseableFrame extends Frame {
  public CloseableFrame(String title) {
    super(title);
    addWindowListener(new ExitListener());
  }
}