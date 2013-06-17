package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

import Controller.ActivityBookingCtr;
import Controller.ActivityCtr;
import Controller.DateCheck;
import Controller.FacilityCtr;
import Controller.GuestCtr;
import Controller.InstructorCtr;
import Controller.PersonCtr;
import Controller.RoomBookingCtr;
import Controller.TeamCtr;
import Controller.TravelAgencyCtr;
import Model.ActivityBooking;
import Model.ActivityLine;
import Model.ActivityType;
import Model.Facility;
import Model.Guest;
import Model.Instructor;
import Model.Person;
import Model.Room;
import Model.RoomBooking;
import Model.RoomLine;
import Model.Team;
import Model.TravelAgency;
import Model.Location;
import Controller.LocationCtr;

public class EmployeeMenu
{
	private int bookingId;
	private int guestId;
	private LinkedList<ActivityBooking> activityBookingList = new LinkedList<ActivityBooking>();
	private TravelAgencyCtr travelCtr = new TravelAgencyCtr();
	
	private GuestCtr guestCtr = new GuestCtr();
	private ActivityBookingCtr activityBookingCtr = new ActivityBookingCtr();
	private DateCheck dateCheck = new DateCheck();
	private ActivityCtr activityCtr = new ActivityCtr();
	private FacilityCtr facilityCtr = new FacilityCtr();
	private InstructorCtr instructorCtr = new InstructorCtr();
	private TeamCtr teamCtr = new TeamCtr();
	private PersonCtr personCtr = new PersonCtr();
	private LocationCtr locationCtr = new LocationCtr();

	private JFrame frame;
	private JTextField txtRoomBookingId;
	private JTextField txtNumberOfChildren;
	private JTextField txtStatus;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField txtDd;
	private JTextField txtMm;
	private JTextField txtYyyy;
	private JTextField txtDepDD;
	private JTextField txtDepmm;
	private JTextField txtDepyyyy;
	private JTextField selectedDateTextField;
	private JTextField guestIdTextBox;
	private JTextField maxParticipantsTextBox;
	private JTextField teamNumberOfParticipantsTextField;
	private JButton createActivityBookingButton;
	private JTable activityBookingsTable;
	private JTable activityLinesTable;
	private JComboBox<String> dayComboBox;
	private JComboBox<String> monthComboBox;
	private JComboBox<String> yearComboBox;
	private JComboBox<String> allAvailableActivitiesComboBox;
	private JComboBox<String> startHoursComboBox;
	private JComboBox<String> availableFacilityComboBox;
	private JComboBox<String> availableInstructorsComboBox;
	private JComboBox<String> activityBookingAllTeamsComboBox;
	private JButton getAvailableFacilitiesButton;
	private JButton getInstructorsButton;
	private JButton instructorClearButton;
	private JButton getTeamsButton;
	private JButton teamClearButton;
	private JButton addActivityLineButton;
	private JButton cancelActivityLineButton;
	private JButton allActivityLinesButton;
	private JTextField guestIdTextField;
	private JTextField guestNameTextField;
	private JTextField guestCityTextField;
	private JTextField guestCountryTextField;
	private JTextField guestZipcodeTextField;
	private JTextField guestAddressTextField;
	private JTextField guestPhoneNoTextField;
	private JTextField guestEmailTextField;
	private JTextField guestPasswordTextField;
	private JComboBox<String> guestGuestTypeComboBox;
	private JComboBox<String> guestTravelAgencyComboBox;
	private JTable guestTable;
	
	public EmployeeMenu()
	{
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize()
	{
		frame = new JFrame("Employee menu");
		frame.setFont(new Font("Dialog", Font.PLAIN, 14));
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1000, 660);
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
		logOffButton.setBounds(845, 572, 125, 39);
		frame.getContentPane().add(logOffButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 960, 550);
		frame.getContentPane().add(tabbedPane);
		
		JPanel WelcomePanel = new JPanel();
		tabbedPane.addTab("Main", null, WelcomePanel, null);
		
		//delete room booking button
		/*JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				RoomBookingCtr rbCtr=new RoomBookingCtr();
				
				if(txtRoomBookingId.getText().equals("")!=true)
				{
					String stringID=txtRoomBookingId.getText();
					int bookingId=Integer.parseInt(stringID);
					
					RoomBooking rb=rbCtr.findRoomBookingByID(bookingId);
					if(rb!=null)
					{
						rbCtr.deleteBooking(bookingId);
						
						clearValues();
						
						JOptionPane.showMessageDialog(null, "Booking has been deleted.", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "There is no such booking!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please insert either id of the booking.", "Error!", JOptionPane.ERROR_MESSAGE);
				}						
			}
		});
		btnDelete.setBounds(6, 154, 110, 35);
		panel.add(btnDelete);*/
		
		JPanel GuestPanel = new JPanel();
		tabbedPane.addTab("Guest", null, GuestPanel, null);
		GuestPanel.setLayout(null);
		
		JPanel guestAttributesPanel = new JPanel();
		guestAttributesPanel.setLayout(null);
		guestAttributesPanel.setBorder(new TitledBorder(null, "Guest attributes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		guestAttributesPanel.setBounds(10, 11, 939, 93);
		GuestPanel.add(guestAttributesPanel);
		
		JLabel guestIdLabel2 = new JLabel("Id:");
		guestIdLabel2.setFont(new Font("Arial", Font.PLAIN, 11));
		guestIdLabel2.setBounds(16, 19, 46, 14);
		guestAttributesPanel.add(guestIdLabel2);
		
		guestIdTextField = new JTextField();
		guestIdTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestIdTextField.setColumns(10);
		guestIdTextField.setBounds(70, 16, 100, 20);
		guestAttributesPanel.add(guestIdTextField);
		
		JLabel guestNameLabel = new JLabel("Name:");
		guestNameLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		guestNameLabel.setBounds(186, 19, 46, 14);
		guestAttributesPanel.add(guestNameLabel);
		
		guestNameTextField = new JTextField();
		guestNameTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestNameTextField.setColumns(10);
		guestNameTextField.setBounds(239, 16, 100, 20);
		guestAttributesPanel.add(guestNameTextField);
		
		JLabel guestCityLabel = new JLabel("City:");
		guestCityLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		guestCityLabel.setBounds(401, 19, 80, 14);
		guestAttributesPanel.add(guestCityLabel);
		
		guestCityTextField = new JTextField();
		guestCityTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestCityTextField.setColumns(10);
		guestCityTextField.setBounds(439, 16, 100, 20);
		guestAttributesPanel.add(guestCityTextField);
		
		JLabel guestCountryLabel = new JLabel("Country:");
		guestCountryLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		guestCountryLabel.setBounds(577, 19, 80, 14);
		guestAttributesPanel.add(guestCountryLabel);
		
		guestCountryTextField = new JTextField();
		guestCountryTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestCountryTextField.setColumns(10);
		guestCountryTextField.setBounds(639, 16, 100, 20);
		guestAttributesPanel.add(guestCountryTextField);
		
		JLabel guestZipcodeLabel = new JLabel("Zipcode:");
		guestZipcodeLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		guestZipcodeLabel.setBounds(749, 19, 80, 14);
		guestAttributesPanel.add(guestZipcodeLabel);
		
		guestZipcodeTextField = new JTextField();
		guestZipcodeTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestZipcodeTextField.setColumns(10);
		guestZipcodeTextField.setBounds(815, 16, 100, 20);
		guestAttributesPanel.add(guestZipcodeTextField);
		
		JLabel guestAddressLabel = new JLabel("Address:");
		guestAddressLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		guestAddressLabel.setBounds(16, 44, 86, 14);
		guestAttributesPanel.add(guestAddressLabel);
		
		guestAddressTextField = new JTextField();
		guestAddressTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestAddressTextField.setColumns(10);
		guestAddressTextField.setBounds(70, 41, 100, 20);
		guestAttributesPanel.add(guestAddressTextField);
		
		JLabel guestPhoneNoLabel = new JLabel("Phone no:");
		guestPhoneNoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		guestPhoneNoLabel.setBounds(186, 44, 80, 14);
		guestAttributesPanel.add(guestPhoneNoLabel);
		
		guestPhoneNoTextField = new JTextField();
		guestPhoneNoTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestPhoneNoTextField.setColumns(10);
		guestPhoneNoTextField.setBounds(239, 41, 100, 20);
		guestAttributesPanel.add(guestPhoneNoTextField);
		
		JLabel guestEmailLabel = new JLabel("E-mail:");
		guestEmailLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		guestEmailLabel.setBounds(401, 44, 80, 14);
		guestAttributesPanel.add(guestEmailLabel);
		
		guestEmailTextField = new JTextField();
		guestEmailTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestEmailTextField.setColumns(10);
		guestEmailTextField.setBounds(439, 41, 100, 20);
		guestAttributesPanel.add(guestEmailTextField);
		
		JLabel guestPasswordLabel = new JLabel("Password:");
		guestPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		guestPasswordLabel.setBounds(577, 44, 80, 14);
		guestAttributesPanel.add(guestPasswordLabel);
		
		guestPasswordTextField = new JTextField();
		guestPasswordTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestPasswordTextField.setColumns(10);
		guestPasswordTextField.setBounds(639, 41, 100, 20);
		guestAttributesPanel.add(guestPasswordTextField);
		
		JLabel guestGuestTypeLabel = new JLabel("Guest type:");
		guestGuestTypeLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		guestGuestTypeLabel.setBounds(749, 44, 80, 14);
		guestAttributesPanel.add(guestGuestTypeLabel);
		
		JLabel guestTravelAgencyLabel = new JLabel("Travel agency:");
		guestTravelAgencyLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		guestTravelAgencyLabel.setBounds(6, 69, 86, 14);
		guestAttributesPanel.add(guestTravelAgencyLabel);
		
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
		noTravelAgencyButton.setFont(new Font("Arial", Font.PLAIN, 11));
		noTravelAgencyButton.setBounds(216, 64, 146, 25);
		guestAttributesPanel.add(noTravelAgencyButton);
		
		guestGuestTypeComboBox = new JComboBox<String>();
		guestGuestTypeComboBox.addItem("Private");
		guestGuestTypeComboBox.addItem("Business");
		guestGuestTypeComboBox.addItem("Family");
		guestGuestTypeComboBox.setSelectedItem(null);
		guestGuestTypeComboBox.setBounds(815, 41, 100, 20);
		guestAttributesPanel.add(guestGuestTypeComboBox);
		
		JPanel guestOptionsPanel = new JPanel();
		guestOptionsPanel.setLayout(null);
		guestOptionsPanel.setBorder(new TitledBorder(null, "Guest", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		guestOptionsPanel.setBounds(10, 115, 140, 157);
		GuestPanel.add(guestOptionsPanel);
		
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
		guestSearchButton.setBounds(6, 16, 124, 25);
		guestOptionsPanel.add(guestSearchButton);
		
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
						Location locationObj = locationCtr.getLocation(guestZipcode, guestCountry);
						if(locationObj == null)
						{
							locationCtr.insertLocation(guestZipcode, guestCountry, guestCity);
						}
						
						personCtr.insertPerson(guestName, guestAddress, guestZipcode, guestCountry, guestPhoneNo, guestEmail, "Guest", guestPassword);
						guestCtr.insertGuest(guestName, travelAgencyCVR, guestType);
						
						JOptionPane.showMessageDialog(null, "Guest successfully inserted", "Info", JOptionPane.INFORMATION_MESSAGE);
						clearGuestPanel();
						clearGuestTable();
						guestTable.setModel(getGuestTableModel());
					}
				}
			}
		});
		guestCreateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		guestCreateButton.setBounds(6, 52, 124, 25);
		guestOptionsPanel.add(guestCreateButton);
		
