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
	
	public CallListener()
	{
		/*try
		{*/
			Nickname = "Guest";
			internetAddress = "127.0.0.1";
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
	
	public Connection getConnection() /*throws IOException*/
	{
		socket=new Socket();
		Connection connection=new Connection();
		try
		{
			serverSocket = new ServerSocket(port);
			//socket.setSoTimeout(20000);
			//serverSocket.setSoTimeout(20000);
			socket = serverSocket.accept();
			
			connection=new Connection(Nickname,socket);	
			return connection;	
		}
		catch(IOException e)
		{
			System.out.println("IO Error!!! getConnection()");
		}
		System.out.println("Nickname: "+Nickname+"s.getInetAddress: "+socket.getInetAddress()+" s.getPort: "+socket.getPort());
		return connection;
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
