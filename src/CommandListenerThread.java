import java.util.Observable;
import java.util.Observer;


public class CommandListenerThread extends Observable implements Runnable
{
	private Connection connection;
	private Command lastCommand;
	private String line;
	private boolean check;
	
	private boolean disconnect;
	private String opponentName;
	
	private final static String endOfLine="\n";
	
	CommandListenerThread(Connection connection)
	{
		this.connection=connection;
		disconnect=false;
	}
	
	public CommandListenerThread() 
	{
		
	}

	void start() 
	{
		Thread commandListenerThread = new Thread(this);
		commandListenerThread.start();
	}

	void stop() 
	{
		disconnect = true;
	}

	public void run() 
	{
		//System.out.println("Here111111111");
		lastCommand=new Command();
		check=false;
		while(!disconnect)
		{
			//System.out.println("Here2222222");
			System.out.println("connection.returnConnection(): "+connection.returnConnection());
			if(connection.returnConnection()!=null)
			{
				line=connection.receiveCommand();
			}
			
			//System.out.println("HERE3333333");
			//System.out.println(connection.getIP()+" "+line);
			
			System.out.println("LastCommand: "+lastCommand.receive(line));
			
			if (/*lastCommand*/lastCommand.receive(line) != null) 
			{		
				if ((getLastCommandS().toUpperCase().equals("DISCONNECT")
						|| ((getLastCommandS().toUpperCase().equals("REJECT"))))) 
				{
					disconnect = true;
					//connection.disconnectReceiver();
				}
			}
			
			if (check==false)
			{
				check=true;
				opponentName=line.substring(line.indexOf("user")+5,line.length()-1);
				line="";
			}
		
			this.setChanged();
			this.notifyObservers();
			
		}
		
		
		/*while(!disconnect)
		{
			if ((lastCommand.receive(connection.receiveCommand()).equals("DISCONNECT"))||(lastCommand.receive(connection.receiveCommand()).equals("REJECT")))
			{
				stop();
			}
		}*/
		
	}
	
	public void setConnection(Connection connection)
	{
		disconnect=false;
		this.connection=connection;
	}
	
	public String getMessage() 
	{
		if ((line.toUpperCase().startsWith("MESSAGE"))&&(line!=""))
		{
			line=line.substring(8);
		}
		return line;
	}
	
	public String getLastCommandS() 
	{
		return lastCommand.getCurrentCommand();
	}
	
	public boolean isConnected() 
	{
		return disconnect;
	}
	
	public String getOpponentName() 
	{
		return opponentName;
	}
	
	public Command getLastCommand() 
	{
		return lastCommand;
	}



}
