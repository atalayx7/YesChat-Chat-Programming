package login_client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Formatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import server.currentTime;

public class ClientWindow extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTextField txtMessage;
	private JTextArea history;
	private DefaultCaret caret;
	private Formatter theTime;
	private Thread listen, run;
	private Client client;
	private boolean running = false;
	private JButton btnNewButton;
	private JButton btnPlay;

	// private ClientWindow x;
	private String inputValue;
	private String inputt;

	// private String text;

	public ClientWindow(String name, String address, int port) {
		setResizable(false);

		setBackground(Color.YELLOW);
		setForeground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\AtalaySamet\\Documents\\Javax\\JFrame\\src\\resources\\yesApp.png"));
		setFont(new Font("Lucida Console", Font.PLAIN, 15));
		setTitle("YesChat");
		client = new Client(name, address, port);
		
		boolean connect = client.openConnection(address);
		if (!connect) {
			System.err.println("Connection Failed...");
			console("Connection Failed...");

		}
		createWindow();

		// console("Attempting a connection to "+address+" : "+port+", user:
		// "+name);
	
		console("Connected -> " + name);
	
		String connection = "/c/" + name + "/e/";
		client.send(connection.getBytes());
		running = true;
		run = new Thread(this, "Running");
		run.start();
		backgroundMusic.runMusic();
		// encrypto();

	}

	/*
	 * public void envsde(String text) { this.text = text; if
	 * (this.inputValue.equals(this.inputt)) { Cipher solver = new Cipher();
	 * text = solver.translate(text, inputValue);
	 * 
	 * } return; }
	 * 
	 * // public String getText() { // return this.text; // }
	 */

	public String encrypto() {

		return this.inputValue = JOptionPane.showInputDialog("Please Enter a Key to Encryption of Your Messages");

	}

	public String decrypt() {
		return this.inputt = JOptionPane.showInputDialog("Please Enter a Key for Decryption");
	}

	public String getInput() {
		return inputValue;
	}

	private void createWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(880, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setForeground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		history = new JTextArea();
		history.setBackground(Color.GREEN);
		history.setEditable(false);
		caret = (DefaultCaret) history.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		contentPane.setLayout(null);
		JScrollPane scroll = new JScrollPane(history);
		scroll.setBounds(10, 5, 786, 453);
		history.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		contentPane.add(scroll);
		txtMessage = new JTextField();
		txtMessage.setBounds(5, 467, 756, 22);
		txtMessage.setBackground(Color.GREEN);
		txtMessage.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					theTime = currentTime.getCurrentTime();
					send(txtMessage.getText(), true);
				}
			}
		});

		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(ClientWindow.class.getResource("/login_client/mute-48.png")));
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnNewButton.setBounds(803, 5, 47, 61);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				backgroundMusic.notListen();
			}
		});
		contentPane.add(btnNewButton);

		btnPlay = new JButton("");
		btnPlay.setIcon(new ImageIcon(ClientWindow.class.getResource("/login_client/volume-up-48.png")));
		btnPlay.setBackground(Color.GREEN);
		btnPlay.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backgroundMusic.listen();
			}
		});
		btnPlay.setBounds(803, 79, 47, 61);
		contentPane.add(btnPlay);
		txtMessage.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		contentPane.add(txtMessage);
		txtMessage.setColumns(10);
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(777, 465, 69, 25);
		btnSend.setBackground(Color.GREEN);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theTime = currentTime.getCurrentTime();
				send(txtMessage.getText(), true);
			}
		});
		btnSend.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		contentPane.add(btnSend);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				String disconnect = "/d/" + client.getID() + "/e/";
				send(disconnect, false);
				running = false;
				client.close();

			}

		});
		setVisible(true);
		txtMessage.requestFocusInWindow();
	}

	public void run() {
		listen();
	}

	private void send(String message, boolean text) {

		if (message.equals(""))
			return;

		if (text) {

			message = client.getName() + "-> " + message + "   (" + theTime + ")";
			Cipher b = new Cipher(message, "lala");
			message = "/m/" + b.getOutput() + "/e/";
			txtMessage.setText("");
			System.out.println("Bu mesaj şifrelendi::::::: " + message);
		}

		client.send(message.getBytes()); // here m will identify as a
		// message
	}

	public void listen() {

		listen = new Thread("Listen") {
			boolean alreadyExecuted = false;
			// private Cipher a;

			public void run() {

				while (running) {
					String message = client.receive();
					if (message.startsWith("/c/")) { // it indicates connection
														// as c
						// "c/8112/e/ "
						client.setID(Integer.parseInt(message.split("/c/|/e/")[1].trim()));
						console("Successfully connected to server! -> " + client.getID());
						/**
						 * ("/c/|/e/") means, for example we have "c/8112/e/ "
						 * this in the message but we just need 8112 so we
						 * eliminate the other.
						 * 
						 * 
						 * At below else if part; this is kinda sign of message
						 * that all users will receive
						 */
					} else if (message.startsWith("/m/")) {
						String text = message.substring(3);
						text = text.split("/e/")[0].trim();

						System.out.println("Bu textir, üstekiyle aynı olması gerek:::::::: " + text);
						if (!alreadyExecuted) {
							decrypt();
							alreadyExecuted = true;
						}

						// System.out.println("Encrypt key " + inputValue);
						// System.out.println("Decrypt key " + inputt);
						// System.out.println("Orjinal mesajim " + text);
						System.out.println("Bu inputtur " + inputt);
						// System.out.println("Sifrelenmis messajim " +
						// a.getOutput());

						// text = a.getOutput();
						if (inputt.equals("lala")) {
							Cipher a = new Cipher(text, inputt);
							text = a.translate(text, inputt);

						}
						System.out.println(("if ten sonraki line key doğruysa çözülmüş olmalı::::" + text));
						// text=a.getOutput();
						// envsde(text);

						// text = getText();

						/*
						 * decrypt();
						 * 
						 * if (inputt.equals(inputValue)) { Cipher a = new
						 * Cipher(text, inputt); text = a.translate(text,
						 * inputt);
						 * 
						 * }
						 */
						console(text);

					} else if (message.startsWith("/i/")) {
						String text = "/i/" + client.getID() + "/e/";
						send(text, false);
					}
				}
			}
		};
		listen.start();

	}

	private void console(String message) {
		history.append(message + "\n\r");
		history.setCaretPosition(history.getDocument().getLength());
	}

}
