import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class ConnectionFail
{
	private final static int port=7777;
	private int ip;
	private ServerSocket serverSocket;
	private Socket socket;
	
	public void sendMessage(BufferedReader Text)
	{
		/*try 
		{
			System.out.println("Text: "+Text);
			OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream(),"UTF-8");
			out.write(Text);
			out.flush();
			System.out.println("Your message has been sent");
		} */
		try
		{
			//BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			//BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			//System.out.println("keyboard: "+keyboard);
			OutputStreamWriter out=new OutputStreamWriter(socket.getOutputStream(),"UTF-8");
			String line="";
			System.out.println("Write something and press ENTER.");
			while(true)
			{    
				line=Text.readLine();
				System.out.println("line before: "+line);
				//line=new String (line.getBytes()/*,"UTF-16"*/);
				line=line+"\n";
				System.out.println("line after: "+line);
        	
				System.out.println("Sending this line to the server...");
				System.out.println("line send...");
				System.out.println("Sending code line to the server...");
				out.write(line);
				out.flush();
			}
		}
		catch (IOException e) 
		{
	
		}
	}
	public void receiveMessage()
	{
		try
		{
			BufferedReader incomingMessage= new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			String line="";
			while(true)
	        {	
				System.out.println("HERE1");
	        	line=incomingMessage.readLine();
	        	System.out.println("HERE2");
	        	System.out.println("line: "+line+"; IP: "+socket.getInetAddress()+"; port: "+socket.getLocalPort());
	        	System.out.println("HERE3");
	        }	   
		}
		catch(IOException e)
		{
			
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
			
			System.out.println("HERE1");
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			System.out.println("HERE2");
	        while(true)
	        {	
	        	System.out.println("HERE3");
	        	System.out.println(in.read());
	        	System.out.println("IP: "+socket.getInetAddress()+"; port: "+socket.getLocalPort());
	        	System.out.println("HERE4");
	        	 while(true)
	 	        {	
	        	String line=in.readLine();
	        	System.out.println("HERE5");
	        	System.out.println("line: "+line+"; IP: "+socket.getInetAddress()+"; port: "+socket.getLocalPort());
	 	       }	
	        }	
	        
			//receiveMessage();
		}
		catch(IOException e)
		{
			System.out.println("Error!!!");
		}
	}
	public void connectionFromMe(String ipStr)
	{
		try
		{
			InetAddress ip= InetAddress.getByName(ipStr);
			socket= new Socket(ip,port);
			System.out.println("Successfully connected");
		}
		catch(IOException e)
		{
			
		}		
	}
	public void disconnect()
	{
		try 
		{
			socket.close();
		} 
		catch (IOException e) 
		{

		}
	}
}
