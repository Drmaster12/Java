import java.net.*;
import java.io.*;
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
 *  This is just a warmup to help you understand the idea.
 *  See EchoServer (a multithreaded variation) for the "real" version.
 *  
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class SimpleEchoServer extends NetworkServer {
  public SimpleEchoServer(int port) {
    super(port);
  }

  /** Overrides the NetworkServer handleConnection method to
   *  read each line of data received, save it into a List
   *  of strings, then send it back embedded inside a PRE
   *  element in an HTML page.
   */

  // Could move body of this method to WebUtils if you really
  // wanted it to be reusable. But, this version is just
  // an illustration of the idea, and EchoServer (the multithreaded variant)
  // is the "real" code, and I wanted to leave the meat of the method
  // in the real code. This code (SimpleEchoServer) is not even
  // shown in the lecture.
  
  @Override
  public void handleConnection(Socket socket)
      throws IOException{
    String serverName = "Single-Threaded EchoServer";
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

  /** Supply a port number as a command-line
   *  argument. Otherwise, use port 8088.
   */

  public static void main(String[] args) {
    int port = 8080;
    try {
      port = Integer.parseInt(args[0]);
    } catch(NumberFormatException|ArrayIndexOutOfBoundsException e) {}
    SimpleEchoServer server = new SimpleEchoServer(port);
    server.listen();
  }
}