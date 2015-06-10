import java.applet.Applet;
import java.awt.*;

/** Eight ungrouped buttons in an Applet using FlowLayout.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class ButtonTest1 extends Applet {
  @Override
  public void init() {
    String[] labelPrefixes = { "Start", "Stop", "Pause",
                               "Resume" };
    for (int i=0; i<4; i++) {
      add(new Button(labelPrefixes[i] + " Thread1"));
    }
    for (int i=0; i<4; i++) {
      add(new Button(labelPrefixes[i] + " Thread2"));
    }
  }
}