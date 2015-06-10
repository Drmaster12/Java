package coreservlets;

import java.awt.event.*;

/** A listener that you attach to the top-level JFrame of
 *  your application, so that quitting the frame exits the 
 *  application.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */
 
public class ExitListener extends WindowAdapter {
  @Override
  public void windowClosing(WindowEvent event) {
    System.exit(0);
  }
}