import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.*;

/** Bounce circles around on the screen. Doesn't use double
 *  buffering, so has problems with overlapping circles.
 *  Overrides update to avoid flicker problems.
 *
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class Bounce extends Applet implements Runnable,
                                              ActionListener {
  private ExecutorService taskList;
  private volatile boolean running = false;
  private ArrayList<MovingCircle> circles;
  private int width, height;
  private Button startButton, stopButton;

  @Override
  public void init() {
    taskList = Executors.newFixedThreadPool(5);
    setBackground(Color.WHITE);
    width = getSize().width;
    height = getSize().height;
    circles = new ArrayList<MovingCircle>();
    startButton = new Button("Start a circle");
    startButton.addActionListener(this);
    add(startButton);
    stopButton = new Button("Stop all circles");
    stopButton.addActionListener(this);
    add(stopButton);
  }

  /** When the "start" button is pressed, start the animation
   *  thread if it is not already started. Either way, add a
   *  circle to the list of circles that are being bounced.
   *  <P>
   *  When the "stop" button is pressed, stop the thread and
   *  clear the list of circles.
   */

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == startButton) {
      if (!running) {
        // Erase any circles from previous run.
        getGraphics().clearRect(0, 0, getSize().width,
                                      getSize().height);
        running = true;
        taskList.execute(this);
      }
      int radius = 25;
      int x = radius + randomInt(width - 2 * radius);
      int y = radius + randomInt(height - 2 * radius);
      int deltaX = 1 + randomInt(10);
      int deltaY = 1 + randomInt(10);
      circles.add(new MovingCircle(x, y, radius, deltaX, deltaY));
    } else if (event.getSource() == stopButton) {
      running = false;
      circles.clear();
    }
    repaint();
  }

  /** Each time around the loop, call paint and then take a
   *  short pause. The paint method will move the circles and
   *  draw them.
   */

  @Override
  public void run() {
    while(running) {
      repaint();
      pause(100);
    }
  }

  /** Skip the usual screen-clearing step of update so that
   *  there is no flicker between each drawing step.
   */

  @Override
  public void update(Graphics g) {
    paint(g);
  }

  /** Erase each circle's old position, move it, then draw it
   *  in new location.
   */

  @Override
  public void paint(Graphics g) {
    for(MovingCircle circle: circles) {
      g.setColor(getBackground());
      circle.draw(g);  // Old position.
      circle.move(width, height);
      g.setColor(getForeground());
      circle.draw(g);  // New position.
    }
  }

  // Returns an int from 0 to max (inclusive),
  // yielding max + 1 possible values.

  private int randomInt(int max) {
    double x =
      Math.floor((max + 1) * Math.random());
    return((int)(Math.round(x)));
  }


  // Sleep for the specified amount of time.

  private void pause(int milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch(InterruptedException ie) {}
  }
}