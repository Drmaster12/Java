import java.io.IOException;
import java.net.*;

public class ConnectionHandler implements Runnable {
  private Socket socket;
  
  public ConnectionHandler(Socket socket) {
    this.socket = socket;
  }
  
  public void run() {
    try {
      handleConnection(socket);
    } catch(IOException ioe) {
      System.err.println("IOException: " + ioe);
      ioe.printStackTrace();
    }
  }
  
  public void handleConnection(Socket socket)
      throws IOException{
    // Do something with socket
  }
}
