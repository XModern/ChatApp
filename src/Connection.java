import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Connection 
{
	private final static int port=7777;
	//private int ip;
	private ServerSocket serverSocket;
	private Socket socket;
	public void sendMessage(String Text)
	{
		try
		{
			OutputStream out=socket.getOutputStream();
			DataOutputStream outC = new DataOutputStream(out);
		              	
	        	outC.writeUTF(Text);
	        	outC.flush();
		}
		catch(IOException e)
		{
			System.out.println("IO Error!!! (sendMessage)");
		}
	}
	public void BusyLine(String Nick)
	{
		sendMessage("ChatApp 2015 v1. user "+Nick+" busy");
	}
	public void Call(String Nick)
	{
		sendMessage("ChatApp 2015 v1. The user with the nickname "+Nick+" says hi to you");
	}
	public void receiveMessage()
	{
		try
		{
			InputStream in= socket.getInputStream();
			DataInputStream inC = new DataInputStream(in);
			String line = null;
			
				line=inC.readUTF();
				System.out.println("line: "+line+"; IP: "+socket.getInetAddress()+"; port: "+socket.getLocalPort());
				sendMessage("Message has been received");

		}
		catch(IOException e)
		{
			System.out.println("IO Error!!! (receiveMessage)");
		}
	}
	public void connectionToMe()
	{
		try
		{
			System.out.println("Waiting for a client...");
			serverSocket=new ServerSocket(port);
			socket= serverSocket.accept();
			System.out.println("Got a client!");
			sendMessage("Successfully connected");
		}
		catch(IOException e)
		{
			System.out.println("IO Error!!! (connectionToMe)");
		}
		
	}
	public void connectionFromMe(String ipStr)
	{
		try
		{
			InetAddress ip= InetAddress.getByName(ipStr);
			socket= new Socket(ip,port);
			receiveMessage();
			//System.out.println("Successfully connected");
		}
		catch(IOException e)
		{
			System.out.println("IO Error!!! (connectionFromMe)");
		}
	}
	public void disconnect()
	{
		try 
		{
			sendMessage("Disconnect");
			socket.close();
		} 
		catch (IOException e) 
		{
			System.out.println("IO Error!!! (disconnect)");
		}
	}

}
