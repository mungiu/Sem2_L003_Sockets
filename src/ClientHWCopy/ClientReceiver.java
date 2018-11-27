package ClientHWCopy;

import ServerHWCopy.Message;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientReceiver implements Runnable
{
	private ObjectInputStream inFromServer;
	private ClientGUI view;

	public ClientReceiver(ObjectInputStream inFromServer, ClientGUI view)
	{
		System.out.println("Created client receiver");
		this.inFromServer = inFromServer;
		this.view = view;
	}

	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				Message msg = (Message) inFromServer.readObject();
				view.updateMessage(msg.getBody());
			}
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
