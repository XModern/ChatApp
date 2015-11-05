import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;



public class Test 
{

	public static void main(String[] args) throws IOException 
	{
		Connection client= new Connection();
		//client.connectionToMe();//k komu podkluchaiutsa
		
		client.connectionFromMe("127.0.0.1");//kto podkluchaetsa
		client.sendMessage("Hello");//
		
		
		
		
		
		
		
		/*ConnectionFail a = new Connection();
		//a.connectionToMe();
		
		a.connectionFromMe("127.0.0.1");
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		BuffferedReader t = new BufferedReader(new InputStreamReader(new InputStream())));
		a.sendMessage(keyboard);*/
		
	}

}
