/** Takes a string of the form "user@host" and
 *  separates it into the "user" and "host" parts.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class MailAddress {
  private String username, hostname;

  public MailAddress(String emailAddress) {
    String[] pieces = emailAddress.split("@");
    if (pieces.length != 2) {
      System.out.println("Illegal email address");
      System.exit(-1);
    } else {
      username = pieces[0];
      hostname = pieces[1];
    }
  }

  public String getUsername() {
    return(username);
  }

  public String getHostname() {
    return(hostname);
  }
}