package Server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerMain {
	public static void main(String[] args) {

		SocketPool socketPool = new SocketPool();

		try
		{
			ServerSocket serverSocket = new ServerSocket(2910);
			while (true)
			{
				// note, the code stops at this method until the connection is made
				// this socket is used to read/modify/write
				new Thread(new ServerSocketHandler(serverSocket.accept(), socketPool)).start();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
