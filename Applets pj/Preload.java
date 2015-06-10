import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

/** A class that compares the time to draw an image preloaded
 *  (getImage, prepareImage, and drawImage) vs. regularly
 *  (getImage and drawImage).
 *  <p>
 *  The answer you get the regular way is dependent on the
 *  network speed and the size of the image, but if you assume
 *  you load the applet "long" (compared to the time the image
 *  loading requires) before pressing the button, the drawing
 *  time in the preloaded version depends only on the speed of
 *  the local machine.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class Preload extends JPanel implements ActionListener {
  private JTextField timeField;
  private long start = 0;
  private boolean draw = false;
  private JButton button;
  private Image plate;

  public Preload(String imageFile, boolean preload) {
    setLayout(new BorderLayout());
    button = new JButton("Display Image");
    button.setFont(new Font("SansSerif", Font.BOLD, 24));
    button.addActionListener(this);
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(button);
    timeField = new JTextField(25);
    timeField.setEditable(false);
    timeField.setFont(new Font("SansSerif", Font.BOLD, 24));
    buttonPanel.add(timeField);
    add(buttonPanel, BorderLayout.SOUTH);
    registerImage(imageFile, preload);

  }

  /** No need to check which object caused this,
   *  since the button is the only possibility.
   */

  public void actionPerformed(ActionEvent event) {
    draw = true;
    start = System.currentTimeMillis();
    repaint();
  }

  // Do getImage, optionally starting the loading.

  private void registerImage(String imageFile, boolean preload) {
    try {
      plate = getToolkit().getImage(new URL(imageFile));
      if (preload) {
        prepareImage(plate, this);
      }
    } catch(MalformedURLException mue) {
      System.out.println("Bad URL: " + mue);
    }
  }

  /** If button has been clicked, draw image and
   *  show elapsed time. Otherwise, do nothing.
   */

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (draw) {
      g.drawImage(plate, 0, 0, this);
      showTime();
    }
  }

  // Show elapsed time in textfield.

  private void showTime() {
    timeField.setText("Elapsed Time: " + elapsedTime() +
                      " seconds.");
  }

  // Time in seconds since button was clicked.

  private double elapsedTime() {
    double delta = System.currentTimeMillis() - start;
    return(delta/1000.0);
  }

  public static void main(String[] args) {
    JPanel preload;

    if (args.length == 0) {
      System.out.println("Must provide URL");
      System.exit(0);
    }
    if (args.length == 2 && args[1].equals("-preload")) {
      preload = new Preload(args[0], true);
    } else {
      preload = new Preload(args[0], false);
    }

    WindowUtilities.setNativeLookAndFeel();
    WindowUtilities.openInJFrame(preload, 1000, 750);
  }
}