/** Prints the tokens resulting from treating the first
 *  command-line argument as the string to be tokenized
 *  and the second as the delimiter set. Uses
 *  String.split instead of StringTokenizer.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class SplitTest {
  public static void main(String[] args) {
    if (args.length == 2) {
      String[] tokens = args[0].split(args[1]);
      for(String token: tokens) {
        if (token.length() != 0) {
          System.out.println(token);
        }
      }
    } else {
      System.out.println
        ("Usage: java SplitTest string delimeters");
    }
  }
}