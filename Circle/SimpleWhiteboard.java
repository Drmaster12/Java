import java.awt.*;
import java.awt.event.*;
import java.awt.Panel;
/** An applet that lets you perform freehand drawing.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on JSF 2, PrimeFaces, Ajax, jQuery, GWT, Android,
 *  Spring, Hibernate, JPA, RESTful Web Services, Hadoop, 
 *  servlets, JSP, and Java 7 and Java 8 programming</a>.
 */

public class SimpleWhiteboard extends Panel {
      protected int lastX=0, lastY=0;



    public class Border extends Panel {
      public void init() {
    SimpleWhiteboard swb= new SimpleWhiteboard();
    add(new Button("North Wing"), BorderLayout.NORTH);
    add(new Button("South Wing"), BorderLayout.SOUTH);
    add(new Button("East Wing"), BorderLayout.EAST);
    add(new Button("West Wing"), BorderLayout.WEST);
    add(swb, BorderLayout.CENTER);
  }
  }


    public void Border() {
    setBackground(Color.GRAY);
    setForeground(Color.BLUE);
    addMouseListener(new PositionRecorder());
    addMouseMotionListener(new LineDrawer());
  }

    protected void record(int x, int y) {
    lastX = x;
    lastY = y;
  }


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


  public static void main(String[] args){
      
  }
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
