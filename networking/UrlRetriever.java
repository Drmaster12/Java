/** This parses the input to get a host, port, and uri (file), then
 *  passes these three values to the UriRetriever class to
 *  grab the URL from the Web.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class UrlRetriever {
  public static void main(String[] args) {
    checkUsage(args);
    UrlParser parser = new UrlParser(args[0]);
    UriRetriever uriClient = 
      new UriRetriever(parser.getHost(), parser.getPort(), 
                       parser.getUri());
    uriClient.connect();
  }

  /** Warn user if the URL was forgotten. */

  private static void checkUsage(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: UrlRetriever <URL>");
      System.exit(-1);
    }
  }
}