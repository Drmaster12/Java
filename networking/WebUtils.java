

import java.io.*;
import java.util.*;

public class WebUtils {
  /** Sends standard HTTP response line, response headers, and top of a standard Web page.
   *  <p>
   *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
   *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
   *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
   *  servlets, JSP, and Java 7 and Java 8 programming</a>.
   */
  public static void printHeader(PrintWriter out, String serverName) {
    out.println
      ("HTTP/1.1 200 OK\r\n" +
       "Server: " + serverName + "\r\n" +
       "Content-Type: text/html\r\n" +
       "\r\n" +
       "<!DOCTYPE html>\n" +
       "<html lang=\"en\">\n" +
       "<head>\n" +
       "  <meta charset=\"utf-8\"/>\n" +
       "  <title>" + serverName + " Results</title>\n" +
       "</head>\n" +
       "\n" +
       "<body bgcolor=\"#fdf5e6\">\n" +
       "<h1 align=\"center\">" + serverName +
         " Results</h1>\n" +
       "Here are the request line and request headers\n" +
       "sent by your browser:\n" +
       "<pre>");
  }

  /** Sends bottom of a standard Web page.
   *  <p>
   *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
   *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
   *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
   *  servlets, JSP, and Java 7 and Java 8 programming</a>.
   */
  public static void printTrailer(PrintWriter out) {
    out.println
      ("</pre></body></html>\n");
  }

  // 

  /** Gets the POST line. 
   *  Normal Web page requests use GET, so this server can simply
   * read a line at a time. However, HTML forms can also use
   * POST, in which case we have to read one extra line.
   * (This server does not support multipart/form-data.)
   */
  public static boolean usingPost(List<String> inputs) {
    return(inputs.get(0).toUpperCase().startsWith("POST"));
  }


  private WebUtils() {} // Uninstantiatable class
}
