
public class CommandListenerThread implements Runnable
{
	private Connection connection;
	private Command lastCommand;
	
	private boolean disconnect;
	private final static String endOfLine="0x0A";
	
	CommandListenerThread(Connection connection)
	{
		this.connection=connection;
		disconnect=false;
	}
	
	void start() 
	{
		Thread commandListenerThread = new Thread();
		commandListenerThread.start();
	}

	void stop() 
	{
		disconnect = true;
	}

	public void run() 
	{
		while(!disconnect)
		{
			if ((lastCommand.receive(connection.receiveCommand()).equals("DISCONNECT"))||(lastCommand.receive(connection.receiveCommand()).equals("REJECT")))
			{
				stop();
			}
		}
		
	}

}
