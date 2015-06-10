import java.io.*;
import java.net.*;
import java.util.*;

/** A simple HTTP server that generates a Web page showing all
 *  of the data that it received from the Web client (usually
 *  a browser). To use this server, start it on the system of
 *  your choice, supplying a port number if you want something
 *  other than port 8080. Call this system server.com. Next,
 *  start a Web browser on the same or a different system, and
 *  connect to http://server.com:8080/whatever. The resultant
 *  Web page will show the data that your browser sent. For
 *  debugging in servlet or other Web programming, specify
 *  http://server.com:8080/whatever as the ACTION of your HTML
 *  form. You can send GET or POST data; either way, the
 *  resultant page will show what your browser sent.
 *  
 *  This is a multithreaded variation of SimpleEchoServer.
 *  
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class EchoServer extends MultithreadedServer {
  public EchoServer(int port) {
    super(port);
  }
  
  /** Supply a port number as a command-line
   *  argument. Otherwise, it uses port 8088.
   */

  public static void main(String[] args) {
    int port = 8080;
    try {
      port = Integer.parseInt(args[0]);
    } catch(NumberFormatException|ArrayIndexOutOfBoundsException e) {}
    EchoServer server = new EchoServer(port);
    server.listen();
  }
  
  /** Overrides the MultithreadedServer handleConnection method to
   *  read each line of data received, save it into a List
   *  of strings, then send it back embedded inside a PRE
   *  element in an HTML page.
   */

  @Override
  public void handleConnection(Socket socket)
      throws IOException{
    String serverName = "Multithreaded EchoServer";
    PrintWriter out = SocketUtils.getWriter(socket);
    BufferedReader in = SocketUtils.getReader(socket);
    List<String> inputLines = new ArrayList<>();
    String line;
    while((line = in.readLine()) != null) {
      inputLines.add(line);
      if (line.isEmpty()) { // Blank line.
        if (WebUtils.usingPost(inputLines)) { // If POST, one more line to read, with no CR at end. Ignore multi-line posts, such as file uploads.
            char[] data = new char[1000];
            int chars = in.read(data);
            String postData = new String(data, 0, chars);
            inputLines.add(postData);
        }
        break;
      }
    }
    WebUtils.printHeader(out, serverName);
    for (String inputLine: inputLines) {
      out.println(inputLine);
    }
    WebUtils.printTrailer(out);
    socket.close();
  }
}
