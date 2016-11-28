package test2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Scanner;

import javax.security.auth.callback.TextOutputCallback;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatView extends JFrame implements ActionListener {

	private JTextField textFieldInput;
	private JTextArea textAreaOutput;
	private JButton buttonSend;
	private JButton buttonQuit;
	Socket clientSocket;
	ObjectInputStream inFromServer;
	ObjectOutputStream outToServer;

	public ChatView() throws IOException {
		super("View");

		initialize();
		addComponentsToFrame();
		final int PORT = 6789;
		final String HOST = "localhost";

		// create client socket, connect to server.
		Socket clientSocket = new Socket(HOST, PORT);
		

		// create output stream attached to the socket.
		outToServer = new ObjectOutputStream(clientSocket.getOutputStream());

		inFromServer = new ObjectInputStream(clientSocket.getInputStream());
		ClientReceiver r = new ClientReceiver(inFromServer, this);
		new Thread(r, "Reaceiver").start();
		String exiting =textAreaOutput.getText();
		textAreaOutput.setText(exiting+"Client connected to server\n");
	}

	public void start() {
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

	@Override
	// ActionListener
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Send")) {
			Message message = new Message(textFieldInput.getText());
			try {

				// Send message to server

				outToServer.writeObject(message);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else {
			System.exit(0);
		}
	}

	

	public void updateMessage(String body) {

		textAreaOutput.append(body + "\n");

	}

}
