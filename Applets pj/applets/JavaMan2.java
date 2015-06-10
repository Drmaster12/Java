import java.applet.Applet;
import java.awt.*;
import java.net.*;

/** An applet that loads an image from an absolute
 *  URL. This URL MUST be on the same machine
 *  that the applet came from. So, this approach is NOT
 *  useful for applets. But in desktop applications, this
 *  approach is very useful. Plus, this gives me an excuse
 *  to introduce try/catch blocks. But, please note that
 *  you will get an error message if you try to run this applet,
 *  since it is not deployed on corewebprogramming.com.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class JavaMan2 extends Applet {
  private Image javaMan;

  @Override // I'll explain later what this optional tag does
  public void init() {
    try {
      URL imageFile = new URL("http://www.corewebprogramming.com" +
                              "/images/Java-Man.gif");
      javaMan = getImage(imageFile);
    } catch(MalformedURLException mue) {
      showStatus("Bogus image URL.");
      System.out.println("Bogus URL");
    }
  }

  @Override // I'll explain later what this optional tag does
  public void paint(Graphics g) {
    g.drawImage(javaMan, 0, 0, this);
  }
}