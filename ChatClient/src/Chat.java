import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.io.PrintWriter;
import java.util.Scanner;

public class Chat extends Frame implements Runnable, WindowListener, ActionListener, ItemListener
{   

	 int port = 8080;
	    String host =  "127.0.0.1";
    //GUI objects
    TextField ipAddressField = new TextField();
    TextField portField = new TextField(""+port); 
    TextField hostField = new TextField(host);
    TextField inputBox = new TextField();
    static TextArea conversationBox = new TextArea();
    Button startServerButton = new Button("Start Server");
    Button sendButton = new Button("Send");
    Button changeHost = new Button("Change Host");
    Button changePort = new Button("Change Port");
    Button connectButton = new Button("Connect");
    Button disconnectButton = new Button("Disconnect");
    Label hostLabel = new Label("Host:");
    Label portLabel = new Label("Port:");
    MenuBar mb = new MenuBar();
    Menu colorMenu = new Menu("Color");
    CheckboxMenuItem redItem = new CheckboxMenuItem("Red");
    CheckboxMenuItem blueItem = new CheckboxMenuItem("Blue");
    CheckboxMenuItem orangeItem = new CheckboxMenuItem("Orange");
    CheckboxMenuItem blackItem = new CheckboxMenuItem("Black");

    //runtime variables
    Color color = Color.red;
    volatile boolean kill = false;
    volatile boolean isServer = false;
    volatile boolean isClient = false;
    String connections[];

    //messaging variables
    String message;
    Scanner in;
    PrintWriter out;

    public static void main(String [] args)
    {
        new Chat();
    }

    ServerSocket server;
    Socket s;
    //gui components

    protected Chat()
    {
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
        this.conversationBox.setSize(400, 400);
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 5;
        c.gridheight = 5;
        c.fill = 1;
        c.gridx = 0;
        c.gridy = 0;
        gbl.setConstraints(this.conversationBox, c);

        //input text box
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 4;
        c.gridheight = 1;
        c.fill = 1;
        c.gridx = 0;
        c.gridy = 5;
        gbl.setConstraints(this.inputBox, c);

        //send button
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = 1;
        c.gridx = 4;
        c.gridy = 5;
        gbl.setConstraints(this.sendButton, c);

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

        //change host button
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = 1;
        c.gridx = 3;
        c.gridy = 6;
        gbl.setConstraints(this.changeHost, c);

        //start server button
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = 1;
        c.gridx = 4;
        c.gridy = 6;
        gbl.setConstraints(this.startServerButton, c);

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

        //change port button
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = 1;
        c.gridx = 3;
        c.gridy = 7;
        gbl.setConstraints(this.changePort, c);

        //connect button
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = 1;
        c.gridx = 4;
        c.gridy = 7;
        gbl.setConstraints(this.connectButton, c);

        //disconnect button, shunned in the corner
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = 1;
        c.gridx = 4;
        c.gridy = 8;
        gbl.setConstraints(this.disconnectButton, c);

        mb.add(colorMenu);
        colorMenu.add(redItem);
        colorMenu.add(blueItem);
        colorMenu.add(orangeItem);
        colorMenu.add(blackItem);


        add(this.conversationBox);
        add(this.inputBox);
        add(this.sendButton);
        add(this.hostLabel);
        add(this.hostField);
        add(this.changeHost);
        add(this.startServerButton);
        add(this.portLabel);
        add(this.portField);
        add(this.changePort);
        add(this.connectButton);
        add(this.disconnectButton);

        inputBox.addActionListener(this);
        sendButton.addActionListener(this);
        changeHost.addActionListener(this);
        changePort.addActionListener(this);
        startServerButton.addActionListener(this);
        connectButton.addActionListener(this);
        disconnectButton.addActionListener(this);
        hostField.addActionListener(this);
        portField.addActionListener(this);
        redItem.addItemListener(this);
        blueItem.addItemListener(this);
        orangeItem.addItemListener(this);
        blackItem.addItemListener(this);

        setMenuBar(mb);
        addWindowListener(this);
        setResizable(true);
        pack();
        setVisible(true);

        new Thread(this).start();
    }

    public void run()
    {   
        conversationBox.appendText("Session Start.\n");
        inputBox.requestFocus();

        while (!kill)
        {
            if (isServer)
            {
                conversationBox.appendText("Server starting on port " + port + "\n");
                conversationBox.appendText("Waiting for clients...\n");
                startServer();
            }
            if (isClient)
            {
                conversationBox.appendText("Starting connection to host " + host + " on port " + port + "\n");
                startClient();
            }
        }       

    }

