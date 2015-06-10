import java.applet.Applet;
import java.awt.*;

/** From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class HelloWWW2 extends Applet {
  @Override // I'll explain later what this optional tag does
  public void init() {
    setFont(new Font("SansSerif", Font.BOLD, 30));
    Color background = Color.GRAY;
    Color foreground = Color.DARK_GRAY;
    String backgroundType = getParameter("BACKGROUND");
    if (backgroundType != null) {
      if (backgroundType.equalsIgnoreCase("LIGHT")) {
        background = Color.WHITE;
        foreground = Color.BLACK;
      } else if (backgroundType.equalsIgnoreCase("DARK")) {
        background = Color.BLACK;
        foreground = Color.WHITE;
      }
    }
    setBackground(background);
    setForeground(foreground);
  }

  @Override // I'll explain later what this optional tag does
  public void paint(Graphics g) {
    g.drawString("Hello, World Wide Web.", 5, 35);
  }
}