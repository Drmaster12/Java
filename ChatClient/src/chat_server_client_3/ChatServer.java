package chat_server_client_3;

import java.net.*;
import java.io.*;

/**
 * 	Version 3: 
 *  works with the same client as before (client version 1)
 *  Server  will remain 'open' for additional connection once a client has quit. 
 *  Server can handle multiple clients simultaneously. 
 *  The output from all connected clients will appear on the server's screen.
 *  The following are the same as the previous version: default constructor, start method, main method
 * */
public class ChatServer implements Runnable{
	
	private ServerSocket server = null;
	private Thread thread = null;
	private ChatServerThread client = null;
	private boolean done = true;//setting flag to true, it will get set to false inside run
	
	//same as previous version
	public ChatServer(int port){
		try{
			 System.out.println("Binding to port " + port + " please wait...");
			 server = new ServerSocket(port);
			 System.out.println("Server started: " + server);
			 start();
		}
		catch(IOException ioe){
			   System.out.println("error encountered: "+ioe.getMessage());
		}

	}

	@Override
	public void run() {
		while(thread!=null){
			try{
				System.out.println("Waiting for a client...");
				addThread(server.accept());
			}
			catch(IOException ioe){
				System.out.println("error accepting the client "+ioe.getMessage());
			}
		}
		
		
	}
	
	public void addThread(Socket socket){
		System.out.println("Client accepted on socket: "+socket);
		client = new ChatServerThread(this, socket);
		try{
			client.open();//openstream for the ChatServerThread client
			client.start();//start running the ChatServerhread client
		}
		catch(IOException ioe){
			System.out.println("Error adding thread "+ioe.getMessage());
		}
	}
	
	//same as previous version  (i.e. same as version 2)
	public void start(){
		if(thread==null){
			thread = new Thread(this);
			thread.start();
		}
	}
	
	//same as previous version (i.e. same as version 2)	
	public void stop(){
		if(thread !=null){
			thread=null;
		}
	}
	//same as previous version
	public static void main(String[] args){
		   ChatServer server = null;
		   if(args.length !=1){
			   System.out.println("To run the server you need to specify a port");
		   }
		   else{
			   server = new ChatServer(Integer.parseInt(args[0]));
			   
		   }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
