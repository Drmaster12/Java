import java.awt.*;
import javax.swing.*;

/** An example of performing animation through Swing timers.
 *  Two timed Dukes are created with different timer periods.
 *
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class TimedAnimation extends JApplet {
  private static final int NUMDUKES = 2;
  private TimedDuke[] dukes;
  private int i, index;

  @Override
  public void init() {
    dukes = new TimedDuke[NUMDUKES];
    setBackground(Color.white);
    dukes[0] = new TimedDuke( 1, 100, this);
    dukes[1] = new TimedDuke(-1, 500, this);

  }

  //  Start each Duke timer.

  @Override
  public void start() {
    for (int i=0; i<NUMDUKES ; i++) {
      dukes[i].startTimer();
    }
  }

  @Override
  public void paint(Graphics g) {
    for (i=0 ; i<NUMDUKES ; i++) {
      if (dukes[i] != null) {
        index = dukes[i].getIndex();
        g.drawImage(TimedDuke.images[index], 200*i, 0, this);
      }
    }
  }

  //  Stop each Duke timer.

  @Override
  public void stop() {
    for (int i=0; i<NUMDUKES ; i++) {
      dukes[i].stopTimer();
    }
  }
}