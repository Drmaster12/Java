public class FrameExample3 extends CloseableFrame {
  public FrameExample3() {
    super("Frame Example 3");
    setSize(400, 300);
    setVisible(true);
  }
  
  public static void main(String[] args) {
    new FrameExample3();
  }
}