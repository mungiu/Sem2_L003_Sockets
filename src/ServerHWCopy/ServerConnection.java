package ServerHWCopy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection implements Runnable
{
	private Socket clientSocket;
	private ObjectInputStream inFromClient;
	private ObjectOutputStream outToClient;


	public ServerConnection(Socket connectionSocket) throws IOException
	{
		clientSocket = connectionSocket;

		// create output stream attached to the socket
		this.outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());

		// create input stream attached to the socket
		inFromClient = new ObjectInputStream((connectionSocket.getInputStream()));
	}


	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				// read message from client.
				Message message;
				message = (Message) inFromClient.readObject();

				System.out.println("Message from Client: " + message);

				// Send reply to client.
				Message replyMessage = new Message(message.getId(),
						message.getBody().toUpperCase());

				System.out.println("Server reply: " + replyMessage);

				outToClient.writeObject(replyMessage);
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
