import java.awt.*;
import java.awt.event.*;
import java.awt.Font;


public class Whiteboard extends SimpleWhiteboard {
  protected FontMetrics fm;

  public static void main(String[] args){
  }
  public Whiteboard() {
    super();
    Font font = new Font("Serif", Font.BOLD, 20);
    setFont(font);
    fm = getFontMetrics(font);
    addKeyListener(new CharDrawer());
  }

        

  private class CharDrawer extends KeyAdapter {
    // When user types a printable character,
    // draw it and shift position rightwards.

    @Override
    public void keyTyped(KeyEvent event) {
      String s = String.valueOf(event.getKeyChar());
      getGraphics().drawString(s, lastX, lastY);
      record(lastX + fm.stringWidth(s), lastY);
    }
  }
}

