public class Person4 {
  public String firstName, lastName;
  
  public Person4(String firstName,
                 String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
  
  public String getFullName() {
    return(firstName + " " + lastName);
  }
}
