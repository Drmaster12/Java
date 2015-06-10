/** Represents a ship in a small simulation system.
 *  Made to demonstrate OOP in Java.
 *
 * @author <A HREF="mailto:hall@coreservlets.com">
 *         Marty Hall</A>
 */

public class Ship {
  private double x=0.0, y=0.0, speed=1.0, direction=0.0;
  private String name;

  /** Build a ship with specified parameters. */

  public Ship(double x, double y, double speed,
              double direction, String name) {
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.direction = direction;
    this.name = name;
  }

  /** Build a ship with default values
   *  (x=0, y=0, speed=1.0, direction=0.0).
   */

  public Ship(String name) {
    this.name = name;
  }

  /** Moves ship one step at current speed/direction. */

  public void move() {
    moveInternal(1);
  }

  /** Moves N steps at current speed/direction.. */

  public void move(int steps) {
    moveInternal(steps);
  }

  private void moveInternal(int steps) {
    double angle = degreesToRadians(direction);
    x = x + steps * speed * Math.cos(angle);
    y = y + steps * speed * Math.sin(angle);
  }

  private double degreesToRadians(double degrees) {
    return(degrees * Math.PI / 180.0);
  }

  /** Reports location to standard output. We will see 
   *  later that instead of printing directly, you can
   *  make a toString method that returns some printable
   *  representation, then the external code can print
   *  the ship instance and it will automatically use
   *  the toString method. 
   */

  public void printLocation() {
    System.out.println(getName() + " is at (" + getX() +
                       "," + getY() + ").");
  }

  /** Gets current X location. */

  public double getX() {
    return(x);
  }

  /** Sets current X location. */

  public void setX(double x) {
    this.x = x;
  }

  /** Gets current Y location. */

  public double getY() {
    return(y);
  }

  /** Sets current Y location. */

  public void setY(double y) {
    this.y = y;
  }

  /** Gets current speed. */

  public double getSpeed() {
    return(speed);
  }

  /** Sets current speed. */

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  /** Gets current heading (0=East, 90=North, 180=West,
   *  270=South). I.e., uses standard math angles, <B>not</B>
   *  nautical system where 0=North, 90=East, etc.
   */

  public double getDirection() {
    return(direction);
  }

  /** Sets current direction (0=East, 90=North, 180=West,
   *  270=South). I.e., uses standard math angles,<B>not</B>
   *  nautical system where 0=North,90=East, etc.
   */

  public void setDirection(double direction) {
    this.direction = direction;
  }

  /** Gets Ship's name. Name cannot be changed once
   *  the Ship is instantiated (no corresponding setter method).
   */

  public String getName() {
    return(name);
  }
}