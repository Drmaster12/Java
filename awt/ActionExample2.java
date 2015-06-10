import java.awt.*;
import java.awt.event.*;

/** Second version puts the action handlers in the main class. */

public class ActionExample2 extends CloseableFrame
                            implements ActionListener {
  private Button button1, button2, button3;

  public ActionExample2() {
    super("Handling Events in Main Class via Interface");
    setLayout(new FlowLayout());
    setFont(new Font("Serif", Font.BOLD, 18));
    button1 = new Button("Resize to 300x200");
    button2 = new Button("Resize to 400x300");
    button3 = new Button("Resize to 500x400");
    button1.addActionListener(this);
    button2.addActionListener(this);
    button3.addActionListener(this);
    add(button1);
    add(button2);
    add(button3);
    setSize(400, 300);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == button1) {
      updateLayout(300, 200);
    } else if (event.getSource() == button2) {
      updateLayout(400, 300);
    } else if (event.getSource() == button3) {
      updateLayout(500, 400);
    }
  }

  private void updateLayout(int width, int height) {
    setSize(width, height);
    invalidate();
    validate();
  }
  
  public static void main(String[] args) {
    new ActionExample2();
  }
}