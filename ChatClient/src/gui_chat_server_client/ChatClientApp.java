package gui_chat_server_client;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import chat_server_client_4.ChatClient;
import chat_server_client_4.ChatClientThread;

/**
 * a non-threaded version of the gui chatclient to 
 * it does not receive input from the server
 * The next version,ChatClientAppB, creates a new thread and is able to display input from the server 
 *
 */


public class ChatClientApp extends Applet implements ActionListener{
	
	   private Socket socket              = null;
	   //private DataInputStream  streamIn   = null;
	   private DataOutputStream streamOut = null;
	   private ChatClientThread client    = null;
	   private TextArea  display = new TextArea();
	   private TextField input   = new TextField();
	   private Button    send    = new Button("Send"), connect = new Button("Connect"),
	                     quit    = new Button("Bye");
	   private String    serverName = "localhost";
	   private int       serverPort = 8080;
	   Panel mainPanel = new Panel();
	   Panel keys = new Panel();
	   Panel south = new Panel();
		
	
	
	public void init(){
		
		mainPanel.setLayout(new BorderLayout());
		
		keys.setLayout(new GridLayout(1,2));
		quit.setEnabled(false);
		connect.setEnabled(true);
		quit.addActionListener(this);
		connect.addActionListener(this);
		keys.add(quit);
		keys.add(connect);
		
		south.setLayout(new BorderLayout());
		south.add("West", keys);
		south.add("Center", input);
		send.setEnabled(false);
		send.addActionListener(this);
		south.add("East", send);
		
		Label title = new Label("Our Chat", Label.CENTER);
		title.setFont(new Font("Helvetica", Font.BOLD, 14));
		
		mainPanel.add(title, BorderLayout.NORTH);
		display.setEditable(false);//set editable to false so users can't type there
		display.setBackground(Color.blue);//so it isnt gray
		mainPanel.add(display, BorderLayout.CENTER);
		mainPanel.add(south, BorderLayout.SOUTH);
		
		add(mainPanel);
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==quit){
			disconnect();
		}
		else if(e.getSource()==connect){
			connect(serverName, serverPort);
		}
		else if(e.getSource()==send){
			send();
			//input.requestFocus();
		}
		
	}
	
	public void connect(String serverName, int serverPort){		
		displayOutput("call to connect was made");
		//create new socket, open stream, disable connect button, enable send and quit button
		try{
			socket=new Socket(serverName, serverPort);
			displayOutput("Connected: "+ socket);
			open();
			send.setEnabled(true);
			quit.setEnabled(true);
			connect.setEnabled(false);
		}
		catch(UnknownHostException uhe){
			displayOutput(uhe.getMessage());
		}
		catch(IOException ioe){
			displayOutput(ioe.getMessage());
		}
		
	}

	public void disconnect(){
		input.setText("bye");
		send();
		quit.setEnabled(false);
		connect.setEnabled(true);
		send.setEnabled(false);
	}
	
	private void send(){
		//send message
	//	displayOutput(input.getText());//testing buttons before testing connection and streams
	//	input.setText("");//testing buttons before testing connection and streams
		String msg = input.getText();
		try{
			displayOutput(msg);
			streamOut.writeUTF(msg);
			streamOut.flush();
			if(msg.equalsIgnoreCase("bye")){
				quit.setEnabled(false);
				connect.setEnabled(true);
				send.setEnabled(false);
				close();
			}
			input.setText("");
		}
		catch(IOException ioe){
			displayOutput("Sending error "+ioe.getMessage());
			close();
		}
	}
	
	public void open(){
		try{
			streamOut = new DataOutputStream(socket.getOutputStream());
			//streamIn =  new DataInputStream(socket.getInputStream());
		}
		catch(IOException ioe){
			
		}
	}
	
	public void close(){
		try{
			if(streamOut !=null){
				streamOut.close();
			}
			if(socket !=null){
				socket.close();
			}
			
		}
		catch(IOException ioe){
			displayOutput("Error closing");
			client.close();
			client = null;
		}
	}
	
	public void displayOutput(String msg){
		display.append(msg +"\n");
	}
	
	public void handle(String msg){
		if(msg.equals("bye")){
			displayOutput("GOODBYE");
			close();
		}
		else{
			displayOutput(msg);
		}
	}
	public void getParameters(){
		serverName = getParameter("host");
		serverPort = Integer.parseInt(getParameter("port"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
