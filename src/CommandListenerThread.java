import java.util.Observable;
import java.util.Observer;


public class CommandListenerThread extends Observable implements Runnable
{
	private Connection connection;
	private Command lastCommand;
	private String line;
	private boolean check;
	private Thread commandListenerThreadin;
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
		commandListenerThreadin = new Thread(this);
	}

	void start() 
	{
		commandListenerThreadin = new Thread(this);
		commandListenerThreadin.start();
	}

	void stop() 
	{
		disconnect = true;
		//Thread commandListenerThread = new Thread(this);
		commandListenerThreadin.stop();
	}

	public void run() 
	{
		//System.out.println("Here111111111");
		lastCommand=new Command();
		check=false;
		while((!disconnect)||(line!=null))
		{
			//System.out.println("Here2222222");
			//System.out.println("connection.returnConnection(): "+connection.returnConnection());
			if(connection.returnConnection()!=null)
			{
				line=connection.receiveCommand();
			}
			
			//System.out.println("HERE3333333");
			//System.out.println(connection.getIP()+" "+line);
			try
			{
			System.out.println("LastCommand: "+lastCommand.receive(line));
			if(line==null)
			{
				stop();
				break;
			}
			
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
			catch(NullPointerException e)
			{
				
			}
			
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
	
	public boolean setConnection(boolean state) 
	{
		return disconnect=state;
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
