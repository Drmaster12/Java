import java.awt.*;
import java.awt.event.*;
import java.io.*;

/** A Frame that lets you draw circles with mouse clicks
 *  and then save the Frame and all circles to disk.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class SaveableFrame extends CloseableFrame
                           implements ActionListener {
  private static String serializeFilename ="SavedFrame.ser";
  private CirclePanel circlePanel;
  private Button clearButton, saveButton;

  /** Build a frame with CirclePanel and buttons. */

  public SaveableFrame() {
    super("SaveableFrame");
    setBackground(Color.white);
    setFont(new Font("Serif", Font.BOLD, 18));
    circlePanel = new CirclePanel();
    add("Center", circlePanel);
    Panel buttonPanel = new Panel();
    buttonPanel.setBackground(Color.lightGray);
    clearButton = new Button("Clear");
    saveButton = new Button("Save");
    buttonPanel.add(clearButton);
    buttonPanel.add(saveButton);
    add(buttonPanel, BorderLayout.SOUTH);
    clearButton.addActionListener(this);
    saveButton.addActionListener(this);
    setSize(600, 400);
    setVisible(true);
  }

  public void sendFrame(OutputStream rawOut) {
    try(ObjectOutputStream out =
          new ObjectOutputStream(rawOut)) { 
      out.writeObject(this);
    } catch(IOException ioe) {
      System.out.println("Error saving frame: " + ioe);
    }
  }

  public static SaveableFrame getFrame(InputStream rawIn) {
    SaveableFrame frame = null;
    try(ObjectInputStream in = new ObjectInputStream(rawIn)) {
      frame = (SaveableFrame)in.readObject();
      frame.setVisible(true);
      return(frame);
    } catch(IOException ioe) {
      System.out.println("Error reading file: " + ioe);
    } catch(ClassNotFoundException cnfe) {
      System.out.println("No such class: " + cnfe);
    }
    return(frame);
  }

  /** If "Clear" clicked, delete all existing circles. If "Save"
   *  clicked, save existing frame configuration (size,
   *  location, circles, etc.) to disk.
   */

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == clearButton) {
      circlePanel.removeAll();
      circlePanel.repaint();
    } else if (event.getSource() == saveButton) {
      try(FileOutputStream fileOut =
            new FileOutputStream("SavedFrame.ser")) {
        sendFrame(fileOut);
      } catch(IOException ioe) {
        System.out.println("IOException: " + ioe);
      }
    }
  }

  /** If a saved version exists, use it. Otherwise create a
   *  new one.
   */

  public static void main(String[] args) {
    @SuppressWarnings("unused") // frame is not really unused, since it pops up and you can interact with it.
    SaveableFrame frame;
    File serializeFile = new File(serializeFilename);
    if (serializeFile.exists()) {
      try(FileInputStream fileIn =
            new FileInputStream(serializeFile)) {
        frame = getFrame(fileIn);
      } catch(IOException ioe) {
        System.out.println("IOException: " + ioe);
      }
    } else {
      frame = new SaveableFrame();
    }
  }

}