    public void startClient()
    {
        try
        {
            Socket c = new Socket(host, port);
            in = new Scanner(c.getInputStream());
            out = new PrintWriter(c.getOutputStream());
            while (true)
            {
                if (in.hasNext())
                {
                    Chat.conversationBox.appendText("You Said: " + message);
                    out.println("Client Said: " + message);
                    out.flush();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void startServer()
    {
        try
        {
            server = new ServerSocket(port);
            while (true)
            {

                s = server.accept();

                s.getInputStream();

                conversationBox.appendText("Client connected from " + s.getLocalAddress().getHostName() + "\n");

                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

                char buffer[] = new char[1024];
                conversationBox.appendText("Reading....");
                while(br.read(buffer) > 0)
                {
                    conversationBox.appendText("Client Say : " + new String(buffer));
                    bw.write(buffer);
                    bw.flush();
                }
                conversationBox.appendText("Reading Done....");
            }
        }
        catch (Exception e)
        {
            conversationBox.appendText("An error occurred.\n");
            e.printStackTrace();
            isServer = false;
            reEnableAll();
        }
    }

    public void actionPerformed(ActionEvent e) throws NumberFormatException
    {
        Object o = e.getSource();

        if (o == sendButton || o == inputBox)
        {
            if(inputBox.getText() != "")
            {
                out.println(inputBox.getText());
                inputBox.setText("");
            }
        }
        if (o == changeHost || o == hostField)
        {
            if (hostField.getText() != "" && hostField.getText() != host)
            {
                host = hostField.getText();
                conversationBox.appendText("Host changed to " + host + "\n");
            }
        }
        if (o == changePort || o == portField)
        {
            if (portField.getText() != "" && Integer.valueOf(portField.getText()) != port)
            {
                try
                {
                    port = Integer.valueOf(portField.getText());
                    conversationBox.appendText("Port changed to " + port + "\n");
                }
                catch(NumberFormatException up)
                {
                    throw up; //blargh enter a real value
                }
            }
        }
        if (o == startServerButton)
        {
            isServer = true;
            isClient = false;
            startServerButton.enable(false);
            connectButton.enable(false);
            changeHost.enable(false);
            changePort.enable(false);
            hostField.enable(false);
            portField.enable(false);
        }
        if (o == connectButton)
        {
            isServer = false;
            isClient = true;
            startServerButton.enable(false);
        }
        inputBox.requestFocus();
    }

    public void reEnableAll()
    {
        startServerButton.enable(true);
        connectButton.enable(true);
        changeHost.enable(true);
        changePort.enable(true);
        hostField.enable(true);
        portField.enable(true);
    }

    public void windowClosing(WindowEvent e)
    {
        removeWindowListener(this);
        inputBox.removeActionListener(this);
        sendButton.removeActionListener(this);
        changeHost.removeActionListener(this);
        changePort.removeActionListener(this);
        startServerButton.removeActionListener(this);
        connectButton.removeActionListener(this);
        disconnectButton.removeActionListener(this);
        dispose();
        System.exit(0);
    }
    public void windowClosed(WindowEvent e){}
    public void windowOpened(WindowEvent e){}
    public void windowActivated(WindowEvent e){}
    public void windowDeactivated(WindowEvent e){}
    public void windowIconified(WindowEvent e){}
    public void windowDeiconified(WindowEvent e){}

    public void itemStateChanged(ItemEvent e)
    {
        Object o = e.getSource();
        if (o == redItem)
        {
            redItem.setState(true);
            blueItem.setState(false);
            orangeItem.setState(false);
            blackItem.setState(false);
            color = Color.red;
        }

        if (o == blueItem)
        {
            redItem.setState(false);
            blueItem.setState(true);
            orangeItem.setState(false);
            blackItem.setState(false);
            color = Color.red;          
        }

        if (o == orangeItem)
        {
            redItem.setState(false);
            blueItem.setState(false);
            orangeItem.setState(true);
            blackItem.setState(false);
            color = Color.red;          
        }

        if (o == blackItem)
        {
            redItem.setState(false);
            blueItem.setState(false);
            orangeItem.setState(false);
            blackItem.setState(true);
            color = Color.red;          
        }
    }
}