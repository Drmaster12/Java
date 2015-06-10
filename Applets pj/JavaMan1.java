import java.applet.Applet;
import java.awt.*;

/** An applet that loads an image from a relative URL. Base your image-drawing
 *  exercises on this example.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class JavaMan1 extends Applet {
  private Image javaMan;

  @Override // I'll explain later what this optional tag does
  public void init() {
    javaMan = getImage(getCodeBase(),"images/Java-Man.gif");
  }

  @Override // I'll explain later what this optional tag does
  public void paint(Graphics g) {
    g.drawImage(javaMan, 0, 0, this);
  }
}