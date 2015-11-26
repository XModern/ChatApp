import java.util.Observable;
import java.util.Observer;


public class CommandListenerThread extends Observable implements Runnable
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
	
	public void setConnection(Connection connection)
	{
		disconnect=false;
		this.connection=connection;
	}
	
	public String getLastCommandS() 
	{
		return lastCommand.getCurrentCommand();
	}
	
	public Command getLastCommand() 
	{
		return lastCommand;
	}



}
