/** An extension of SimpleCircle that can be moved around
 *  according to deltaX and deltaY values. Movement will
 *  continue in a given direction until the edge of the circle
 *  reaches a wall, when it will "bounce" and move in the other
 *  direction.
 *
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

public class MovingCircle extends SimpleCircle {
  private int deltaX, deltaY;

  public MovingCircle(int x, int y, int radius, int deltaX,
                      int deltaY) {
    super(x, y, radius);
    this.deltaX = deltaX;
    this.deltaY = deltaY;
  }

  public void move(int windowWidth, int windowHeight) {
    setX(getX() + getDeltaX());
    setY(getY() + getDeltaY());
    bounce(windowWidth, windowHeight);
  }

  private void bounce(int windowWidth, int windowHeight) {
    int x = getX(), y = getY(), radius = getRadius(),
        deltaX = getDeltaX(), deltaY = getDeltaY();
    if ((x - radius < 0) && (deltaX < 0)) {
      setDeltaX(-deltaX);
    } else if ((x + radius > windowWidth) && (deltaX > 0)) {
      setDeltaX(-deltaX);
    }
    if ((y -radius < 0) && (deltaY < 0)) {
      setDeltaY(-deltaY);
    } else if((y + radius > windowHeight) && (deltaY > 0)) {
      setDeltaY(-deltaY);
    }
  }

  public int getDeltaX() {
    return(deltaX);
  }

  public void setDeltaX(int deltaX) {
    this.deltaX = deltaX;
  }

  public int getDeltaY() {
    return(deltaY);
  }

  public void setDeltaY(int deltaY) {
    this.deltaY = deltaY;
  }
}