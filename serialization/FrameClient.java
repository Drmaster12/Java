import java.net.*;
import java.io.*;

/** Connects to a FrameServer, gets a snapshot of that app, and pops a copy up locally.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class FrameClient {
  public FrameClient(String host, int port) {
    try(Socket client = new Socket(host, port)) {
      @SuppressWarnings("unused") // frame is not really unused, since it pops up and you can interact with it.
      SaveableFrame frame =
        SaveableFrame.getFrame(client.getInputStream());
    } catch(UnknownHostException uhe) {
      System.out.println("Unknown host: " + host);
      uhe.printStackTrace();
    } catch(IOException ioe) {
      System.out.println("IOException: " + ioe);
      ioe.printStackTrace();
    }
  }

  public static void main(String[] args) {
    String host = "localhost";
    int port = 1234;
    if (args.length > 1) {
      try {
        host = args[0];
        port = Integer.parseInt(args[1]);
      } catch(NumberFormatException nfe) {}
    }
    new FrameClient(host, port);
  }
}