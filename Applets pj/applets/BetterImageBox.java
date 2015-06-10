import java.applet.Applet;
import java.awt.*;

/** This version fixes the problems associated with ImageBox by
 *  using a MediaTracker to be sure the image is loaded before
 *  you try to get its dimensions.
 *  
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class BetterImageBox extends Applet {
  private int imageWidth, imageHeight;
  private Image image;

  @Override // I'll explain later what this optional tag does
  public void init() {
    String imageName = getParameter("IMAGE");
    if (imageName != null) {
      image = getImage(getCodeBase(), imageName);
    } else {
      image = getImage(getCodeBase(), "error.gif");
    }
    setBackground(Color.white);
    MediaTracker tracker = new MediaTracker(this);
    tracker.addImage(image, 0);
    try {
      tracker.waitForAll();
    } catch(InterruptedException ie) {}
    if (tracker.isErrorAny()) {
      System.out.println("Error while loading image");
    }

    // This is safe: image is fully loaded
    imageWidth = image.getWidth(this);
    imageHeight = image.getHeight(this);
  }

  @Override // I'll explain later what this optional tag does
  public void paint(Graphics g) {
    g.drawImage(image, 0, 0, this);
    g.drawRect(0, 0, imageWidth, imageHeight);
  }
}