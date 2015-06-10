import java.awt.*;

/** First version puts the action handlers in the button class. */

public class ActionExample1 extends CloseableFrame {
  public ActionExample1() {
    super("Handling Events in Component");
    setLayout(new FlowLayout());
    setFont(new Font("Serif", Font.BOLD, 18));
    add(new SetSizeButton(300, 200));
    add(new SetSizeButton(400, 300));
    add(new SetSizeButton(500, 400));
    setSize(400, 300);
    setVisible(true);
  }
  
  public static void main(String[] args) {
    new ActionExample1();
  }
}