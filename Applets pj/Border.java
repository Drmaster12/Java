import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
/** An example of BorderLayout.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class Border extends JApplet {
    private Component east;
    private Component south;
    private Component west;
    private Component north;
    private JPanel JPanel;

    @Override
    public void init() {
        
        JPanel = new JPanel(new BorderLayout());
        SimpleWhiteboard swb= new SimpleWhiteboard();
        add(new JButton("North Wing"), BorderLayout.NORTH);
        add(new JButton("South Wing"), BorderLayout.SOUTH);
        add(new JButton("East Wing"), BorderLayout.EAST);
        add(new JButton("West Wing"), BorderLayout.WEST);
        add(swb, BorderLayout.CENTER);
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(north , BorderLayout.NORTH);
        content.add(east  , BorderLayout.EAST);
        content.add(south , BorderLayout.SOUTH);
        content.add(west  , BorderLayout.WEST);
        content.add(swb, BorderLayout.CENTER);
        
    }
}
