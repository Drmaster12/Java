package coreservlets;

/** A minor class to be used to illustrate printf.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */ 

public class Person {
  private String name;
  private int salary;  // in billions

  public Person(String name, int salary) {
    this.name = name;
    this.salary = salary;
  }

  public String getName() { return(name); }

  public int getSalary() { return(salary); }
}