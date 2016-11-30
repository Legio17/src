package database.network.chatFromClass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatView extends JFrame implements ActionListener, KeyListener {

	private JTextField textFieldInput;
	private JTextArea textAreaOutput;
	private JButton buttonSend;
	private JButton buttonQuit;

	final int PORT = 6789;
	final String HOST = "localhost";
	Socket clientSocket;
	ObjectOutputStream outToServer;
	ObjectInputStream inFromServer;

	public ChatView() {
		super("View");

		initialize();
		addComponentsToFrame();
	}

	public void start() {
		try {
			// create client socket, connect to server.
			clientSocket = new Socket(HOST, PORT);

			// create output stream attached to the socket.
			outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
			// create input stream attached to the socket.
			inFromServer = new ObjectInputStream(clientSocket.getInputStream());

			String existing = textAreaOutput.getText();
			textAreaOutput.setText(existing + "Client> connected to server"+ "\n");

			ClientReceiver r = new ClientReceiver(inFromServer, this);
			new Thread(r, "Receiver").start();
		} catch (IOException e) {
			System.out.println("error!");
			e.printStackTrace();
		}
		buttonSend.addActionListener(this);
		buttonQuit.addActionListener(this);
		textFieldInput.addActionListener(this);
		setVisible(true);
	}

	private void initialize() {
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

	private void addComponentsToFrame() {
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

	public void updateMessages(String s) {
		textAreaOutput.append(s);
	}

	@Override
	// ActionListener
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Send")
				|| e.getSource().equals(textFieldInput)) {

			try {
				/*
				 * Read line from keyboard. Scanner keyboard = new
				 * Scanner(System.in);
				 * System.out.print("Write a line for the server: "); String
				 * inputLine = keyboard.nextLine(); keyboard.close();
				 */

				// Send message to server
				String inputLine = textFieldInput.getText();
				Message message = new Message(inputLine);
				System.out.println("Client> " + message);
				outToServer.writeObject(message);
				textFieldInput.setText("");
			} catch (IOException k) {
				System.out.println("das wrong");
			}

		} else {
			System.exit(0);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		e.getKeyCode();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
