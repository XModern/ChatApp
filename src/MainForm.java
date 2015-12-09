
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
	
	private JTextField textArea;
	private JTextArea IncomingMessage=new JTextArea();
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
		IncomingMessage.setEditable(false);
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
		textArea= new JTextField();
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
	                
						if (!textArea.getText().equals("")) 
						{
							connection.sendMessage(textArea.getText());
							IncomingMessage.setText(IncomingMessage.getText()+"\n"+"[" +new Date().getHours()+": "+new Date().getMinutes()+": "+new Date().getSeconds()+"] "+localLoginField.getText()+": "+textArea.getText());
							textArea.setText("");
						}

					sendButton.setEnabled(true);

	            }
	        });
		
		connectButton.addActionListener(new ActionListener() 
		 {
	            public void actionPerformed(ActionEvent e) 
	            {
	                if (!remoteAddrField.getText().equals("")) 
	                {
	                	if (localLoginField.getText().equals(""))
	                	{
	                		localLoginField.setText("Guest");
	                	}
	                	caller=new Caller(localLoginField.getText(),remoteAddrField.getText());
	                	/*try
	                	{*/
	                	//System.out.println("Here11111");
	                		connection= caller.call();
		                	//System.out.println("Here22222");
	                		//connection=new Connection(localLoginField.getText(),remoteAddrField.getText());
	                		//remoteAddrField.setText(callListenerThread.getCallInternetAddress().toString());

	                		if (connection != null) 
	                		{
		                		connection.openStreams();
	                			/*connectButton.setEnabled(false);
		    	                applyButton.setEnabled(true);
		    	                
		                		localLoginField.setEditable(false);
		                		remoteAddrField.setEditable(false);
		                		
	                			connection.openStreams();
	                			disconnectButton.setEnabled(true);
	                			sendButton.setEnabled(true);*/
	                			forConnect();
	                			//remoteLoginField.setText(callListenerThread.getCallInternetAddress().toString());
	                			remoteLoginField.setText(connection.getIP());
	                			//System.out.println("---------------------------");
//connection.openStreams();
								commandListenerThread = new CommandListenerThread(connection);
								ThreadOfCommand();
								////
			                    remoteLoginField.setText(caller.getRemoteAddress());
			                    /////
								commandListenerThread.start();
								
								//System.out.println(localLoginField.getText()+" "+connection.getIP());
								//connection.openOutputStream();
								connection.SuccessfulCall(localLoginField.getText());
								System.out.println("---------------------------");
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
						/*connectButton.setEnabled(true);
						disconnectButton.setEnabled(false);
						sendButton.setEnabled(false);
						applyButton.setEnabled(true);
						localLoginField.setEditable(true);
						remoteAddrField.setEditable(true);*/
						forDisconnect();
	            	}
	            }
	        });
		
		applyButton.addActionListener(new ActionListener() 
		 {
	            public void actionPerformed(ActionEvent e) 
	            {
	            	forApply();
	            	if (localLoginField.getText().equals(""))
	            	{
	            		localLoginField.setText("Guest");
	            	}
	            	callListenerThread = new CallListenerThread();
	            	callListenerThread.start();
					commandListenerThread = new CommandListenerThread();
					//System.out.println("11111111111111111111111aaaa");
					
					ThreadOfCall();
					//System.out.println("2222222222222222222aaaa");
//System.out.println("lol_1");
					ThreadOfCommand();
					//System.out.println("33333333333333333aaaa");
//System.out.println("lol_2");
	            }
	        });
		
		
		frame.add(bottomPanel,BorderLayout.PAGE_END);
		
	}
	
	public void ThreadOfCall() /*throws IOException*/ 
	{
		callListenerThread.addObserver(new Observer() 
		{

			public void update(Observable arg0, Object arg1) 
			{
				//System.out.println("wait for opponent");
					connection = callListenerThread.getConnection();
					System.out.println("Connection getted");
	                sendButton.setEnabled(true);
					//connection.openStreams();
					//connection.openStreams();
					connection.SuccessfulCall(localLoginField.getText());
					commandListenerThread.setConnection(connection);
					commandListenerThread.start();
					
					
				//commandListenerThread.setConnection(connection);
				//commandListenerThread.start();
				
				
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
						//System.out.println("Hhhhhhhhhhhhhhhhhhhhhere!!!!");
						String lastCommand = commandListenerThread.getLastCommandS();
						/////
						
						//IncomingMessage.setText(IncomingMessage.getText()+" "+lastCommand);
						
						
						/////
						//System.out.println("Hhhhhhhhhhhhhhhhhere!!!");
						
						//System.out.println("commandListenerThread.getLastCommandS()"+commandListenerThread.getLastCommandS());
						
						//if(commandListenerThread.getMessage()!="")
						//{
							//System.out.println("commandListenerThread.getMessage(): "+commandListenerThread.getMessage());
							//IncomingMessage.setText(IncomingMessage.getText()+"\n"+"[" +new Date().getHours()+": "+new Date().getMinutes()+": "+new Date().getSeconds()+"] "+/*remoteLoginField.getText()*/commandListenerThread.getOpponentName()+": "+commandListenerThread.getMessage());
						//}
							Command commandList=new Command();
						if ((lastCommand.toUpperCase()/*.equals*/.startsWith("MESSAGE"))&&(commandListenerThread.getMessage()!="")) 
						{
							System.out.println("commandListenerThread.getMessage(): "+commandListenerThread.getMessage());
							IncomingMessage.setText(IncomingMessage.getText()+"\n"+"[" +new Date().getHours()+": "+new Date().getMinutes()+": "+new Date().getSeconds()+"] "+/*remoteLoginField.getText()*/commandListenerThread.getOpponentName()+": "+commandListenerThread.getMessage());
							//System.out.println("--*-*-**--*-*-");
							/*IncomingMessage.setText(remoteLoginField.getText()+ new Date()+
									commandListenerThread.getLastCommand().toString());*/
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
							IncomingMessage.setText(IncomingMessage.getText()+"\n"+"User was disconnected");
							connection.disconnectReceiver();
							//IncomingMessage.append("User was disconnected");
							//connection=null;
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
	
	public void forConnect()
	{
		connectButton.setEnabled(false);
        applyButton.setEnabled(true);
        
		localLoginField.setEditable(false);
		remoteAddrField.setEditable(false);
		remoteLoginField.setEditable(true);

		disconnectButton.setEnabled(true);
		sendButton.setEnabled(true);
	}
	
	public void forDisconnect()
	{
		connectButton.setEnabled(true);
		disconnectButton.setEnabled(false);
		sendButton.setEnabled(false);
		applyButton.setEnabled(true);
		localLoginField.setEditable(true);
		localLoginField.setText("");
		remoteAddrField.setEditable(true);
		remoteAddrField.setText("");
		IncomingMessage.setEditable(false);
	}
	public void forApply()
	{		
		connectButton.setEnabled(true);
        disconnectButton.setEnabled(false);
        applyButton.setEnabled(false);
        localLoginField.setEditable(false);
    }

	

}
