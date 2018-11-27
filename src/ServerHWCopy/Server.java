package ServerHWCopy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
	public static void main(String args[]) throws IOException,
			ClassNotFoundException
	{
		boolean isConnected = true;
		final int PORT = 6789;
		System.out.println("Starting Server...");

		// create welcoming socket at port 6789
		ServerSocket welcomeSocket = new ServerSocket(PORT);

		System.out.println("Waiting for a client...");

		// Wait, on welcoming socket for contact by client
		Socket connectionSocket = welcomeSocket.accept();
		new Thread(new ServerConnection(connectionSocket), "Communication").start();

		// loop back and wait for another client connection.
		welcomeSocket.close();
	}
}

