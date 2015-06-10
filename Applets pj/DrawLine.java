import java.applet.Applet;
import java.awt.*;

/** Draws a line from TOP-left corner halfway to bottom-right. 
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class DrawLine extends Applet {
  private int width;   // Instance var declarations here
  private int height; 

  @Override // I'll explain later what this optional tag does
  public void init() {           // Initializations here
    setBackground(Color.YELLOW);
    setForeground(Color.RED);
    width = getWidth();
    height = getHeight();
  }

  @Override // I'll explain later what this optional tag does
  public void paint(Graphics g) {  // Drawing here
    g.drawLine(0, 0, width/2, height/2);
  }
}