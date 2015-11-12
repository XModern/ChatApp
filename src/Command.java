
public class Command
{
	private String currentCommand;
	
	private Connection connection= new Connection();
	
	public void ConnectWith(String ipStr)
	{
		connection.connectionFromMe(ipStr);
	}
	public void ReceiveConnection()
	{
		connection.connectionToMe();
	}
	public void breakTheConnection()
	{
		connection.disconnect();
	}
	public void checkTheConnection()
	{

	}
	
	public void sendMessage(String Text)
	{
		
	}
	

}
