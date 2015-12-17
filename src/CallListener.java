import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;


public class CallListener 
{
	private final static int port=28411;
	private String internetAddress;
	private ServerSocket serverSocket;	
	private String Nickname;
	private boolean isBusy;
	private SocketAddress listenAddress;
	private Socket socket;
	//private boolean flag=true;
	
	public CallListener()
	{
		/*try
		{*/
			Nickname = "Guest";
			internetAddress = "127.0.0.1";
			try 
			{
				serverSocket = new ServerSocket(port);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			//serverSocket = new ServerSocket(Connection.port);
			isBusy=false;
		/*}
		catch(IOException e)
		{
			System.out.println("Error!!! (CallListener())");
		}*/
	}
	
	public CallListener(String Nickname)
	{
		this.Nickname=Nickname;
	}
	
	public CallListener(String Nickname,String ip)
	{
		try
		{
			this.Nickname=Nickname;
			this.internetAddress=ip;
			serverSocket=new ServerSocket(port);
		}
		catch(IOException e)
		{
			System.out.println("IO Error!!! CallListener(String Nickname,String ip)");
		}
	}
	
	public void dieSocket()
	{
		try 
		{
			socket.close();
		} 
		catch (IOException e) {
	
		}
	}
	
	public Connection getConnection() /*throws IOException*/
	{
		try
		{
			System.out.println("1) SOCKET: "+socket+"; address: "+socket.getInetAddress()+socket.getPort());
		}
		catch(NullPointerException e)
		{
			System.out.println("1)NullPointerException e in getConnection (Class CallListener)");
		}
		//socket=new Socket();
		Connection connection=new Connection();
		/*try
		{
			//serverSocket=null;
			System.out.println("Inside socket");
			//if(flag)
			//{
				serverSocket = new ServerSocket(port);
				//flag=false;
			//}
		}
		catch(IOException e)
		{
			System.out.println("IO Error!!! getConnection()(Setting port)");
		}*/
			//socket.setSoTimeout(20000);
			//serverSocket.setSoTimeout(20000);
			try
			{
				System.out.println("2) SOCKET: "+socket+"; address: "+socket.getInetAddress()+socket.getPort());
			}
			catch(NullPointerException e)
			{
				System.out.println("2)NullPointerException e in getConnection (Class CallListener)");
			}
			try
			{
				socket = serverSocket.accept();
				serverSocket.close();
			}
			catch(IOException e)
			{
				System.out.println("IO Error!!! getConnection()(serverSocket Accepting)");
			}
			catch(NullPointerException e)
			{
				System.out.println("NullPointerException!!! getConnection()(serverSocket Accepting)");
			}

			
			try
			{
				System.out.println("3) SOCKET: "+socket+"; address: "+socket.getInetAddress()+socket.getPort());
			}
			catch(NullPointerException e)
			{
				System.out.println("3)NullPointerException e in getConnection (Class CallListener)");
			}
			
			connection=new Connection(Nickname,socket);
			
			System.out.println("Nickname: "+Nickname+"s.getInetAddress: "+socket.getInetAddress()+" s.getPort: "+socket.getPort());
			
			return connection;	
		/*}
		catch(IOException e)
		{
			System.out.println("IO Error!!! getConnection()");
		}*/
		//System.out.println("Nickname: "+Nickname+"s.getInetAddress: "+socket.getInetAddress()+" s.getPort: "+socket.getPort());
		//return connection;
	}

	    public String getLocalNick()
	    {
	        return Nickname;
	    }

	    public boolean isBusy()
	    {
	        return serverSocket.isBound();
	    }

	    public SocketAddress getListenAddress()
	    {
	    		return serverSocket.getLocalSocketAddress();
	    }

	    public String getRemoteNick()
	    {
	        return null;
	    }

	    public SocketAddress getRemoteAddress() 
	    {
			return socket.getRemoteSocketAddress();
		}

	    public void setLocalNick(String Nickname)
	    {
	        this.Nickname = Nickname;
	    }

	    public void setBusy(boolean isBusy)
	    {
	        this.isBusy=isBusy;
	    }

	    public void setListenAddress(SocketAddress listenAddress)
	    {
	       this.listenAddress=listenAddress;
	    }

	    public String toString() 
	    {
	    	return Nickname + " " + listenAddress;
	    }
	

}
