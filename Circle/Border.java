import java.applet.Applet;
import java.awt.*;

/** An example of BorderLayout.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class Border extends Applet {
  @Override
  public void init() {
    setLayout(new BorderLayout());
    SimpleWhiteboard swb= new SimpleWhiteboard();
    add(new Button("North Wing"), BorderLayout.NORTH);
    add(new Button("South Wing"), BorderLayout.SOUTH);
    add(new Button("East Wing"), BorderLayout.EAST);
    add(new Button("West Wing"), BorderLayout.WEST);
    add(swb, BorderLayout.CENTER);
  }
  }