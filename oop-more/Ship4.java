// A variation of the three previous examples. This one introduces overloading.
// The next examples make the instance variables private and add JavaDoc.

public class Ship4 {
  public double x=0.0, y=0.0, speed=1.0, direction=0.0;
  public String name;

  // This constructor takes the parameters explicitly.

  public Ship4(double x, double y, double speed,
               double direction, String name) {
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.direction = direction;
    this.name = name;
  }

  // This constructor requires a name but lets you accept
  // the default values for x, y, speed, and direction.

  public Ship4(String name) {
    this.name = name;
  }

  private double degreesToRadians(double degrees) {
    return(degrees * Math.PI / 180.0);
  }

  // Move one step.

  public void move() {
    move(1);
  }

  // Move N steps.

 public void move(int steps) {
    double angle = degreesToRadians(direction);
    x = x + steps * speed * Math.cos(angle);
    y = y + steps * speed * Math.sin(angle);
  }

  public void printLocation() {
    System.out.println(name + " is at (" + x + "," + y + ").");
  }
}