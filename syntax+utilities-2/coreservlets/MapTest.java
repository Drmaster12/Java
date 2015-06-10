package coreservlets;

import java.util.*;

/** Simple use of a Map to look up values associated with a key.
 *  Highly simplified, since the number of entries is so small
 *  that the performance benefits of HashMap might be minor.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class MapTest {
  public static void main(String[] args) {
    StateMap states = new StateMap();
    Map<String,String> stateAbbreviationTable =
      states.getStateMap();
    Scanner inputScanner = new Scanner(System.in);
    System.out.println("Enter state names. Hit RETURN to quit");
    String stateName;
    String abbreviation;
    while(true) {
      System.out.print("State: ");
      stateName = inputScanner.nextLine();
      if (stateName.equals("")) {
        System.out.println("Come again soon.");
        break;
      }
      abbreviation =
        stateAbbreviationTable.get(stateName);
      if (abbreviation == null) {
        abbreviation = "Unknown";
      }
      System.out.println(abbreviation);
    }
    inputScanner.close();
  }
}