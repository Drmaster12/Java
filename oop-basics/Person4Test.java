public class Person4Test {
  public static void main(String[] args) {
    Person4[] people = new Person4[20];
    for(int i=0; i<people.length; i++) {
      people[i] = 
        new Person4(NameUtils.randomFirstName(),
                    NameUtils.randomLastName());
    }
    for(Person4 person: people) {
      System.out.println("Person's full name: " +
                         person.getFullName());
    }
  }
}
