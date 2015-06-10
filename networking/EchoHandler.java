import java.io.*;
import java.net.*;
import java.util.*;

public class EchoHandler implements Runnable {
  private Socket socket;
  
  public EchoHandler(Socket socket) {
    this.socket = socket;
  }
  
  public void run() {
    try {
      handleConnection(socket);
    } catch(IOException ioe) {
      System.err.println("IOException: " + ioe);
      ioe.printStackTrace();
    }
  }
  
  /** Reads each line of data received, saves it into a List
   *  of strings, then sends it back embedded inside a PRE
   *  element in an HTML page.
   */
  
  public void handleConnection(Socket socket)
      throws IOException{
    PrintWriter out = SocketUtils.getWriter(socket);
    BufferedReader in = SocketUtils.getReader(socket);
    List<String> inputLines = new ArrayList<>();
    String line;
    while((line = in.readLine()) != null) {
      inputLines.add(line);
      if (line.length() == 0) { // Blank line.
        if (usingPost(inputLines)) { // One more line if POST
          char[] data = new char[1000];
          int chars = in.read(data);
          String postData = new String(data, 0, chars);
          inputLines.add(postData);
        }
        break;
      }
    }
    printHeader(out);
    for (String inputLine: inputLines) {
      out.println(inputLine);
    }
    printTrailer(out);
    socket.close();
  }
  
  // Send standard HTTP response and top of a standard Web page.
  // Use HTTP 1.0 for compatibility with all clients.

  private void printHeader(PrintWriter out) {
    String serverName = "EchoServer";
    out.println
      ("HTTP/1.1 200 OK\r\n" +
       "Server: " + serverName + "\r\n" +
       "Content-Type: text/html\r\n" +
       "\r\n" +
       "<!DOCTYPE HTML PUBLIC " +
         "\"-//W3C//DTD HTML 4.0 Transitional//EN\">\n" +
       "<HTML>\n" +
       "<HEAD>\n" +
       "  <TITLE>" + serverName + " Results</TITLE>\n" +
       "</HEAD>\n" +
       "\n" +
       "<BODY BGCOLOR=\"#FDF5E6\">\n" +
       "<H1 ALIGN=\"CENTER\">" + serverName +
         " Results</H1>\n" +
       "Here is the request line and request headers\n" +
       "sent by your browser:\n" +
       "<PRE>");
  }

  // Print bottom of a standard Web page.

  private void printTrailer(PrintWriter out) {
    out.println
      ("</PRE></BODY></HTML>\n");
  }

  // Normal Web page requests use GET, so this server can simply
  // read a line at a time. However, HTML forms can also use
  // POST, in which case we have to read one extra line.
  // (This server does not support multipart/form-data.)

  private boolean usingPost(List<String> inputs) {
    return(inputs.get(0).toUpperCase().startsWith("POST"));
  }
}
