import java.applet.Applet;
import java.awt.*;

public class ImageAnimation extends Applet {

/** Sequence through an array of 15 images to perform the
 *  animation. A separate Thread controls each tumbling Duke.
 *  The Applet's stop method calls a public service of the
 *  Duke class to terminate the thread. Override update to
 *  avoid flicker problems.
 *
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

  private static final int NUMDUKES  = 2;
  private Duke[] dukes;
  private int i;

  @Override
  public void init() {
    dukes = new Duke[NUMDUKES];
    setBackground(Color.WHITE);
  }


  /** Start each thread, specifing a direction to sequence
   *  through the array of images.
   */

  @Override
  public void start() {
    int tumbleDirection;
    for (int i=0; i<NUMDUKES ; i++) {
      tumbleDirection = (i%2==0) ? 1 :-1;
      dukes[i] = new Duke(tumbleDirection, this);
      dukes[i].start();
    }
  }


  /** Skip the usual screen-clearing step of update so that
   *  there is no flicker between each drawing step.
   */

  @Override
  public void update(Graphics g) {
    paint(g);
  }

  @Override
  public void paint(Graphics g) {
    for (i=0 ; i<NUMDUKES ; i++) {
      if (dukes[i] != null) {
        g.drawImage(Duke.images[dukes[i].getIndex()],
                    200*i, 0, this);
      }
    }
  }


  /** When the Applet's stop method is called, use the public
   *  service, setState, of the Duke class to set a flag and
   *  terminate the run method of the thread.
   */

  @Override
  public void stop() {
    for (int i=0; i<NUMDUKES ; i++) {
      if (dukes[i] != null) {
        dukes[i].setState(Duke.STOP);
      }
    }
  }
}