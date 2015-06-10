/** Represents people that work at a company. */

public class Employee extends Person {
  private int employeeId;
  private String companyName;
  
  public Employee(String firstName, String lastName,
                  int employeeId, String companyName) {
    super(firstName, lastName);
    this.employeeId = employeeId;
    this.companyName = companyName;
  }
  
  /** The ID of the employee, with the assumption that
   *  lower numbers are people that started working at
   *  the company earlier than those with higher ids.
   */
  public int getEmployeeId() {
    return (employeeId);
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  /** The name of the company that the person 
   *  works for. 
   */
  public String getCompanyName() {
    return (companyName);
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
}
