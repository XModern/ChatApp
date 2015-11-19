import java.io.IOException;
import java.util.Scanner;


public class Command
{
	private String currentCommand;
	
	//private Connection connection= new Connection();
	
	private void check(String command)
	{
		if (command.equals("Disconnect"))
		{
			currentCommand=command.toUpperCase();
		}
		else if(command.equals("Accept"))
		{
			currentCommand=command.toUpperCase();
		}
		else if(command.equals("Reject"))
		{
			currentCommand=command.toUpperCase();
		}
		else if(command.equals("Message"))
		{
			currentCommand=command.toUpperCase();
		}
		else if(command.equals("Nick"))
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
}