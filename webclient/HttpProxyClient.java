import java.net.*;
import java.io.*;
import java.util.*;
import javax.net.*;
import javax.net.ssl.*;
import javax.swing.*;

/** The underlying proxy client used by WebClient. Proxy
 *  requests are sent in the following format:<P>
 *
 *  GET URL HTTP/1.0
 *
 *  <P>where the URL is the WebClient URL, for example,
 *  http://www.google.com/.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages Volume II
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://volume2.coreservlets.com/.
 *  (C) 2007 Marty Hall, Larry Brown, and Yaakov Chaikin;
 *  may be freely used or adapted.
 */

public class HttpProxyClient extends HttpClient {
  private URL proxyURL;

  public HttpProxyClient(URL url,
                         URL proxyURL,
                         String requestMethod,
                         String httpVersion,
                         List requestHeaders,
                         String queryData,
                         JTextArea outputArea) {
    super(url, requestMethod, httpVersion,
          requestHeaders, queryData, outputArea);
    this.proxyURL = proxyURL;
  }

  /** Open TCP connection to Proxy host. */

  public void connect() {
    if(!isValidURL() || !isValidProxyURL()) {
      return;
    }
    String host = proxyURL.getHost();
    int port = proxyURL.getPort();
    if (port == -1) {
      port = proxyURL.getDefaultPort();
    }
    connect(host, port);
  }

  /** Create HTTP request line for proxy server. Instead of
   *  stating a URI, the GET or POST request states the full
   *  URL for the original page request. For example, <P>
   *
   *  GET http://www.google.com/  HTTP/1.0
   */

  protected String getRequestLine() {
    String method = "GET";
    String url = this.url.toString();
    String version = "HTTP/1.0";
    // Determine if POST request. If not, then GET request.
    // Add query data after ? for GET request.
    if ("POST".equalsIgnoreCase(requestMethod)) {
      method = "POST";
    } else {
      if (queryData.length() > 0) {
        url += "?" + queryData;
      }
    }
    if ("HTTP/1.1".equalsIgnoreCase(httpVersion)) {
      version = "HTTP/1.1";
    }
    String request = method + " " + url + " " + version;
    return(request);
  }

  /** Determine if proxy server is a valid host address. */

  protected boolean isValidProxyURL() {
    if (proxyURL == null) {
      return(false);
    }
    try {
      InetAddress.getByName(proxyURL.getHost());
      return(true);
    } catch(UnknownHostException uhe) {
      report("Bogus Proxy: " + url.getHost());
      return(false);
    }
  }
}