		JButton guestUpdateButton = new JButton("Update");
		guestUpdateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(guestIdTextField.getText().equals("") == true || guestNameTextField.getText().equals("") == true || guestCityTextField.getText().equals("") == true ||
						guestCountryTextField.getText().equals("") == true || guestZipcodeTextField.getText().equals("") == true || guestAddressTextField.getText().equals("") == true ||
								guestGuestTypeComboBox.getSelectedItem().equals(null) == true || guestPasswordTextField.getText().equals("") == true)
			
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
						String guestPassword = guestPasswordTextField.getText();
						
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
							locationObj = locationCtr.getLocation(guestZipcode, guestCountry);
							if(locationObj == null)
							{
								locationCtr.insertLocation(guestZipcode, guestCountry, guestCity);
							}
							
							personCtr.updatePerson(guestId, guestName, guestAddress, guestZipcode, guestCountry, guestPhoneNo, guestEmail, "Guest", guestPassword);
							guestCtr.updateGuest(guestId, guestType, travelAgencyCVR);
							
							JOptionPane.showMessageDialog(null, "Guest updated successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
							clearGuestPanel();
							clearGuestTable();
							guestTable.setModel(getGuestTableModel());
						}
					}
				}
			}
		});
		guestUpdateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		guestUpdateButton.setBounds(6, 88, 124, 25);
		guestOptionsPanel.add(guestUpdateButton);
		
		JButton guestAllButton = new JButton("All");
		guestAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		guestAllButton.setBounds(6, 124, 124, 25);
		guestOptionsPanel.add(guestAllButton);
		
		JButton guestClearAllButton = new JButton("Clear all");
		guestClearAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		guestClearAllButton.setBounds(10, 283, 140, 25);
		GuestPanel.add(guestClearAllButton);
		
		JScrollPane guestTableScrollPane = new JScrollPane();
		guestTableScrollPane.setBounds(160, 115, 785, 396);
		GuestPanel.add(guestTableScrollPane);
		
		guestTable = new JTable();
		guestTable.setFillsViewportHeight(true);
		guestTableScrollPane.setViewportView(guestTable);
		
		JPanel RoomBookingPanel = new JPanel();
		tabbedPane.addTab("Room booking", null, RoomBookingPanel, null);	
		RoomBookingPanel.setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Booking data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 7, 759, 120);
		RoomBookingPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblBookingId = new JLabel("Booking ID:");
		lblBookingId.setBounds(6, 16, 100, 25);
		panel_1.add(lblBookingId);
		lblBookingId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblArrivalDate = new JLabel("Arrival date:");
		lblArrivalDate.setBounds(6, 52, 100, 25);
		panel_1.add(lblArrivalDate);
		lblArrivalDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNumberOfChildren = new JLabel("Children #:");
		lblNumberOfChildren.setBounds(6, 88, 100, 25);
		panel_1.add(lblNumberOfChildren);
		lblNumberOfChildren.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(375, 16, 100, 25);
		panel_1.add(lblStatus);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblDepartureDate = new JLabel("Departure date:");
		lblDepartureDate.setBounds(375, 52, 100, 25);
		panel_1.add(lblDepartureDate);
		lblDepartureDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtRoomBookingId = new JTextField();
		txtRoomBookingId.setBounds(96, 20, 240, 20);
		panel_1.add(txtRoomBookingId);
		txtRoomBookingId.setColumns(10);
		
		txtNumberOfChildren = new JTextField();
		txtNumberOfChildren.setBounds(96, 92, 240, 20);
		panel_1.add(txtNumberOfChildren);
		txtNumberOfChildren.setColumns(10);
		
		txtStatus = new JTextField();
		txtStatus.setBounds(485, 20, 240, 20);
		panel_1.add(txtStatus);
		txtStatus.setColumns(10);
		
		JLabel lblDdmmyyyy = new JLabel("DD:MM:YYYY");
		lblDdmmyyyy.setBounds(278, 59, 76, 14);
		panel_1.add(lblDdmmyyyy);
		
		JLabel lblDdmmyyyy_1 = new JLabel("DD:MM:YYYY");
		lblDdmmyyyy_1.setBounds(679, 59, 70, 14);
		panel_1.add(lblDdmmyyyy_1);
		
		txtDd = new JTextField();
		txtDd.setBounds(96, 56, 28, 20);
		panel_1.add(txtDd);
		txtDd.setColumns(10);
		
		JLabel label = new JLabel(":");
		label.setBounds(134, 59, 10, 14);
		panel_1.add(label);
		
		txtMm = new JTextField();
		txtMm.setBounds(144, 56, 28, 20);
		panel_1.add(txtMm);
		txtMm.setColumns(10);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(182, 59, 10, 14);
		panel_1.add(label_1);
		
		txtYyyy = new JTextField();
		txtYyyy.setBounds(202, 56, 66, 20);
		panel_1.add(txtYyyy);
		txtYyyy.setColumns(10);
		
		txtDepDD = new JTextField();
		txtDepDD.setBounds(485, 56, 28, 20);
		panel_1.add(txtDepDD);
		txtDepDD.setColumns(10);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(523, 59, 10, 14);
		panel_1.add(label_2);
		
		txtDepmm = new JTextField();
		txtDepmm.setBounds(533, 56, 28, 20);
		panel_1.add(txtDepmm);
		txtDepmm.setColumns(10);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(571, 59, 10, 14);
		panel_1.add(label_3);
		
		txtDepyyyy = new JTextField();
		txtDepyyyy.setBounds(583, 56, 86, 20);
		panel_1.add(txtDepyyyy);
		txtDepyyyy.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 142, 125, 338);
		RoomBookingPanel.add(panel);
		panel.setLayout(null);
		
		//search button
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				RoomBookingCtr rbCtr=new RoomBookingCtr();
				
				String txtArrivalDate = txtYyyy.getText()+txtMm.getText()+txtDd.getText();
				String txtDeparturedate = txtDepyyyy.getText()+txtDepmm.getText()+txtDepDD.getText();
				
				if(!txtRoomBookingId.getText().equals(""))
				{
					String stringID=txtRoomBookingId.getText();
					System.out.println(stringID);
					int id=Integer.parseInt(stringID);
					RoomBooking rb = rbCtr.findRoomBookingByID(id);
					putValuesOnTheScreen(rb);
					
					ArrayList<RoomLine> rlList=new ArrayList<RoomLine>();
					rlList=rbCtr.findRoomLinesForBooking(id);
					
					DefaultTableModel model2 = new DefaultTableModel()
					{
						private static final long serialVersionUID = 1L;

						@Override
						public boolean isCellEditable(int row, int column)
						{
							//all cells false
							return false;
						}
					};
				
					model2.setColumnIdentifiers(new String[] {"Booking id", "Guest", "Guest type", "Room", "Room type", "Room price"});
				
					try
					{
						for(RoomLine rlObj : rlList)
						{
							model2.addRow(new String[]
								{
									String.valueOf(rlObj.getBooking().getId()),
									rlObj.getGuest().getName(),
									rlObj.getGuest().getGuestType(),
									String.valueOf(rlObj.getRoom().getNumber()),
									rlObj.getRoom().getRoomType().getCategory(),
									String.valueOf(rlObj.getRoom().getRoomType().getPrice())
								});
						}
						table.setModel(model2);
					}
				
					catch(Exception e)
					{
						System.out.println("Exception: " + e);
					}
				}
				else if(!txtArrivalDate.equals(""))
				{
					int arrival = Integer.parseInt(txtArrivalDate);
					ArrayList<RoomBooking> rbList=new ArrayList<RoomBooking>();
					rbList=rbCtr.findRoomBookingByArrival(arrival);
					
					DefaultTableModel model2 = new DefaultTableModel()
					{
						private static final long serialVersionUID = 1L;

						@Override
						public boolean isCellEditable(int row, int column)
						{
							//all cells false
							return false;
						}
					};
				
					model2.setColumnIdentifiers(new String[] {"Booking id", "Guest", "Guest type", "Room", "Room type", "Room price"});
				
					try
					{
						for(RoomBooking rbObj : rbList)
						{
							ArrayList<RoomLine> rlList = new ArrayList<>();
							rlList=rbCtr.findRoomLinesForBooking(rbObj.getId());
							try
							{
								for(RoomLine rlObj : rlList)
								{
									model2.addRow(new String[]
										{
											String.valueOf(rlObj.getBooking().getId()),
											rlObj.getGuest().getName(),
											rlObj.getGuest().getGuestType(),
											String.valueOf(rlObj.getRoom().getNumber()),
											rlObj.getRoom().getRoomType().getCategory(),
											String.valueOf(rlObj.getRoom().getRoomType().getPrice())
										});
								}
								table.setModel(model2);
							}						
							catch(Exception e)
							{
								System.out.println("Exception: " + e);
							}
						}
					}
					catch(Exception e)
					{
						System.out.println("Exception: " + e);
					}
				}
				else if(!txtDeparturedate.equals(""))
				{
					int departure = Integer.parseInt(txtDeparturedate);
					ArrayList<RoomBooking> rbList=new ArrayList<RoomBooking>();
					rbList=rbCtr.findRoomBookingByDeparture(departure);
					
					DefaultTableModel model2 = new DefaultTableModel()
					{
						private static final long serialVersionUID = 1L;

						@Override
						public boolean isCellEditable(int row, int column)
						{
							//all cells false
							return false;
						}
					};
				
					model2.setColumnIdentifiers(new String[] {"Booking id", "Guest", "Guest type", "Room", "Room type", "Room price"});
				
					try
					{
						for(RoomBooking rbObj : rbList)
						{
							ArrayList<RoomLine> rlList = new ArrayList<>();
							rlList=rbCtr.findRoomLinesForBooking(rbObj.getId());
							try
							{
								for(RoomLine rlObj : rlList)
								{
									model2.addRow(new String[]
										{
											String.valueOf(rlObj.getBooking().getId()),
											rlObj.getGuest().getName(),
											rlObj.getGuest().getGuestType(),
											String.valueOf(rlObj.getRoom().getNumber()),
											rlObj.getRoom().getRoomType().getCategory(),
											String.valueOf(rlObj.getRoom().getRoomType().getPrice())
										});
								}
								table.setModel(model2);
							}
						
							catch(Exception e)
							{
								System.out.println("Exception: " + e);
							}
						}
					}
					catch(Exception e)
					{
						System.out.println("Exception: " + e);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please insert id of the booking.", "Error!", JOptionPane.ERROR_MESSAGE);			
				}
			}
		});
		btnNewButton.setBounds(6, 16, 110, 35);
		panel.add(btnNewButton);
		
		//update room booking button
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				RoomBookingCtr rbCtr=new RoomBookingCtr();				

				String txtArrivalDate = txtYyyy.getText()+txtMm.getText()+txtDd.getText();
				String txtDeparturedate = txtDepyyyy.getText()+txtDepmm.getText()+txtDepDD.getText();
				
				if(txtRoomBookingId.getText().equals("")!=true)
				{
					String stringID=txtRoomBookingId.getText();
					int rbId=Integer.parseInt(stringID);
					
					RoomBooking rb1=rbCtr.findRoomBookingByID(rbId);
					if(rb1!=null)
					{
						if(txtArrivalDate.equals("")!=true || 
							txtNumberOfChildren.getText().equals("")!=true ||
							txtStatus.getText().equals("")!=true ||
							txtDeparturedate.equals("")!=true)
						{
							int arrival=Integer.parseInt(txtArrivalDate);
							int children=Integer.parseInt(txtNumberOfChildren.getText());
							String status=txtStatus.getText();
							int departure=Integer.parseInt(txtDeparturedate);
							
							rbCtr.updateBooking(rbId, arrival, departure, status, children);
							
							clearValues();
							
							JOptionPane.showMessageDialog(null, "Booking has been successfully updated.", "Information", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Some fields may be empty!", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "There is no such booking!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please insert id of the booking.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				
				DefaultTableModel tdm=(DefaultTableModel)table.getModel();
				tdm.getDataVector().removeAllElements();
				tdm.fireTableDataChanged();
			}
		});
		btnUpdate.setBounds(6, 108, 110, 35);
		panel.add(btnUpdate);
		
			//create button
			JButton btnCreate = new JButton("Create");
			btnCreate.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					String txtArrivalDate = txtYyyy.getText()+txtMm.getText()+txtDd.getText();
					String txtDeparturedate = txtDepyyyy.getText()+txtDepmm.getText()+txtDepDD.getText();
					
					if(txtArrivalDate.equals("")==true || txtDeparturedate.equals("")==true || txtNumberOfChildren.getText().equals("")==true || txtStatus.getText().equals("")==true)
					{
						JOptionPane.showMessageDialog(null, "Please insert all data!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						RoomBookingCtr rbCtr=new RoomBookingCtr();
						int arrival=Integer.parseInt(txtArrivalDate);
						int departure=Integer.parseInt(txtDeparturedate);
						String status=txtStatus.getText();
						int numberOfChildren=Integer.parseInt(txtNumberOfChildren.getText());
						System.out.println(txtArrivalDate);
						int bookingId = rbCtr.createNewBooking(arrival, departure, status, numberOfChildren);
						
						DefaultTableModel tdm=(DefaultTableModel)table.getModel();
						tdm.getDataVector().removeAllElements();
						tdm.fireTableDataChanged();
						
						RoomLineMenu.getInstance(bookingId, arrival, departure);
					}
				}
			});
			btnCreate.setBounds(6, 62, 110, 35);
			panel.add(btnCreate);
			
			//get available room lines 
			JButton btnGetRoomsAvailable = new JButton("Available rooms");
			btnGetRoomsAvailable.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					RoomBookingCtr rbCtr=new RoomBookingCtr();
					String txtArrivalDate = txtYyyy.getText()+txtMm.getText()+txtDd.getText();
					String txtDeparturedate = txtDepyyyy.getText()+txtDepmm.getText()+txtDepDD.getText();
					
					if(txtArrivalDate.equals("") || txtDeparturedate.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Please insert arrival date and departure date.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						int arrival=Integer.parseInt(txtArrivalDate);
						int departure=Integer.parseInt(txtDeparturedate);
						
						ArrayList<Room> rlList=new ArrayList<Room>();
						rlList=rbCtr.findAvailableRooms(arrival, departure);
						
						DefaultTableModel model2 = new DefaultTableModel()
						{
							private static final long serialVersionUID = 1L;

							@Override
							public boolean isCellEditable(int row, int column)
							{
								//all cells false
								return false;
							}
						};
					
						model2.setColumnIdentifiers(new String[] {"Booking id", "Guest", "Guest type", "Room", "Room type", "Room price"});
					
						try
						{
							for(Room rlObj : rlList)
							{
								model2.addRow(new String[]
									{
										"None",//String.valueOf(rlObj.getBooking().getId()),//None
										"None",//rlObj.getGuest().getName(),//None
										"None",//rlObj.getGuest().getGuestType(),//None
										String.valueOf(rlObj.getNumber()),
										rlObj.getRoomType().getCategory(),
										String.valueOf(rlObj.getRoomType().getPrice())
									});
							}
							table.setModel(model2);
						}
					
						catch(Exception e)
						{
							System.out.println("Exception: " + e);
						}
					}
				}
			});
			btnGetRoomsAvailable.setBounds(6, 200, 110, 35);
			panel.add(btnGetRoomsAvailable);
			
			//clear window data button
			JButton btnClear = new JButton("Clear");
			btnClear.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					clearValues();
				}
			});
			btnClear.setBounds(6, 246, 110, 35);
			panel.add(btnClear);
			
			JButton btnClose = new JButton("Close");
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
				}
			});
			btnClose.setBounds(6, 292, 110, 35);
			panel.add(btnClose);
			
			//scroll pane and table creation
			scrollPane = new JScrollPane();
			scrollPane.setBounds(146, 142, 744, 346);
			RoomBookingPanel.add(scrollPane);
			
			table = new JTable();
			scrollPane.setViewportView(table);
			table.setFillsViewportHeight(true);
		//end room booking panel
		
		JPanel ActivityBookingPanel = new JPanel();
		tabbedPane.addTab("Activity booking", null, ActivityBookingPanel, null);
		ActivityBookingPanel.setLayout(null);
		
		JPanel activityBookingPanel = new JPanel();
		activityBookingPanel.setLayout(null);
		activityBookingPanel.setBorder(new TitledBorder(null, "Activity booking", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityBookingPanel.setBounds(10, 11, 237, 227);
		ActivityBookingPanel.add(activityBookingPanel);
		
		JLabel dateLabel = new JLabel("Date:");
		dateLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		dateLabel.setBounds(6, 33, 90, 14);
		activityBookingPanel.add(dateLabel);
		
		JPanel dayPanel = new JPanel();
		dayPanel.setLayout(null);
		dayPanel.setBorder(new TitledBorder(null, "dd", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		dayPanel.setBounds(39, 16, 57, 43);
		activityBookingPanel.add(dayPanel);
		
		dayComboBox = new JComboBox<String>();
		LinkedList<String> days = new LinkedList<String>();
		days = dateCheck.getDays();
		for(String aDay : days)
		{
			String insertedDay = aDay;
			dayComboBox.addItem(insertedDay);
		}
		dayComboBox.setSelectedItem(null);
		dayComboBox.setBounds(6, 16, 45, 20);
		dayPanel.add(dayComboBox);
		
		JPanel monthPanel = new JPanel();
		monthPanel.setLayout(null);
		monthPanel.setBorder(new TitledBorder(null, "MM", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		monthPanel.setBounds(97, 16, 56, 43);
		activityBookingPanel.add(monthPanel);
		
		monthComboBox = new JComboBox<String>();
		LinkedList<String> months = new LinkedList<String>();
		months = dateCheck.getMonths();
		for(String aMonth : months)
		{
			String insertedMonth = aMonth;
			monthComboBox.addItem(insertedMonth);
		}
		monthComboBox.setSelectedItem(null);
		monthComboBox.setBounds(6, 16, 45, 20);
		monthPanel.add(monthComboBox);
		
		JPanel yearPanel = new JPanel();
		yearPanel.setLayout(null);
		yearPanel.setBorder(new TitledBorder(null, "yyyy", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		yearPanel.setBounds(152, 16, 77, 43);
		activityBookingPanel.add(yearPanel);
		
		yearComboBox = new JComboBox<String>();
		LinkedList<String> years = new LinkedList<String>();
		years = dateCheck.getYears();
		for(String aYear : years)
		{
			String insertedYear = aYear;
			yearComboBox.addItem(insertedYear);
		}
		yearComboBox.setSelectedItem(null);
		yearComboBox.setBounds(6, 16, 65, 20);
		yearPanel.add(yearComboBox);
		
		JLabel currentDateLabel = new JLabel("Selected date:");
		currentDateLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		currentDateLabel.setBounds(6, 67, 122, 14);
		activityBookingPanel.add(currentDateLabel);
		
		selectedDateTextField = new JTextField();
		selectedDateTextField.setEditable(false);
		selectedDateTextField.setColumns(10);
		selectedDateTextField.setBounds(91, 64, 138, 20);
		activityBookingPanel.add(selectedDateTextField);
		
		JLabel guestIdLabel = new JLabel("Guest id:");
		guestIdLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		guestIdLabel.setBounds(6, 95, 122, 14);
		activityBookingPanel.add(guestIdLabel);
		
		guestIdTextBox = new JTextField();
		guestIdTextBox.setColumns(10);
		guestIdTextBox.setBounds(91, 92, 138, 20);
		activityBookingPanel.add(guestIdTextBox);
		
		createActivityBookingButton = new JButton("Create");
		createActivityBookingButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String activityBookingStatus = "Made";
				if(dayComboBox.getSelectedItem() == null || monthComboBox.getSelectedItem() == null ||
						yearComboBox.getSelectedItem() == null || guestIdTextBox.getText().equals("") == true)
				{
					JOptionPane.showMessageDialog(null, "Please insert the guest id and date of booking.", "Error!", JOptionPane.ERROR_MESSAGE);
					clearActivityLinePanels();
					clearActivityLinesTable();
					activityLineDisabler();
				}
				else
				{
					guestId = Integer.parseInt(guestIdTextBox.getText());
					String day = (String) dayComboBox.getSelectedItem();
					String month = (String) monthComboBox.getSelectedItem();
					String year = (String) yearComboBox.getSelectedItem();
					String inputDate = day + "-" + month + "-" + year;
					String insertDate = year + "-" + month + "-" + day;
					
					if(guestCtr.searchGuestById(guestId) == null)
					{
						JOptionPane.showMessageDialog(null, "There is no guest with the inserted id. Please insert a valid guest id.", "Error!", JOptionPane.ERROR_MESSAGE);
						clearActivityLinePanels();
						clearActivityLinesTable();
						activityLineDisabler();
					}
					else
					{
						if(DateCheck.isDateValid(inputDate) != true)
						{
							JOptionPane.showMessageDialog(null, "Inserted date is incorrect. Please insert a valid date", "Error!", JOptionPane.ERROR_MESSAGE);
							clearActivityLinePanels();
							clearActivityLinesTable();
							activityLineDisabler();
						}
						else
						{
							if(dateCheck.checkIfDateIsOlder(inputDate) != true)
							{
								JOptionPane.showMessageDialog(null, "Cannot insert a date older than the current date.", "Error!", JOptionPane.ERROR_MESSAGE);
								clearActivityLinePanels();
								clearActivityLinesTable();
								activityLineDisabler();
							}
							else
							{
								if(activityBookingCtr.activityBookingCompleteCheck(insertDate, guestId) != true)
								{
									JOptionPane.showMessageDialog(null, "Cannot make two bookings on the same date.", "Error!", JOptionPane.ERROR_MESSAGE);
									clearActivityLinePanels();
									clearActivityLinesTable();
									activityLineDisabler();
								}
								else
								{
									activityBookingCtr.insertActivityBooking(guestId, insertDate, activityBookingStatus);
									JOptionPane.showMessageDialog(null, "Activity booking made successfully. You can add up to four activities.", "Info", JOptionPane.INFORMATION_MESSAGE);
									
									ActivityBooking activityBookingObj = new ActivityBooking();
									activityBookingObj = activityBookingCtr.getActivityBookingForDate(guestId, insertDate, activityBookingStatus);
									bookingId = activityBookingObj.getId();
									selectedDateTextField.setText(inputDate);
									clearActivityBookingTable();
									activityBookingsTable.setModel(getActivityBookingTableModel());
									clearActivityLinePanels();
									clearActivityLinesTable();
									activityLineEnabler();
								}
							}
						}
					}
				}
			}
		});
		createActivityBookingButton.setBounds(4, 120, 225, 25);
		activityBookingPanel.add(createActivityBookingButton);
		createActivityBookingButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton searchActivityBookingButton = new JButton("Activate activity lines");
		searchActivityBookingButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(dayComboBox.getSelectedItem() == null || monthComboBox.getSelectedItem() ==null ||
						yearComboBox.getSelectedItem() == null || guestIdTextBox.getText().equals("") == true)
				{
					JOptionPane.showMessageDialog(null, "Please insert the guest id and date of booking.", "Error!", JOptionPane.ERROR_MESSAGE);
					clearActivityLinePanels();
					clearActivityLinesTable();
					activityLineDisabler();
				}
				else
				{
					guestId = Integer.parseInt(guestIdTextBox.getText());
					String day = (String) dayComboBox.getSelectedItem();
					String month = (String) monthComboBox.getSelectedItem();
					String year = (String) yearComboBox.getSelectedItem();
					String inputDate = day + "-" + month + "-" + year;
					String insertDate = year + "-" + month + "-" + day;
					
					if(guestCtr.searchGuestById(guestId) == null)
					{
						JOptionPane.showMessageDialog(null, "There is no guest with the inserted id. Please insert a valid guest id.", "Error!", JOptionPane.ERROR_MESSAGE);
						clearActivityLinePanels();
						clearActivityLinesTable();
						activityLineDisabler();
					}
					else
					{
						if(DateCheck.isDateValid(inputDate) != true)
						{
							JOptionPane.showMessageDialog(null, "Inserted date is incorrect. Please insert a valid date", "Error!", JOptionPane.ERROR_MESSAGE);
							clearActivityLinePanels();
							clearActivityLinesTable();
							activityLineDisabler();
						}
						else
						{
							ActivityBooking activityBookingObj = new ActivityBooking();
							activityBookingObj = activityBookingCtr.getActivityBookingForDate(guestId, insertDate, "Made");
							if(activityBookingObj == null)
							{
								JOptionPane.showMessageDialog(null, "There is no made activity booking by this date. Please insert a valid activity booking date.", "Error!", JOptionPane.ERROR_MESSAGE);
								clearActivityLinePanels();
								clearActivityLinesTable();
								activityLineDisabler();
							}
							else
							{
								activityLineEnabler();
								selectedDateTextField.setText(inputDate);
								bookingId = activityBookingObj.getId();
								clearActivityLinePanels();
								clearActivityLinesTable();
							}
						}
					}
				}
			}
		});
		searchActivityBookingButton.setBounds(6, 156, 225, 25);
		activityBookingPanel.add(searchActivityBookingButton);
		searchActivityBookingButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton getAllActivityBookingsButton = new JButton("All");
		getAllActivityBookingsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearActivityLinePanels();
				clearActivityLinesTable();
				activityLineDisabler();
				
				if(guestIdTextBox.getText().equals("") == true)
				{
					JOptionPane.showMessageDialog(null, "Please insert the id of the guest first", "Error!", JOptionPane.ERROR_MESSAGE);
					clearActivityBookingTable();
				}
				else
				{
					int insertedGuestId = Integer.parseInt(guestIdTextBox.getText());
					Guest guestObj = guestCtr.searchGuestById(insertedGuestId);
					if(guestObj == null)
					{
						JOptionPane.showMessageDialog(null, "The inserted id is not valid. Please insert a valid guest id.", "Error!", JOptionPane.ERROR_MESSAGE);
						clearActivityBookingTable();
					}
					else
					{
						LinkedList<ActivityBooking> guestActivityBookings = activityBookingCtr.getActivityBookingsForGuest(insertedGuestId);
						if(guestActivityBookings.isEmpty() == true)
						{
							JOptionPane.showMessageDialog(null, "There are no activity bookings", "Error!", JOptionPane.ERROR_MESSAGE);
							clearActivityBookingTable();
						}
						else
						{
							activityBookingList = guestActivityBookings;
							activityBookingsTable.setModel(getActivityBookingTableModel());
						}
					}
				}
			}
		});
		getAllActivityBookingsButton.setBounds(6, 192, 225, 25);
		activityBookingPanel.add(getAllActivityBookingsButton);
		getAllActivityBookingsButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JPanel activityPanel = new JPanel();
		activityPanel.setLayout(null);
		activityPanel.setBorder(new TitledBorder(null, "Activity", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityPanel.setBounds(10, 237, 237, 93);
		ActivityBookingPanel.add(activityPanel);
		
		JLabel activityLabel = new JLabel("Activity:");
		activityLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		activityLabel.setBounds(6, 19, 90, 14);
		activityPanel.add(activityLabel);
		
		allAvailableActivitiesComboBox = new JComboBox<String>();
		allAvailableActivitiesComboBox.setSelectedItem(null);
		LinkedList<ActivityType> allActivityTypeList = new LinkedList<ActivityType>();
		allActivityTypeList = activityCtr.getAllActivityTypes();
		if(allActivityTypeList.isEmpty() == false)
		{
			for(ActivityType activityTypeObj : allActivityTypeList)
			{
				String activityTypeName = activityTypeObj.getName();
				allAvailableActivitiesComboBox.addItem(activityTypeName);
			}
		}
		else
		{
			allAvailableActivitiesComboBox.removeAllItems();
		}
		
		allAvailableActivitiesComboBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				availableFacilityComboBox.removeAllItems();
				availableInstructorsComboBox.removeAllItems();
				getTeamsButton.setEnabled(true);
				
				if(allAvailableActivitiesComboBox.getSelectedItem() != null)
				{
					String activityName = (String) allAvailableActivitiesComboBox.getSelectedItem();
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = activityCtr.getActivityTypeByName(activityName);
					int maxNumberOfParticipants = activityTypeObj.getMaxParticipants();
					String stringMaxNumberOfParticipants = String.valueOf(maxNumberOfParticipants);
					maxParticipantsTextBox.setText(stringMaxNumberOfParticipants);
				}
				else
				{
					maxParticipantsTextBox.setText("");
				}
			}
		});
		allAvailableActivitiesComboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		allAvailableActivitiesComboBox.setEnabled(false);
		allAvailableActivitiesComboBox.setBounds(57, 16, 170, 20);
		activityPanel.add(allAvailableActivitiesComboBox);
		
		JLabel maxParticipantsLabel = new JLabel("Max participants:");
		maxParticipantsLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		maxParticipantsLabel.setBounds(6, 44, 90, 14);
		activityPanel.add(maxParticipantsLabel);
		
		maxParticipantsTextBox = new JTextField();
		maxParticipantsTextBox.setEditable(false);
		maxParticipantsTextBox.setColumns(10);
		maxParticipantsTextBox.setBounds(104, 41, 123, 20);
		activityPanel.add(maxParticipantsTextBox);
		
		JLabel startHourLabel = new JLabel("Start hour:");
		startHourLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		startHourLabel.setBounds(6, 69, 90, 14);
		activityPanel.add(startHourLabel);
		
		startHoursComboBox = new JComboBox<String>();
		startHoursComboBox.setSelectedItem(null);
		LinkedList<String> activityBookingHours = new LinkedList<String>();
		activityBookingHours = dateCheck.getStartHours();
		for(String hour : activityBookingHours)
		{
			String insertedHour = hour;
			startHoursComboBox.addItem(insertedHour);
		}
		startHoursComboBox.setSelectedItem(null);
		startHoursComboBox.setEnabled(false);
		startHoursComboBox.setBounds(104, 66, 123, 20);
		activityPanel.add(startHoursComboBox);
		
		JPanel facilityPanel = new JPanel();
		facilityPanel.setLayout(null);
		facilityPanel.setBorder(new TitledBorder(null, "Facility", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		facilityPanel.setBounds(10, 330, 237, 75);
		ActivityBookingPanel.add(facilityPanel);
		
		JLabel facilityLabel = new JLabel("Facility:");
		facilityLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		facilityLabel.setBounds(6, 19, 90, 14);
		facilityPanel.add(facilityLabel);
		
		availableFacilityComboBox = new JComboBox<String>();
		availableFacilityComboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		availableFacilityComboBox.setEnabled(false);
		availableFacilityComboBox.setBounds(57, 16, 170, 20);
		facilityPanel.add(availableFacilityComboBox);
		
		getAvailableFacilitiesButton = new JButton("Get facilities");
		getAvailableFacilitiesButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(allAvailableActivitiesComboBox.getSelectedItem() == null)
				{
					JOptionPane.showMessageDialog(null,  "Please insert the activity type first.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String activityName = (String) allAvailableActivitiesComboBox.getSelectedItem();
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = activityCtr.getActivityTypeByName(activityName);
					int activityId = activityTypeObj.getID();
					
					String desiredStatus = "Available";
					
					LinkedList<Facility> availableFacilitiesForActivity = new LinkedList<Facility>();
					availableFacilitiesForActivity = facilityCtr.getAvailableFacilitiesForActivity(activityId, desiredStatus);
					
					if(availableFacilitiesForActivity.isEmpty() == true)
					{
						JOptionPane.showMessageDialog(null, "No available facilities", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						for(Facility facilityObj : availableFacilitiesForActivity)
						{
							String facilityName = facilityObj.getName();
							availableFacilityComboBox.addItem(facilityName);
						}
					}
				}
			}
		});
		getAvailableFacilitiesButton.setFont(new Font("Arial", Font.PLAIN, 11));
		getAvailableFacilitiesButton.setEnabled(false);
		getAvailableFacilitiesButton.setBounds(6, 43, 225, 25);
		facilityPanel.add(getAvailableFacilitiesButton);
		
		JPanel instructorPanel = new JPanel();
		instructorPanel.setLayout(null);
		instructorPanel.setBorder(new TitledBorder(null, "Instructor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		instructorPanel.setBounds(10, 407, 237, 100);
		ActivityBookingPanel.add(instructorPanel);
		
		JLabel instructorNameLabel = new JLabel("Instructor:");
		instructorNameLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorNameLabel.setBounds(6, 19, 90, 14);
		instructorPanel.add(instructorNameLabel);
		
		availableInstructorsComboBox = new JComboBox<String>();
		availableInstructorsComboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		availableInstructorsComboBox.setEnabled(false);
		availableInstructorsComboBox.setBounds(60, 16, 170, 20);
		instructorPanel.add(availableInstructorsComboBox);
		
		getInstructorsButton = new JButton("Get instructors");
		getInstructorsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(allAvailableActivitiesComboBox.getSelectedItem() == null)
				{
					JOptionPane.showMessageDialog(null,  "Please insert the activity first.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String activityName = (String) allAvailableActivitiesComboBox.getSelectedItem();
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = activityCtr.getActivityTypeByName(activityName);
					int activityId = activityTypeObj.getID();
					
					String desiredStatus = "Available";
					
					LinkedList<Instructor> availableInstructorForActivity = new LinkedList<Instructor>();
					availableInstructorForActivity = instructorCtr.getAvailableInstructorsForActivityList(activityId, desiredStatus);
					
					if(availableInstructorForActivity.isEmpty() == true)
					{
						JOptionPane.showMessageDialog(null, "No available instructors.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						for(Instructor instructorObj : availableInstructorForActivity)
						{
							String instructorName = instructorObj.getName();
							availableInstructorsComboBox.addItem(instructorName);
						}
						
						activityBookingAllTeamsComboBox.setSelectedItem(null);
						activityBookingAllTeamsComboBox.removeAllItems();
						activityBookingAllTeamsComboBox.setEnabled(false);
						getTeamsButton.setEnabled(false);
					}
				}
			}
		});
		getInstructorsButton.setFont(new Font("Arial", Font.PLAIN, 11));
		getInstructorsButton.setEnabled(false);
		getInstructorsButton.setBounds(6, 40, 225, 25);
		instructorPanel.add(getInstructorsButton);
		
		instructorClearButton = new JButton("Clear");
		instructorClearButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				availableInstructorsComboBox.setSelectedItem(null);
				availableInstructorsComboBox.removeAllItems();
				
				activityBookingAllTeamsComboBox.setSelectedItem(null);
				activityBookingAllTeamsComboBox.removeAllItems();
				activityBookingAllTeamsComboBox.setEnabled(true);
				
				getTeamsButton.setEnabled(true);
			}
		});
		instructorClearButton.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorClearButton.setEnabled(false);
		instructorClearButton.setBounds(6, 68, 225, 25);
		instructorPanel.add(instructorClearButton);
		
		JPanel activityLinePanel = new JPanel();
		activityLinePanel.setLayout(null);
		activityLinePanel.setBorder(new TitledBorder(null, "Activity line", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityLinePanel.setBounds(708, 389, 237, 122);
		ActivityBookingPanel.add(activityLinePanel);
		
		addActivityLineButton = new JButton("Add");
		addActivityLineButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(selectedDateTextField.getText().equals("") == true || allAvailableActivitiesComboBox.getSelectedItem() == null ||
						startHoursComboBox.getSelectedItem() == null || availableFacilityComboBox.getSelectedItem() == null)
				{
					JOptionPane.showMessageDialog(null, "Please select the activity, facility, date and starting hour for the activity you wish to book.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					// 16-06-2013 01:00:00 000
					//01234567890
					String inputDate = selectedDateTextField.getText();
					String day = inputDate.substring(0, 2);
					String month = inputDate.substring(3, 5);
					String year = inputDate.substring(6, 10);
					
					/*
					String day = (String) dayComboBox.getSelectedItem();
					String month = (String) monthComboBox.getSelectedItem();
					String year = (String) yearComboBox.getSelectedItem();
					*/
					String startHour = (String) startHoursComboBox.getSelectedItem();
					
					//String inputDate = day + "-" + month + "-" + year;
					String insertDate = year + "-" + month + "-" + day;
					String time = day + "-" + month + "-" + year + " " + startHour;
					
					String activityName = (String) allAvailableActivitiesComboBox.getSelectedItem();
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = activityCtr.getActivityTypeByName(activityName);
					int activityId = activityTypeObj.getID();
					int maxNumberOfParticipants = activityTypeObj.getMaxParticipants();
					
					String facilityName = (String) availableFacilityComboBox.getSelectedItem();
					Facility facilityObj = new Facility();
					facilityObj = facilityCtr.getFacilityByName(facilityName);
					int facilityId = facilityObj.getId();
					
					Team teamObj = new Team();
					int teamId = 0;
					int numberOfParticipants = 0;
					if(activityBookingAllTeamsComboBox.getSelectedItem() != null)
					{
						String stringTeamId = (String) activityBookingAllTeamsComboBox.getSelectedItem();
						teamId = Integer.parseInt(stringTeamId);
						teamObj = teamCtr.getTeamById(teamId);
						numberOfParticipants = teamObj.getNumberOfParticipants();
					}
					
					Person personObj = new Person();
					int instructorId = 0;
					if(availableInstructorsComboBox.getSelectedItem() != null)
					{
						String instructorName = (String) availableInstructorsComboBox.getSelectedItem();
						personObj = personCtr.searchPersonByName(instructorName);
						instructorId = personObj.getId();
					}
					
					if(DateCheck.isDateValid(inputDate) != true)
					{
						JOptionPane.showMessageDialog(null, "Inserted date is incorrect. Please insert a valid date", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						if(dateCheck.checkIfTimeIsOlder(time) != true)
						{
							JOptionPane.showMessageDialog(null, "Cannot select a time before current time.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							if(activityBookingCtr.checkNumberOfActivityLinesInActivityBooking(bookingId, insertDate) != true)
							{
								JOptionPane.showMessageDialog(null, "Cannot book more than 4 activities per day.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								if(activityBookingCtr.checkActivityLineInstances1(activityId, bookingId, facilityId) != true)
								{
									JOptionPane.showMessageDialog(null, "Cannot book the same activity and facility more than once per day. Check already booked activities.", "Error!", JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									if(activityBookingCtr.checkActivityLineInstances3(activityId, insertDate, startHour, facilityId) != true)
									{
										JOptionPane.showMessageDialog(null, "An activity has already been booked on the selected start hour. Check schedule.", "Error!", JOptionPane.ERROR_MESSAGE);
									}
									else
									{
										if(activityBookingCtr.checkActivityLineInstances4(bookingId, insertDate, startHour) != true)
										{
											JOptionPane.showMessageDialog(null, "You have already booked an activity on the selected start hour. Check schedule.", "Error!", JOptionPane.ERROR_MESSAGE);
										}
										else
										{
											if(teamId != 0)
											{
												if(numberOfParticipants > maxNumberOfParticipants)
												{
													JOptionPane.showMessageDialog(null, "Too many participants in team. Check activity type maximum participants property.", "Error!", JOptionPane.ERROR_MESSAGE);
												}
												else
												{
													activityBookingCtr.insertTeamActivityLine(activityId, bookingId, insertDate, startHour, facilityId, teamId, "Made");
													JOptionPane.showMessageDialog(null, "Team activity successfully booked.", "Info", JOptionPane.INFORMATION_MESSAGE);
													clearActivityLinePanels();
													clearActivityLinesTable();
													activityLinesTable.setModel(getActivityLinesTableModel());
												}
											}
											if(instructorId != 0)
											{
												if(activityBookingCtr.checkActivityLineInstances2(insertDate, startHour, instructorId) != true)
												{
													JOptionPane.showMessageDialog(null, "Cannot book that instructor at the wanted timpe period. Check schedule.", "Error!", JOptionPane.ERROR_MESSAGE);
												}
												else
												{
													activityBookingCtr.insertInstructorActivityLine(activityId, bookingId, insertDate, startHour, facilityId, instructorId, "Made");
													JOptionPane.showMessageDialog(null, "Instructor activity successfully booked.", "Info", JOptionPane.INFORMATION_MESSAGE);
													clearActivityLinePanels();
													clearActivityLinesTable();
													activityLinesTable.setModel(getActivityLinesTableModel());
												}
											}
											if(teamId == 0 && instructorId == 0)
											{
												activityBookingCtr.insertSimpleActivityLine(activityId, bookingId, insertDate, startHour, facilityId, "Made");
												JOptionPane.showMessageDialog(null, "Activity successfully booked.", "Info", JOptionPane.INFORMATION_MESSAGE);
												clearActivityLinePanels();
												clearActivityLinesTable();
												activityLinesTable.setModel(getActivityLinesTableModel());
											}
										}
									}
								}
							}
						}
					}
				}
			}
		});
		addActivityLineButton.setFont(new Font("Arial", Font.PLAIN, 11));
		addActivityLineButton.setEnabled(false);
		addActivityLineButton.setBounds(6, 16, 225, 25);
		activityLinePanel.add(addActivityLineButton);
		
		cancelActivityLineButton = new JButton("Cancel activity");
		cancelActivityLineButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(dayComboBox.getSelectedItem() == null || monthComboBox.getSelectedItem() == null || yearComboBox.getSelectedItem() == null ||
						allAvailableActivitiesComboBox.getSelectedItem() == null || startHoursComboBox.getSelectedItem() == null || availableFacilityComboBox.getSelectedItem() == null)
				{
					JOptionPane.showMessageDialog(null, "Please select the activity, facility, date and starting hour for the activity you wish to book.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					//16-06-2013 01:00:00 000
					String day = (String) dayComboBox.getSelectedItem();
					String month = (String) monthComboBox.getSelectedItem();
					String year = (String) yearComboBox.getSelectedItem();
					String startHour = (String) startHoursComboBox.getSelectedItem();
					
					String inputDate = day + "-" + month + "-" + year;
					String time = day + "-" + month + "-" + year + " " + startHour;
					
					String activityName = (String) allAvailableActivitiesComboBox.getSelectedItem();
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = activityCtr.getActivityTypeByName(activityName);
					int activityId = activityTypeObj.getID();
					
					String facilityName = (String) availableFacilityComboBox.getSelectedItem();
					Facility facilityObj = new Facility();
					facilityObj = facilityCtr.getFacilityByName(facilityName);
					int facilityId = facilityObj.getId();
					
					if(DateCheck.isDateValid(inputDate) != true)
					{
						JOptionPane.showMessageDialog(null, "Inserted date is incorrect. Please insert a valid date", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						if(dateCheck.checkIfTimeIsOlder(time) != true)
						{
							JOptionPane.showMessageDialog(null, "Cannot select a time before current time.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							activityBookingCtr.updateActivityLine(activityId, bookingId, facilityId, "Canceled");
							JOptionPane.showMessageDialog(null, "Activity and facility booking has been canceled.");
							clearActivityLinePanels();
							clearActivityLinesTable();
							activityLinesTable.setModel(getActivityLinesTableModel());
						}
					}
				}
			}
		});
		cancelActivityLineButton.setFont(new Font("Arial", Font.PLAIN, 11));
		cancelActivityLineButton.setEnabled(false);
		cancelActivityLineButton.setBounds(6, 50, 225, 25);
		activityLinePanel.add(cancelActivityLineButton);
		
		allActivityLinesButton = new JButton("All");
		allActivityLinesButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearActivityLinePanels();
				clearActivityLinesTable();
				activityLinesTable.setModel(getActivityLinesTableModel());
			}
		});
		allActivityLinesButton.setFont(new Font("Arial", Font.PLAIN, 11));
		allActivityLinesButton.setEnabled(false);
		allActivityLinesButton.setBounds(6, 86, 225, 25);
		activityLinePanel.add(allActivityLinesButton);
		
		JPanel activityLinesTablePanel = new JPanel();
		activityLinesTablePanel.setLayout(null);
		activityLinesTablePanel.setBorder(new TitledBorder(null, "Activity lines", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityLinesTablePanel.setBounds(257, 237, 688, 150);
		ActivityBookingPanel.add(activityLinesTablePanel);
		
		JScrollPane activityLinesScrollPane = new JScrollPane();
		activityLinesScrollPane.setBounds(10, 21, 672, 116);
		activityLinesTablePanel.add(activityLinesScrollPane);
		
		activityLinesTable = new JTable();
		activityLinesTable.setFillsViewportHeight(true);
		activityLinesScrollPane.setViewportView(activityLinesTable);
		
		JPanel activityBookingTablePanel = new JPanel();
		activityBookingTablePanel.setLayout(null);
		activityBookingTablePanel.setBorder(new TitledBorder(null, "Activity bookings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityBookingTablePanel.setBounds(257, 11, 688, 215);
		ActivityBookingPanel.add(activityBookingTablePanel);
		
		JScrollPane activityBookingsScrollPane = new JScrollPane();
		activityBookingsScrollPane.setBounds(6, 16, 672, 188);
		activityBookingTablePanel.add(activityBookingsScrollPane);
		
		activityBookingsTable = new JTable();
		activityBookingsTable.setFillsViewportHeight(true);
		activityBookingsScrollPane.setViewportView(activityBookingsTable);
		
		JPanel teamPanel = new JPanel();
		teamPanel.setBounds(256, 407, 272, 100);
		ActivityBookingPanel.add(teamPanel);
		teamPanel.setLayout(null);
		teamPanel.setBorder(new TitledBorder(null, "Team", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel activityTeamIdLabel = new JLabel("Team:");
		activityTeamIdLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		activityTeamIdLabel.setBounds(6, 19, 90, 14);
		teamPanel.add(activityTeamIdLabel);
		
		activityBookingAllTeamsComboBox = new JComboBox<String>();
		activityBookingAllTeamsComboBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(activityBookingAllTeamsComboBox.getSelectedItem() != null)
				{
					String stringTeamId = (String) activityBookingAllTeamsComboBox.getSelectedItem();
					int teamId = Integer.parseInt(stringTeamId);
					Team teamObj = new Team();
					teamObj = teamCtr.getTeamById(teamId);
					int numberOfParticipants = teamObj.getNumberOfParticipants();
					String stringNumberOfParticipants = String.valueOf(numberOfParticipants);
					teamNumberOfParticipantsTextField.setText(stringNumberOfParticipants);
				}
				else
				{
					teamNumberOfParticipantsTextField.setText("");
				}
			}
		});
		activityBookingAllTeamsComboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		activityBookingAllTeamsComboBox.setEnabled(false);
		activityBookingAllTeamsComboBox.setBounds(60, 16, 65, 20);
		teamPanel.add(activityBookingAllTeamsComboBox);
		
		getTeamsButton = new JButton("Get teams");
		getTeamsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int guestID = guestId;
				
				LinkedList<Team> leaderListTeam = new LinkedList<Team>();
				leaderListTeam = teamCtr.getTeamsByLeaderId(guestID);
				if(leaderListTeam.isEmpty() == true)
				{
					JOptionPane.showMessageDialog(null, "There are no teams", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					for(Team teamObj : leaderListTeam)
					{
						int teamId = teamObj.getId();
						String stringTeamId = String.valueOf(teamId);
						
						activityBookingAllTeamsComboBox.addItem(stringTeamId);
					}
					
					availableInstructorsComboBox.setSelectedItem(null);
					availableInstructorsComboBox.removeAllItems();
					availableInstructorsComboBox.setEnabled(false);
					
					getInstructorsButton.setEnabled(false);
				}
			}
		});
		getTeamsButton.setFont(new Font("Arial", Font.PLAIN, 11));
		getTeamsButton.setEnabled(false);
		getTeamsButton.setBounds(6, 40, 256, 25);
		teamPanel.add(getTeamsButton);
		
		teamClearButton = new JButton("Clear");
		teamClearButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				activityBookingAllTeamsComboBox.setSelectedItem(null);
				activityBookingAllTeamsComboBox.removeAllItems();
				
				availableInstructorsComboBox.setSelectedItem(null);
				availableInstructorsComboBox.removeAllItems();
				availableInstructorsComboBox.setEnabled(true);
				
				getInstructorsButton.setEnabled(true);
			}
		});
		teamClearButton.setFont(new Font("Arial", Font.PLAIN, 11));
		teamClearButton.setEnabled(false);
		teamClearButton.setBounds(6, 68, 256, 25);
		teamPanel.add(teamClearButton);
		
		JLabel teamNumberOfParticipantsLabel = new JLabel("Participants:");
		teamNumberOfParticipantsLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		teamNumberOfParticipantsLabel.setBounds(135, 19, 90, 14);
		teamPanel.add(teamNumberOfParticipantsLabel);
		
		teamNumberOfParticipantsTextField = new JTextField();
		teamNumberOfParticipantsTextField.setEditable(false);
		teamNumberOfParticipantsTextField.setColumns(10);
		teamNumberOfParticipantsTextField.setBounds(198, 16, 65, 20);
		teamPanel.add(teamNumberOfParticipantsTextField);
		
		JButton activityBookingScheduleButton = new JButton("Activity schedule");
		activityBookingScheduleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ScheduleMenu scheduleMenu = new ScheduleMenu();
				scheduleMenu.frame.setVisible(true);
			}
		});
		activityBookingScheduleButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		activityBookingScheduleButton.setBounds(663, 572, 172, 39);
		frame.getContentPane().add(activityBookingScheduleButton);
		
		
	}
	
	private void putValuesOnTheScreen(RoomBooking rbObj)
	{
		txtRoomBookingId.setText(String.valueOf(rbObj.getId()));
		txtDd.setText(String.valueOf(rbObj.getArrivalDate()).substring(6, 8));
		txtMm.setText(String.valueOf(rbObj.getArrivalDate()).substring(4, 6));
		txtYyyy.setText(String.valueOf(rbObj.getArrivalDate()).substring(0, 4));
		txtDepDD.setText(String.valueOf(rbObj.getDepartureDate()).substring(6, 8));
		txtDepmm.setText(String.valueOf(rbObj.getDepartureDate()).substring(4, 6));
		txtDepyyyy.setText(String.valueOf(rbObj.getDepartureDate()).substring(0, 4));
		txtNumberOfChildren.setText(String.valueOf(rbObj.getNumberOfChildren()));
		txtStatus.setText(rbObj.getStatus());
	}

	private void clearValues()
	{
		txtRoomBookingId.setText(null);
		txtDd.setText(null);
		txtMm.setText(null);
		txtYyyy.setText(null);
		txtDepDD.setText(null);
		txtDepmm.setText(null);
		txtDepyyyy.setText(null);
		txtNumberOfChildren.setText(null);
		txtStatus.setText(null);
		
		DefaultTableModel tdm=(DefaultTableModel)table.getModel();
		tdm.getDataVector().removeAllElements();
		tdm.fireTableDataChanged();
	}
	
	public void activityLineEnabler()
	{
		allAvailableActivitiesComboBox.setSelectedItem(null);
		allAvailableActivitiesComboBox.setEnabled(true);
		startHoursComboBox.setSelectedItem(null);
		startHoursComboBox.setEnabled(true);
		
		availableFacilityComboBox.setSelectedItem(null);
		availableFacilityComboBox.setEnabled(true);
		getAvailableFacilitiesButton.setEnabled(true);
		
		availableInstructorsComboBox.setSelectedItem(null);
		availableInstructorsComboBox.setEnabled(true);
		getInstructorsButton.setEnabled(true);
		instructorClearButton.setEnabled(true);
		
		activityBookingAllTeamsComboBox.setSelectedItem(null);
		activityBookingAllTeamsComboBox.setEnabled(true);
		getTeamsButton.setEnabled(true);
		teamClearButton.setEnabled(true);
		
		addActivityLineButton.setEnabled(true);
		cancelActivityLineButton.setEnabled(true);
		allActivityLinesButton.setEnabled(true);
	}
	
	public void activityLineDisabler()
	{
		allAvailableActivitiesComboBox.setSelectedItem(null);
		allAvailableActivitiesComboBox.setEnabled(false);
		startHoursComboBox.setSelectedItem(null);
		startHoursComboBox.setEnabled(false);
		
		availableFacilityComboBox.setSelectedItem(null);
		availableFacilityComboBox.setEnabled(false);
		getAvailableFacilitiesButton.setEnabled(false);
		
		availableInstructorsComboBox.setSelectedItem(null);
		availableInstructorsComboBox.setEnabled(false);
		getInstructorsButton.setEnabled(false);
		instructorClearButton.setEnabled(false);
		
		activityBookingAllTeamsComboBox.setSelectedItem(null);
		activityBookingAllTeamsComboBox.setEnabled(false);
		getTeamsButton.setEnabled(false);
		teamClearButton.setEnabled(false);
		
		addActivityLineButton.setEnabled(false);
		cancelActivityLineButton.setEnabled(false);
		allActivityLinesButton.setEnabled(false);
	}
	
	private void clearActivityLinePanels()
	{
		startHoursComboBox.setSelectedItem(null);
		
		availableFacilityComboBox.removeAllItems();
		availableFacilityComboBox.setSelectedItem(null);
		
		availableInstructorsComboBox.removeAllItems();
		availableInstructorsComboBox.setSelectedItem(null);
		getInstructorsButton.setEnabled(true);
		instructorClearButton.setEnabled(true);
		
		activityBookingAllTeamsComboBox.removeAllItems();
		activityBookingAllTeamsComboBox.setSelectedItem(null);
		teamNumberOfParticipantsTextField.setText("");
		getTeamsButton.setEnabled(true);
		teamClearButton.setEnabled(true);
	}
	
	private void clearActivityLinesTable()
	{
		activityLinesTable.setCellSelectionEnabled(false);
		activityLinesTable.setModel(new DefaultTableModel());
	}
	
	private void clearActivityBookingTable()
	{
		activityBookingsTable.setCellSelectionEnabled(false);
		activityBookingsTable.setModel(new DefaultTableModel());
	}
	
	private DefaultTableModel getActivityBookingTableModel()
	{
		//activityBookingList = activityBookingCtr.getActivityBookingsForGuest(guestId);
				
		DefaultTableModel activityBookingTableModel = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column)
			{
				//all cells false
				return false;
			}
		};
		
		activityBookingTableModel.setColumnIdentifiers(new String[] {"BookingId", "Date", "Status"});
		
		try
		{
			for(ActivityBooking activityBookingObj : activityBookingList)
			{
				activityBookingTableModel.addRow(new String[]
						{
						String.valueOf(activityBookingObj.getId()),
						activityBookingObj.getDate(),
						activityBookingObj.getStatus()
						});
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception: " + e);
		}
		
		activityBookingsTable.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = activityBookingsTable.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				ActivityBooking activityBookingObj = new ActivityBooking();
				
				int selectedRow = activityBookingsTable.getSelectedRow();
				if(selectedRow > -1)
				{
					String stringBookingId = (String) activityBookingsTable.getValueAt(selectedRow, 0);
					bookingId = Integer.parseInt(stringBookingId);
					
					activityBookingObj = activityBookingCtr.getActivityBookingById(bookingId);
					
					String date = activityBookingObj.getDate();
					String day = date.substring(3,5);
					String month = date.substring(0,2);
					String year = date.substring(6,10);
					String outputDate = day + "-" + month + "-" + year;
					
					selectedDateTextField.setText(outputDate);
					dayComboBox.setSelectedItem(day);
					monthComboBox.setSelectedItem(month);
					yearComboBox.setSelectedItem(year);
					bookingId = activityBookingObj.getId();
					clearActivityLinePanels();
					clearActivityLinesTable();
					activityLineDisabler();
				}
			}
		});
		
		
		return activityBookingTableModel;
	}
	
	private DefaultTableModel getActivityLinesTableModel()
	{
		LinkedList<ActivityLine> bookingActivityLines = new LinkedList<ActivityLine>();
		bookingActivityLines = activityBookingCtr.getActivityLinesForActivityBooking(bookingId);
		
		DefaultTableModel activityLinesTableModel = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column)
			{
				//all cells false
				return false;
			}
		};
		
		activityLinesTableModel.setColumnIdentifiers(new String[] {"Booking id" ,"Activity", "Facility", "StartHour", "Instructor", "Team"});
		
		for(ActivityLine activityLineObj : bookingActivityLines)
		{
			String instructorName = new String("");
			if(activityLineObj.getInstructor() != null)
			{
				instructorName = activityLineObj.getInstructor().getName();
			}
			
			String stringTeamId = new String("");
			if(activityLineObj.getTeam() != null)
			{
				stringTeamId = String.valueOf(activityLineObj.getTeam().getId());
			}
			
			activityLinesTableModel.addRow(new String[]
					{
					String.valueOf(activityLineObj.getActivityBooking().getId()),
					activityLineObj.getActivity().getName(),
					activityLineObj.getFacility().getName(),
					activityLineObj.getStartHour(),
					instructorName,
					stringTeamId
					});
		}
		
		activityLinesTable.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = activityLinesTable.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				
				int selectedRow = activityLinesTable.getSelectedRow();
				if(selectedRow > -1)
				{
					//String stringBookingID = (String) activityLinesTable.getValueAt(selectedRow, 0);
					String stringActivityName = (String) activityLinesTable.getValueAt(selectedRow, 1);
					String stringFacilityName = (String) activityLinesTable.getValueAt(selectedRow, 2);
					String stringStartHour = (String) activityLinesTable.getValueAt(selectedRow, 3);
					String stringInstructorName = (String) activityLinesTable.getValueAt(selectedRow, 4);
					String stringTeamId = (String) activityLinesTable.getValueAt(selectedRow, 5);
					
					ActivityType activityTypeObj = activityCtr.getActivityTypeByName(stringActivityName);
					String stringMaxNumberOfParticipants = String.valueOf(activityTypeObj.getMaxParticipants());
					
					allAvailableActivitiesComboBox.setSelectedItem(stringActivityName);
					maxParticipantsTextBox.setText(stringMaxNumberOfParticipants);
					
					availableFacilityComboBox.removeAllItems();
					availableFacilityComboBox.addItem(stringFacilityName);
					availableFacilityComboBox.setSelectedItem(stringFacilityName);
					
					startHoursComboBox.setSelectedItem(stringStartHour);
					
					if(stringInstructorName.equals("") == true)
					{
						availableInstructorsComboBox.removeAllItems();
						availableInstructorsComboBox.setSelectedItem(null);
					}
					else
					{
						availableInstructorsComboBox.removeAllItems();
						availableInstructorsComboBox.addItem(stringInstructorName);
						availableInstructorsComboBox.setSelectedItem(stringInstructorName);
					}
					
					if(stringTeamId.equals("") == true)
					{
						activityBookingAllTeamsComboBox.removeAllItems();
						activityBookingAllTeamsComboBox.setSelectedItem(null);
					}
					else
					{
						activityBookingAllTeamsComboBox.removeAllItems();
						activityBookingAllTeamsComboBox.addItem(stringTeamId);
						activityBookingAllTeamsComboBox.setSelectedItem(stringTeamId);
					}
				}
			}
		});
		return activityLinesTableModel;
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
	
	public DefaultTableModel getGuestTableModel()
	{
		LinkedList<Guest> completeGuestList = new LinkedList<Guest>();
		completeGuestList = guestCtr.getAllGuests();
		
		DefaultTableModel guestTableModel = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			
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
}
