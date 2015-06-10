/** A fast Ship. Red and going 20 knots by default. 
 *  Created to illustrate inheritance and the need for "super".
 */

public class Speedboat extends Ship {
  private String color = "red";

  /** Builds a red Speedboat going N at 20 knots. */

  public Speedboat(String name) {
    super(name);
    setSpeed(20);
  }

  /** Builds a speedboat with specified parameters. */

  public Speedboat(double x, double y, double speed,
                   double direction, String name,
                   String color) {
    super(x, y, speed, direction, name);
    setColor(color);
  }

  /** Reports location. Override version from Ship. */

  @Override // Optional -- discussed later
  public void printLocation() {
    System.out.print(getColor().toUpperCase() + " ");
    super.printLocation();
  }

  /** Gets the Speedboat's color. */

  public String getColor() {
    return(color);
  }

  /** Sets the Speedboat's color. */

  public void setColor(String colorName) {
    color = colorName;
  }
}