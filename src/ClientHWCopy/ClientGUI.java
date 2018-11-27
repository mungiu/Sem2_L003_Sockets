package ClientHWCopy;

import ServerHWCopy.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientGUI extends JFrame
{

	private JTextField textFieldInput;
	private JTextArea textAreaOutput;
	private JButton buttonSend;
	private JButton buttonQuit;

	ObjectOutputStream outToServer = null;
	ObjectInputStream inFromServer = null;
	Socket clientSocket = null;

	public ClientGUI()
	{
		super("View");
		initialize();
		addComponentsToFrame();
	}

	public void start()
	{
		buttonSend.addActionListener(new SendAction());
		buttonQuit.addActionListener(new QuitAction());
		setVisible(true);

		// message stuff goes here
		final int PORT = 6789;
		final String HOST = "localhost";

		try
		{
			clientSocket = new Socket(HOST, PORT);
			updateMessage("Client> connected to server");

			outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
			inFromServer = new ObjectInputStream(clientSocket.getInputStream());

			new Thread(new ClientReceiver(inFromServer, this), "Receiver");
		}
		catch (IOException e1)
		{
			updateMessage("Could not connect");
		}

	}

	private void initialize()
	{
		textFieldInput = new JTextField();
		textAreaOutput = new JTextArea();
		textAreaOutput.setEditable(false);
		textAreaOutput.setBackground(Color.LIGHT_GRAY);
		buttonSend = new JButton("Send");
		buttonQuit = new JButton("Quit");
		setSize(400, 500); // set frame size
		setLocationRelativeTo(null); // center of the screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textAreaOutput.setBackground(Color.ORANGE);
	}

	// add the message to the text area
	void updateMessage(String message)
	{
		textAreaOutput.append(message + "\n");
	}

	private void addComponentsToFrame()
	{
		JPanel panelButtons = new JPanel();
		panelButtons.add(buttonSend);
		panelButtons.add(buttonQuit);

		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(textFieldInput, BorderLayout.CENTER);
		panel1.add(panelButtons, BorderLayout.EAST);

		JScrollPane scrollPane = new JScrollPane(textAreaOutput);

		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.add(panel1, BorderLayout.NORTH);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		setContentPane(contentPane);
	}

	private class SendAction implements ActionListener
	{

		public SendAction()
		{
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			String inputLine = textFieldInput.getText();

//			 Send message to server
			Message message = new Message(inputLine);
			textAreaOutput.append("Client> " + message);
			try
			{
				outToServer.writeObject(message);
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}
	}

	private class QuitAction implements ActionListener
	{

		public QuitAction()
		{
		}

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			System.exit(0);
		}
	}

}
