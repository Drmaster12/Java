public class Person3 {
  public String firstName, lastName;
  
  public Person3(String initialFirstName,
                 String initialLastName) {
    firstName = initialFirstName;
    lastName = initialLastName;
  }
  
  public String getFullName() {
    return(firstName + " " + lastName);
  }
}
