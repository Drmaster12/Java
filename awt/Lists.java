import java.awt.*;

public class Lists extends CloseableFrame {
  public static void main(String[] args) {
    new Lists();
  }

  public Lists() {
    super("Lists");
    setLayout(new FlowLayout());
    setBackground(Color.LIGHT_GRAY);
    setFont(new Font("SansSerif", Font.BOLD, 18));
    List list1 = new List(3, false);
    list1.add("Vanilla");
    list1.add("Chocolate");
    list1.add("Strawberry");
    add(list1);
    List list2 = new List(3, true);
    list2.add("Colored Sprinkles");
    list2.add("Cashews");
    list2.add("Kiwi");
    add(list2);
    pack();
    setVisible(true);
  }
}