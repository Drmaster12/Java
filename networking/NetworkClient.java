import java.net.*;
import java.io.*;

/** A starting point for network clients. You'll need to
 *  override handleConnection, but in most cases connect can
 *  remain unchanged. NetworkClient uses SocketUtils to simplify
 *  the creation of the PrintWriter and BufferedReader.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public abstract class NetworkClient {
  private String host;
  private int port;

  public String getHost() {
    return(host);
  }

  public int getPort() {
    return(port);
  }

  /** Register host and port. The connection won't
   *  actually be established until you call
   *  connect.
   */

  public NetworkClient(String host, int port) {
    this.host = host;
    this.port = port;
  }

  /** Establishes the connection, then passes the socket
   *  to handleConnection.
   */

  public void connect() {
    try(Socket client = new Socket(host, port)) {
      handleConnection(client);
    } catch(UnknownHostException uhe) {
      System.out.println("Unknown host: " + host);
      uhe.printStackTrace();
    } catch(IOException ioe) {
      System.out.println("IOException: " + ioe);
      ioe.printStackTrace();
    }
  }

  /** This is the method you will override when
   *  making a network client for your task.
   */

  protected abstract void handleConnection(Socket client)
      throws IOException;
}