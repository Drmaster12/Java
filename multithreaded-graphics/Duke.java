import java.applet.Applet;
import java.awt.*;

/** Duke is a Thread that has knowledge of the parent applet
 *  (highly coupled) and thus can call the parent's repaint
 *  method. Duke is mainly responsible for changing an index
 *  value into an image array.
 *
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class Duke extends Thread {
  public static final int STOP = 0;
  public static final int RUN  = 1;
  public static final int WAIT = 2;

  public static Image[] images;
  private static final int NUMIMAGES = 15;
  private static Object lock = new Object();
  private int state = RUN;
  private int tumbleDirection;
  private int index = 0;
  private Applet parent;

  public Duke(int tumbleDirection, Applet parent) {
    this.tumbleDirection = tumbleDirection;
    this.parent = parent;
    synchronized(lock) {
      if (images==null) {  // If not previously loaded.
        images = new Image[ NUMIMAGES ];
        for (int i=0; i<NUMIMAGES; i++) {
          images[i] = parent.getImage( parent.getCodeBase(),
                                       "images/T" + i + ".gif");
        }
      }
    }
  }

  /** Return current index into image array.  */

  public int getIndex() { return index; }

  /** Public method to permit setting a flag to stop or
   *  suspend the thread. State is monitored through
   *  corresponding checkState method.
   */

  public synchronized void setState(int state) {
    this.state = state;
    if (state==RUN) {
      notify();
    }
  }

  /** Returns the desired state (RUN, STOP, WAIT) of the
   *  thread. If the thread is to be suspended, then the
   *  thread method wait is continuously called until the
   *  state is changed through the public method setState.
   */

  private synchronized int checkState() {
    while (state==WAIT) {
      try {
        wait();
      } catch (InterruptedException e) {}
    }
    return state;
  }

  /** The variable index (into image array) is incremented
   *  once each time through the while loop, calls repaint,
   *  and pauses for a moment. Each time through the loop the
   *  state (flag) of the thread is checked.
   */

  @Override
  public void run() {
    while (checkState()!=STOP) {
      index += tumbleDirection;
      if (index < 0) {
        index = NUMIMAGES - 1;
      }
      if (index >= NUMIMAGES) {
        index = 0;
      }

      parent.repaint();

      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        break;   // Break while loop.
      }
    }
  }
}