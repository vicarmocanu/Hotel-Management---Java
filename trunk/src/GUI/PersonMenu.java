package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Controller.LocationCtr;
import Controller.PersonCtr;
import Model.Person;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PersonMenu {
	private static PersonMenu instance;
	
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblZipcode;
	private JTextField textField_2;
	private JLabel lblCountry;
	private JTextField textField_3;
	private JLabel lblAddress;
	private JTextField textField_4;
	private JLabel lblPhoneno;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel lblEmail;
	private JLabel lblPersontype;
	private JTextField textField_7;
	private JLabel lblPassword;
	private JTextField textField_8;
	private JTable table;
	private JTextField textField_9;
	private JLabel lblCity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonMenu window = new PersonMenu();
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
	private PersonMenu() {
		initialize();
		frame.setVisible(true);
	}
	
	public static PersonMenu getInstance()
	{
		if (instance==null) {
			instance = new PersonMenu();
		}
		return instance;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 962, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPerson = new JLabel("Person");
		lblPerson.setBounds(10, 0, 46, 14);
		frame.getContentPane().add(lblPerson);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(46, 25, 18, 14);
		frame.getContentPane().add(lblId);
		
		textField = new JTextField();
		textField.setBounds(83, 22, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(191, 24, 46, 17);
		frame.getContentPane().add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(226, 22, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		lblZipcode = new JLabel("ZipCode");
		lblZipcode.setBounds(322, 25, 46, 14);
		frame.getContentPane().add(lblZipcode);
		
		textField_2 = new JTextField();
		textField_2.setBounds(373, 22, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		lblCountry = new JLabel("Country");
		lblCountry.setBounds(475, 25, 46, 14);
		frame.getContentPane().add(lblCountry);
		
		textField_3 = new JTextField();
		textField_3.setBounds(531, 22, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		lblAddress = new JLabel("Address");
		lblAddress.setBounds(622, 25, 62, 14);
		frame.getContentPane().add(lblAddress);
		
		textField_4 = new JTextField();
		textField_4.setBounds(83, 53, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		lblPhoneno = new JLabel("PhoneNo");
		lblPhoneno.setBounds(11, 56, 62, 14);
		frame.getContentPane().add(lblPhoneno);
		
		textField_5 = new JTextField();
		textField_5.setBounds(684, 22, 86, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(226, 53, 86, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(191, 56, 31, 14);
		frame.getContentPane().add(lblEmail);
		
		lblPersontype = new JLabel("PersonType");
		lblPersontype.setBounds(321, 53, 77, 20);
		frame.getContentPane().add(lblPersontype);
		
		textField_7 = new JTextField();
		textField_7.setBounds(383, 53, 86, 20);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(475, 56, 62, 14);
		frame.getContentPane().add(lblPassword);
		
		textField_8 = new JTextField();
		textField_8.setBounds(541, 53, 86, 20);
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(934, 385, -512, -282);
		frame.getContentPane().add(scrollPane);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
			}
		});
		btnClose.setBounds(10, 357, 159, 30);
		frame.getContentPane().add(btnClose);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearValues();
			}
		});
		btnClear.setBounds(10, 320, 159, 26);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(336, 114, 600, 284);
		frame.getContentPane().add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		JButton btnCreate = new JButton("Create");
			btnCreate.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(textField_1.getText().equals("") == true || textField_2.getText().equals("") == true || textField_3.getText().equals("") == true ||
							textField_4.getText().equals("") == true || textField_5.getText().equals("") == true || textField_6.getText().equals("") == true ||
							textField_7.getText().equals("") == true || textField_8.getText().equals("") == true || textField.getText().equals("") == true || textField_9.getText().equals("") == true)
					{
						JOptionPane.showMessageDialog(null, "A person attribute might be missing. Please insert all person attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						String personName = textField_1.getText();
						int personZipCode=Integer.parseInt(textField_2.getText());
						String personCountry = textField_3.getText();
						String personAddress = textField_4.getText();
						String personPhoneNo = textField_5.getText();
						String personEmail = textField_6.getText();
						String personPersonType = textField_7.getText();
						String personPassword = textField_8.getText();
						String personCity = textField_9.getText();
						
						LocationCtr locationCtr = new LocationCtr();
						locationCtr.insertLocation(personZipCode, personCountry, personCity);
						PersonCtr personCtr = new PersonCtr();
						personCtr.insertPerson(personName, personAddress, personZipCode, personCountry, personPhoneNo, personEmail, personPersonType, personPassword);
						
						
						JOptionPane.showMessageDialog(null, "Person successfully inserted.", "Info", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
		btnCreate.setBounds(10, 144, 159, 33);
		frame.getContentPane().add(btnCreate);
		
		JButton btnDelete = new JButton("Delete");		
		btnDelete.setBounds(10, 232, 159, 33);
		frame.getContentPane().add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				if(textField.getText().equals("")!= true)
				{
					int id = Integer.parseInt(textField.getText());
					PersonCtr personCtr = new PersonCtr();
					Person personObj = new Person();
					personObj = personCtr.searchPersonById(id);
					if(personObj == null)
					{
						JOptionPane.showMessageDialog(null, "There is no person by this Id", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						if(textField_1.getText().equals("") == true || textField_2.getText().equals("") == true || textField_3.getText().equals("") == true ||
								textField_4.getText().equals("") == true || textField_5.getText().equals("") == true || textField_6.getText().equals("") == true ||
								textField_7.getText().equals("") == true || textField_8.getText().equals("") == true || textField.getText().equals("") == true || textField_9.getText().equals("") == true)
						{
							JOptionPane.showMessageDialog(null, "Some fields might be empty", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							
							String personName = textField_1.getText();
							int personZipCode=Integer.parseInt(textField_2.getText());
							String personCountry = textField_3.getText();
							String personAddress = textField_4.getText();
							String personPhoneNo = textField_5.getText();
							String personEmail = textField_6.getText();
							String personPersonType = textField_7.getText();
							String personPassword = textField_8.getText();
							String personCity = textField_9.getText();
							
							LocationCtr locationCtr = new LocationCtr();
							locationCtr.updateLocation(personZipCode, personCountry, personCity);
							personCtr.updatePerson(id, personName, personAddress, personZipCode, personCountry, personPhoneNo, personEmail, personPersonType, personPassword);
							JOptionPane.showMessageDialog(null, "Person has been succsefully updated", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
				else
				{
					if(textField_1.getText().equals("") != true)
					{
						String personName = textField_1.getText();
						PersonCtr personCtr = new PersonCtr();
						Person personObj = new Person();
						personObj = personCtr.searchPersonByName(personName);
						if(personObj == null)
						{
							JOptionPane.showMessageDialog(null, "There is no person by this name", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							if(textField_1.getText().equals("") == true || textField_2.getText().equals("") == true || textField_3.getText().equals("") == true ||
									textField_4.getText().equals("") == true || textField_5.getText().equals("") == true || textField_6.getText().equals("") == true ||
									textField_7.getText().equals("") == true || textField_8.getText().equals("") == true || textField.getText().equals("") == true || textField_9.getText().equals("") == true)
							{
								JOptionPane.showMessageDialog(null, "Some fields might be empty", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								int id = personObj.getId();
								int personZipCode=Integer.parseInt(textField_2.getText());
								String personCountry = textField_3.getText();
								String personAddress = textField_4.getText();
								String personPhoneNo = textField_5.getText();
								String personEmail = textField_6.getText();
								String personPersonType = textField_7.getText();
								String personPassword = textField_8.getText();
								String personCity = textField_9.getText();
								
								LocationCtr locationCtr = new LocationCtr();
								locationCtr.updateLocation(personZipCode, personCountry, personCity);
								personCtr.updatePerson(id, personName, personAddress, personZipCode, personCountry, personPhoneNo, personEmail, personPersonType, personPassword);
								JOptionPane.showMessageDialog(null, "Person has been succsefully updated", "Info", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please insert either the name or the id of the person you wish to update.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnUpdate.setBounds(10, 188, 159, 33);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnAll = new JButton("All");
		btnAll.setBounds(10, 276, 159, 33);
		frame.getContentPane().add(btnAll);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(10, 106, 159, 33);
		frame.getContentPane().add(btnSearch);
		
		textField_9 = new JTextField();
		textField_9.setBounds(694, 53, 86, 20);
		frame.getContentPane().add(textField_9);
		textField_9.setColumns(10);
		
		lblCity = new JLabel("City");
		lblCity.setBounds(638, 56, 46, 14);
		frame.getContentPane().add(lblCity);
	}
	
	private void clearValues()
	{
		textField.setText(null);
		textField_1.setText(null);
		textField_2.setText(null);
		textField_3.setText(null);
		textField_4.setText(null);
		textField_5.setText(null);
		textField_6.setText(null);
		textField_7.setText(null);
		textField_8.setText(null);
		textField_9.setText(null);
	}
}
