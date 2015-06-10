import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Duke facilitates animation by creating an internal timer.
 *  When the timer fires, an actionPerformed event is
 *  triggered, which in turn calls repaint on the parent
 *  Applet.
 *
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class TimedDuke implements ActionListener {
  private static final int NUMIMAGES = 15;
  private static boolean loaded = false;
  private static Object lock = new Object();
  private int tumbleDirection;
  private int index = 0;
  private Applet parent;
  private Timer timer;
  public static Image[] images = new Image[NUMIMAGES];

  public TimedDuke(int tumbleDirection, int msec,
                   Applet parent) {
    this.tumbleDirection = tumbleDirection;
    this.parent = parent;

    synchronized (lock) {
      if (!loaded) {
         MediaTracker tracker = new MediaTracker(parent);
         for (int i=0; i<NUMIMAGES; i++) {
           images[i] = parent.getImage(parent.getCodeBase(),
                                       "images/T" + i + ".gif");
           tracker.addImage(images[i],0);
         }
         try {
           tracker.waitForAll();
         } catch (InterruptedException ie) {}
         if (!tracker.isErrorAny()) {
           loaded = true;
         }
      }
    }

    timer = new Timer(msec, this);
  }

  // Return current index into image array.

  public int getIndex() { return index; }

  // Receives timer firing event.  Increments the index into
  // image array and forces repainting of the new image.

  public void actionPerformed(ActionEvent event) {
    index += tumbleDirection;
    if (index < 0){
      index = NUMIMAGES - 1;
    }
    if (index >= NUMIMAGES) {
      index = 0;
    }
    parent.repaint();
  }

  // Public service to start the timer.
  public void startTimer() {
    timer.start();
  }

  // Public service to stop the timer.
  public void stopTimer() {
    timer.stop();
  }
}