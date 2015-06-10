import java.awt.*;
import java.awt.event.*;

public class SetSizeButton extends Button
                           implements ActionListener {
  private int width, height;

  public SetSizeButton(int width, int height) {
    super("Resize to " + width + "x" + height);
    this.width = width;
    this.height = height;
    addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    getParent().setSize(width, height);
    getParent().invalidate();
    getParent().validate();
  }
}