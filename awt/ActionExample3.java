import java.awt.*;
import java.awt.event.*;

/** Third version puts the action handlers in inner classes. */

public class ActionExample3 extends CloseableFrame {
  private Button button1, button2, button3;

  public ActionExample3() {
    super("Handling Events with Inner Classes");
    setLayout(new FlowLayout());
    setFont(new Font("Serif", Font.BOLD, 18));
    button1 = new Button("Resize to 300x200");
    button2 = new Button("Resize to 400x300");
    button3 = new Button("Resize to 500x400");
    button1.addActionListener(new ResizeHandler(300, 200));
    button2.addActionListener(new ResizeHandler(400, 300));
    button3.addActionListener(new ResizeHandler(500, 400));
    add(button1);
    add(button2);
    add(button3);
    setSize(400, 300);
    setVisible(true);
  }

  private void updateLayout(int width, int height) {
    setSize(width, height);
    invalidate();
    validate();
  }
  
  private class ResizeHandler implements ActionListener {
    private int width, height;
    
    public ResizeHandler(int width, int height) {
      this.width= width;
      this.height = height;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
      updateLayout(width, height);
    }
  }
  
  public static void main(String[] args) {
    new ActionExample3();
  }
}