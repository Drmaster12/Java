import java.awt.*;

/** A utility class that lets you load and wait for an image or
 *  images in one fell swoop. If you are loading multiple
 *  images, only use multiple calls to waitForImage if you
 *  <b>need</b> loading to be done serially. Otherwise, use
 *  waitForImages, which loads concurrently, which can be
 *  much faster.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class TrackerUtil {
  public static boolean waitForImage(Image image, Component c) {
    MediaTracker tracker = new MediaTracker(c);
    tracker.addImage(image, 0);
    try {
      tracker.waitForAll();
    } catch(InterruptedException ie) {}
    if (tracker.isErrorAny()) {
      return(false);
    } else {
      return(true);
    }
  }

  public static boolean waitForImages(Image[] images,
                                      Component c) {
    MediaTracker tracker = new MediaTracker(c);
    for(int i=0; i<images.length; i++) {
      tracker.addImage(images[i], 0);
    }
    try {
      tracker.waitForAll();
    } catch(InterruptedException ie) {}
    if (tracker.isErrorAny()) {
      return(false);
    } else {
      return(true);
    }
  }
}