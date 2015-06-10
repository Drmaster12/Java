public class Person1Test {
  public static void main(String[] args) {
    Person1 p = new Person1();
    p.firstName = "Larry";
    p.lastName = "Ellison";
    // doSomethingWith(p);
    System.out.println("Person's first name: " +
                       p.firstName);
    System.out.println("Person's last name: " +
                       p.lastName);
  }
}
