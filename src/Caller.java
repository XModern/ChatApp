import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


public class Caller 
{
	private String Nickname;
	private final static int port=28411;
	private String internetAddress;
	private String companionNickname;
	//private String callStatus;


	public Caller()
	{
		Nickname="Guest";
		internetAddress="127.0.0.1";
	}
	public Caller(String Nickname)
	{
		this.Nickname=Nickname;
		internetAddress="127.0.0.1";
	}
	public Caller(String Nickname,String internetAddress)
	{
		this.Nickname=Nickname;
		this.internetAddress=internetAddress;
	}
	
	public Connection call()
	{
		try
		{
			InetAddress ip= InetAddress.getByName(internetAddress);
			System.out.println("Calling...");
			Socket socket= new Socket(ip,port);
			System.out.println("Calling...");
			if (socket.isConnected())
			{
				System.out.println("Call accepted");
				Connection connection=new Connection(socket);
				//connection.openOutputStream();
				return connection;
			}
		}
		catch(IOException e)
		{
			System.out.println("IO Error!!! (connectionFromMe)");
		}
		return null;
	}
	
	/*public void callStatus(String status)
	{
		callStatus=status.toUpperCase();
	}*/
	
	public void setLocalNick(String Nickname) 
	{
		this.Nickname = Nickname;
	}

	public void setCompanionAddress(String internetAddress) 
	{
		this.internetAddress = internetAddress;
	}
	public String getLocalNick() {
		return Nickname;
	}

	public String getRemoteAddress() {
		return internetAddress;
	}
	

}
