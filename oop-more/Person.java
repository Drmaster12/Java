/** Represents a person's given name 
 *  and family name. Also see Employee for a subclass
 *  that has additional information.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */
public class Person {
  private String firstName, lastName;
  
  public Person(String firstName,
                String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
  
  /** The person's given (first) name. */
  
  public String getFirstName() {
    return (firstName);
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /** The person's family name (i.e., 
   *  last name or surname). 
   */
  public String getLastName() {
    return (lastName);
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  /** The person's given name and family name, printed
   *  in American style, with given name first and
   *  a space in between.
   */
  public String getFullName() {
    return(firstName + " " + lastName);
  }
}
