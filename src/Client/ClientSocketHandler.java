package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientSocketHandler implements Runnable
{
	private Socket socket;

	public ClientSocketHandler(Socket socket)
	{
		this.socket = socket;
	}

	@Override
	public void run()
	{
		while (true)
			try
			{
				System.out.println("Andrei: " + new ObjectInputStream(socket.getInputStream()).readObject());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
	}
}
