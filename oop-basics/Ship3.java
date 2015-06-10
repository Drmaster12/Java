// Give Ship3 a constructor to let the instance variables
// be specified when the object is created.

public class Ship3 {
  public double x, y, speed, direction;
  public String name;

  public Ship3(double x, double y, double speed,
               double direction, String name) {
    this.x = x; // "this" differentiates instance vars
    this.y = y; //  from local vars.
    this.speed = speed;
    this.direction = direction;
    this.name = name;
  }

  private double degreesToRadians(double degrees) {
    return(degrees * Math.PI / 180.0);
  }

 public void move() {
    double angle = degreesToRadians(direction);
    x = x + speed * Math.cos(angle);
    y = y + speed * Math.sin(angle);
  }

  public void printLocation() {
    System.out.println(name + " is at " +
                       "(" + x + "," + y + ").");
  }
}