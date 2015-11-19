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
	
	private void check(String command)
	{
		if (command.toUpperCase().equals("DISCONNECT"))
		{
			currentCommand=command.toUpperCase();
		}
		else if(command.toUpperCase().equals("ACCEPT"))
		{
			currentCommand=command.toUpperCase();
		}
		else if(command.toUpperCase().equals("REJECT"))
		{
			currentCommand=command.toUpperCase();
		}
		else if(command.toUpperCase().equals("MESSAGE"))
		{
			currentCommand=command.toUpperCase();
		}
		else if(command.toUpperCase().equals("NICK"))
		{
			currentCommand=command.toUpperCase();
		}
		else currentCommand=null;
	}
	public String receive(String command)
	{
		check(command);
		return currentCommand;
	}
	public String getCurrentCommand()
	{
		return currentCommand;
	}
}