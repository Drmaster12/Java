import java.applet.Applet;
import java.awt.*;

/** A class that incorrectly tries to load an image and draw an
 *  outline around it. Don't try this at home.
 *  
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class ImageBox extends Applet {
  private int imageWidth, imageHeight;
  private Image image;

  @Override
  public void init() {
    String imageName = getParameter("IMAGE");
    if (imageName != null) {
      image = getImage(getCodeBase(), imageName);
    } else {
      image = getImage(getCodeBase(), "error.gif");
    }
    setBackground(Color.WHITE);

    // The following is wrong, since the image won't be done
    // loading, and -1 will be returned.
    imageWidth = image.getWidth(this);
    imageHeight = image.getHeight(this);
  }

  @Override
  public void paint(Graphics g) {
    g.drawImage(image, 0, 0, this);
    g.drawRect(0, 0, imageWidth, imageHeight);
  }
}