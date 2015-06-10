import java.applet.Applet;
import java.awt.*;

public class Scrollbars extends Applet {
  @Override
  public void init() {
    int i;
    setLayout(new GridLayout(1, 2));
    Panel left = new Panel(), right = new Panel();
    left.setLayout(new GridLayout(10, 1));
    for(i=5; i<55; i=i+5) {
      left.add(new Scrollbar(Scrollbar.HORIZONTAL, 50, i,
                             0, 100));
    }
    right.setLayout(new GridLayout(1, 10));
    for(i=5; i<55; i=i+5) {
      right.add(new Scrollbar(Scrollbar.VERTICAL, 50, i,
                              0, 100));
    }
    add(left);
    add(right);
  }
}