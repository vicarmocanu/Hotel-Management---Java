package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controller.ActivityCtr;
import Controller.EmployeeCtr;
import Controller.FacilityCtr;
import Controller.GuestCtr;
import Controller.InstructorCtr;
import Controller.LocationCtr;
import Controller.PersonCtr;
import Controller.TravelAgencyCtr;
import Model.ActivityType;
import Model.Facility;
import Model.Guest;
import Model.Instructor;
import Model.Location;
import Model.Person;
import Model.TravelAgency;

public class ManagerMenu
{
	private ActivityCtr activityTypeCtr = new ActivityCtr();
	private FacilityCtr facilityCtr = new FacilityCtr();
	private TravelAgencyCtr travelCtr = new TravelAgencyCtr();
	private GuestCtr guestCtr = new GuestCtr();
	private PersonCtr personCtr = new PersonCtr();
	private LocationCtr locationCtr = new LocationCtr();
	private InstructorCtr instructorCtr = new InstructorCtr();
	private EmployeeCtr employeeCtr = new EmployeeCtr();

	private JFrame frame;
	private JLabel dinamicLabel;
	private JTextField facilityIdTextField;
	private JTextField facilityNameTextField;
	private JComboBox<String> activityTypeComboBox;
	private JComboBox<String> statusComboBox;
	private JTable facilityTable;
	private JScrollPane facilityTableScrollPane;
	private JTextField activityTypeIdTextField;
	private JTextField activityTypeNameTextField;
	private JTextField maxParticipantsTextField;
	private JTable activityTypeTable;
	private JTextField travelAgencyCVRTextField;
	private JTextField travelAgencyNameTextField;
	private JTextField travelAgencyCityTextField;
	private JTextField travelAgencyCountryTextField;
	private JTextField travelAgencyZipcodeTextField;
	private JTextField travelAgencyAddressTextField;
	private JTextField travelAgencyPhoneNoTextField;
	private JTextField travelAgencyEmailTextField;
	private JTable travelAgencyTable;
	private JTextField guestIdTextField;
	private JTextField guestNameTextField;
	private JTextField guestCityTextField;
	private JTextField guestCountryTextField;
	private JTextField guestZipcodeTextField;
	private JTextField guestAddressTextField;
	private JTextField guestPhoneNoTextField;
	private JTextField guestEmailTextField;
	private JTextField guestPasswordTextField;
	private JTable guestTable;
	private JComboBox<String> guestTravelAgencyComboBox;
	private JComboBox<String> guestGuestTypeComboBox;
	private JTextField instructorIdTextField;
	private JTextField instructorNameTextField;
	private JTextField instructorCityTextField;
	private JTextField instructorCountryTextField;
	private JTextField instructorZipcodeTextField;
	private JTextField instructorAddressTextField;
	private JTextField instructorPhoneNoTextField;
	private JTextField instructorEmailTextField;
	private JTextField instructorPasswordTextField;
	private JTextField instructorSalaryTextField;
	private JTextField instructorPriceTextField;
	private JTable instructorTable;
	private JComboBox<String> instructorActivityTypeComboBox;
	private JComboBox<String> instructorStatusComboBox;
	
	public ManagerMenu()
	{
		initialize();
		frame.setVisible(true);
	}

