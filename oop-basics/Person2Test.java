public class Person2Test {
  public static void main(String[] args) {
    Person2 p = new Person2();
    p.firstName = "Bill";
    p.lastName = "Gates";
    // doSomethingWith(p);
    System.out.println("Person's full name: " +
                       p.getFullName());
  }
}
