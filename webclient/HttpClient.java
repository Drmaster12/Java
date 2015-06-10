import java.net.*;
import java.io.*;
import java.util.*;
import javax.net.*;
import javax.net.ssl.*;
import javax.swing.*;

/** The underlying network client used by WebClient. Sends an
 *  HTTP request in the following format:<P>
 *
 *  GET / HTTP/1.0
 *  <P>
 *  Request can be GET or POST, and the HTTP version can be 1.0
 *  or 1.1 (a Host: header is required for HTTP 1.1).
 *  Supports both HTTP and HTTPS (SSL).
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages Volume II
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://volume2.coreservlets.com/.
 *  (C) 2007 Marty Hall, Larry Brown, and Yaakov Chaikin;
 *  may be freely used or adapted.
 */

public class HttpClient {
  protected URL url;
  protected String requestMethod;
  protected String httpVersion;
  protected List requestHeaders;
  protected String queryData;
  protected JTextArea outputArea;
  protected boolean interrupted;

  public HttpClient(URL url,
                    String requestMethod,
                    String httpVersion,
                    List requestHeaders,
                    String queryData,
                    JTextArea outputArea) {
    this.url = url;
    this.requestMethod = requestMethod;
    this.httpVersion = httpVersion;
    this.requestHeaders = requestHeaders;
    this.queryData = queryData;
    this.outputArea = outputArea;
  }

  /** Establish the connection, then pass the socket
   *  to handleConnection.
   */

  public void connect() {
    if(!isValidURL()) {
      return;
    }
    String host = url.getHost();
    int port = url.getPort();
    if (port == -1) {
      port = url.getDefaultPort();
    }
    connect(host, port);
  }

  /** Open a TCP connection to host on specified port and
   *  then call handleConnection to process the request.
   *  For an https request, use a SSL socket.
   */

  protected void connect(String host, int port) {
    try {
      Socket client = null;
      if (isSecure()) {
        SocketFactory factory = SSLSocketFactory.getDefault();
        client = factory.createSocket(host, port);
      } else {
        client = new Socket(host, port);
      }
      handleConnection(client);
      client.close();
    } catch(UnknownHostException uhe) {
      report("Unknown host: " + host);
      uhe.printStackTrace();
    } catch(ConnectException ce) {
      report("Connection problem: " + ce.getMessage());
      ce.printStackTrace();
    } catch(IOException ioe) {
      report("IOException: " + ioe.getMessage());
      ioe.printStackTrace();
    }
  }

  /** Send request to server, providing all specified headers
   *  and query data. If a POST request, add a header for the
   *  Content-Length.
   */

  public void handleConnection(Socket socket) {
    try {
      // Make a PrintWriter to send outgoing data.
      // Second argument of true means autoflush.
      PrintWriter out =
        new PrintWriter(socket.getOutputStream(), true);
      // Make a BufferedReader to get incoming data.
      BufferedReader in =
        new BufferedReader(
          new InputStreamReader(socket.getInputStream()));
      StringBuffer buffer = new StringBuffer();
      outputArea.setText("");
      buffer.append(getRequestLine() + "\r\n");
      for(int i=0; i<requestHeaders.size(); i++) {
        buffer.append(requestHeaders.get(i) + "\r\n");
      }
      // Add Content-Length header for POST data.
      if ("POST".equalsIgnoreCase(requestMethod)) {
        buffer.append("Content-Length: " +
                      queryData.length() + "\r\n");
        buffer.append("\r\n");
        buffer.append(queryData);
      } else {
        buffer.append("\r\n");
      }
      System.out.println("Request:\n\n" + buffer.toString());
      out.println(buffer.toString());
      out.flush();
      String line;
      while ((line = in.readLine()) != null &&
             !interrupted) {
        outputArea.append(line + "\n");
      }
      if (interrupted) {
        outputArea.append("---- Download Interrupted ----");
      }
      out.close();
      in.close();
    } catch(Exception e) {
      outputArea.setText("Error: " + e);
    }
  }

  /** Create HTTP request line, i.e., GET URI HTTP/1.0 */

  protected String getRequestLine() {
    String method = "GET";
    String uri = url.getPath();
    String version = "HTTP/1.0";
    // Determine if POST request. If not, then GET request.
    // Add query data after ? for GET request.
    if ("POST".equalsIgnoreCase(requestMethod)) {
      method = "POST";
    } else {
      if (queryData.length() > 0) {
        uri += "?" + queryData;
      }
    }
    if ("HTTP/1.1".equalsIgnoreCase(httpVersion)) {
      version = "HTTP/1.1";
    }
    String request = method + " " + uri + " " + version;
    return(request);
  }

  protected void report(String str) {
    outputArea.setText(str);
  }

  /* Check protocol for https (SSL). */

  protected boolean isSecure() {
    return("https".equalsIgnoreCase(url.getProtocol()));
  }

  public void setInterrupted(boolean interrupted) {
    this.interrupted = interrupted;
  }

  /** Determine if host evaluates to an Internet address. */

  protected boolean isValidURL() {
    if (url == null) {
      return(false);
    }
    try {
      InetAddress.getByName(url.getHost());
      return(true);
    } catch(UnknownHostException uhe) {
      report("Bogus Host: " + url.getHost());
      return(false);
    }
  }
}