	private void initialize() 
	{
		frame = new JFrame();
		frame.setFont(new Font("Dialog", Font.PLAIN, 14));
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 850, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton logOffButton = new JButton("Log Off");
		logOffButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				frame.dispose();
				LoginMenu loginMenu = LoginMenu.getInstance();
				loginMenu.frame.setVisible(true);
			}
		});
		logOffButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		logOffButton.setBounds(715, 550, 125, 39);
		frame.getContentPane().add(logOffButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 830, 528);
		frame.getContentPane().add(tabbedPane);
		
		JPanel WelcomePanel = new JPanel();
		tabbedPane.addTab("Main menu", null, WelcomePanel, null);
		WelcomePanel.setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome,");
		welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		welcomeLabel.setBounds(34, 37, 115, 25);
		WelcomePanel.add(welcomeLabel);
		
		dinamicLabel = new JLabel("MANAGER");
		dinamicLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		dinamicLabel.setBounds(137, 37, 216, 25);
		WelcomePanel.add(dinamicLabel);
		
		JPanel GuestPanel = new JPanel();
		tabbedPane.addTab("Guest menu", null, GuestPanel, null);
		GuestPanel.setLayout(null);
		
		JPanel guestAttributesPanel = new JPanel();
		guestAttributesPanel.setBorder(new TitledBorder(null, "Guest attributes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		guestAttributesPanel.setBounds(6, 11, 768, 93);
		GuestPanel.add(guestAttributesPanel);
		guestAttributesPanel.setLayout(null);
		
		JLabel guestIdLabel = new JLabel("Id:");
		guestIdLabel.setBounds(6, 19, 46, 14);
		guestAttributesPanel.add(guestIdLabel);
		guestIdLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestIdTextField = new JTextField();
		guestIdTextField.setBounds(70, 16, 80, 20);
		guestAttributesPanel.add(guestIdTextField);
		guestIdTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestIdTextField.setColumns(10);
		
		JLabel guestNameLabel = new JLabel("Name:");
		guestNameLabel.setBounds(160, 19, 46, 14);
		guestAttributesPanel.add(guestNameLabel);
		guestNameLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestNameTextField = new JTextField();
		guestNameTextField.setBounds(240, 16, 80, 20);
		guestAttributesPanel.add(guestNameTextField);
		guestNameTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestNameTextField.setColumns(10);
		
		JLabel guestCityLabel = new JLabel("City:");
		guestCityLabel.setBounds(330, 19, 86, 14);
		guestAttributesPanel.add(guestCityLabel);
		guestCityLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestCityTextField = new JTextField();
		guestCityTextField.setBounds(372, 16, 80, 20);
		guestAttributesPanel.add(guestCityTextField);
		guestCityTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestCityTextField.setColumns(10);
		
		JLabel guestCountryLabel = new JLabel("Country:");
		guestCountryLabel.setBounds(462, 19, 86, 14);
		guestAttributesPanel.add(guestCountryLabel);
		guestCountryLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestCountryTextField = new JTextField();
		guestCountryTextField.setBounds(531, 16, 80, 20);
		guestAttributesPanel.add(guestCountryTextField);
		guestCountryTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestCountryTextField.setColumns(10);
		
		JLabel guestZipcodeLabel = new JLabel("Zipcode:");
		guestZipcodeLabel.setBounds(621, 19, 86, 14);
		guestAttributesPanel.add(guestZipcodeLabel);
		guestZipcodeLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestZipcodeTextField = new JTextField();
		guestZipcodeTextField.setBounds(682, 16, 80, 20);
		guestAttributesPanel.add(guestZipcodeTextField);
		guestZipcodeTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestZipcodeTextField.setColumns(10);
		
		JLabel guestAddressLabel = new JLabel("Address:");
		guestAddressLabel.setBounds(6, 44, 86, 14);
		guestAttributesPanel.add(guestAddressLabel);
		guestAddressLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestAddressTextField = new JTextField();
		guestAddressTextField.setBounds(70, 41, 80, 20);
		guestAttributesPanel.add(guestAddressTextField);
		guestAddressTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestAddressTextField.setColumns(10);
		
		JLabel guestPhoneNoLabel = new JLabel("Phone no:");
		guestPhoneNoLabel.setBounds(160, 44, 86, 14);
		guestAttributesPanel.add(guestPhoneNoLabel);
		guestPhoneNoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestPhoneNoTextField = new JTextField();
		guestPhoneNoTextField.setBounds(240, 41, 80, 20);
		guestAttributesPanel.add(guestPhoneNoTextField);
		guestPhoneNoTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestPhoneNoTextField.setColumns(10);
		
		JLabel guestEmailLabel = new JLabel("E-mail:");
		guestEmailLabel.setBounds(330, 44, 86, 14);
		guestAttributesPanel.add(guestEmailLabel);
		guestEmailLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestEmailTextField = new JTextField();
		guestEmailTextField.setBounds(372, 41, 80, 20);
		guestAttributesPanel.add(guestEmailTextField);
		guestEmailTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestEmailTextField.setColumns(10);
		
		JLabel guestPasswordLabel = new JLabel("Password:");
		guestPasswordLabel.setBounds(462, 44, 86, 14);
		guestAttributesPanel.add(guestPasswordLabel);
		guestPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestPasswordTextField = new JTextField();
		guestPasswordTextField.setEditable(false);
		guestPasswordTextField.setBounds(531, 41, 80, 20);
		guestAttributesPanel.add(guestPasswordTextField);
		guestPasswordTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestPasswordTextField.setColumns(10);
		
		JLabel guestGuestTypeLabel = new JLabel("Guest type:");
		guestGuestTypeLabel.setBounds(621, 44, 86, 14);
		guestAttributesPanel.add(guestGuestTypeLabel);
		guestGuestTypeLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JLabel guestTravelAgencyLabel = new JLabel("Travel agency:");
		guestTravelAgencyLabel.setBounds(6, 69, 86, 14);
		guestAttributesPanel.add(guestTravelAgencyLabel);
		guestTravelAgencyLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestTravelAgencyComboBox = new JComboBox<String>();
		LinkedList<TravelAgency> allTravelAgenciesList = new LinkedList<TravelAgency>();
		allTravelAgenciesList = travelCtr.getAllTravelAgencies();
		if(allTravelAgenciesList.isEmpty() == false)
		{
			for(TravelAgency listElementTravelAgencyObj: allTravelAgenciesList)
			{
				String comboBoxItem = listElementTravelAgencyObj.getName();
				guestTravelAgencyComboBox.addItem(comboBoxItem);
			}
		}
		guestTravelAgencyComboBox.setSelectedItem(null);
		guestTravelAgencyComboBox.setBounds(84, 66, 122, 20);
		guestAttributesPanel.add(guestTravelAgencyComboBox);
		
		JButton noTravelAgencyButton = new JButton("No travel agency");
		noTravelAgencyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				guestTravelAgencyComboBox.setSelectedItem(null);
			}
		});
		noTravelAgencyButton.setFont(new Font("Arial", Font.PLAIN, 11));
		noTravelAgencyButton.setBounds(216, 64, 146, 25);
		guestAttributesPanel.add(noTravelAgencyButton);
		
		guestGuestTypeComboBox = new JComboBox<String>();
		guestGuestTypeComboBox.addItem("Private");
		guestGuestTypeComboBox.addItem("Business");
		guestGuestTypeComboBox.addItem("Family");
		guestGuestTypeComboBox.setSelectedItem(null);
		guestGuestTypeComboBox.setBounds(682, 41, 80, 20);
		guestAttributesPanel.add(guestGuestTypeComboBox);
		
		JPanel guestOptionsMenu = new JPanel();
		guestOptionsMenu.setLayout(null);
		guestOptionsMenu.setBorder(new TitledBorder(null, "Guest options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		guestOptionsMenu.setBounds(6, 108, 104, 192);
		GuestPanel.add(guestOptionsMenu);
		
		JButton guestSearchButton = new JButton("Search");
		guestSearchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(guestIdTextField.getText().equals("")==true && guestNameTextField.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the wanted guest.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(guestNameTextField.getText().equals("") == true)
					{
						String stringGuestId = guestIdTextField.getText();
						int guestId = Integer.parseInt(stringGuestId);
						
						Guest guestObj = new Guest();
						guestObj = guestCtr.searchGuestById(guestId);
						
						if(guestObj == null)
						{
							JOptionPane.showMessageDialog(null, "There is no guest by this id. Please insert a valid guest id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							String guestName = guestObj.getName();
							int guestZipCode = guestObj.getZipCode();
							String stringGuestZipcode = String.valueOf(guestZipCode);
							String guestCountry = guestObj.getCountry();
							String guestAddress = guestObj.getAddress();
							String guestPhoneNo = guestObj.getPhoneNo();
							String guestEmail = guestObj.getEmail();
							String guestPassword = guestObj.getPassword();
							String guestType = guestObj.getGuestType();
							
							String travelAgencyName = new String();
							TravelAgency travelAgencyObj = new TravelAgency();
							travelAgencyObj = guestObj.getTravelAgency();
							if(travelAgencyObj == null)
							{
								travelAgencyName = "0";
							}
							else
							{
								travelAgencyName = travelAgencyObj.getName();
							}
							
							Location locationObj = new Location();
							locationObj = locationCtr.getLocation(guestZipCode, guestCountry);
							String guestCity = locationObj.getCity();
							
							guestIdTextField.setText(stringGuestId);
							guestNameTextField.setText(guestName);
							guestCityTextField.setText(guestCity);
							guestCountryTextField.setText(guestCountry);
							guestZipcodeTextField.setText(stringGuestZipcode);
							guestAddressTextField.setText(guestAddress);
							guestPhoneNoTextField.setText(guestPhoneNo);
							guestEmailTextField.setText(guestEmail);
							guestPasswordTextField.setText(guestPassword);
							guestGuestTypeComboBox.setSelectedItem(guestType);
							if(travelAgencyName.equals("0") == true)
							{
								guestTravelAgencyComboBox.setSelectedItem(null);
							}
							else
							{
								guestTravelAgencyComboBox.setSelectedItem(travelAgencyName);
							}
						}
					}
					else
					{
						if(guestIdTextField.getText().equals("") == true)
						{
							String guestName = guestNameTextField.getText();
							
							Person personObj = new Person();
							personObj = personCtr.searchPersonByName(guestName);
							
							if(personObj == null || personObj.getPersonType().equals("Guest") == false)
							{
								JOptionPane.showMessageDialog(null, "There is no guest by this name. Please insert a valid guest name.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								int guestId = personObj.getId();
								Guest guestObj = new Guest();
								guestObj = guestCtr.searchGuestById(guestId);
								String stringGuestId = String.valueOf(guestId);
								int guestZipCode = guestObj.getZipCode();
								String stringGuestZipcode = String.valueOf(guestZipCode);
								String guestCountry = guestObj.getCountry();
								String guestAddress = guestObj.getAddress();
								String guestPhoneNo = guestObj.getPhoneNo();
								String guestEmail = guestObj.getEmail();
								String guestPassword = guestObj.getPassword();
								String guestType = guestObj.getGuestType();
															
								String travelAgencyName = new String();
								TravelAgency travelAgencyObj = new TravelAgency();
								travelAgencyObj = guestObj.getTravelAgency();
								if(travelAgencyObj == null)
								{
									travelAgencyName = "0";
								}
								else
								{
									travelAgencyName = travelAgencyObj.getName();
								}
								
								Location locationObj = new Location();
								locationObj = locationCtr.getLocation(guestZipCode, guestCountry);
								String guestCity = locationObj.getCity();
								
								guestIdTextField.setText(stringGuestId);
								guestNameTextField.setText(guestName);
								guestCityTextField.setText(guestCity);
								guestCountryTextField.setText(guestCountry);
								guestZipcodeTextField.setText(stringGuestZipcode);
								guestAddressTextField.setText(guestAddress);
								guestPhoneNoTextField.setText(guestPhoneNo);
								guestEmailTextField.setText(guestEmail);
								guestPasswordTextField.setText(guestPassword);
								guestGuestTypeComboBox.setSelectedItem(guestType);
								if(travelAgencyName.equals("0") == true)
								{
									guestTravelAgencyComboBox.setSelectedItem(null);
								}
								else
								{
									guestTravelAgencyComboBox.setSelectedItem(travelAgencyName);
								}
							}
						}
						else
						{
							if(guestIdTextField.getText().equals("") != true && guestNameTextField.getText().equals("") != true)
							{
								String stringGuestId = guestIdTextField.getText();
								int guestId = Integer.parseInt(stringGuestId);
								
								Guest guestObj = new Guest();
								guestObj = guestCtr.searchGuestById(guestId);
								
								if(guestObj == null)
								{
									JOptionPane.showMessageDialog(null, "There is no guest by this id. Please insert a valid guest id.", "Error!", JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									String guestName = guestObj.getName();
									int guestZipCode = guestObj.getZipCode();
									String stringGuestZipcode = String.valueOf(guestZipCode);
									String guestCountry = guestObj.getCountry();
									String guestAddress = guestObj.getAddress();
									String guestPhoneNo = guestObj.getPhoneNo();
									String guestEmail = guestObj.getEmail();
									String guestPassword = guestObj.getPassword();
									String guestType = guestObj.getGuestType();
																
									String travelAgencyName = new String();
									TravelAgency travelAgencyObj = new TravelAgency();
									travelAgencyObj = guestObj.getTravelAgency();
									if(travelAgencyObj == null)
									{
										travelAgencyName = "0";
									}
									else
									{
										travelAgencyName = travelAgencyObj.getName();
									}
									
									Location locationObj = new Location();
									locationObj = locationCtr.getLocation(guestZipCode, guestCountry);
									String guestCity = locationObj.getCity();
									
									guestIdTextField.setText(stringGuestId);
									guestNameTextField.setText(guestName);
									guestCityTextField.setText(guestCity);
									guestCountryTextField.setText(guestCountry);
									guestZipcodeTextField.setText(stringGuestZipcode);
									guestAddressTextField.setText(guestAddress);
									guestPhoneNoTextField.setText(guestPhoneNo);
									guestEmailTextField.setText(guestEmail);
									guestPasswordTextField.setText(guestPassword);									
									guestGuestTypeComboBox.setSelectedItem(guestType);
									
									if(travelAgencyName.equals("0") == true)
									{
										guestTravelAgencyComboBox.setSelectedItem(null);
									}
									else
									{
										guestTravelAgencyComboBox.setSelectedItem(travelAgencyName);
									}
								}
							}
						}
					}
				}
			}
		});
		guestSearchButton.setFont(new Font("Arial", Font.PLAIN, 11));
		guestSearchButton.setBounds(6, 16, 90, 25);
		guestOptionsMenu.add(guestSearchButton);
		
		JButton guestCreateButton = new JButton("Create");
		guestCreateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(guestNameTextField.getText().equals("") == true || guestCityTextField.getText().equals("") == true ||
						guestCountryTextField.getText().equals("") == true || guestZipcodeTextField.getText().equals("") == true || guestAddressTextField.getText().equals("") == true ||
								guestGuestTypeComboBox.getSelectedItem().equals(null) == true)
			
				{
					JOptionPane.showMessageDialog(null, "A guest attribute might be missing. Please insert all needed guest attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String guestName = guestNameTextField.getText();
					String guestCity = guestCityTextField.getText();
					String guestCountry = guestCountryTextField.getText();
					String stringGuestZipcode = guestZipcodeTextField.getText();
					int guestZipcode = Integer.parseInt(stringGuestZipcode);
					String guestAddress = guestAddressTextField.getText();
					String guestPhoneNo = guestPhoneNoTextField.getText();
					String guestEmail = guestEmailTextField.getText();
					String guestPassword = personCtr.getPersonPassword(guestName, stringGuestZipcode, guestCountry, guestAddress);
					String guestType =(String) guestGuestTypeComboBox.getSelectedItem();
					
					int travelAgencyCVR = 0;
					
					if(guestTravelAgencyComboBox.getSelectedItem() == null)
					{
						travelAgencyCVR = 0;
					}
					else
					{
						String travelAgencyName = (String) guestTravelAgencyComboBox.getSelectedItem();
						TravelAgency travelAgencyObj = new TravelAgency();
						travelAgencyObj = travelCtr.getTravelAgencyByName(travelAgencyName);
						travelAgencyCVR = travelAgencyObj.getCVR();
					}
					
					if(personCtr.searchPersonByName(guestName) != null)
					{
						JOptionPane.showMessageDialog(null, "Cannot register the same guest twice.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						locationCtr.insertLocation(guestZipcode, guestCountry, guestCity);
						personCtr.insertPerson(guestName, guestAddress, guestZipcode, guestCountry, guestPhoneNo, guestEmail, "Guest", guestPassword);
						guestCtr.insertGuest(guestName, travelAgencyCVR, guestType);
						clearGuestPanel();
						clearGuestTable();
						JOptionPane.showMessageDialog(null, "Guest successfully inserted", "Info", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		guestCreateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		guestCreateButton.setBounds(6, 52, 90, 25);
		guestOptionsMenu.add(guestCreateButton);
		
		JButton guestUpdateButton = new JButton("Update");
		guestUpdateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(guestIdTextField.getText().equals("") == true || guestNameTextField.getText().equals("") == true || guestCityTextField.getText().equals("") == true ||
						guestCountryTextField.getText().equals("") == true || guestZipcodeTextField.getText().equals("") == true || guestAddressTextField.getText().equals("") == true ||
								guestGuestTypeComboBox.getSelectedItem().equals(null) == true)
			
				{
					JOptionPane.showMessageDialog(null, "A guest attribute might be missing. Please insert all needed guest attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String stringGuestId = guestIdTextField.getText();
					int guestId = Integer.parseInt(stringGuestId);
					
					Guest guestObj = new Guest();
					guestObj = guestCtr.searchGuestById(guestId);
					
					if(guestObj == null)
					{
						JOptionPane.showMessageDialog(null, "The wanted guest does not exist in the system. Please check guest list.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						String guestName = guestNameTextField.getText();
						String guestCity = guestCityTextField.getText();
						String guestCountry = guestCountryTextField.getText();
						String stringGuestZipcode = guestZipcodeTextField.getText();
						int guestZipcode = Integer.parseInt(stringGuestZipcode);
						String guestAddress = guestAddressTextField.getText();
						String guestPhoneNo = guestPhoneNoTextField.getText();
						String guestEmail = guestEmailTextField.getText();
						String guestType =(String) guestGuestTypeComboBox.getSelectedItem();
						String guestPassword = personCtr.getPersonPassword(guestName, stringGuestZipcode, guestCountry, guestAddress);
						
						int travelAgencyCVR = 0;
						if(guestTravelAgencyComboBox.getSelectedItem() == null)
						{
							travelAgencyCVR = 0;
						}
						else
						{
							String travelAgencyName = (String) guestTravelAgencyComboBox.getSelectedItem();
							TravelAgency travelAgencyObj = new TravelAgency();
							travelAgencyObj = travelCtr.getTravelAgencyByName(travelAgencyName);
							travelAgencyCVR = travelAgencyObj.getCVR();
						}
						
						if(personCtr.checkPersonInstanceCount(guestId, guestName, guestZipcode, guestCountry, guestAddress) == false)
						{
							JOptionPane.showMessageDialog(null, "You may not update with an already existing guest on this id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							Location locationObj =  new Location();
							locationObj = locationCtr.getCompleteLocation(guestZipcode, guestCountry, guestCity);
							if(locationObj == null)
							{
								locationCtr.insertLocation(guestZipcode, guestCountry, guestCity);
							}
							
							personCtr.updatePerson(guestId, guestName, guestAddress, guestZipcode, guestCountry, guestPhoneNo, guestEmail, "Guest", guestPassword);
							guestCtr.updateGuest(guestId, guestType, travelAgencyCVR);
							clearGuestPanel();
							clearGuestTable();
							JOptionPane.showMessageDialog(null, "Guest updated successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
		guestUpdateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		guestUpdateButton.setBounds(6, 88, 90, 25);
		guestOptionsMenu.add(guestUpdateButton);
		
		JButton guestDeleteButton = new JButton("Delete");
		guestDeleteButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(guestIdTextField.getText().equals("")==true && guestNameTextField.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the wanted guest.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(guestNameTextField.getText().equals("") == true)
					{
						String stringGuestId = guestIdTextField.getText();
						int guestId = Integer.parseInt(stringGuestId);
						
						Guest guestObj = new Guest();
						guestObj = guestCtr.searchGuestById(guestId);
						
						if(guestObj == null)
						{
							JOptionPane.showMessageDialog(null, "There is no guest by this id. Please insert a valid guest id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							guestCtr.deleteGuest(guestId);
							personCtr.deletePerson(guestId);
							clearGuestPanel();
							clearGuestTable();
							JOptionPane.showMessageDialog(null, "Guest successfully removed", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
					{
						if(guestIdTextField.getText().equals("") == true)
						{
							String guestName = guestNameTextField.getText();
							
							Person personObj = new Person();
							personObj = personCtr.searchPersonByName(guestName);
							
							if(personObj == null || personObj.getPersonType().equals("Guest") == false)
							{
								JOptionPane.showMessageDialog(null, "There is no guest by this name. Please insert a valid guest name.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								int guestId = personObj.getId();
								guestCtr.deleteGuest(guestId);
								personCtr.deletePerson(guestId);
								JOptionPane.showMessageDialog(null, "Guest successfully removed", "Info", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else
						{
							if(guestIdTextField.getText().equals("") != true && guestNameTextField.getText().equals("") != true)
							{
								String stringGuestId = guestIdTextField.getText();
								int guestId = Integer.parseInt(stringGuestId);
								
								Guest guestObj = new Guest();
								guestObj = guestCtr.searchGuestById(guestId);
								
								if(guestObj == null)
								{
									JOptionPane.showMessageDialog(null, "There is no guest by this id. Please insert a valid guest id.", "Error!", JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									guestCtr.deleteGuest(guestId);
									personCtr.deletePerson(guestId);
									JOptionPane.showMessageDialog(null, "Guest successfully removed", "Info", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
				}
			}
		});
		guestDeleteButton.setFont(new Font("Arial", Font.PLAIN, 11));
		guestDeleteButton.setBounds(6, 124, 90, 25);
		guestOptionsMenu.add(guestDeleteButton);
		
		JButton guestAllButton = new JButton("All");
		guestAllButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				clearGuestPanel();
				clearGuestTable();
				
				guestTable.setModel(getGuestTableModel());
			}
		});
		guestAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		guestAllButton.setBounds(6, 160, 90, 25);
		guestOptionsMenu.add(guestAllButton);
		
		JButton guestClearAllButton = new JButton("Clear all");
		guestClearAllButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearGuestPanel();
				clearGuestTable();
			}
		});
		guestClearAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		guestClearAllButton.setBounds(6, 311, 104, 25);
		GuestPanel.add(guestClearAllButton);
		
		JScrollPane guestTableScrollPane = new JScrollPane();
		guestTableScrollPane.setBounds(120, 115, 695, 304);
		GuestPanel.add(guestTableScrollPane);
		
		guestTable = new JTable();
		guestTable.setFillsViewportHeight(true);
		guestTableScrollPane.setViewportView(guestTable);
		
		JPanel TravelAgencyPanel = new JPanel();
		tabbedPane.addTab("Travel agency menu", null, TravelAgencyPanel, null);
		TravelAgencyPanel.setLayout(null);
		
		JPanel travelAgencyAttributesPanel = new JPanel();
		travelAgencyAttributesPanel.setBorder(new TitledBorder(null, "Travel agency attributes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		travelAgencyAttributesPanel.setBounds(26, 34, 567, 68);
		TravelAgencyPanel.add(travelAgencyAttributesPanel);
		travelAgencyAttributesPanel.setLayout(null);
		
		JLabel travelAgencyCVRLabel = new JLabel("CVR:");
		travelAgencyCVRLabel.setBounds(6, 19, 85, 14);
		travelAgencyAttributesPanel.add(travelAgencyCVRLabel);
		travelAgencyCVRLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyCVRTextField = new JTextField();
		travelAgencyCVRTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyCVRTextField.setBounds(57, 16, 80, 20);
		travelAgencyAttributesPanel.add(travelAgencyCVRTextField);
		travelAgencyCVRTextField.setColumns(10);
		
		JLabel travelAgencyNameLabel = new JLabel("Name:");
		travelAgencyNameLabel.setBounds(147, 19, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyNameLabel);
		travelAgencyNameLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyNameTextField = new JTextField();
		travelAgencyNameTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyNameTextField.setBounds(201, 16, 80, 20);
		travelAgencyAttributesPanel.add(travelAgencyNameTextField);
		travelAgencyNameTextField.setColumns(10);
		
		JLabel travelAgencyCityLabel = new JLabel("City:");
		travelAgencyCityLabel.setBounds(287, 19, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyCityLabel);
		travelAgencyCityLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyCityTextField = new JTextField();
		travelAgencyCityTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyCityTextField.setBounds(341, 16, 80, 20);
		travelAgencyAttributesPanel.add(travelAgencyCityTextField);
		travelAgencyCityTextField.setColumns(10);
		
		JLabel travelAgencyCountryLabel = new JLabel("Country:");
		travelAgencyCountryLabel.setBounds(431, 19, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyCountryLabel);
		travelAgencyCountryLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyCountryTextField = new JTextField();
		travelAgencyCountryTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyCountryTextField.setBounds(481, 16, 80, 20);
		travelAgencyAttributesPanel.add(travelAgencyCountryTextField);
		travelAgencyCountryTextField.setColumns(10);
		
		JLabel travelAgencyZipcodeLabel = new JLabel("Zipcode:");
		travelAgencyZipcodeLabel.setBounds(6, 44, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyZipcodeLabel);
		travelAgencyZipcodeLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyZipcodeTextField = new JTextField();
		travelAgencyZipcodeTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyZipcodeTextField.setBounds(57, 41, 80, 20);
		travelAgencyAttributesPanel.add(travelAgencyZipcodeTextField);
		travelAgencyZipcodeTextField.setColumns(10);
		
		JLabel travelAgencyAddressLabel = new JLabel("Address:");
		travelAgencyAddressLabel.setBounds(147, 44, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyAddressLabel);
		travelAgencyAddressLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyAddressTextField = new JTextField();
		travelAgencyAddressTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyAddressTextField.setBounds(201, 41, 80, 20);
		travelAgencyAttributesPanel.add(travelAgencyAddressTextField);
		travelAgencyAddressTextField.setColumns(10);
		
		JLabel travelAgencyPhoneNoLabel = new JLabel("Phone no:");
		travelAgencyPhoneNoLabel.setBounds(287, 44, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyPhoneNoLabel);
		travelAgencyPhoneNoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyPhoneNoTextField = new JTextField();
		travelAgencyPhoneNoTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyPhoneNoTextField.setBounds(341, 41, 80, 20);
		travelAgencyAttributesPanel.add(travelAgencyPhoneNoTextField);
		travelAgencyPhoneNoTextField.setColumns(10);
		
		JLabel travelAgencyEmailLabel = new JLabel("E-mail:");
		travelAgencyEmailLabel.setBounds(431, 44, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyEmailLabel);
		travelAgencyEmailLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyEmailTextField = new JTextField();
		travelAgencyEmailTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyEmailTextField.setBounds(481, 41, 80, 20);
		travelAgencyAttributesPanel.add(travelAgencyEmailTextField);
		travelAgencyEmailTextField.setColumns(10);
		
		JPanel travelAgencyOptionsPanel = new JPanel();
		travelAgencyOptionsPanel.setLayout(null);
		travelAgencyOptionsPanel.setBorder(new TitledBorder(null, "Travel agency", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		travelAgencyOptionsPanel.setBounds(26, 113, 146, 192);
		TravelAgencyPanel.add(travelAgencyOptionsPanel);
		
		JButton travelAgencySearchButton = new JButton("Search");
		travelAgencySearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		travelAgencySearchButton.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencySearchButton.setBounds(6, 16, 134, 25);
		travelAgencyOptionsPanel.add(travelAgencySearchButton);
		
		JButton travelAgencyCreateButton = new JButton("Create");
		travelAgencyCreateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyCreateButton.setBounds(6, 52, 134, 25);
		travelAgencyOptionsPanel.add(travelAgencyCreateButton);
		
		JButton travelAgencyUpdateButton = new JButton("Update");
		travelAgencyUpdateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyUpdateButton.setBounds(6, 88, 134, 25);
		travelAgencyOptionsPanel.add(travelAgencyUpdateButton);
		
		JButton travelAgencyDeleteButton = new JButton("Delete");
		travelAgencyDeleteButton.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyDeleteButton.setBounds(6, 124, 134, 25);
		travelAgencyOptionsPanel.add(travelAgencyDeleteButton);
		
		JButton travelAgencyAllButton = new JButton("All");
		travelAgencyAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyAllButton.setBounds(6, 160, 134, 25);
		travelAgencyOptionsPanel.add(travelAgencyAllButton);
		
		JScrollPane travelAgencyScrollPane = new JScrollPane();
		travelAgencyScrollPane.setBounds(182, 113, 633, 245);
		TravelAgencyPanel.add(travelAgencyScrollPane);
		
		travelAgencyTable = new JTable();
		travelAgencyTable.setFillsViewportHeight(true);
		travelAgencyScrollPane.setViewportView(travelAgencyTable);
		
		JButton travelAgencyClearAllButton = new JButton("Clear all");
		travelAgencyClearAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyClearAllButton.setBounds(26, 368, 134, 25);
		TravelAgencyPanel.add(travelAgencyClearAllButton);
		
		JPanel RoomPanel = new JPanel();
		tabbedPane.addTab("Room menu", null, RoomPanel, null);
		
		JPanel ActivityTypePanel = new JPanel();
		tabbedPane.addTab("Activity type menu", null, ActivityTypePanel, null);
		ActivityTypePanel.setLayout(null);
		
		JPanel activityTypeAttributesPanel = new JPanel();
		activityTypeAttributesPanel.setBorder(new TitledBorder(null, "Activity type attributes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityTypeAttributesPanel.setBounds(30, 19, 636, 43);
		ActivityTypePanel.add(activityTypeAttributesPanel);
		activityTypeAttributesPanel.setLayout(null);
		
		JLabel activityTypeIdLabel = new JLabel("Activity type id:");
		activityTypeIdLabel.setBounds(6, 19, 86, 14);
		activityTypeAttributesPanel.add(activityTypeIdLabel);
		activityTypeIdLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		activityTypeIdTextField = new JTextField();
		activityTypeIdTextField.setBounds(93, 16, 50, 20);
		activityTypeAttributesPanel.add(activityTypeIdTextField);
		activityTypeIdTextField.setColumns(10);
		
		JLabel activityTypeNameLabel = new JLabel("Activity type name:");
		activityTypeNameLabel.setBounds(153, 19, 127, 14);
		activityTypeAttributesPanel.add(activityTypeNameLabel);
		activityTypeNameLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		activityTypeNameTextField = new JTextField();
		activityTypeNameTextField.setBounds(255, 16, 180, 20);
		activityTypeAttributesPanel.add(activityTypeNameTextField);
		activityTypeNameTextField.setColumns(10);
		
		JLabel maxParticipantsLabel = new JLabel("Maximum participants:");
		maxParticipantsLabel.setBounds(445, 19, 127, 14);
		activityTypeAttributesPanel.add(maxParticipantsLabel);
		maxParticipantsLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		maxParticipantsTextField = new JTextField();
		maxParticipantsTextField.setBounds(560, 16, 70, 20);
		activityTypeAttributesPanel.add(maxParticipantsTextField);
		maxParticipantsTextField.setColumns(10);
		
		JPanel activityTypesOptionsPanel = new JPanel();
		activityTypesOptionsPanel.setLayout(null);
		activityTypesOptionsPanel.setBorder(new TitledBorder(null, "Activity", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityTypesOptionsPanel.setBounds(30, 73, 150, 203);
		ActivityTypePanel.add(activityTypesOptionsPanel);
		
		JButton activityTypeSearchButton = new JButton("Search");
		activityTypeSearchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(activityTypeIdTextField.getText().equals("")==true && activityTypeNameTextField.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the wanted activity type.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(activityTypeNameTextField.getText().equals("") == true)
					{
						String stringActivityTypeId = activityTypeIdTextField.getText();
						int activityTypeId = Integer.parseInt(stringActivityTypeId);
						
						ActivityType activityTypeObj = new ActivityType();
						activityTypeObj = activityTypeCtr.getActivityTypeByID(activityTypeId);
						
						if(activityTypeObj == null)
						{
							JOptionPane.showMessageDialog(null, "There is no activity type by this id. Please insert a valid activity type id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							String activityTypeName = activityTypeObj.getName();
							int maxParticipants = activityTypeObj.getMaxParticipants();
							String stringMaxParticipants = String.valueOf(maxParticipants);
							
							activityTypeIdTextField.setText(stringActivityTypeId);
							activityTypeNameTextField.setText(activityTypeName);
							maxParticipantsTextField.setText(stringMaxParticipants);
						}
					}
					else
					{
						if(activityTypeIdTextField.getText().equals("") == true)
						{
							String activityTypeName = activityTypeNameTextField.getText();
							
							ActivityType activityTypeObj = new ActivityType();
							activityTypeObj = activityTypeCtr.getActivityTypeByName(activityTypeName);
							
							if(activityTypeObj == null)
							{
								JOptionPane.showMessageDialog(null, "There is no activity type by this name. Please insert a valid activity type name.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								int activityTypeId = activityTypeObj.getID();
								String stringActivityTypeId = String.valueOf(activityTypeId);
								int maxParticipants = activityTypeObj.getMaxParticipants();
								String stringMaxParticipants = String.valueOf(maxParticipants);
								
								activityTypeIdTextField.setText(stringActivityTypeId);
								activityTypeNameTextField.setText(activityTypeName);
								maxParticipantsTextField.setText(stringMaxParticipants);
							}
						}
						else
						{
							if(activityTypeIdTextField.getText().equals("") != true && activityTypeNameTextField.getText().equals("") != true)
							{
								String stringActivityTypeId = activityTypeIdTextField.getText();
								int activityTypeId = Integer.parseInt(stringActivityTypeId);
								
								ActivityType activityTypeObj = new ActivityType();
								activityTypeObj = activityTypeCtr.getActivityTypeByID(activityTypeId);
								
								if(activityTypeObj == null)
								{
									JOptionPane.showMessageDialog(null, "There is no activity type by this id. Please insert a valid activity type id.", "Error!", JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									String activityTypeName = activityTypeObj.getName();
									int maxParticipants = activityTypeObj.getMaxParticipants();
									String stringMaxParticipants = String.valueOf(maxParticipants);
									
									activityTypeIdTextField.setText(stringActivityTypeId);
									activityTypeNameTextField.setText(activityTypeName);
									maxParticipantsTextField.setText(stringMaxParticipants);
								}
							}
						}
					}
				}
			}
		});
		activityTypeSearchButton.setFont(new Font("Arial", Font.PLAIN, 11));
		activityTypeSearchButton.setBounds(6, 24, 134, 25);
		activityTypesOptionsPanel.add(activityTypeSearchButton);
		
		JButton activityTypeCreateButton = new JButton("Create");
		activityTypeCreateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(activityTypeNameTextField.getText().equals("") == true || maxParticipantsTextField.getText().equals("") == true)
				{
					JOptionPane.showMessageDialog(null, "A activity type attribute might be missing. Please insert all needed activity type attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String activityTypeName = activityTypeNameTextField.getText();
					String stringMaxParticipants = maxParticipantsTextField.getText();
					int maxParticipants = Integer.parseInt(stringMaxParticipants);
					if(activityTypeCtr.getActivityTypeByName(activityTypeName) != null)
					{
						JOptionPane.showMessageDialog(null, "Cannot register the same activity type twice.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						activityTypeCtr.insertActivityType(activityTypeName, maxParticipants);
						
						LinkedList<ActivityType> allActivityTypesList = new LinkedList<ActivityType>();
						
						activityTypeComboBox.removeAllItems();						
						allActivityTypesList = activityTypeCtr.getAllActivityTypes();
						if(allActivityTypesList.isEmpty()==false)
						{
							for(ActivityType activityTypeObj : allActivityTypesList)
							{
								String comboBoxItem = activityTypeObj.getName();
								activityTypeComboBox.addItem(comboBoxItem);
							}
						}
						activityTypeComboBox.setSelectedItem(null);
						
						instructorActivityTypeComboBox.removeAllItems();
						allActivityTypesList = activityTypeCtr.getAllActivityTypes();
						if(allActivityTypesList.isEmpty()==false)
						{
							for(ActivityType activityTypeObj : allActivityTypesList)
							{
								String comboBoxItem = activityTypeObj.getName();
								instructorActivityTypeComboBox.addItem(comboBoxItem);
							}
						}
						instructorActivityTypeComboBox.setSelectedItem(null);
						
						clearActivityTypePanel();
						clearActivityTypeTable();
						
						JOptionPane.showMessageDialog(null, "Activity type successfully inserted.", "Info", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		activityTypeCreateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		activityTypeCreateButton.setBounds(6, 60, 134, 25);
		activityTypesOptionsPanel.add(activityTypeCreateButton);
		
		JButton activityTypeUpdateButton = new JButton("Update");
		activityTypeUpdateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(activityTypeIdTextField.getText().equals("") == true || activityTypeNameTextField.getText().equals("") == true || maxParticipantsTextField.getText().equals("") == true)
				{
					JOptionPane.showMessageDialog(null, "Some activity type attributes have not been inserted. Please insert all activity type attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String stringActivityTypeId = activityTypeIdTextField.getText();
					int activityTypeId = Integer.parseInt(stringActivityTypeId);
					
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = activityTypeCtr.getActivityTypeByID(activityTypeId);
					
					if(activityTypeObj == null)
					{
						JOptionPane.showMessageDialog(null, "The wanted activity type does not exist in the system. Please check activity type list.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						String activityTypeName = activityTypeNameTextField.getText();
						
						String stringMaxParticipants = maxParticipantsTextField.getText();
						int maxParticipants = Integer.parseInt(stringMaxParticipants);
						
						if(activityTypeCtr.checkActivityTypeInstances(activityTypeId, activityTypeName) == false)
						{
							JOptionPane.showMessageDialog(null, "You may not update with an already existing activity type on this id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							activityTypeCtr.updateActivityType(activityTypeId, activityTypeName, maxParticipants);
							
							LinkedList<ActivityType> allActivityTypesList = new LinkedList<ActivityType>();
						
							activityTypeComboBox.removeAllItems();
							allActivityTypesList = activityTypeCtr.getAllActivityTypes();
							if(allActivityTypesList.isEmpty()==false)
							{
								for(ActivityType activityTypeListObj : allActivityTypesList)
								{
									String comboBoxItem = activityTypeListObj.getName();
									activityTypeComboBox.addItem(comboBoxItem);
								}
							}
							activityTypeComboBox.setSelectedItem(null);
							
							instructorActivityTypeComboBox.removeAllItems();
							allActivityTypesList = activityTypeCtr.getAllActivityTypes();
							if(allActivityTypesList.isEmpty()==false)
							{
								for(ActivityType activityTypeListObj : allActivityTypesList)
								{
									String comboBoxItem = activityTypeListObj.getName();
									instructorActivityTypeComboBox.addItem(comboBoxItem);
								}
							}
							instructorActivityTypeComboBox.setSelectedItem(null);
							
							clearActivityTypePanel();
							clearActivityTypeTable();
							
							JOptionPane.showMessageDialog(null, "Activity type updated successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
		activityTypeUpdateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		activityTypeUpdateButton.setBounds(6, 96, 134, 25);
		activityTypesOptionsPanel.add(activityTypeUpdateButton);
		
		JButton activityTypeDeleteButton = new JButton("Delete");
		activityTypeDeleteButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(activityTypeIdTextField.getText().equals("")==true && activityTypeNameTextField.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the wanted activity type.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(activityTypeNameTextField.getText().equals("") == true)
					{
						String stringActivityTypeId = activityTypeIdTextField.getText();
						int activityTypeId = Integer.parseInt(stringActivityTypeId);
						
						ActivityType activityTypeObj = new ActivityType();
						activityTypeObj = activityTypeCtr.getActivityTypeByID(activityTypeId);
						
						if(activityTypeObj == null)
						{
							JOptionPane.showMessageDialog(null, "There is no activity type by this id. Please insert a valid activity type id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							activityTypeCtr.deleteActivityTypeByID(activityTypeId);
							LinkedList<ActivityType> allActivityTypesList = new LinkedList<ActivityType>();
							
							activityTypeComboBox.removeAllItems();
							allActivityTypesList = activityTypeCtr.getAllActivityTypes();
							if(allActivityTypesList.isEmpty()==false)
							{
								for(ActivityType activityTypeListObj : allActivityTypesList)
								{
									String comboBoxItem = activityTypeListObj.getName();
									activityTypeComboBox.addItem(comboBoxItem);
								}
							}
							activityTypeComboBox.setSelectedItem(null);
							
							instructorActivityTypeComboBox.removeAllItems();
							allActivityTypesList = activityTypeCtr.getAllActivityTypes();
							if(allActivityTypesList.isEmpty()==false)
							{
								for(ActivityType activityTypeListObj : allActivityTypesList)
								{
									String comboBoxItem = activityTypeListObj.getName();
									instructorActivityTypeComboBox.addItem(comboBoxItem);
								}
							}
							instructorActivityTypeComboBox.setSelectedItem(null);
							
							clearActivityTypePanel();
							clearActivityTypeTable();
							
							JOptionPane.showMessageDialog(null, "Activity type successfully removed from the system.", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
					{
						if(activityTypeIdTextField.getText().equals("") == true)
						{
							String activityTypeName = activityTypeNameTextField.getText();
							
							ActivityType activityTypeObj = new ActivityType();
							activityTypeObj = activityTypeCtr.getActivityTypeByName(activityTypeName);
							
							if(activityTypeObj == null)
							{
								JOptionPane.showMessageDialog(null, "There is no activity type by this name. Please insert a valid activity type name.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								activityTypeCtr.deleteActivityTypeByName(activityTypeName);
								
								activityTypeComboBox.removeAllItems();
								LinkedList<ActivityType> allActivityTypesList = new LinkedList<ActivityType>();
								allActivityTypesList = activityTypeCtr.getAllActivityTypes();
								if(allActivityTypesList.isEmpty()==false)
								{
									for(ActivityType activityTypeListObj : allActivityTypesList)
									{
										String comboBoxItem = activityTypeListObj.getName();
										activityTypeComboBox.addItem(comboBoxItem);
									}
								}
								activityTypeComboBox.setSelectedItem(null);
								
								clearActivityTypePanel();
								clearActivityTypeTable();
								
								JOptionPane.showMessageDialog(null, "Activity type successfully removed from the system.", "Info", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else
						{
							if(activityTypeIdTextField.getText().equals("") != true && activityTypeNameTextField.getText().equals("") != true)
							{
								String stringActivityTypeId = activityTypeIdTextField.getText();
								int activityTypeId = Integer.parseInt(stringActivityTypeId);
								
								ActivityType activityTypeObj = new ActivityType();
								activityTypeObj = activityTypeCtr.getActivityTypeByID(activityTypeId);
								
								if(activityTypeObj == null)
								{
									JOptionPane.showMessageDialog(null, "There is no activity type by this id. Please insert a valid activity type id.", "Error!", JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									activityTypeCtr.deleteActivityTypeByID(activityTypeId);
									
									activityTypeComboBox.removeAllItems();
									LinkedList<ActivityType> allActivityTypesList = new LinkedList<ActivityType>();
									allActivityTypesList = activityTypeCtr.getAllActivityTypes();
									if(allActivityTypesList.isEmpty()==false)
									{
										for(ActivityType activityTypeListObj : allActivityTypesList)
										{
											String comboBoxItem = activityTypeListObj.getName();
											activityTypeComboBox.addItem(comboBoxItem);
										}
									}
									activityTypeComboBox.setSelectedItem(null);
									
									clearActivityTypePanel();
									clearActivityTypeTable();
									
									JOptionPane.showMessageDialog(null, "Activity type successfully removed from the system.", "Info", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
				}
			}
		});
		activityTypeDeleteButton.setFont(new Font("Arial", Font.PLAIN, 11));
		activityTypeDeleteButton.setBounds(6, 132, 134, 25);
		activityTypesOptionsPanel.add(activityTypeDeleteButton);
		
		JButton activityTypeAllButton = new JButton("All");
		activityTypeAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				clearActivityTypePanel();
				clearActivityTypeTable();
				
				activityTypeTable.setModel(getActivityTypeTableModel());
			}
		});
		activityTypeAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		activityTypeAllButton.setBounds(6, 168, 134, 25);
		activityTypesOptionsPanel.add(activityTypeAllButton);
		
		JScrollPane activityTypeTableScrollPane = new JScrollPane();
		activityTypeTableScrollPane.setBounds(190, 73, 560, 240);
		ActivityTypePanel.add(activityTypeTableScrollPane);
		
		activityTypeTable = new JTable();
		activityTypeTable.setFillsViewportHeight(true);
		activityTypeTableScrollPane.setViewportView(activityTypeTable);
		
		JButton activityTypeClearAllButton = new JButton("Clear all");
		activityTypeClearAllButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearActivityTypePanel();
				clearActivityTypeTable();
			}
		});
		activityTypeClearAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		activityTypeClearAllButton.setBounds(30, 334, 134, 25);
		ActivityTypePanel.add(activityTypeClearAllButton);
		
		JPanel EmployeePanel = new JPanel();
		tabbedPane.addTab("Employee menu", null, EmployeePanel, null);
		EmployeePanel.setLayout(null);
		
		JPanel InstructorPanel = new JPanel();
		tabbedPane.addTab("Instructor menu", null, InstructorPanel, null);
		InstructorPanel.setLayout(null);
		
		JPanel instructorAttributePanel = new JPanel();
		instructorAttributePanel.setLayout(null);
		instructorAttributePanel.setBorder(new TitledBorder(null, "Instructor attributes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		instructorAttributePanel.setBounds(10, 11, 768, 100);
		InstructorPanel.add(instructorAttributePanel);
		
		JLabel istructorIdLabel = new JLabel("Id:");
		istructorIdLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		istructorIdLabel.setBounds(6, 19, 46, 14);
		instructorAttributePanel.add(istructorIdLabel);
		
		instructorIdTextField = new JTextField();
		instructorIdTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorIdTextField.setColumns(10);
		instructorIdTextField.setBounds(70, 16, 80, 20);
		instructorAttributePanel.add(instructorIdTextField);
		
		JLabel instructorNameLabel = new JLabel("Name:");
		instructorNameLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorNameLabel.setBounds(160, 19, 46, 14);
		instructorAttributePanel.add(instructorNameLabel);
		
		instructorNameTextField = new JTextField();
		instructorNameTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorNameTextField.setColumns(10);
		instructorNameTextField.setBounds(240, 16, 80, 20);
		instructorAttributePanel.add(instructorNameTextField);
		
		JLabel instructorCityLabel = new JLabel("City:");
		instructorCityLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorCityLabel.setBounds(330, 19, 86, 14);
		instructorAttributePanel.add(instructorCityLabel);
		
		instructorCityTextField = new JTextField();
		instructorCityTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorCityTextField.setColumns(10);
		instructorCityTextField.setBounds(372, 16, 80, 20);
		instructorAttributePanel.add(instructorCityTextField);
		
		JLabel instructorCountryLabel = new JLabel("Country:");
		instructorCountryLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorCountryLabel.setBounds(462, 19, 86, 14);
		instructorAttributePanel.add(instructorCountryLabel);
		
		instructorCountryTextField = new JTextField();
		instructorCountryTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorCountryTextField.setColumns(10);
		instructorCountryTextField.setBounds(531, 16, 80, 20);
		instructorAttributePanel.add(instructorCountryTextField);
		
		JLabel instructorZipcodeLabel = new JLabel("Zipcode:");
		instructorZipcodeLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorZipcodeLabel.setBounds(621, 19, 86, 14);
		instructorAttributePanel.add(instructorZipcodeLabel);
		
		instructorZipcodeTextField = new JTextField();
		instructorZipcodeTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorZipcodeTextField.setColumns(10);
		instructorZipcodeTextField.setBounds(682, 16, 80, 20);
		instructorAttributePanel.add(instructorZipcodeTextField);
		
		JLabel instructorAddressLabel = new JLabel("Address:");
		instructorAddressLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorAddressLabel.setBounds(6, 44, 86, 14);
		instructorAttributePanel.add(instructorAddressLabel);
		
		instructorAddressTextField = new JTextField();
		instructorAddressTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorAddressTextField.setColumns(10);
		instructorAddressTextField.setBounds(70, 41, 80, 20);
		instructorAttributePanel.add(instructorAddressTextField);
		
		JLabel instructorPhoneNoLabel = new JLabel("Phone no:");
		instructorPhoneNoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorPhoneNoLabel.setBounds(160, 44, 86, 14);
		instructorAttributePanel.add(instructorPhoneNoLabel);
		
		instructorPhoneNoTextField = new JTextField();
		instructorPhoneNoTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorPhoneNoTextField.setColumns(10);
		instructorPhoneNoTextField.setBounds(240, 41, 80, 20);
		instructorAttributePanel.add(instructorPhoneNoTextField);
		
		JLabel instructorEmailLabel = new JLabel("E-mail:");
		instructorEmailLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorEmailLabel.setBounds(330, 44, 86, 14);
		instructorAttributePanel.add(instructorEmailLabel);
		
		instructorEmailTextField = new JTextField();
		instructorEmailTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorEmailTextField.setColumns(10);
		instructorEmailTextField.setBounds(372, 41, 80, 20);
		instructorAttributePanel.add(instructorEmailTextField);
		
		JLabel instructorPasswordLabel = new JLabel("Password:");
		instructorPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorPasswordLabel.setBounds(462, 44, 86, 14);
		instructorAttributePanel.add(instructorPasswordLabel);
		
		instructorPasswordTextField = new JTextField();
		instructorPasswordTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorPasswordTextField.setEditable(false);
		instructorPasswordTextField.setColumns(10);
		instructorPasswordTextField.setBounds(531, 41, 80, 20);
		instructorAttributePanel.add(instructorPasswordTextField);
		
		JLabel instructorActivityTypeLabel = new JLabel("Activity type:");
		instructorActivityTypeLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorActivityTypeLabel.setBounds(350, 70, 86, 14);
		instructorAttributePanel.add(instructorActivityTypeLabel);
		
		instructorActivityTypeComboBox = new JComboBox<String>();
		LinkedList<ActivityType> instructorAllActivityTypesList = new LinkedList<ActivityType>();
		instructorAllActivityTypesList = activityTypeCtr.getAllActivityTypes();
		if(instructorAllActivityTypesList.isEmpty()==false)
		{
			for(ActivityType activityTypeObj : instructorAllActivityTypesList)
			{
				String comboBoxItem = activityTypeObj.getName();
				instructorActivityTypeComboBox.addItem(comboBoxItem);
			}
		}
		instructorActivityTypeComboBox.setSelectedItem(null);
		instructorActivityTypeComboBox.setBounds(426, 67, 122, 20);
		instructorAttributePanel.add(instructorActivityTypeComboBox);
		
		JLabel instructorSalaryLabel = new JLabel("Salary:");
		instructorSalaryLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorSalaryLabel.setBounds(621, 44, 86, 14);
		instructorAttributePanel.add(instructorSalaryLabel);
		
		instructorSalaryTextField = new JTextField();
		instructorSalaryTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorSalaryTextField.setColumns(10);
		instructorSalaryTextField.setBounds(682, 41, 80, 20);
		instructorAttributePanel.add(instructorSalaryTextField);
		
		JLabel instructorPriceLabel = new JLabel("Price:");
		instructorPriceLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorPriceLabel.setBounds(6, 69, 86, 14);
		instructorAttributePanel.add(instructorPriceLabel);
		
		JLabel instructorStatusLabel = new JLabel("Status:");
		instructorStatusLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorStatusLabel.setBounds(160, 69, 86, 14);
		instructorAttributePanel.add(instructorStatusLabel);
		
		instructorPriceTextField = new JTextField();
		instructorPriceTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorPriceTextField.setColumns(10);
		instructorPriceTextField.setBounds(70, 66, 80, 20);
		instructorAttributePanel.add(instructorPriceTextField);
		
		instructorStatusComboBox = new JComboBox<String>();
		instructorStatusComboBox.addItem("Available");
		instructorStatusComboBox.addItem("Unavailable");
		instructorStatusComboBox.setSelectedItem(null);
		instructorStatusComboBox.setBounds(218, 66, 122, 20);
		instructorAttributePanel.add(instructorStatusComboBox);
		
		JPanel instructorOptionsPanel = new JPanel();
		instructorOptionsPanel.setLayout(null);
		instructorOptionsPanel.setBorder(new TitledBorder(null, "Instructor options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		instructorOptionsPanel.setBounds(10, 122, 104, 192);
		InstructorPanel.add(instructorOptionsPanel);
		
		JButton instructorSearchButton = new JButton("Search");
		instructorSearchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(instructorIdTextField.getText().equals("")==true && instructorNameTextField.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the wanted instructor.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(instructorNameTextField.getText().equals("") == true)
					{
						String stringInstructorId = instructorIdTextField.getText();
						int instructorId = Integer.parseInt(stringInstructorId);
						
						Instructor instructorObj = new Instructor();
						instructorObj = instructorCtr.getInstructorById(instructorId);
						
						if(instructorObj == null)
						{
							JOptionPane.showMessageDialog(null, "There is no instructor by this id. Please insert a valid instructor id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							String instructorName = instructorObj.getName();
							String instructorCountry = instructorObj.getCountry();
							int instructorZipcode = instructorObj.getZipcode();
							String stringInstructorZipcode = String.valueOf(instructorZipcode);
							String instructorAddress = instructorObj.getAddress();
							String instructorPhoneNo = instructorObj.getPhoneNo();
							String instructorEmail = instructorObj.getEmail();
							String instructorPassword = instructorObj.getPassword();
							double instructorSalary = instructorObj.getSalary();
							String stringInstructorSalary = String.valueOf(instructorSalary);
							double instructorPrice = instructorObj.getPrice();
							String stringInstructorPrice = String.valueOf(instructorPrice);
							String instructorStatus = instructorObj.getStatus();
							ActivityType activityTypeObj = instructorObj.getActivityType();
							String activityTypeName = activityTypeObj.getName();
							
							Location locationObj = new Location();
							locationObj = locationCtr.getLocation(instructorZipcode, instructorCountry);
							String instructorCity = locationObj.getCity();
							
							instructorIdTextField.setText(stringInstructorId);
							instructorNameTextField.setText(instructorName);
							instructorCityTextField.setText(instructorCity);
							instructorCountryTextField.setText(instructorCountry);
							instructorZipcodeTextField.setText(stringInstructorZipcode);
							instructorAddressTextField.setText(instructorAddress);
							instructorPhoneNoTextField.setText(instructorPhoneNo);
							instructorEmailTextField.setText(instructorEmail);
							instructorPasswordTextField.setText(instructorPassword);
							instructorSalaryTextField.setText(stringInstructorSalary);
							instructorPriceTextField.setText(stringInstructorPrice);
							instructorStatusComboBox.setSelectedItem(instructorStatus);
							instructorActivityTypeComboBox.setSelectedItem(activityTypeName);
						}
					}
					else
					{
						if(instructorIdTextField.getText().equals("") == true)
						{
							String instructorName = instructorNameTextField.getText();
							
							Person personObj = new Person();
							personObj = personCtr.searchPersonByName(instructorName);
							
							if(personObj == null || personObj.getPersonType().equals("Instructor") == false)
							{
								JOptionPane.showMessageDialog(null, "There is no instructor by this name. Please insert a valid instructor name.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								int instructorId = personObj.getId();
								Instructor instructorObj = instructorCtr.getInstructorById(instructorId);
								
								String stringInstructorId = String.valueOf(instructorId);								
								String instructorCountry = instructorObj.getCountry();
								int instructorZipcode = instructorObj.getZipcode();
								String stringInstructorZipcode = String.valueOf(instructorZipcode);
								String instructorAddress = instructorObj.getAddress();
								String instructorPhoneNo = instructorObj.getPhoneNo();
								String instructorEmail = instructorObj.getEmail();
								String instructorPassword = instructorObj.getPassword();
								double instructorSalary = instructorObj.getSalary();
								String stringInstructorSalary = String.valueOf(instructorSalary);
								double instructorPrice = instructorObj.getPrice();
								String stringInstructorPrice = String.valueOf(instructorPrice);
								String instructorStatus = instructorObj.getStatus();
								ActivityType activityTypeObj = instructorObj.getActivityType();
								String activityTypeName = activityTypeObj.getName();
								
								Location locationObj = new Location();
								locationObj = locationCtr.getLocation(instructorZipcode, instructorCountry);
								String instructorCity = locationObj.getCity();
								
								instructorIdTextField.setText(stringInstructorId);
								instructorNameTextField.setText(instructorName);
								instructorCityTextField.setText(instructorCity);
								instructorCountryTextField.setText(instructorCountry);
								instructorZipcodeTextField.setText(stringInstructorZipcode);
								instructorAddressTextField.setText(instructorAddress);
								instructorPhoneNoTextField.setText(instructorPhoneNo);
								instructorEmailTextField.setText(instructorEmail);
								instructorPasswordTextField.setText(instructorPassword);
								instructorSalaryTextField.setText(stringInstructorSalary);
								instructorPriceTextField.setText(stringInstructorPrice);
								instructorStatusComboBox.setSelectedItem(instructorStatus);
								instructorActivityTypeComboBox.setSelectedItem(activityTypeName);
							}
						}
						else
						{
							if(instructorIdTextField.getText().equals("") != true && instructorNameTextField.getText().equals("") != true)
							{
								String stringInstructorId = instructorIdTextField.getText();
								int instructorId = Integer.parseInt(stringInstructorId);
								
								Instructor instructorObj = new Instructor();
								instructorObj = instructorCtr.getInstructorById(instructorId);
								
								if(instructorObj == null)
								{
									JOptionPane.showMessageDialog(null, "There is no instructor by this id. Please insert a valid instructor id.", "Error!", JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									String instructorName = instructorObj.getName();
									String instructorCountry = instructorObj.getCountry();
									int instructorZipcode = instructorObj.getZipcode();
									String stringInstructorZipcode = String.valueOf(instructorZipcode);
									String instructorAddress = instructorObj.getAddress();
									String instructorPhoneNo = instructorObj.getPhoneNo();
									String instructorEmail = instructorObj.getEmail();
									String instructorPassword = instructorObj.getPassword();
									double instructorSalary = instructorObj.getSalary();
									String stringInstructorSalary = String.valueOf(instructorSalary);
									double instructorPrice = instructorObj.getPrice();
									String stringInstructorPrice = String.valueOf(instructorPrice);
									String instructorStatus = instructorObj.getStatus();
									ActivityType activityTypeObj = instructorObj.getActivityType();
									String activityTypeName = activityTypeObj.getName();
									
									Location locationObj = new Location();
									locationObj = locationCtr.getLocation(instructorZipcode, instructorCountry);
									String instructorCity = locationObj.getCity();
									
									instructorIdTextField.setText(stringInstructorId);
									instructorNameTextField.setText(instructorName);
									instructorCityTextField.setText(instructorCity);
									instructorCountryTextField.setText(instructorCountry);
									instructorZipcodeTextField.setText(stringInstructorZipcode);
									instructorAddressTextField.setText(instructorAddress);
									instructorPhoneNoTextField.setText(instructorPhoneNo);
									instructorEmailTextField.setText(instructorEmail);
									instructorPasswordTextField.setText(instructorPassword);
									instructorSalaryTextField.setText(stringInstructorSalary);
									instructorPriceTextField.setText(stringInstructorPrice);
									instructorStatusComboBox.setSelectedItem(instructorStatus);
									instructorActivityTypeComboBox.setSelectedItem(activityTypeName);
								}
							}
						}
					}
				}
			}
		});
		instructorSearchButton.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorSearchButton.setBounds(6, 16, 90, 25);
		instructorOptionsPanel.add(instructorSearchButton);
		
		JButton instructorCreateButton = new JButton("Create");
		instructorCreateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(instructorNameTextField.getText().equals("") == true || instructorCityTextField.getText().equals("") == true ||
						instructorCountryTextField.getText().equals("") == true || instructorZipcodeTextField.getText().equals("") == true ||
						instructorAddressTextField.getText().equals("") == true || instructorSalaryTextField.getText().equals("") == true ||
						instructorPriceTextField.getText().equals("") == true || instructorStatusComboBox.getSelectedItem().equals(null) == true ||
						instructorActivityTypeComboBox.getSelectedItem().equals(null) == true)
				{
					JOptionPane.showMessageDialog(null, "An instructor attribute might be missing. Please insert all needed instructor attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String instructorName = instructorNameTextField.getText();
					String instructorCiy = instructorCityTextField.getText();
					String instructorCountry = instructorCountryTextField.getText();
					String stringInstructorZipcode = instructorZipcodeTextField.getText();
					int instructorZipcode = Integer.parseInt(stringInstructorZipcode);
					String instructorAddress = instructorAddressTextField.getText();
					String instructorPhoneNo = instructorPhoneNoTextField.getText();
					String instructorEmail = instructorEmailTextField.getText();
					String instructorPassword = personCtr.getPersonPassword(instructorName, stringInstructorZipcode, instructorCountry, instructorAddress);
					String stringInstructorSalary = instructorSalaryTextField.getText();
					int instructorSalary = Integer.parseInt(stringInstructorSalary);
					String stringInstructorPrice = instructorPriceTextField.getText();
					int instructorPrice = Integer.parseInt(stringInstructorPrice);
					String instructorStatus = (String) instructorStatusComboBox.getSelectedItem();
					String activityTypeName = (String) instructorActivityTypeComboBox.getSelectedItem();
					ActivityType activityTypeObj = activityTypeCtr.getActivityTypeByName(activityTypeName);
					int activityTypeId = activityTypeObj.getID();
					
					if(personCtr.searchPersonByName(instructorName) != null)
					{
						JOptionPane.showMessageDialog(null, "Cannot register the same instructor twice.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						locationCtr.insertLocation(instructorZipcode, instructorCountry, instructorCiy);
						personCtr.insertPerson(instructorName, instructorAddress, instructorZipcode, instructorCountry, instructorPhoneNo, instructorEmail, "Instructor", instructorPassword);
						employeeCtr.insertEmployee(instructorName, instructorSalary);
						instructorCtr.insertInstructor(instructorName, activityTypeId, instructorPrice, instructorStatus);
						JOptionPane.showMessageDialog(null, "Instructor successfully inserted", "Info", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		instructorCreateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorCreateButton.setBounds(6, 52, 90, 25);
		instructorOptionsPanel.add(instructorCreateButton);
		
		JButton instructorUpdateButton = new JButton("Update");
		instructorUpdateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(instructorIdTextField.getText().equals("") == true || instructorNameTextField.getText().equals("") == true ||
						instructorCityTextField.getText().equals("") == true || instructorCountryTextField.getText().equals("") == true ||
						instructorZipcodeTextField.getText().equals("") == true || instructorAddressTextField.getText().equals("") == true ||
						instructorSalaryTextField.getText().equals("") == true || instructorPriceTextField.getText().equals("") == true ||
						instructorStatusComboBox.getSelectedItem().equals(null) == true || instructorActivityTypeComboBox.getSelectedItem().equals(null) == true)
				{
					JOptionPane.showMessageDialog(null, "An instructor attribute might be missing. Please insert all needed instructor attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String stringInstructorId = instructorIdTextField.getText();
					int instructorId = Integer.parseInt(stringInstructorId);
					
					Instructor instructorObj = new Instructor();
					instructorObj = instructorCtr.getInstructorById(instructorId);
				
					if(instructorObj == null)
					{
						JOptionPane.showMessageDialog(null, "The wanted instructor does not exist in the system. Please check instructor list.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						String instructorName = instructorNameTextField.getText();
						String instructorCiy = instructorCityTextField.getText();
						String instructorCountry = instructorCountryTextField.getText();
						String stringInstructorZipcode = instructorZipcodeTextField.getText();
						int instructorZipcode = Integer.parseInt(stringInstructorZipcode);
						String instructorAddress = instructorAddressTextField.getText();
						String instructorPhoneNo = instructorPhoneNoTextField.getText();
						String instructorEmail = instructorEmailTextField.getText();
						String instructorPassword = personCtr.getPersonPassword(instructorName, stringInstructorZipcode, instructorCountry, instructorAddress);
						String stringInstructorSalary = instructorSalaryTextField.getText();
						int instructorSalary = Integer.parseInt(stringInstructorSalary);
						String stringInstructorPrice = instructorPriceTextField.getText();
						int instructorPrice = Integer.parseInt(stringInstructorPrice);
						String instructorStatus = (String) instructorStatusComboBox.getSelectedItem();
						String activityTypeName = (String) instructorActivityTypeComboBox.getSelectedItem();
						ActivityType activityTypeObj = activityTypeCtr.getActivityTypeByName(activityTypeName);
						int activityTypeId = activityTypeObj.getID();
						
						if(personCtr.checkPersonInstanceCount(instructorId, instructorName, instructorZipcode, instructorCountry, instructorAddress) == false)
						{
							JOptionPane.showMessageDialog(null, "You may not update with an already existing instructor on this id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							Location locationObj =  new Location();
							locationObj = locationCtr.getCompleteLocation(instructorZipcode, instructorCountry, instructorCiy);
							if(locationObj == null)
							{
								locationCtr.insertLocation(instructorZipcode, instructorCountry, instructorCiy);
							}
							personCtr.updatePerson(instructorId, instructorName, instructorAddress, instructorZipcode, instructorCountry, instructorPhoneNo, instructorEmail, "Instructor", instructorPassword);
							employeeCtr.updateEmployee(instructorId, instructorSalary);
							instructorCtr.updateInstructor(instructorId, activityTypeId, instructorPrice, instructorStatus);
							
							JOptionPane.showMessageDialog(null, "Instructor updated successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
		instructorUpdateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorUpdateButton.setBounds(6, 88, 90, 25);
		instructorOptionsPanel.add(instructorUpdateButton);
		
		JButton instructorDeleteButton = new JButton("Delete");
		instructorDeleteButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(instructorIdTextField.getText().equals("")==true && instructorNameTextField.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the wanted instructor.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(instructorNameTextField.getText().equals("") == true)
					{
						String stringInstructorId = instructorIdTextField.getText();
						int instructorId = Integer.parseInt(stringInstructorId);
						
						Instructor instructorObj = new Instructor();
						instructorObj = instructorCtr.getInstructorById(instructorId);
						
						if(instructorObj == null)
						{
							JOptionPane.showMessageDialog(null, "There is no instructor by this id. Please insert a valid instructor id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							instructorCtr.deleteInstructorById(instructorId);
							JOptionPane.showMessageDialog(null, "Instructor successfully removed.", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
					{
						if(instructorIdTextField.getText().equals("") == true)
						{
							String instructorName = instructorNameTextField.getText();
							
							Person personObj = new Person();
							personObj = personCtr.searchPersonByName(instructorName);
							
							if(personObj == null || personObj.getPersonType().equals("Instructor") == false)
							{
								JOptionPane.showMessageDialog(null, "There is no instructor by this name. Please insert a valid instructor name.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								int instructorId = personObj.getId();
								instructorCtr.deleteInstructorById(instructorId);
								JOptionPane.showMessageDialog(null, "Instructor successfully removed.", "Info", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else
						{
							if(instructorIdTextField.getText().equals("") != true && instructorNameTextField.getText().equals("") != true)
							{
								String stringInstructorId = instructorIdTextField.getText();
								int instructorId = Integer.parseInt(stringInstructorId);
								
								Instructor instructorObj = new Instructor();
								instructorObj = instructorCtr.getInstructorById(instructorId);
								
								if(instructorObj == null)
								{
									JOptionPane.showMessageDialog(null, "There is no instructor by this id. Please insert a valid instructor id.", "Error!", JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									instructorCtr.deleteInstructorById(instructorId);
									JOptionPane.showMessageDialog(null, "Instructor successfully removed.", "Info", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
				}
			}
		});
		instructorDeleteButton.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorDeleteButton.setBounds(6, 124, 90, 25);
		instructorOptionsPanel.add(instructorDeleteButton);
		
		JButton instructorAllButton = new JButton("All");
		instructorAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorAllButton.setBounds(6, 160, 90, 25);
		instructorOptionsPanel.add(instructorAllButton);
		
		JButton instructorClearAllButton = new JButton("Clear all");
		instructorClearAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorClearAllButton.setBounds(10, 325, 104, 25);
		InstructorPanel.add(instructorClearAllButton);
		
		JScrollPane instructorTableScrollPane = new JScrollPane();
		instructorTableScrollPane.setBounds(124, 122, 691, 275);
		InstructorPanel.add(instructorTableScrollPane);
		
		instructorTable = new JTable();
		instructorTable.setFillsViewportHeight(true);
		instructorTableScrollPane.setViewportView(instructorTable);
		
		JPanel FacilityPanel = new JPanel();
		tabbedPane.addTab("Facility menu", null, FacilityPanel, null);
		FacilityPanel.setLayout(null);
		
		JPanel facilityAttributesPanel = new JPanel();
		facilityAttributesPanel.setBorder(new TitledBorder(null, "Facility attributes:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		facilityAttributesPanel.setBounds(29, 20, 700, 80);
		FacilityPanel.add(facilityAttributesPanel);
		facilityAttributesPanel.setLayout(null);
		
		JLabel facilityIdLabel = new JLabel("Facility id:");
		facilityIdLabel.setBounds(6, 19, 86, 14);
		facilityAttributesPanel.add(facilityIdLabel);
		facilityIdLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		facilityIdTextField = new JTextField();
		facilityIdTextField.setBounds(91, 16, 220, 20);
		facilityAttributesPanel.add(facilityIdTextField);
		facilityIdTextField.setColumns(10);
		
		JLabel facilityNameLabel = new JLabel("Facility name:");
		facilityNameLabel.setBounds(388, 19, 86, 14);
		facilityAttributesPanel.add(facilityNameLabel);
		facilityNameLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		facilityNameTextField = new JTextField();
		facilityNameTextField.setBounds(470, 16, 220, 20);
		facilityAttributesPanel.add(facilityNameTextField);
		facilityNameTextField.setColumns(10);
		
		JLabel activityTypeLabel = new JLabel("Activity type:");
		activityTypeLabel.setBounds(6, 56, 86, 14);
		facilityAttributesPanel.add(activityTypeLabel);
		activityTypeLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		activityTypeComboBox = new JComboBox<String>();
		LinkedList<ActivityType> allActivityTypesList = new LinkedList<ActivityType>();
		allActivityTypesList = activityTypeCtr.getAllActivityTypes();
		if(allActivityTypesList.isEmpty()==false)
		{
			for(ActivityType activityTypeObj : allActivityTypesList)
			{
				String comboBoxItem = activityTypeObj.getName();
				activityTypeComboBox.addItem(comboBoxItem);
			}
		}
		activityTypeComboBox.setSelectedItem(null);
		activityTypeComboBox.setBounds(91, 53, 220, 20);
		facilityAttributesPanel.add(activityTypeComboBox);
		
		JLabel statusLabel = new JLabel("Status:");
		statusLabel.setBounds(388, 56, 86, 14);
		facilityAttributesPanel.add(statusLabel);
		statusLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		statusComboBox = new JComboBox<String>();
		statusComboBox.addItem("Available");
		statusComboBox.addItem("Under construction");
		statusComboBox.addItem("Cleaning");
		statusComboBox.setSelectedItem(null);
		statusComboBox.setBounds(470, 53, 220, 20);
		facilityAttributesPanel.add(statusComboBox);
		
		JPanel facilityOptionsPanel = new JPanel();
		facilityOptionsPanel.setBorder(new TitledBorder(null, "Facility", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		facilityOptionsPanel.setBounds(29, 111, 146, 192);
		FacilityPanel.add(facilityOptionsPanel);
		facilityOptionsPanel.setLayout(null);
		
		JButton facilitySearchButton = new JButton("Search");
		facilitySearchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(facilityIdTextField.getText().equals("")==true && facilityNameTextField.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the wanted facility.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(facilityNameTextField.getText().equals("") == true)
					{
						String stringFacilityId = facilityIdTextField.getText();
						int facilityId = Integer.parseInt(stringFacilityId);
						
						Facility facilityObj = new Facility();
						facilityObj = facilityCtr.getFacilityById(facilityId);
						
						if(facilityObj == null)
						{
							JOptionPane.showMessageDialog(null, "There is no facility by this id. Please insert a valid facility id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							String facilityName = facilityObj.getName();
							ActivityType activityTypeObj = new ActivityType();
							activityTypeObj = facilityObj.getActivity();
							String activityTypeName = activityTypeObj.getName();
							String status = facilityObj.getStatus();
							
							facilityIdTextField.setText(stringFacilityId);
							facilityNameTextField.setText(facilityName);
							activityTypeComboBox.setSelectedItem(activityTypeName);
							statusComboBox.setSelectedItem(status);
						}
					}
					else
					{
						if(facilityIdTextField.getText().equals("") == true)
						{
							String facilityName = facilityNameTextField.getText();
							
							Facility facilityObj = new Facility();
							facilityObj = facilityCtr.getFacilityByName(facilityName);
							if(facilityObj == null)
							{
								JOptionPane.showMessageDialog(null, "There is not facility by this name. Please insert a valid facility name.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								int facilityId = facilityObj.getId();
								String stringFacilityId = String.valueOf(facilityId);
								ActivityType activityTypeObj = new ActivityType();
								activityTypeObj = facilityObj.getActivity();
								String activityTypeName = activityTypeObj.getName();
								String status = facilityObj.getStatus();
								
								facilityIdTextField.setText(stringFacilityId);
								facilityNameTextField.setText(facilityName);
								activityTypeComboBox.setSelectedItem(activityTypeName);
								statusComboBox.setSelectedItem(status);
							}
						}
						else
						{
							if(facilityIdTextField.getText().equals("") != true && facilityNameTextField.getText().equals("") != true)
							{
								String stringFacilityId = facilityIdTextField.getText();
								int facilityId = Integer.parseInt(stringFacilityId);
								
								Facility facilityObj = new Facility();
								facilityObj = facilityCtr.getFacilityById(facilityId);
								
								if(facilityObj == null)
								{
									JOptionPane.showMessageDialog(null, "There is no facility by this id. Please insert a valid facility id.", "Error!", JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									String facilityName = facilityObj.getName();
									ActivityType activityTypeObj = new ActivityType();
									activityTypeObj = facilityObj.getActivity();
									String activityTypeName = activityTypeObj.getName();
									String status = facilityObj.getStatus();
									
									facilityIdTextField.setText(stringFacilityId);
									facilityNameTextField.setText(facilityName);
									activityTypeComboBox.setSelectedItem(activityTypeName);
									statusComboBox.setSelectedItem(status);
								}
							}
						}
					}
				}
			}
		});
		facilitySearchButton.setBounds(6, 16, 134, 25);
		facilityOptionsPanel.add(facilitySearchButton);
		facilitySearchButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton facilityCreateButton = new JButton("Create");
		facilityCreateButton.setBounds(6, 52, 134, 25);
		facilityOptionsPanel.add(facilityCreateButton);
		facilityCreateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(facilityNameTextField.getText().equals("") == true || activityTypeComboBox.getSelectedItem().equals(null) == true || statusComboBox.getSelectedItem().equals(null) ==true)
				{
					JOptionPane.showMessageDialog(null, "A facility attribute might be missing. Please insert all needed facility attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String facilityName = facilityNameTextField.getText();
					
					ActivityType activityTypeObj = new ActivityType();
					String activityName = String.valueOf(activityTypeComboBox.getSelectedItem());
					activityTypeObj = activityTypeCtr.getActivityTypeByName(activityName);
					int activityTypeId = activityTypeObj.getID();
					
					String status = String.valueOf(statusComboBox.getSelectedItem());
					
					if(facilityCtr.getFacilityByName(facilityName) != null)
					{
						JOptionPane.showMessageDialog(null, "Cannot register the same facility twice.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						
						facilityCtr.insertFacility(facilityName, activityTypeId, status);
						clearFacilityTable();
						clearFacilityPanel();
						JOptionPane.showMessageDialog(null, "Facility successfully inserted.", "Info", JOptionPane.INFORMATION_MESSAGE);
						
					}
				}
			}
		});
		facilityCreateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton facilityUpdateButton = new JButton("Update");
		facilityUpdateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(facilityIdTextField.getText().equals("") == true || facilityNameTextField.getText().equals("") == true || activityTypeComboBox.getSelectedItem().equals(null) == true || statusComboBox.getSelectedItem().equals(null) == true)
				{
					JOptionPane.showMessageDialog(null, "Some facility attributes have not been inserted. Please insert all facility attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String stringFacilityId = facilityIdTextField.getText();
					int facilityId = Integer.parseInt(stringFacilityId);
					Facility facilityObj = new Facility();
					facilityObj = facilityCtr.getFacilityById(facilityId);
					
					if(facilityObj == null)
					{
						JOptionPane.showMessageDialog(null, "The wanted facility does not exist in the system. Please check facility list.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						String facilityName = facilityNameTextField.getText();
						
						ActivityType activityTypeObj = new ActivityType();
						String activityName = String.valueOf(activityTypeComboBox.getSelectedItem());
						activityTypeObj = activityTypeCtr.getActivityTypeByName(activityName);
						int activityId = activityTypeObj.getID();
						
						String status = String.valueOf(statusComboBox.getSelectedItem());
						
						if(facilityCtr.checkFacilityInstanceCount(facilityId, facilityName) == false)
						{
							JOptionPane.showMessageDialog(null, "You may not update with an already existing facility on this id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							facilityCtr.updateFacility(facilityId, facilityName, activityId, status);
							clearFacilityTable();
							clearFacilityPanel();
							JOptionPane.showMessageDialog(null, "Facility updated successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
		facilityUpdateButton.setBounds(6, 88, 134, 25);
		facilityOptionsPanel.add(facilityUpdateButton);
		facilityUpdateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton facilityDeleteButton = new JButton("Delete");
		facilityDeleteButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(facilityIdTextField.getText().equals("")==true && facilityNameTextField.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the wanted facility.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(facilityNameTextField.getText().equals("") == true)
					{
						String stringFacilityId = facilityIdTextField.getText();
						int facilityId = Integer.parseInt(stringFacilityId);
						
						Facility facilityObj = new Facility();
						facilityObj = facilityCtr.getFacilityById(facilityId);
						
						if(facilityObj == null)
						{
							JOptionPane.showMessageDialog(null, "There is no facility by this id. Please insert a valid facility id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							facilityCtr.deleteFacility(facilityId);
							clearFacilityTable();
							clearFacilityPanel();
							JOptionPane.showMessageDialog(null, "Facility successfully removed from the system.", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
					{
						if(facilityIdTextField.getText().equals("") == true)
						{
							String facilityName = facilityNameTextField.getText();
							
							Facility facilityObj = new Facility();
							facilityObj = facilityCtr.getFacilityByName(facilityName);
							if(facilityObj == null)
							{
								JOptionPane.showMessageDialog(null, "There is not facility by this name. Please insert a valid facility name.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								int facilityId = facilityObj.getId();
								facilityCtr.deleteFacility(facilityId);
								clearFacilityTable();
								clearFacilityPanel();
								JOptionPane.showMessageDialog(null, "Facility successfully removed from the system.", "Info", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else
						{
							if(facilityIdTextField.getText().equals("") != true && facilityNameTextField.getText().equals("") != true)
							{
								String stringFacilityId = facilityIdTextField.getText();
								int facilityId = Integer.parseInt(stringFacilityId);
								
								Facility facilityObj = new Facility();
								facilityObj = facilityCtr.getFacilityById(facilityId);
								
								if(facilityObj == null)
								{
									JOptionPane.showMessageDialog(null, "There is no facility by this id. Please insert a valid facility id.", "Error!", JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									facilityCtr.deleteFacility(facilityId);
									clearFacilityTable();
									clearFacilityPanel();
									JOptionPane.showMessageDialog(null, "Facility successfully removed from the system.", "Info", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
				}
			}
		});
		facilityDeleteButton.setBounds(6, 124, 134, 25);
		facilityOptionsPanel.add(facilityDeleteButton);
		facilityDeleteButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton facilityAllButton = new JButton("All");
		facilityAllButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearFacilityPanel();
				clearFacilityTable();
				
				facilityTable.setModel(getFacilityTableModel());
			}
		});
		facilityAllButton.setBounds(6, 160, 134, 25);
		facilityOptionsPanel.add(facilityAllButton);
		facilityAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		facilityTableScrollPane = new JScrollPane();
		facilityTableScrollPane.setBounds(185, 125, 630, 230);
		FacilityPanel.add(facilityTableScrollPane);
		
		facilityTable = new JTable();
		facilityTable.setFillsViewportHeight(true);
		facilityTableScrollPane.setViewportView(facilityTable);
		
		JButton facilityClearAllButton = new JButton("Clear all");
		facilityClearAllButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearFacilityPanel();
				clearFacilityTable();
			}
		});
		facilityClearAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		facilityClearAllButton.setBounds(29, 381, 134, 25);
		FacilityPanel.add(facilityClearAllButton);
		frame.setUndecorated(true);
	}
	
	public void setManagerName(String managerName)
	{
		dinamicLabel.setText(managerName);
	}
	
	private DefaultTableModel getFacilityTableModel()
	{
		LinkedList<Facility> facilityCompleteList = new LinkedList<Facility>();
		facilityCompleteList = facilityCtr.getAllFacilities();
		
		DefaultTableModel facilityTableModel = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column)
			{
				//all cells false
				return false;
			}
		};
		
		facilityTableModel.setColumnIdentifiers(new String[] {"FacilityId", "FacilityName", "ActivityId", "ActivityName", "Status"});
		
		try
		{
			for(Facility facilityObj : facilityCompleteList)
			{
				facilityTableModel.addRow(new String[]
						{
						String.valueOf(facilityObj.getId()),
						facilityObj.getName(),
						String.valueOf(facilityObj.getActivity().getID()),
						facilityObj.getActivity().getName(),
						facilityObj.getStatus()
						});
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception: " + e);
		}
		
		facilityTable.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = facilityTable.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener()
		{
			
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				Facility facilityObj = new Facility();
				
				int selectedRow = facilityTable.getSelectedRow();
				if(selectedRow > -1)
				{
					String selectedData = (String) facilityTable.getValueAt(selectedRow, 0);
					
					int facilityId = Integer.parseInt(selectedData);
					
					facilityObj = facilityCtr.getFacilityById(facilityId);
					facilityIdTextField.setText(String.valueOf(facilityObj.getId()));
					facilityNameTextField.setText(facilityObj.getName());
					activityTypeComboBox.setSelectedItem(facilityObj.getActivity().getName());
					statusComboBox.setSelectedItem(facilityObj.getStatus());
				}
				
			}
		});
		
		
		return facilityTableModel;
	}
	
	public void clearFacilityPanel()
	{
		facilityIdTextField.setText("");
		facilityNameTextField.setText("");
		activityTypeComboBox.setSelectedItem(null);
		statusComboBox.setSelectedItem(null);
	}
	
	public void clearFacilityTable()
	{
		facilityTable.setCellSelectionEnabled(false);
		facilityTable.setModel(new DefaultTableModel());
	}
	
	public DefaultTableModel getActivityTypeTableModel()
	{
		LinkedList<ActivityType> completeActivityTypeList = new LinkedList<ActivityType>();
		completeActivityTypeList = activityTypeCtr.getAllActivityTypes();
		
		DefaultTableModel activityTypeModel = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column)
			{
				//all cells false
				return false;
			}
		};
		
		activityTypeModel.setColumnIdentifiers(new String[] {"ActivityId", "ActivityName", "MaxNumberOfParticipants"});
		
		for(ActivityType activityTypeObj : completeActivityTypeList)
		{
			activityTypeModel.addRow(new String[]
					{
					String.valueOf(activityTypeObj.getID()),
					activityTypeObj.getName(),
					String.valueOf(activityTypeObj.getMaxParticipants())
					});
		}
		
		
		activityTypeTable.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = activityTypeTable.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener()
		{
			
			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				ActivityType activityTypeObj = new ActivityType();
				
				int selectedRow = activityTypeTable.getSelectedRow();
				if(selectedRow > -1)
				{
					String selectedData = (String) activityTypeTable.getValueAt(selectedRow, 0);
					int activityTypeId = Integer.parseInt(selectedData);
					
					activityTypeObj = activityTypeCtr.getActivityTypeByID(activityTypeId);
					
					activityTypeIdTextField.setText(String.valueOf(activityTypeObj.getID()));
					activityTypeNameTextField.setText(activityTypeObj.getName());
					maxParticipantsTextField.setText(String.valueOf(activityTypeObj.getMaxParticipants()));
				}
			}
		});
		
		return activityTypeModel;
	}
	
	public void clearActivityTypePanel()
	{
		activityTypeIdTextField.setText("");
		activityTypeNameTextField.setText("");
		maxParticipantsTextField.setText("");
	}
	
	public void clearActivityTypeTable()
	{
		activityTypeTable.setCellSelectionEnabled(false);
		activityTypeTable.setModel(new DefaultTableModel());
	}
	
	public DefaultTableModel getGuestTableModel()
	{
		LinkedList<Guest> completeGuestList = new LinkedList<Guest>();
		completeGuestList = guestCtr.getAllGuests();
		
		DefaultTableModel guestTableModel = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column)
			{
				//all cells false
				return false;
			}
		};
		
		guestTableModel.setColumnIdentifiers(new String[] {"Id", "Name", "Guest type", "Travel agency", "Password"});
		
		for(Guest guestObj : completeGuestList)
		{
			String travelAgencyName = new String();
			TravelAgency travelAgencyObj = new TravelAgency();
			travelAgencyObj = guestObj.getTravelAgency();
			if(travelAgencyObj == null)
			{
				travelAgencyName = "0";
			}
			else
			{
				travelAgencyName = travelAgencyObj.getName();
			}
			guestTableModel.addRow(new String[]
					{
					String.valueOf(guestObj.getId()),
					guestObj.getName(),
					guestObj.getGuestType(),
					travelAgencyName,
					guestObj.getPassword()
					});
		}
		
		guestTable.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = guestTable.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				Guest guestObj = new Guest();
				
				int selectedRow = guestTable.getSelectedRow();
				if(selectedRow > -1)
				{
					String selectedData = (String) guestTable.getValueAt(selectedRow, 0);
					int guestId = Integer.parseInt(selectedData);
					String stringGuestId = String.valueOf(guestId);
					
					guestObj = guestCtr.searchGuestById(guestId);
					String guestName = guestObj.getName();
					int guestZipCode = guestObj.getZipCode();
					String stringGuestZipcode = String.valueOf(guestZipCode);
					String guestCountry = guestObj.getCountry();
					String guestAddress = guestObj.getAddress();
					String guestPhoneNo = guestObj.getPhoneNo();
					String guestEmail = guestObj.getEmail();
					String guestPassword = guestObj.getPassword();
					String guestType = guestObj.getGuestType();
												
					String travelAgencyName = new String();
					TravelAgency travelAgencyObj = new TravelAgency();
					travelAgencyObj = guestObj.getTravelAgency();
					if(travelAgencyObj == null)
					{
						travelAgencyName = "0";
					}
					else
					{
						travelAgencyName = travelAgencyObj.getName();
					}
					
					Location locationObj = new Location();
					locationObj = locationCtr.getLocation(guestZipCode, guestCountry);
					String guestCity = locationObj.getCity();
					
					guestIdTextField.setText(stringGuestId);
					guestNameTextField.setText(guestName);
					guestCityTextField.setText(guestCity);
					guestCountryTextField.setText(guestCountry);
					guestZipcodeTextField.setText(stringGuestZipcode);
					guestAddressTextField.setText(guestAddress);
					guestPhoneNoTextField.setText(guestPhoneNo);
					guestEmailTextField.setText(guestEmail);
					guestPasswordTextField.setText(guestPassword);									
					guestGuestTypeComboBox.setSelectedItem(guestType);
					
					if(travelAgencyName.equals("0") == true)
					{
						guestTravelAgencyComboBox.setSelectedItem(null);
					}
					else
					{
						guestTravelAgencyComboBox.setSelectedItem(travelAgencyName);
					}
				}
			}
		});
		
		return guestTableModel;
	}
	
	public void clearGuestPanel()
	{
		guestIdTextField.setText("");
		guestNameTextField.setText("");
		guestCityTextField.setText("");
		guestCountryTextField.setText("");
		guestZipcodeTextField.setText("");
		guestAddressTextField.setText("");
		guestPhoneNoTextField.setText("");
		guestEmailTextField.setText("");
		guestPasswordTextField.setText("");									
		guestGuestTypeComboBox.setSelectedItem(null);
		guestTravelAgencyComboBox.setSelectedItem(null);
	}
	
	public void clearGuestTable()
	{
		guestTable.setCellSelectionEnabled(false);
		guestTable.setModel(new DefaultTableModel());
	}
	
	
}
