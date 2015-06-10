import java.net.*;
import java.io.*;

/** Sends a snaphot of the app to whatever client connects.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class FrameServer {
  public FrameServer(int port) {
    SaveableFrame frame = new SaveableFrame();
    listen(port, frame);
  }

  public void listen(int port, SaveableFrame frame) {
    try(ServerSocket listener = new ServerSocket(port)) {
      Socket server;
      while(true) {
        server = listener.accept();
        frame.sendFrame(server.getOutputStream());
        server.close();
      }
    } catch (IOException ioe) {
      System.out.println("IOException: " + ioe);
      ioe.printStackTrace();
    }
  }

  public static void main(String[] args) {
    int port = 1234;
    if (args.length > 0) {
      try {
        port = Integer.parseInt(args[0]);
      } catch(NumberFormatException nfe) {}
    }
    new FrameServer(port);
  }
}