package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class LoginMenu {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginMenu window = new LoginMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLogin.setBounds(74, 193, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(239, 193, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(74, 83, 46, 14);
		frame.getContentPane().add(lblId);
		
		textField = new JTextField();
		textField.setBounds(130, 80, 198, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(74, 132, 46, 14);
		frame.getContentPane().add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(130, 129, 198, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLogin.setBounds(74, 28, 57, 23);
		frame.getContentPane().add(lblLogin);
	}
}
