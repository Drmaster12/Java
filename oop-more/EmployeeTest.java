public class EmployeeTest {
  public static void main(String[] args) {
    Employee e = new Employee("Steve", "Balmer", 2, "Microsoft");
    // doSomethingWith(e);
    System.out.println("Employee's full name: " +
                       e.getFullName());
    System.out.println("Employee's ID: " +
                       e.getEmployeeId());
    System.out.println("Employee's company: " +
                      e.getCompanyName());
    // TODO: Array with random first/last names
  }
}
