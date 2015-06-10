package chat_server_client_3;

import java.net.*;
import java.io.*;

/**
 * 
 * Separate Thread Class used by ChatServer Version3
 * handles each incoming Chat on a separate thread
 *
 */
public class ChatServerThread extends Thread{

	private Socket socket = null;
	private ChatServer server = null;
	private int ID = -1;
	private DataInputStream streamIn = null;
	boolean done = true;
	
	
	public ChatServerThread(ChatServer _server, Socket _socket){
		server = _server; 
		socket = _socket;
		ID = socket.getPort();
		System.out.println("Chat Server Thread Info: server"+ server + " socket "+ socket + " ID "+ ID);
	}
	
	public void run(){
		try{
			while(ID!= -1){//only call try to getInput if there is a valid ID (i.e. remotePort is connected to socket)
			getInput();//get input from client
			close();//close the socket and stream
			}
		}
		catch(IOException ioe){
			System.out.println("Error in current thread " + ioe.getMessage());
			
		}
		
	}
	
	public void getInput(){
		done = false;//setting the flag done to false since getInput was called
		while(!done){
			try{
				String line = streamIn.readUTF();
			 	System.out.println("Message from " + ID+ ": " + line);
				done = line.equals("bye");
			}
			catch(IOException ioe){
				System.out.println("Error getting input" + ioe.getMessage());
				done=true;//set flag done to true if was an error reading input
			}
		}
		System.out.println("ID "+ID+" has left");
		ID=-1;//set id -1 since the user left	
	}
	
	public void open() throws IOException{
		streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	}
	
	public void close() throws IOException{
		if(streamIn != null){
			streamIn.close();
		}

		if(socket != null){
			socket.close();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
