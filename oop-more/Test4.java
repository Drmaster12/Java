public class Test4 {
  public static void main(String[] args) {
    Ship4 s1 = new Ship4("Ship1");
    Ship4 s2 = new Ship4(0.0, 0.0, 2.0, 135.0, "Ship2");
    s1.move();
    s2.move(3);
    s1.printLocation();
    s2.printLocation();
  }
}