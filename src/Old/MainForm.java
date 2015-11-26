
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javafx.geometry.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class MainForm 
{
	JFrame frame;
	private Caller caller;
	private Connection connection;
	private CallListener callListener;
	private CallListenerThread callListenerThread;
	private CommandListenerThread commandListenerThread;
	
	
	private JButton connectButton= new JButton("Connect");
	private JButton disconnectButton= new JButton("Disconnect");
	private JButton sendButton= new JButton("Send");
	private JButton applyButton= new JButton("Apply");
	
	private JTextArea IncomingMessage=new JTextArea("Text");
	private JLabel remoteLogin= new JLabel("remote login: ");
	private JTextField remoteLoginField= new JTextField(10);
	private JLabel remoteAddr= new JLabel("remote addr: ");
	private JTextField remoteAddrField= new JTextField(10);
	private JLabel localLogin= new JLabel("local login: ");
	private JTextField localLoginField= new JTextField(10);

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
								{
									public void run() 
									{
										try 
										{
											MainForm window = new MainForm();
											window.frame.setVisible(true);
										}
										catch (Exception e) 
										{
											e.printStackTrace();
										}
									}
								});
	}
	
	private MainForm()
	{
		creationMainWindow();
	}
	
	private void creationMainWindow()
	{
		frame= new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		Dimension Screensize=Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((Screensize.height)/2, (Screensize.width)/6);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.setTitle("KILL me");
		
		
		
		disconnectButton.setEnabled(false);
		sendButton.setEnabled(false);
		
		
		JPanel leftSideTopPanel= new JPanel();
		leftSideTopPanel.setLayout(new BoxLayout(leftSideTopPanel,BoxLayout.Y_AXIS));
		
		JPanel leftSideTopPanelTextLabel= new JPanel();
		leftSideTopPanelTextLabel.setLayout(new BoxLayout(leftSideTopPanelTextLabel,BoxLayout.X_AXIS));
		
		
		//applyButton= new JButton("Apply");

		
		JPanel leftSideTopPanelApplyButton= new JPanel();
		leftSideTopPanelApplyButton.setLayout(new BorderLayout());
		leftSideTopPanelApplyButton.add(applyButton,BorderLayout.LINE_START);
		
		leftSideTopPanelTextLabel.add(localLogin);
		leftSideTopPanelTextLabel.add(localLoginField);
		leftSideTopPanel.add(leftSideTopPanelTextLabel);
		leftSideTopPanel.add(leftSideTopPanelApplyButton);
		
		
		
		
		JPanel rightSideTopPanel= new JPanel();
		rightSideTopPanel.setLayout(new BoxLayout(rightSideTopPanel,BoxLayout.Y_AXIS));
		
		JPanel rightSideTopPanelRemoteLogin= new JPanel();
		rightSideTopPanelRemoteLogin.setLayout(new BoxLayout(rightSideTopPanelRemoteLogin,BoxLayout.X_AXIS));
		

		//disconnectButton= new JButton("Disconnect");

		rightSideTopPanelRemoteLogin.add(remoteLogin);
		rightSideTopPanelRemoteLogin.add(remoteLoginField);
		rightSideTopPanelRemoteLogin.add(disconnectButton);
		
		
		JPanel rightSideTopPanelRemoteAddr= new JPanel();
		rightSideTopPanelRemoteAddr.setLayout(new BoxLayout(rightSideTopPanelRemoteAddr,BoxLayout.X_AXIS));
		

		//connectButton= new JButton("Connect");
		
		
		/*connectButton.addActionListener(new ActionListener() 
		 {
	            public void actionPerformed(ActionEvent e) 
	            {
	                connectButton.setEnabled(false);
	                disconnectButton.setEnabled(true);
	                sendButton.setEnabled(true);
	                applyButton.setEnabled(true);
	                remoteLoginField.getText();

	            }
	        });*/
		 
		rightSideTopPanelRemoteAddr.add(remoteAddr);
		rightSideTopPanelRemoteAddr.add(remoteAddrField);
		rightSideTopPanelRemoteAddr.add(connectButton);
		
		rightSideTopPanel.add(rightSideTopPanelRemoteLogin);
		rightSideTopPanel.add(rightSideTopPanelRemoteAddr);
		
		
		JPanel topPanel= new JPanel();
		topPanel.setLayout(new BorderLayout());		
		
		topPanel.add(leftSideTopPanel,BorderLayout.LINE_START);
		topPanel.add(rightSideTopPanel,BorderLayout.LINE_END);
		/*topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.X_AXIS));
		topPanel.add(leftSideTopPanel);
		topPanel.add(rightSideTopPanel);*/
		//leftSideTopPanel.setMaximumSize(new Dimension(100,100));
		//rightSideTopPanel.setMaximumSize(new Dimension(100,100));
		topPanel.setMaximumSize(new Dimension(Screensize.width,60));


		
		
		
		JPanel CentralPanel= new JPanel();
		CentralPanel.setLayout(new BoxLayout(CentralPanel,BoxLayout.LINE_AXIS));
		
		
		IncomingMessage.setLineWrap(true);
		IncomingMessage.setWrapStyleWord(true);
		IncomingMessage.setAutoscrolls(true);
		//IncomingMessage.setSize(1000, 1000);
		//IncomingMessage.setRows(100);
		IncomingMessage.setLineWrap(true);
		
		
		   //JScrollPane scrollPane = new JScrollPane(IncomingMessage);
		  // scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 //  scrollPane.add(IncomingMessage);
		   
		   
		IncomingMessage.setBackground(new Color(0,0,0));
		IncomingMessage.setForeground(new Color(255,100,20));
		IncomingMessage.setMaximumSize(new Dimension(frame.getWidth(),frame.getHeight()-107));
		//JLabel IncomingMessage=new JLabel("Hi");
		JScrollPane scrollPane = new JScrollPane(IncomingMessage);
		
		CentralPanel.add(scrollPane);
		
	
		
		
		JPanel mergeTopCenter= new JPanel();
		mergeTopCenter.setLayout(new BorderLayout(5,5));
		//mergeTopCenter.setLayout(new BoxLayout(mergeTopCenter,BoxLayout.Y_AXIS));
		
		mergeTopCenter.add(topPanel,BorderLayout.PAGE_START);
		mergeTopCenter.add(CentralPanel,BorderLayout.CENTER);
		
		/*mergeTopCenter.setLayout(new GridLayout());		
		
		mergeTopCenter.add(topPanel,new GridLayout(0,1));		
		
		mergeTopCenter.add(CentralPanel,new GridLayout());*/
		
		/*mergeTopCenter.add(topPanel,new GridBagConstraints(0,0,1,1,1,1,
		GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
		new Insets(2,2,2,2),0,0));*/
		
		
		frame.add(mergeTopCenter,BorderLayout.CENTER);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//sendButton = new JButton("Send");
		sendButton.setBackground(new Color(0,0,0));
		sendButton.setForeground(new Color(255,100,20));
		//JPanel placeForSendButton=new JPanel();
		//placeForSendButton.add(sendButton);
		
		
		//JTextArea textArea= new JTextArea();
		JTextField textArea= new JTextField();
		//textArea.setLineWrap(true);
		textArea.setBackground(new Color(255,100,20));
		textArea.setMaximumSize(new Dimension(frame.getWidth()-20,frame.getHeight()));

		
		//JPanel placeForText=new JPanel();
				//placeForText.add(textArea);
		
		/*Dimension Windowsize = frame.getSize();
		TextArea.setSize((int)Windowsize.getWidth(), 100);
		
		
		//Dimension Windowsize = frame.getSize();
		System.out.println(Windowsize.getWidth());
		Windowsize = SendButton.getSize();
		System.out.println(placeForSendButton.getWidth());
		
		
		//placeForWritingMessages.setSize(new Dimension().getWidth());*/
	
		
		
		
		JPanel bottomPanel= new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.LINE_AXIS));
		
		
		
		bottomPanel.add(textArea);
		bottomPanel.add(sendButton);
		
		sendButton.addActionListener(new ActionListener() 
		 {
	            public void actionPerformed(ActionEvent e) 
	            {
	                sendButton.setEnabled(false);

	            }
	        });
		
		connectButton.addActionListener(new ActionListener() 
		 {
	            public void actionPerformed(ActionEvent e) 
	            {
	                connectButton.setEnabled(false);
	                disconnectButton.setEnabled(true);
	                sendButton.setEnabled(true);
	                applyButton.setEnabled(true);
	                if (!remoteAddrField.getText().equals("")) 
	                {
	                	if (localLoginField.getText().equals(""))
	                	{
	                		localLoginField.setText("Guest");
	                	}
	                	caller=new Caller(localLoginField.getText(),remoteAddrField.getText());
	                	/*try
	                	{*/
	                		connection= caller.call();
	                		//connection=new Connection(localLoginField.getText(),remoteAddrField.getText());
	                		localLoginField.setEditable(false);
	                		remoteAddrField.setEditable(false);
	                		//remoteAddrField.setText(callListenerThread.getCallInternetAddress().toString());
	                		if (connection != null) 
	                		{
	                			remoteLoginField.setText(callListenerThread.getCallInternetAddress().toString());
	                			//System.out.println("---------------------------");
								connection.SuccessfulCall(localLoginField.getText());
								System.out.println("---------------------------");
								commandListenerThread = new CommandListenerThread(connection);
								commandListenerThread.start();
							}
	                	/*}
	                	catch (InterruptedException er)
		                {
	                		System.out.println("Error!!! (connectButton)(InterruptedException)");
						} 
	                	catch (IOException er) 
						{
	                										
						}*/
	                	//remoteAddrField.getText();
	                }

	            }
	        });
		
		disconnectButton.addActionListener(new ActionListener() 
		 {
	            public void actionPerformed(ActionEvent e) 
	            {
	            	if (connection != null) 
	            	{
						connection.disconnect();
						connectButton.setEnabled(true);
						disconnectButton.setEnabled(false);
						sendButton.setEnabled(false);
						applyButton.setEnabled(true);
						localLoginField.setEditable(true);
						remoteAddrField.setEditable(true);
	            	}
	            }
	        });
		
		applyButton.addActionListener(new ActionListener() 
		 {
	            public void actionPerformed(ActionEvent e) 
	            {
	                connectButton.setEnabled(true);
	                disconnectButton.setEnabled(false);
	                sendButton.setEnabled(false);
	                applyButton.setEnabled(true);
	            }
	        });
		
		
		frame.add(bottomPanel,BorderLayout.PAGE_END);
		
	}
	
	public void ThreadOfCall() throws IOException {
		callListenerThread.addObserver(new Observer() {

			public void update(Observable arg0, Object arg1) 
			{
				connection = callListenerThread.getConnection();
				commandListenerThread.setConnection(connection);
				commandListenerThread.start();
				/*try 
				{
					forConnect();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}

		});

	}
	
	public void ThreadOfCommand() 
	{
				commandListenerThread.addObserver(new Observer() 
				{
					public void update(Observable arg0, Object arg1) 
					{
						String lastCommand = commandListenerThread.getLastCommandS();
						Command commandList=new Command();
						if (lastCommand.toUpperCase().equals("MESSAGE")) 
						{
							IncomingMessage.setText(remoteLoginField.getText()+ new Date()+
									commandListenerThread.getLastCommand().toString());
						} 
						else if (lastCommand.toUpperCase().equals("NICK")) 
						{							
							remoteLoginField.setText(lastCommand.toString());
						} 
						
						else if (lastCommand.toUpperCase().equals("ACCEPT")) 
						{							
							IncomingMessage.append("User is accepted");
						} 
						else if (lastCommand.toUpperCase().equals("REJECT")) 
						{							
							IncomingMessage.append("User is rejected");
						} 
						else if (lastCommand.toUpperCase().equals("DISCONNECT")) 
						{							
							IncomingMessage.append("User was disconnected");
							if (connection != null) 
			            	{
								connection.disconnect();
								connectButton.setEnabled(true);
								disconnectButton.setEnabled(false);
								sendButton.setEnabled(false);
								applyButton.setEnabled(true);
								localLoginField.setEditable(true);
								remoteAddrField.setEditable(true);
			            	}
						} 
						

						}
				});
			}

	

}