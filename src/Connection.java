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
	private final static int port=7777;
	//private int ip;
	private ServerSocket serverSocket;
	private Socket socket;
	
	private String line;
	
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
	public void BusyLine()
	{
		sendMessage("ChatApp 2015 v1. user '"+socket.getInetAddress()+"' busy");
	}
	public void SuccessfulCall()
	{
		sendMessage("ChatApp 2015 v1. The user with ip: '"+socket.getInetAddress()+"' answered");
		receiveMessage();
	}
	public void receiveMessage()
	{
		try
		{
			InputStream in= socket.getInputStream();
			DataInputStream inC = new DataInputStream(in);
			line = null;
				line=inC.readUTF();
				System.out.println("line: "+line+"; IP: "+socket.getInetAddress()+"; port: "+socket.getLocalPort());

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
			int choose=0;
				//Записывать и читать ограниченое кол-во бит
				System.out.println();
				System.out.println("1 - Принять.");
				System.out.println("2 - Отклонить.");

				Scanner input = new Scanner(System.in);
				choose=input.nextInt();
				//input.close();
			
				switch(choose)
				{
					case 1:
						SuccessfulCall();
						break;
					case 2:
						BusyLine();
						break;
					default:
						System.out.println();
						System.out.println("Вам почти удалось попасть в диапазон!!! Попытайте удачу снова!!!");
						System.out.println();
						break;
				}
			//sendMessage("Successfully connected");
			//receiveMessage();
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
			sendMessage("ChatApp 2015 v1. The user with ip: '"+socket.getInetAddress()+"' successfully connected");
			//System.out.println("Successfully connected");
		}
		catch(IOException e)
		{
			System.out.println("IO Error!!! (connectionFromMe)");
		}
	}
	public void disconnect()
	{
			sendMessage("Disconnect");
	}

}
