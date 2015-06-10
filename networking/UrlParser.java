import java.util.*;

/** Parses the input to get a host, port, and uri (file). 
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class UrlParser {
  private String host;
  private int port = 80;
  private String uri;
  
  public UrlParser(String url) {
    StringTokenizer tok = new StringTokenizer(url);
    String protocol = tok.nextToken(":");
    checkProtocol(protocol);
    host = tok.nextToken(":/");
    try {
      uri = tok.nextToken("");
      if (uri.charAt(0) == ':') {
        tok = new StringTokenizer(uri);
        port = Integer.parseInt(tok.nextToken(":/"));
        uri = tok.nextToken("");
      }
    } catch(NoSuchElementException nsee) {
      uri = "/";
    }
  }
  
  private void checkProtocol(String protocol) {
    if (!protocol.equals("http")) {
      System.out.println("Don't understand protocol " + protocol);
    }
  }
  
  public String getHost() {
    return(host);
  }

  public int getPort() {
    return(port);
  }

  public String getUri() {
    return(uri);
  }
  
  @Override
  public String toString() {
    return(String.format("port: %s, host: %s, uri: %s", host, port, uri));
  }
  
  public static void main(String[] args) {
    System.out.println(new UrlParser("http://blah.com/foo/bar/baz.html"));
    System.out.println(new UrlParser("http://blah.com:8080/foo/bar/baz.html"));
    System.out.println(new UrlParser("ftp://blah.com/foo/bar/baz.html"));
    System.out.println(new UrlParser("ftp://blah.com"));
  }
}
