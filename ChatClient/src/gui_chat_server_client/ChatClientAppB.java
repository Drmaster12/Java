package gui_chat_server_client;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.net.UnknownHostException;

import chat_server_client_4.ChatClientThread;


/**
 * a threaded version of the gui chatclient to 
 * be able to receive input from the server
 *
 */

public class ChatClientAppB extends Applet  implements ActionListener, Runnable{
	   int port = 8080;
	   String host =  "192.168.0.11";
	   private Socket socket           = null;
	   private DataInputStream  streamIn   = null;
	   private DataOutputStream streamOut = null;
	   private ChatClientThread client    = null;
	   private TextArea  display = new TextArea();
	   private TextField input   = new TextField();
       private TextField portField = new TextField(""+ port);
       private TextField hostField = new TextField(host);
	   private Button    send    = new Button("Send");
	   private Button private_msg = new Button("Private_msg");
	   private Button   connect = new Button("Connect");
	   private Button    quit    = new Button("Bye");
	   private String    serverName = "localhost";//will get from browser
	   private int       serverPort = 8080;//will get from browser
	   Label hostLabel = new Label("Host:");
	   Label portLabel = new Label("Port:");
	   Panel mainPanel = new Panel();
	   Panel keys = new Panel();
	   Panel south = new Panel();
	   private boolean done = true;
	   private String line = "";
	
	
	public void init(){
        double[] colWeight = {1,1,1,1,1,1};
        double[] rowWeight = {5,1,1,1,1};
        int[] colWidth = {1,1,1,1,1,1};
        int[] rowHeight = {5,1,1,1,1};
        GridBagConstraints c = new GridBagConstraints();
        GridBagLayout gbl = new GridBagLayout();
        gbl.rowHeights = rowHeight;
        gbl.columnWidths = colWidth;
        gbl.columnWeights = colWeight;
        gbl.rowWeights = rowWeight;

        setBounds(100,100,400,600);
        setLayout(gbl);

        //add conversation box
        this.display.setSize(400, 400);
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 5;
        c.gridheight = 5;
        c.fill = 1;
        c.gridx = 0;
        c.gridy = 0;
        gbl.setConstraints(this.display, c);

        //host label
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = 1;
        c.gridx = 0;
        c.gridy = 6;
        gbl.setConstraints(this.hostLabel, c);

        //host input
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.fill = 1;
        c.gridx = 1;
        c.gridy = 6;
        gbl.setConstraints(this.hostField, c);

        //input text box
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 4;
        c.gridheight = 1;
        c.fill = 1;
        c.gridx = 0;
        c.gridy = 5;
        gbl.setConstraints(this.input, c);


        //row
        //port label
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = 1;
        c.gridx = 0;
        c.gridy = 7;
        gbl.setConstraints(this.portLabel, c);

        //port input
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.fill = 1;
        c.gridx = 1;
        c.gridy = 7;
        gbl.setConstraints(this.portField, c);

        //send button
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = 1;
        c.gridx = 4;
        c.gridy = 5;
        gbl.setConstraints(this.send, c);
        //private_msg button
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = 1;
        c.gridx = 4;
        c.gridy = 6;
        gbl.setConstraints(this.private_msg, c);

       
        //connect button
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = 1;
        c.gridx = 4;
        c.gridy = 7;
        gbl.setConstraints(this.connect, c);

        //disconnect button, shunned in the corner
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = 1;
        c.gridx = 4;
        c.gridy = 8;
        gbl.setConstraints(this.quit, c);

        add(this.display);
        add(this.input);
        add(this.send);
        add(this.private_msg);
        add(this.connect);
        add(this.quit);
        add(this.hostLabel);
        add(this.hostField);
        add(this.portLabel);
        add(this.portField);
   
        
        input.addActionListener((ActionListener) this);
        send.addActionListener((ActionListener) this);
        connect.addActionListener((ActionListener) this);
        quit.addActionListener((ActionListener) this);
        setVisible(true);

		

		

		
	}



	
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
		}
		
	}
	
	public void connect(String serverName, int serverPort){		
		done=false;
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
			done=true;
		}
		catch(IOException ioe){
			displayOutput(ioe.getMessage());
			done=true;
		}
		
	}

	public void disconnect(){
		done=true;
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
			//displayOutput(msg);
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
	private void private_msg(){
		//send private message
	//	displayOutput(input.getText());//testing buttons before testing connection and streams
	//	input.setText("");//testing buttons before testing connection and streams
		String msg = input.getText();
		try{
			//displayOutput(msg);
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
			streamIn =  new DataInputStream(socket.getInputStream());
		    //new Thread(this).start();//background thread to handle the input from the server...need to uncomment
		}
		catch(IOException ioe){
			
		}
	}
	
	public void close(){
		done=true;
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



	private String getParameter(String string) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(!done){
				line = streamIn.readUTF();
				displayOutput(line);
			}
		}
		catch(IOException ioe){
			done=true;
			displayOutput(ioe.getMessage());
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
