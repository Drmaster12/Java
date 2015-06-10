import java.net.*;

/** This is just a Thread with a field to store a Socket object.
 *  Used as a thread-safe means to pass the Socket from
 *  handleConnection to run.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class Connection extends Thread {
  private Socket serverSocket;

  public Connection(Runnable serverObject,
                    Socket serverSocket) {
    super(serverObject);
    this.serverSocket = serverSocket;
  }

  public Socket getSocket() {
    return serverSocket;
  }
}