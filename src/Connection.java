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
import java.util.Scanner;


public class Connection 
{
	private final static int port=28411;
	//private int ip;
	private ServerSocket serverSocket;	
	private Socket socket;	
	private String line;	
	private DataOutputStream outStream;
	private DataInputStream inputStream;	
	private String Nickname;
	private String codingName="UTF-8";	
	private final String ChatVersion="ChatApp 2015 v1. user ";
	private final static String endOfLine="0x0A";
	
	public Connection() 
	{
	
	}
	
	public Connection(Socket socket) 
	{
		this.socket = socket;
	}
	public Connection(String Nickname,String ipStr)
	{
		try
		{
			this.Nickname=Nickname;
			InetAddress ip= InetAddress.getByName(ipStr);
			socket= new Socket(ip,port);
			outStream= new DataOutputStream(socket.getOutputStream());
			inputStream=new DataInputStream(socket.getInputStream());
		}
		catch(IOException e)
		{
			System.out.println("IO Error!!! (Connection)");
		}
		
	}
	public Connection(String Nickname,Socket socket)
	{
		try
		{
			this.Nickname=Nickname;
			this.socket= socket;
			outStream= new DataOutputStream(socket.getOutputStream());
			inputStream=new DataInputStream(socket.getInputStream());
		}
		catch(IOException e)
		{
			System.out.println("IO Error!!! (Connection)");
		}
		
	}
	public void sendCommand(String Text)
	{
		try
		{
			outStream.write(Text.getBytes(codingName));
			outStream.write(endOfLine.getBytes(codingName));
			outStream.flush();
		}
		catch(IOException e)
		{
			System.out.println("IO Error!!! (sendMessage)");
		}
	}
	
	public void sendMessage(String Text)
	{
		try
		{
			sendCommand("Message");
			outStream.write(Text.getBytes(codingName));
			outStream.flush();
		}
		catch(IOException e)
		{
			System.out.println("IO Error!!! (sendMessage)");
		}
	}
	
	public void busyLine(String Nickname)
	{
		sendCommand(ChatVersion+Nickname+" busy");
		/*try
		{
			sendCommand(ChatVersion+myNickname+" busy");
			socket.close();
		}
		catch(IOException e)
		{
			System.out.println("IO Error!!! (BusyLine)");
		}*/
	}
	
	public void SuccessfulCall(String Nickname)
	{
		sendCommand(ChatVersion+Nickname+" answer");
	}
	
	public void accept() 
	{
		sendCommand("Accepted");
	}
	
	public void reject() 
	{
		try
		{
			sendCommand("Rejected");
			outStream.close();
			socket.close();
		}
		catch(IOException e)
		{
				System.out.println("IO Error!!! (Reject)");
		}
	}
	
	public void disconnect()
	{
		try
		{
			sendCommand("Disconnect");
			outStream.close();
			socket.close();
		}
		catch(IOException e)
		{
				System.out.println("IO Error!!! (Disconnect)");
		}
	}
	
	public String receiveCommand()
	{
		try
		{
			line = null;
			BufferedReader in= new BufferedReader(new InputStreamReader(socket.getInputStream(),codingName));
			line=in.readLine();
			System.out.println("line: "+line+"; IP: "+socket.getInetAddress()+"; port: "+socket.getLocalPort());
		}
		catch(IOException e)
		{
			System.out.println("IO Error!!! (receiveMessage)");
		}
		return line;
	}
	
	public Connection returnConnection()
	{
		return this;
	}

}
