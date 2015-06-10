package coreservlets;
import java.awt.*;
import javax.swing.*;

/** Simple JList example illustrating the use of a custom
 *  cell renderer (JavaLocationRenderer).
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class JListCustomRenderer extends JFrame {
  public static void main(String[] args) {
    new JListCustomRenderer();
  }

  public JListCustomRenderer() {
    super("JList with a Custom Cell Renderer");
    WindowUtilities.setNimbusLookAndFeel();
    addWindowListener(new ExitListener());
    Container content = getContentPane();

    JavaLocationCollection collection = 
      new JavaLocationCollection();
    JavaLocationListModel listModel =
      new JavaLocationListModel(collection);
    JList<JavaLocation> sampleJList = new JList<>(listModel);
    sampleJList.setCellRenderer(new JavaLocationRenderer());
    Font displayFont = new Font("Serif", Font.BOLD, 18);
    sampleJList.setFont(displayFont);
    content.add(sampleJList);

    pack();
    setVisible(true);
  }
}