package ClientHWCopy;

import java.io.IOException;
import java.net.UnknownHostException;

public class Client
{
	public static void main(String args[]) throws IOException,
			UnknownHostException, ClassNotFoundException
	{
		ClientGUI clientGUI = new ClientGUI();
		clientGUI.start();

	}
}
