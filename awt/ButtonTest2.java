import java.applet.Applet;
import java.awt.*;

/** Eight buttons: four each in two panels.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class ButtonTest2 extends Applet {
  @Override
  public void init() {
    String[] labelPrefixes = { "Start", "Stop", "Pause",
                               "Resume" };
    Panel p1 = new Panel();
    for (int i=0; i<4; i++) {
      p1.add(new Button(labelPrefixes[i] + " Thread1"));
    }
    Panel p2 = new Panel();
    for (int i=0; i<4; i++) {
      p2.add(new Button(labelPrefixes[i] + " Thread2"));
    }
    add(p1);
    add(p2);
  }
}