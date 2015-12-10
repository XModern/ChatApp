import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Connection 
{
	public static final int port=28411;
	//private int ip;
	private ServerSocket serverSocket;	
	private Socket socket;	
	private String line;	
	private BufferedOutputStream outStream;
	//private BufferedInputStream inputStream;
	private Scanner inputStream;
	
	private String Nickname;
	private String codingName="UTF-8";	
	private final String ChatVersion="ChatApp 2015 v1. user ";
	private final static String endOfLine=new String("\n");
	
	public Connection() 
	{
	
	}
	
	public Connection(Socket socket) 
	{
		this.socket = socket;
		//System.out.println("tttttttttt");
		///////////////////
		/*try
		{
		outStream= new BufferedOutputStream(socket.getOutputStream());
		inputStream=new Scanner(socket.getInputStream());
		}
		catch(IOException e)
		{
			System.out.println("(IOException e) Connection(Socket)");
		}
		catch(NullPointerException e)
		{
			System.out.println("(NullPointerException e) Connection(Socket)");
		}*/
		//////////////////////
	}
	public Connection(String Nickname,String ipStr)
	{
		try
		{
			this.Nickname=Nickname;
			InetAddress ip= InetAddress.getByName(ipStr);
			socket= new Socket(ip,port);
//outStream= new BufferedOutputStream(socket.getOutputStream());
			///////////////////////////////////////inputStream=new BufferedInputStream(socket.getInputStream());
//inputStream=new Scanner(socket.getInputStream());
		}
		catch(IOException e)
		{
			System.out.println("IO Error!!! (Connection)");
		}
		
	}
	public Connection(String Nickname,Socket socket)
	{
/*try
{*/
			this.Nickname=Nickname;
			this.socket= socket;
//outStream= new BufferedOutputStream(socket.getOutputStream());
			///////////////////////////////////////////inputStream=new BufferedInputStream(socket.getInputStream());
/*inputStream=new Scanner(socket.getInputStream());
}
catch(IOException e)
{
System.out.println("IO Error!!! (Connection)");
}*/
		
	}
	
	public void openStreams()
	{ 
		try
		{
			outStream= new BufferedOutputStream(socket.getOutputStream());
			///////////////////////////////////////////////////inputStream=new BufferedInputStream(socket.getInputStream());
			inputStream=new Scanner(socket.getInputStream());
		}
		catch (UnsupportedEncodingException e) 
		{
			System.out.println("UE Error!!! (openStreams())");
		} 
		catch (IOException e) 
		{
			System.out.println("IO Error!!! (openStreams())");
		}
		catch (NullPointerException e)
		{
			System.out.println("NullPointerException (openStreams())");
		}
	}
	
	public void closeStreams()
	{
		try
		{
			outStream.close();
			///////////////////////////////////////////////////inputStream=new BufferedInputStream(socket.getInputStream());
			inputStream.close();
		}
		catch (UnsupportedEncodingException e) 
		{
			System.out.println("UE Error!!! (openStreams())");
		} 
		catch (IOException e) 
		{
			System.out.println("IO Error!!! (openStreams())");
		}
	}
	
	public void disconnectReceiver()
	{
		try
		{
			outStream.close();
			inputStream.close();
			socket.close();
		}
		catch (UnsupportedEncodingException e) 
		{
			System.out.println("UE Error!!! (openStreams())");
		} 
		catch (IOException e) 
		{
			System.out.println("IO Error!!! (openStreams())");
		}
	}
	
	public void openInputStream()
	{
		try
		{
			inputStream=new Scanner(socket.getInputStream());
		}
		catch (UnsupportedEncodingException e) 
		{
			System.out.println("UE Error!!! (openStreams())");
		} 
		catch (IOException e) 
		{
			System.out.println("IO Error!!! (openStreams())");
		}
	}
	
	public void openOutputStream()
	{
		try
		{
			outStream= new BufferedOutputStream(socket.getOutputStream());
		}
		catch (UnsupportedEncodingException e) 
		{
			System.out.println("UE Error!!! (openStreams())");
		} 
		catch (IOException e) 
		{
			System.out.println("IO Error!!! (openStreams())");
		}
	}
	
	public void sendCommand(String Text)
	{
		try 
		{
//openStreams();
			////////////////////////////////////hhhhhhhhhheeeeeeeeeerrrrrrrrrrrrreeeeeeeeeee eeeeerrrrrrrrrroooooooorrrrrrrrrrrr!!!!!!!
			System.out.println(socket.getInetAddress()+":"+socket.getPort()+" "+Text +" "+ endOfLine);
			outStream.write((Text+" "+endOfLine).getBytes(codingName));
			outStream.flush();
		} 
		catch (UnsupportedEncodingException e) 
		{
			System.out.println("UE Error!!! (sendCommand)");
		} 
		catch (IOException e) 
		{
			System.out.println("IO Error!!! (sendCommand)");
		}
		//////////////////////////////////////outStream.write(Text.getBytes(codingName));
		///////////////////////////////////////outStream.write(endOfLine.getBytes(codingName));
	}
	
	public void sendMessage(String Text)
	{
		try
		{
			//////////////////////////////////////////sendCommand("Message");
			////////////////////////////////////////outStream.write(Text.getBytes(codingName));
			System.out.println(socket.getInetAddress()+":"+socket.getPort()+" "+"Message"+" "+Text+" "+endOfLine);
			outStream.write(("Message"+" "+Text+" "+endOfLine).getBytes(codingName));
			outStream.flush();
		}
		catch(IOException e)
		{
			System.out.println("IO Error!!! (sendMessage)");
		}
	}
	
	public void busyLine(String Nickname)
	{
		//System.out.println(ChatVersion+Nickname+" busy");
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
		//System.out.println(ChatVersion+Nickname);
		sendCommand(ChatVersion+Nickname);
		/*try 
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			String line= in.readLine();
			System.out.println(line);
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}*/
	}
	
	public void accept() 
	{
		sendCommand("Accepted");
	}
	
	public void reject() 
	{
		//try
		//{
			sendCommand("Reject");
			//outStream.close();
			//socket.close();
		//}
		//catch(IOException e)
		//{
		//		System.out.println("IO Error!!! (Reject)");
		//}
	}
	
	public void disconnect()
	{
	//	try
	//	{
			sendCommand("Disconnect");
			//outStream.close();
			//closeStreams();
			//socket.close();
		//}
		//catch(IOException e)
	//	{
	//			System.out.println("IO Error!!! (Disconnect)");
	//	}
	}
	
	public String receiveCommand()
	{
		line = null;
		/*try
		{
			BufferedReader in= new BufferedReader(new InputStreamReader(socket.getInputStream(),codingName));
			line=in.readLine();
			System.out.println("line: "+line+"; IP: "+socket.getInetAddress()+"; port: "+socket.getLocalPort());
			
		}
		catch(IOException e)
		{
			System.out.println("IO Error!!! (receiveMessage)");
		}*/
		try
		{
			line=inputStream.nextLine();
		}
		catch(NoSuchElementException e)
		{
			
		}
		catch(IllegalStateException e)
		{
			
		}
		System.out.println("line: "+line+"; IP: "+socket.getInetAddress()+"; port: "+socket.getLocalPort());
		return line;
	}
	
	
	public Connection returnConnection()
	{
		return this;
	}
	
	public String getIP()
	{
		return socket.getRemoteSocketAddress().toString();
	}

}
