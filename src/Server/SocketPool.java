package Server;

import java.net.Socket;
import java.util.ArrayList;

public class SocketPool
{

	ArrayList<Socket> socketPool;

	public SocketPool()
	{
		socketPool = new ArrayList<>();
	}

	// since only the main thread is going to write using this method
	// there is no need for synchronization
	public void addSocket(Socket socket)
	{
		socketPool.add(socket);
	}

	public ArrayList<Socket> getSocketPool()
	{
		return socketPool;
	}

}
