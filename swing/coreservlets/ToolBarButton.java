package coreservlets;

import java.awt.*;
import javax.swing.*;

/** Part of a small example showing basic use of JToolBar.
 *  The point here is that dropping a regular JButton in a
 *  JToolBar (or adding an Action) in JDK 1.2 doesn't give 
 *  you what you want -- namely, a small button just enclosing
 *  the icon, and with text labels (if any) below the icon,
 *  not to the right of it. In JDK 1.3, if you add an Action
 *  to the toolbar, the Action label is no longer displayed.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class ToolBarButton extends JButton {
  private static final Insets MARGINS =
    new Insets(0, 0, 0, 0);

  public ToolBarButton(Icon icon) {
    super(icon);
    setMargin(MARGINS);
    setVerticalTextPosition(BOTTOM);
    setHorizontalTextPosition(CENTER);
  }

  public ToolBarButton(String imageFile) {
    this(new ImageIcon(imageFile));
  }

  public ToolBarButton(String imageFile, String text) {
    this(new ImageIcon(imageFile));
    setText(text);
  }
}