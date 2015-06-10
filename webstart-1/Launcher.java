/** From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class Launcher {
  public static void main(String[] args) {
    String[] names =
      {"Ebay", "Amazon.com", "Chase Bank", "PayPal"};
    int index = (int)(Math.random() * names.length);
    new Phisher(names[index]);
  }
}