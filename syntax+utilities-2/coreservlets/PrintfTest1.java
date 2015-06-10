package coreservlets;
/** A minor class to be used to illustrate printf.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */ 

public class PrintfTest1 {
  public static void main(String[] args) {
    printSomeStrings();
    printSomeSalaries();
  }

  public static void printSomeStrings() {
    String firstName = "John";
    String lastName = "Doe";
    int numPets = 7;
    String petType = "chickens";
    System.out.printf("%s %s has %s %s.%n",
                      firstName, lastName, numPets, petType);
    System.out.println(firstName + " " + lastName +
                       " has " + numPets + " " +
                       petType + ".");
  }

  public static void printSomeSalaries() {
    CEO[] softwareCEOs =
      { new CEO("Steve Jobs", 3.1234),
        new CEO("Scott McNealy", 45.5678),
        new CEO("Jeff Bezos", 567.982323),
        new CEO("Larry Ellison", 6789.0),
        new CEO("Bill Gates", 78901234567890.12) };
    System.out.println("SALARIES:");
    for(CEO ceo: softwareCEOs) {
      System.out.printf("%15s: $%,8.2f%n",
                        ceo.getName(), ceo.getSalary());
    }
  }

}