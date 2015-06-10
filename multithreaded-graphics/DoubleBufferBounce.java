import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.*;

/** Bounce circles around on the screen, using double buffering
 *  for speed and to avoid problems with overlapping circles.
 *  Overrides update to avoid flicker problems.
 *
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class DoubleBufferBounce extends Applet
                                implements Runnable, ActionListener {
  private ExecutorService taskList;
  private volatile boolean running = false;
  private ArrayList<MovingCircle> circles;
  private int width, height;
  private Image offScreenImage;
  private Graphics offScreenGraphics;
  private Button startButton, stopButton;

  @Override
  public void init() {
    taskList = Executors.newFixedThreadPool(5);
    setBackground(Color.WHITE);
    width = getSize().width;
    height = getSize().height;
    offScreenImage = createImage(width, height);
    offScreenGraphics = offScreenImage.getGraphics();
    // Automatic in some systems, not in others.
    offScreenGraphics.setColor(Color.BLACK);
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
        running = true;
        taskList.execute(this);
      }
      int radius = 25;
      int x = radius + randomInt(width - 2 * radius);
      int y = radius + randomInt(height - 2 * radius);
      int deltaX = 1 + randomInt(10);
      int deltaY = 1 + randomInt(10);
      circles.add(new MovingCircle(x, y, radius, deltaX, deltaY));
      repaint();
    } else if (event.getSource() == stopButton) {
      running = false;
      circles.clear();
    }
  }

  /** Each time around the loop, move each circle based on its
   *  current position and deltaX/deltaY values. These values
   *  reverse when the circles reach the edge of the window.
   */

  @Override
  public void run() {
    while(running) {
      for(MovingCircle circle: circles) {
        circle.move(width, height);
      }
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

  /** Clear the off-screen pixmap, draw each circle onto it, then
   *  draw that pixmap onto the applet window.
   */

  @Override
  public void paint(Graphics g) {
    offScreenGraphics.clearRect(0, 0, width, height);
    for(MovingCircle circle: circles) {
      circle.draw(offScreenGraphics);
    }
    g.drawImage(offScreenImage, 0, 0, this);
  }

  // Returns an int from 0 to max (inclusive), yielding max + 1
  // possible values.

  private int randomInt(int max) {
    double x = Math.floor((max + 1) * Math.random());
    return((int)(Math.round(x)));
  }

  // Sleep for the specified amount of time.

  private void pause(int milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch(InterruptedException ie) {}
  }
}