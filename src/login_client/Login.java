package login_client;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField ipField;
	private JLabel lblPort;
	private JTextField portField;
	private JLabel lbleg;
	private JLabel lbleg_1;

	public Login() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\AtalaySamet\\Documents\\Javax\\JFrame\\src\\resources\\yesApp.png"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("YesChat Login");
		setSize(400, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nameField = new JTextField();
		nameField.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		nameField.setBounds(81, 94, 232, 31);
		contentPane.add(nameField);
		nameField.setColumns(10);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblUsername.setBounds(151, 61, 91, 31);
		contentPane.add(lblUsername);

		JLabel lblNewLabel = new JLabel("IP Adress:");
		lblNewLabel.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblNewLabel.setBounds(151, 155, 91, 26);
		contentPane.add(lblNewLabel);

		ipField = new JTextField();
		ipField.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		ipField.setColumns(10);
		ipField.setBounds(81, 179, 232, 31);
		contentPane.add(ipField);

		lblPort = new JLabel("Port:");
		lblPort.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblPort.setBounds(173, 273, 47, 26);
		contentPane.add(lblPort);

		portField = new JTextField();
		portField.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		portField.setColumns(10);
		portField.setBounds(81, 298, 232, 31);
		contentPane.add(portField);

		lbleg = new JLabel("(eg. 192.168.0.1)");
		lbleg.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lbleg.setBounds(120, 213, 153, 26);
		contentPane.add(lbleg);

		lbleg_1 = new JLabel("(eg. 8192)");
		lbleg_1.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lbleg_1.setBounds(151, 327, 91, 26);
		contentPane.add(lbleg_1);

		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(Color.BLUE);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String address = ipField.getText();
				int port = Integer.parseInt(portField.getText());
				login(name, address, port);

			}

		});

		btnLogin.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		btnLogin.setBounds(127, 400, 139, 31);
		contentPane.add(btnLogin);
	}

	/**
	 * Login stuff here.
	 */
	private void login(String name, String address, int port) {
		dispose(); // close
		new ClientWindow(name, address, port);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}