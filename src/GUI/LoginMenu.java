package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.LoginCtr;
import Controller.PersonCtr;
import Model.Person;

public class LoginMenu
{
	private static LoginMenu instance = null;
	private PersonCtr personCtr = new PersonCtr();
	private LoginCtr loginCtr = new LoginCtr();
	
	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private int universalId;
	
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
	private LoginMenu()
	{
		initialize();
	}
	
	//instance of login
	public static LoginMenu getInstance()
	{
		if(instance==null)
		{
			instance = new LoginMenu();
		}
		return instance;
		
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
				
				//IFDBPerson dbPerson = new DBPerson();
				Person personObj = new Person();
				
				if(textField.getText().equals("")==true || passwordField.getPassword().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert both the id and the password.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String password = String.valueOf(passwordField.getPassword());
					universalId = Integer.parseInt(textField.getText());
					//id/password check
					if(loginCtr.checkPassword(universalId, password)==false)
					{
						JOptionPane.showMessageDialog(null, "Incorrect id/password. Please insert correct id/password.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						//check if guest
						if(loginCtr.checkGuest(universalId)==true)
						{
							GuestMenu guestMainMenu = new GuestMenu();
							//personObj = dbPerson.searchPersonById(universalId, true);
							personObj = personCtr.searchPersonById(universalId);
							String personName = personObj.getName();
							guestMainMenu.setGuestName(personName);
							guestMainMenu.setUniversalId(universalId);
							
							textField.setText("");
							passwordField.setText("");
							frame.dispose();
						}
						//check if employee
						else if(loginCtr.checkEmployee(universalId))
						{
							EmployeeMenu employeeMenu = new EmployeeMenu();
							//personObj = dbPerson.searchPersonById(universalId, true);
							personObj = personCtr.searchPersonById(universalId);
							String personName = personObj.getName();
							employeeMenu.setDinamicLabel(personName);
							
							textField.setText("");
							passwordField.setText("");
							frame.dispose();
						}
						//check if manager
						else if(loginCtr.checkManager(universalId) == true)
						{
							ManagerMenu managerMainMenu = new ManagerMenu();
							//personObj = dbPerson.searchPersonById(universalId, true);
							personObj = personCtr.searchPersonById(universalId);
							String personName = personObj.getName();
							managerMainMenu.setManagerName(personName);
							
							textField.setText("");
							passwordField.setText("");
							frame.dispose();
						}
						//check if instructor
						else if(loginCtr.checkInstructor(universalId) == true)
						{
							InstructorMenu instructorMenu = new InstructorMenu();
							//personObj = dbPerson.searchPersonById(universalId, true);
							personObj = personCtr.searchPersonById(universalId);
							String personName = personObj.getName();
							instructorMenu.setInstructorName(personName);
							instructorMenu.setUniversalId(universalId);
							
							textField.setText("");
							passwordField.setText("");
							frame.dispose();
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
