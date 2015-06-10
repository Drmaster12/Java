import java.awt.*;
import java.awt.event.*;


public class SimpleWhiteboard extends Panel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
protected int lastX=0, lastY=0;

public static void main(String[] args){
}
  public SimpleWhiteboard() {

    setBackground(Color.GRAY);
    setForeground(Color.BLUE);
    addMouseListener(new PositionRecorder());
    addMouseMotionListener(new LineDrawer());
    
  
  }

    /**
     *
     * @param x
     * @param y
     */
    protected void record(int x, int y) {
    lastX = x;
    lastY = y;
  }

  // Record position that mouse entered window or
  // where user pressed mouse button.


  private class PositionRecorder extends MouseAdapter {
    @Override
    public void mouseEntered(MouseEvent event) {
      requestFocus(); // Plan ahead for typing
      record(event.getX(), event.getY());
    }

    @Override
    public void mousePressed(MouseEvent event) {
      record(event.getX(), event.getY());
    }
  }

  // As user drags mouse, connect subsequent positions
  // with short line segments.

  private class LineDrawer extends MouseMotionAdapter {
    @Override
    public void mouseDragged(MouseEvent event) {
      int x = event.getX();
      int y = event.getY();
      Graphics g = getGraphics();
      g.drawLine(lastX, lastY, x, y);
      record(x, y);
    }
  }
}
