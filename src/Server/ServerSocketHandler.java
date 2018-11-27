package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable
{
	Socket socket;
	SocketPool socketPool;
	Object tempObject;

	public ServerSocketHandler(Socket socket, SocketPool socketPool)
	{
		this.socketPool = socketPool;
		this.socket = socket;
		this.socketPool.addSocket(socket);
	}

	@Override
	public void run()
	{
		// thread stops when run method end is reached
		while (true)
		{
			try
			{
				System.out.println("Server preparing to read:");
				tempObject = new ObjectInputStream(socket.getInputStream()).readObject();
				System.out.println("Server received: " + tempObject);
				// use socket pool here to send message to all other clients
				for (Socket socket : socketPool.getSocketPool())
				{
					new ObjectOutputStream(socket.getOutputStream()).writeObject(tempObject);
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
}
