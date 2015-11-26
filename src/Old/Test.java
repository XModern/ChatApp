import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;



public class Test
{

	public static void main(String[] args) throws IOException 
	{
		
		Command connection=new Command();
		
		connection.ReceiveConnection();
		
		
		/*connection.ConnectWith("127.0.0.1");		
		connection.breakTheConnection();*/
		
		int choose=0;
		do
		{
			//connection.checkTheConnection();
			System.out.println();
			System.out.println("1 - Отправить сообщение.");
			System.out.println("2 - Принять сообщение.");
			System.out.println("3 - Подключится.");
			System.out.println("4 - Отключится.");
			
			Scanner input = new Scanner(System.in);
			choose=input.nextInt();
			//input.close();
			
			switch(choose)
			{
				case 1:
					BufferedReader Command=new BufferedReader(new InputStreamReader(System.in));
					String Text=Command.readLine();
					
					connection.sendMessage(Text);
					break;
				case 2:
					connection.receiveMessage();	
					break;
				case 3:
					connection.ConnectWith("127.0.0.1");		
					break;
				case 4:
					connection.breakTheConnection();
					break;
				default:
					System.out.println();
					System.out.println("Вам почти удалось попасть в диапазон!!! Попытайте удачу снова!!!");
					System.out.println();
					break;
			}
		}
		while(choose!=0);
		
		
		
		//Connection client= new Connection();
		//client.connectionToMe();//k komu podkluchaiutsa
		
		/*client.connectionFromMe("127.0.0.1");//kto podkluchaetsa
		client.sendMessage("Hello");*/
		
		
		//client.disconnect();
		
		
		
		
		
		
		
		/*ConnectionFail a = new Connection();
		//a.connectionToMe();
		
		a.connectionFromMe("127.0.0.1");
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		BuffferedReader t = new BufferedReader(new InputStreamReader(new InputStream())));
		a.sendMessage(keyboard);*/
		
	}

}
