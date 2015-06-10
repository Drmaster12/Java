import java.applet.Applet;
import java.awt.*;

/** An applet that draws an image. 
 * 
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class JavaJump extends Applet {
  private Image jumpingJava; // Instance var declarations here

  @Override // I'll explain later what this optional tag does
  public void init() {       // Initializations here
    setBackground(Color.WHITE);
    setFont(new Font("SansSerif", Font.BOLD, 18));
    jumpingJava = getImage(getCodeBase(),
                           "images/Jumping-Java.gif");
    add(new Label("Great Jumping Java!"));
    System.out.println("Yow! I'm jiving with Java.");
  }

  @Override // I'll explain later what this optional tag does
  public void paint(Graphics g) {  // Drawing here
    g.drawImage(jumpingJava, 0, 50, this);
  }
}