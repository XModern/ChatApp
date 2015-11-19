import java.io.IOException;
import java.net.SocketAddress;
import java.util.Observable;


public class CallListenerThread implements Runnable
{
	private CallListener callListener;
	private Connection connection;
	private String callStatus;
	private volatile boolean isOpen;

	public CallListenerThread()
	{
		callListener=new CallListener();
	}
	public CallListenerThread(String Nickname)
	{
		callListener=new CallListener(Nickname);
	}
	public CallListenerThread(String Nickname,String ip)
	{
		callListener=new CallListener(Nickname,ip);
	}
	public String getCallStatus()
	{
		return callStatus;
	}
	public SocketAddress getCallInternetAddress()
	{
		return callListener.getListenAddress();
	}
	public String getMyNickname()
	{
		return callListener.getLocalNick();
	}
	public SocketAddress getCompanionInternetAddress() throws IOException
	{
			return callListener.getRemoteAdress();
	}
	public Connection getConnection() 
	{
		return connection;
	}
	
	public boolean isBusy()
	{
		return callListener.isBusy();
	}
	public void setBusy(boolean busy)
	{
		callListener.setBusy(busy);
	}
	public void setLocalNick(String Nickname)
	{
		callListener.setLocalNick(Nickname);
	}
	public void start()
	{
		this.isOpen=true;
		Thread callListenerThread= new Thread(this);
		callListenerThread.start();
	}
	public void stop()
	{
		this.isOpen=false;
	}
	
	
	public void run() 
	{
		while(true)
		{
			try 
			{
				connection=callListener.getConnection();
				if (connection==null)
				{
					callStatus="BUSY";
				}
				else
				{
					callStatus="SUCCESS";
				}
			} 
			catch (IOException e) 
			{
				System.out.println("Error!!! (CallListenerThread)");
			}	
		}
		
	}
	

}
