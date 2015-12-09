import java.io.IOException;
import java.util.Scanner;


public class Command
{
	private String currentCommand;
	private String[] commandList=new String[5];
	
	//private Connection connection= new Connection();
	
	Command()
	{
		commandList[0]="Disconnect".toUpperCase();
		commandList[1]="Accept".toUpperCase();
		commandList[2]="Reject".toUpperCase();
		commandList[3]="Message".toUpperCase();
		commandList[4]="Nick".toUpperCase();
	}
	
	public boolean checkContent(String command)
	{
		for (int i=0;i<=commandList.length;i++)
		{
			if (commandList[i].toUpperCase().equals(commandList))
			{
				return true;
			}
		}
		return false;
	}
	
	private String check(String command)
	{
		if(command.toUpperCase().startsWith("MESSAGE"))
		{
			//currentCommand="ACCEPTED";
			currentCommand="MESSAGE";
			System.out.println("MESSAGE");
		}
		else if((command.toUpperCase().startsWith("CHATAPP"))&&(command.toUpperCase().endsWith("BUSY")))
		{
			currentCommand="REJECTED";
			System.out.println("CHATAPP and BUSY ");
		}
		else if((command.toUpperCase().startsWith("CHATAPP"))&&!(command.toUpperCase().endsWith("BUSY")))
		{
			currentCommand="ACCEPTED";
			System.out.println("CHATAPP and !BUSY ");
		}
		else if(command.toUpperCase().startsWith("CHATAPP"))
		{
			currentCommand="ACCEPTED";
			System.out.println("CHATAPP");
		}
		else if(command.toUpperCase().startsWith("DISCONNECT"))
		{
			currentCommand="DISCONNECT";
			System.out.println("DISCONNECT");
		}
		else 
		{
			currentCommand="MISS";//currentCommand="REJECTED";
			System.out.println("MISS");
		}
		return command;
		/*if (command.toUpperCase().equals("DISCONNECT"))
		{
			//currentCommand=command.toUpperCase();
			currentCommand="REJECTED";
		}
		else if(command.toUpperCase().equals("ACCEPT"))
		{
			//currentCommand=command.toUpperCase();
			currentCommand="ACCEPTED";
		}
		else if(command.toUpperCase().equals("REJECT"))
		{
			//currentCommand=command.toUpperCase();
			currentCommand="REJECTED";
		}
		else if(command.toUpperCase().equals("MESSAGE"))
		{
			//currentCommand=command.toUpperCase();
			currentCommand="REJECTED";
		}
		else if(command.toUpperCase().equals("NICK"))
		{
			//currentCommand=command.toUpperCase();
			currentCommand="REJECTED";
		}*/
	}
	public String receive(String command)
	{
		System.out.println("Command: "+command);
		check(command);
		return currentCommand;
	}
	public String getCurrentCommand()
	{
		return currentCommand;
	}
}