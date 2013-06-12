package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;

import Controller.LoginCtr;
import DBLayer.DBPerson;
import DBLayer.IFDBPerson;
import Model.Person;

public class LoginMenu
{
	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	public int universalId;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					LoginMenu window = new LoginMenu();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginMenu()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent arg0)
			{
				LoginCtr loginCtr = new LoginCtr();
				IFDBPerson dbPerson = new DBPerson();
				Person personObj = new Person();
				
				if(textField.getText().equals("")==true || passwordField.getPassword().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert both the id and the password.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{

					String password = String.valueOf(passwordField.getPassword());
					universalId = Integer.parseInt(textField.getText());
					/*test case
					if(universalId == 11 && password.equals("aardvark")) 
					{
						JOptionPane.showMessageDialog(null, "The aardvark is a mammal.", "The aardvark", JOptionPane.OK_OPTION);
						GuestMainMenu guestMainMenu = new GuestMainMenu();
						guestMainMenu.setUniversalId(universalId);
					}*/
					if(loginCtr.checkPassword(universalId, password)==false)
					{
						JOptionPane.showMessageDialog(null, "Incorrect id/password. Please insert correct id/password.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						if(loginCtr.checkGuest(universalId)==true)
						{
							GuestMenu guestMainMenu = new GuestMenu();
							personObj = dbPerson.searchPersonById(universalId, true);
							String personName = personObj.getName();
							guestMainMenu.setGuestName(personName);
							guestMainMenu.setUniversalId(universalId);
						}	
						else if(loginCtr.checkEmployee(universalId))
						{
							EmployeeMainMenu.getInstance();
						}
						else if(loginCtr.checkManager(universalId))
						{
							ManagerMainMenu.getInstance();
						}
					}
				}
			}
		});
		btnLogin.setBounds(130, 157, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnCancel = new JButton("Close");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				frame.dispose();
			}
		});
		btnCancel.setBounds(239, 157, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(84, 80, 50, 17);
		frame.getContentPane().add(lblId);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(130, 80, 198, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(42, 129, 100, 17);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLogin.setBounds(84, 28, 74, 23);
		frame.getContentPane().add(lblLogin);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(130, 129, 198, 20);
		frame.getContentPane().add(passwordField);
	}
}
