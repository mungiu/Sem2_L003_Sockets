package Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain
{
	public static void main(String[] args)
	{
		Socket socket;

		try
		{
			socket = new Socket("10.152.194.45", 2910);
//			socket = new Socket("localhost", 2910);
			ClientSocketHandler clientSocketHandler = new ClientSocketHandler(socket);
			new Thread(clientSocketHandler).start();

			while (true)
			{
				Scanner keyboard = new Scanner(System.in);
				new ObjectOutputStream(socket.getOutputStream()).writeObject(keyboard.nextLine());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
