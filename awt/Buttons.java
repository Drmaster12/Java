import java.applet.Applet;
import java.awt.*;

public class Buttons extends Applet {
  private Button button1, button2, button3;

  @Override
  public void init() {
    button1 = new Button("Button One");
    button2 = new Button("Button Two");
    button3 = new Button("Button Three");
    add(button1);
    add(button2);
    add(button3);
  }
}