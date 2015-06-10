/** Various correct (1) and incorrect (2, 3) attempts to make an array of Circles.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class CircleArray {
  public static void main(String[] args) {
    // Do something with arrays
  }
  
  /** Builds an array of Circles and returns the array. After allocating space for the Circles, 
   *  it loops down, builds a Circle, and puts it into the appropriate place in the array.
   *  This is the correct approach. 
   */
  public static Circle[] makeCircles1(int numCircles) {
    Circle[] circles = new Circle[numCircles];
    for(int i=0; i<circles.length; i++) {
      circles[i] = new Circle(Math.random() * 10);
    }
    return(circles);
  }
  
  /** Attempts to build an array of Circles. Crashes with NullPointerException since after
   *  first line there are no Circle objects, just null pointers. So, trying to call setRadius
   *  on null crashes. 
   */
  public static Circle[] makeCircles2(int numCircles) {
    Circle[] circles = new Circle[numCircles];
    for(int i=0; i<circles.length; i++) {
      circles[i].setRadius(Math.random() * 10); // Crashes with NullPointerException
    }
    return(circles);
  }
  
  /** Attempts to build an array of Circles. Fails because it never puts any Circles
   *  into the array. To put something into an array, you must have the index.
   */
  public static Circle[] makeCircles3(int numCircles) {
    Circle[] circles = new Circle[numCircles];
    for(Circle c: circles) {
      c = new Circle(Math.random() * 10);; // Fails to store c in array
    }
    return(circles); // Array still contains only null pointers
  }
}
