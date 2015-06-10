import java.net.*;
import java.io.*;

/** Make simple connection to host and port specified.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class NetworkClientTest extends NetworkClient {
  /** Pass host and port to parent constructor. */

  public NetworkClientTest(String host, int port) {
    super(host, port);
  }

  /** Simple client that sends a single line
   *  ("Generic Network Client") to the server,
   *  reads one line of response, prints it, then exits.
   */
  @Override
  protected void handleConnection(Socket client)
      throws IOException {
    PrintWriter out = SocketUtils.getWriter(client);
    BufferedReader in = SocketUtils.getReader(client);
    out.println("Generic Network Client");
    System.out.printf
      ("Generic Network Client:%n" +
       "Connected to '%s' and got '%s' in response.%n",
       getHost(), in.readLine());
  }

  public static void main(String[] args) {
    String host = "ftp.microsoft.com";
    int port = 21;
    if (args.length > 0) {
      host = args[0];
    }
    if (args.length > 1) {
      port = Integer.parseInt(args[1]);
    }
    NetworkClientTest tester =
      new NetworkClientTest(host, port);
    tester.connect();
  }
}