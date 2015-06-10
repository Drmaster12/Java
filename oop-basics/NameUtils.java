public class NameUtils {
  public static String randomFirstName() {
    int num = (int)(Math.random()*1000);
    return("John" + num);
  }
  
  public static String randomLastName() {
    int num = (int)(Math.random()*1000);
    return("Smith" + num);
  }
}
