import java.net.*;
import java.io.*;

/** Given an e-mail address of the form user@host,
 *  connect to port 25 of the host and issue an
 *  'expn' request for the user. Print the results.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class AddressVerifier extends NetworkClient {
  private String username;

  public AddressVerifier(String username, String hostname,
                         int port) {
    super(hostname, port);
    this.username = username;
    connect();
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      usage();
    }
    MailAddress address = new MailAddress(args[0]);
    new AddressVerifier(address.getUsername(),
                        address.getHostname(), 25);
  }

  /** NetworkClient, the parent class, automatically establishes
   *  the connection and then passes the Socket to
   *  handleConnection. This method does all the real work
   *  of talking to the mail server.
   */

  // You can't use readLine, because it blocks. Blocking I/O
  // by readLine is only appropriate when you know how many
  // lines to read. Note that mail servers send a varying
  // number of lines when you first connect or send no line
  // closing the connection (as HTTP servers do), yielding
  // null for readLine. Also, we'll assume that 1000 bytes
  // is more than enough to handle any server welcome
  // message and the actual EXPN response.

  @Override
  protected void handleConnection(Socket client)
      throws IOException {
    PrintWriter out = SocketUtils.getWriter(client);
    InputStream rawIn = client.getInputStream();
    byte[] response = new byte[1000];
    // Clear out mail server's welcome message.
    rawIn.read(response);
    out.println("EXPN " + username);
    // Read the response to the EXPN command.
    int numBytes = rawIn.read(response);
    // The 0 means to use normal ASCII encoding.
    System.out.write(response, 0, numBytes);
    out.println("QUIT");
  }

  /** If the wrong arguments, thn warn user. */

  public static void usage() {
    System.out.println ("You must supply an email address " +
       "of the form 'username@hostname'.");
    System.exit(-1);
  }
}