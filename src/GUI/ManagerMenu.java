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
import javax.swing.JTextPane;
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
import Model.Employee;
import Model.Facility;
import Model.Guest;
import Model.Instructor;
import Model.Location;
import Model.Person;
import Model.Room;
import Model.RoomType;
import Model.TravelAgency;
//import com.jgoodies.forms.factories.DefaultComponentFactory;

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
	private JTable employeeTable;
	private JTextField employeeIdTextField;
	private JTextField employeeNameTextField;
	private JTextField employeeZipcodeTextField;
	private JTextField employeeCountryTextField;
	private JTextField employeeCityTextField;
	private JTextField employeeAddressTextField;
	private JTextField employeePhoneNoTextField;
	private JTextField employeeEmailTextField;
	private JTextField employeePasswordTextField;
	private JTextField employeeSalaryTextField;
	private JTextField txtNumber;
	private JTextField txtRoomType;
	private JTextField txtCategory;
	private JTextField txtPrice;
	private JTable roomTable;
	private JScrollPane roomScrollPane;
	
	public ManagerMenu()
	{
		initialize();
		frame.setVisible(true);
	}
	
	private void clearTable()
	{
		DefaultTableModel tdm=(DefaultTableModel)travelAgencyTable.getModel();
		tdm.getDataVector().removeAllElements();
		tdm.fireTableDataChanged();
	}
	
	private void clearValues()
	{
		travelAgencyCVRTextField.setText("");
		travelAgencyNameTextField.setText("");
		travelAgencyZipcodeTextField.setText("");
		travelAgencyAddressTextField.setText("");
		travelAgencyCountryTextField.setText("");
		travelAgencyCityTextField.setText("");
		travelAgencyEmailTextField.setText("");
		travelAgencyPhoneNoTextField.setText("");
		
	}

	private void initialize() 
	{
		frame = new JFrame("Manager menu");
		frame.setFont(new Font("Dialog", Font.PLAIN, 14));
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 960, 490);
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
		guestAttributesPanel.setBounds(10, 11, 935, 93);
		GuestPanel.add(guestAttributesPanel);
		guestAttributesPanel.setLayout(null);
		
		JLabel guestIdLabel = new JLabel("Id:");
		guestIdLabel.setBounds(16, 19, 46, 14);
		guestAttributesPanel.add(guestIdLabel);
		guestIdLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestIdTextField = new JTextField();
		guestIdTextField.setBounds(70, 16, 100, 20);
		guestAttributesPanel.add(guestIdTextField);
		guestIdTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestIdTextField.setColumns(10);
		
		JLabel guestNameLabel = new JLabel("Name:");
		guestNameLabel.setBounds(186, 19, 46, 14);
		guestAttributesPanel.add(guestNameLabel);
		guestNameLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestNameTextField = new JTextField();
		guestNameTextField.setBounds(239, 16, 100, 20);
		guestAttributesPanel.add(guestNameTextField);
		guestNameTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestNameTextField.setColumns(10);
		
		JLabel guestCityLabel = new JLabel("City:");
		guestCityLabel.setBounds(349, 19, 80, 14);
		guestAttributesPanel.add(guestCityLabel);
		guestCityLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestCityTextField = new JTextField();
		guestCityTextField.setBounds(387, 16, 100, 20);
		guestAttributesPanel.add(guestCityTextField);
		guestCityTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestCityTextField.setColumns(10);
		
		JLabel guestCountryLabel = new JLabel("Country:");
		guestCountryLabel.setBounds(497, 19, 80, 14);
		guestAttributesPanel.add(guestCountryLabel);
		guestCountryLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestCountryTextField = new JTextField();
		guestCountryTextField.setBounds(559, 16, 100, 20);
		guestAttributesPanel.add(guestCountryTextField);
		guestCountryTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestCountryTextField.setColumns(10);
		
		JLabel guestZipcodeLabel = new JLabel("Zipcode:");
		guestZipcodeLabel.setBounds(669, 19, 80, 14);
		guestAttributesPanel.add(guestZipcodeLabel);
		guestZipcodeLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestZipcodeTextField = new JTextField();
		guestZipcodeTextField.setBounds(739, 16, 100, 20);
		guestAttributesPanel.add(guestZipcodeTextField);
		guestZipcodeTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestZipcodeTextField.setColumns(10);
		
		JLabel guestAddressLabel = new JLabel("Address:");
		guestAddressLabel.setBounds(16, 44, 86, 14);
		guestAttributesPanel.add(guestAddressLabel);
		guestAddressLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestAddressTextField = new JTextField();
		guestAddressTextField.setBounds(70, 41, 100, 20);
		guestAttributesPanel.add(guestAddressTextField);
		guestAddressTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestAddressTextField.setColumns(10);
		
		JLabel guestPhoneNoLabel = new JLabel("Phone no:");
		guestPhoneNoLabel.setBounds(186, 44, 80, 14);
		guestAttributesPanel.add(guestPhoneNoLabel);
		guestPhoneNoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestPhoneNoTextField = new JTextField();
		guestPhoneNoTextField.setBounds(239, 41, 100, 20);
		guestAttributesPanel.add(guestPhoneNoTextField);
		guestPhoneNoTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestPhoneNoTextField.setColumns(10);
		
		JLabel guestEmailLabel = new JLabel("E-mail:");
		guestEmailLabel.setBounds(349, 44, 80, 14);
		guestAttributesPanel.add(guestEmailLabel);
		guestEmailLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestEmailTextField = new JTextField();
		guestEmailTextField.setBounds(387, 41, 100, 20);
		guestAttributesPanel.add(guestEmailTextField);
		guestEmailTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestEmailTextField.setColumns(10);
		
		JLabel guestPasswordLabel = new JLabel("Password:");
		guestPasswordLabel.setBounds(497, 41, 80, 14);
		guestAttributesPanel.add(guestPasswordLabel);
		guestPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		guestPasswordTextField = new JTextField();
		guestPasswordTextField.setBounds(559, 41, 100, 20);
		guestAttributesPanel.add(guestPasswordTextField);
		guestPasswordTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		guestPasswordTextField.setColumns(10);
		
		JLabel guestGuestTypeLabel = new JLabel("Guest type:");
		guestGuestTypeLabel.setBounds(669, 44, 80, 14);
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
		guestGuestTypeComboBox.setBounds(739, 41, 100, 20);
		guestAttributesPanel.add(guestGuestTypeComboBox);
		
		JPanel guestOptionsMenu = new JPanel();
		guestOptionsMenu.setLayout(null);
		guestOptionsMenu.setBorder(new TitledBorder(null, "Guest", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		guestOptionsMenu.setBounds(6, 108, 140, 157);
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
		guestSearchButton.setBounds(6, 16, 124, 25);
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
		guestOptionsMenu.add(guestCreateButton);
		
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
		guestOptionsMenu.add(guestUpdateButton);
		
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
		guestAllButton.setBounds(6, 124, 124, 25);
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
		guestClearAllButton.setBounds(6, 268, 140, 34);
		GuestPanel.add(guestClearAllButton);
		
		JScrollPane guestTableScrollPane = new JScrollPane();
		guestTableScrollPane.setBounds(156, 107, 789, 344);
		GuestPanel.add(guestTableScrollPane);
		
		guestTable = new JTable();
		guestTable.setFillsViewportHeight(true);
		guestTableScrollPane.setViewportView(guestTable);
		
		JPanel TravelAgencyPanel = new JPanel();
		tabbedPane.addTab("Travel agency menu", null, TravelAgencyPanel, null);
		TravelAgencyPanel.setLayout(null);
		
		JPanel travelAgencyAttributesPanel = new JPanel();
		travelAgencyAttributesPanel.setBorder(new TitledBorder(null, "Travel agency attributes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		travelAgencyAttributesPanel.setBounds(10, 11, 646, 68);
		TravelAgencyPanel.add(travelAgencyAttributesPanel);
		travelAgencyAttributesPanel.setLayout(null);
		
		JLabel travelAgencyCVRLabel = new JLabel("CVR:");
		travelAgencyCVRLabel.setBounds(6, 19, 85, 14);
		travelAgencyAttributesPanel.add(travelAgencyCVRLabel);
		travelAgencyCVRLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyCVRTextField = new JTextField();
		travelAgencyCVRTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyCVRTextField.setBounds(57, 16, 100, 20);
		travelAgencyAttributesPanel.add(travelAgencyCVRTextField);
		travelAgencyCVRTextField.setColumns(10);
		
		JLabel travelAgencyNameLabel = new JLabel("Name:");
		travelAgencyNameLabel.setBounds(167, 19, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyNameLabel);
		travelAgencyNameLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyNameTextField = new JTextField();
		travelAgencyNameTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyNameTextField.setBounds(211, 16, 100, 20);
		travelAgencyAttributesPanel.add(travelAgencyNameTextField);
		travelAgencyNameTextField.setColumns(10);
		
		JLabel travelAgencyCityLabel = new JLabel("City:");
		travelAgencyCityLabel.setBounds(321, 19, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyCityLabel);
		travelAgencyCityLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyCityTextField = new JTextField();
		travelAgencyCityTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyCityTextField.setBounds(375, 16, 100, 20);
		travelAgencyAttributesPanel.add(travelAgencyCityTextField);
		travelAgencyCityTextField.setColumns(10);
		
		JLabel travelAgencyCountryLabel = new JLabel("Country:");
		travelAgencyCountryLabel.setBounds(485, 19, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyCountryLabel);
		travelAgencyCountryLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyCountryTextField = new JTextField();
		travelAgencyCountryTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyCountryTextField.setBounds(533, 16, 100, 20);
		travelAgencyAttributesPanel.add(travelAgencyCountryTextField);
		travelAgencyCountryTextField.setColumns(10);
		
		JLabel travelAgencyZipcodeLabel = new JLabel("Zipcode:");
		travelAgencyZipcodeLabel.setBounds(6, 44, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyZipcodeLabel);
		travelAgencyZipcodeLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyZipcodeTextField = new JTextField();
		travelAgencyZipcodeTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyZipcodeTextField.setBounds(57, 41, 100, 20);
		travelAgencyAttributesPanel.add(travelAgencyZipcodeTextField);
		travelAgencyZipcodeTextField.setColumns(10);
		
		JLabel travelAgencyAddressLabel = new JLabel("Address:");
		travelAgencyAddressLabel.setBounds(167, 44, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyAddressLabel);
		travelAgencyAddressLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyAddressTextField = new JTextField();
		travelAgencyAddressTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyAddressTextField.setBounds(211, 41, 100, 20);
		travelAgencyAttributesPanel.add(travelAgencyAddressTextField);
		travelAgencyAddressTextField.setColumns(10);
		
		JLabel travelAgencyPhoneNoLabel = new JLabel("Phone no:");
		travelAgencyPhoneNoLabel.setBounds(321, 44, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyPhoneNoLabel);
		travelAgencyPhoneNoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyPhoneNoTextField = new JTextField();
		travelAgencyPhoneNoTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyPhoneNoTextField.setBounds(375, 41, 100, 20);
		travelAgencyAttributesPanel.add(travelAgencyPhoneNoTextField);
		travelAgencyPhoneNoTextField.setColumns(10);
		
		JLabel travelAgencyEmailLabel = new JLabel("E-mail:");
		travelAgencyEmailLabel.setBounds(485, 44, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyEmailLabel);
		travelAgencyEmailLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyEmailTextField = new JTextField();
		travelAgencyEmailTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyEmailTextField.setBounds(533, 41, 100, 20);
		travelAgencyAttributesPanel.add(travelAgencyEmailTextField);
		travelAgencyEmailTextField.setColumns(10);
		
		JPanel travelAgencyOptionsPanel = new JPanel();
		travelAgencyOptionsPanel.setLayout(null);
		travelAgencyOptionsPanel.setBorder(new TitledBorder(null, "Travel agency", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		travelAgencyOptionsPanel.setBounds(10, 90, 146, 160);
		TravelAgencyPanel.add(travelAgencyOptionsPanel);
		
		JButton travelAgencySearchButton = new JButton("Search");
		travelAgencySearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(travelAgencyCVRTextField.getText().equals("")==true && travelAgencyNameTextField.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the wanted  Travel Agency.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(travelAgencyNameTextField.getText().equals("") == true)
					{
						String stringTravelAgencyCVR = travelAgencyCVRTextField.getText();
						int travelAgencyCVR = Integer.parseInt(stringTravelAgencyCVR);
						
						TravelAgency travelAgencyObj = new TravelAgency();
						travelAgencyObj = travelCtr.getTravelAgencyByCVR(travelAgencyCVR);
						
						if(travelAgencyObj == null)
						{
							JOptionPane.showMessageDialog(null, "There is no Travel Agency by this id. Please insert a valid  Travel Agency id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							
							String travelAgencyName = travelAgencyObj.getName();
							String travelAgencyAddress = travelAgencyObj.getAddress();
							String travelAgencyCountry = travelAgencyObj.getCountry();
							String travelAgencyPhoneNo = travelAgencyObj.getPhoneNo();
							String travelAgencyEmail = travelAgencyObj.getEmail();
							int travelAgencyZipCode = travelAgencyObj.getZipCode();
							String stringTravelAgencyZipCode = String.valueOf(travelAgencyZipCode);
							
							
							Location locationObj = new Location();
							locationObj = locationCtr.getLocation(travelAgencyZipCode, travelAgencyCountry);
							String travelAgencyCity = locationObj.getCity();
							
							travelAgencyCVRTextField.setText(stringTravelAgencyCVR);
							travelAgencyNameTextField.setText(travelAgencyName);
							travelAgencyAddressTextField.setText(travelAgencyAddress);
							travelAgencyCountryTextField.setText(travelAgencyCountry);
							travelAgencyPhoneNoTextField.setText(travelAgencyPhoneNo);
							travelAgencyEmailTextField.setText(travelAgencyEmail);
							travelAgencyZipcodeTextField.setText(stringTravelAgencyZipCode);
							travelAgencyCityTextField.setText(travelAgencyCity);
							
						}
					}
					else
					{
						if(travelAgencyCVRTextField.getText().equals("") == true)
						{
							String travelAgencyName = travelAgencyNameTextField.getText();
							
							TravelAgency travelAgencyObj = new TravelAgency();
							travelAgencyObj = travelCtr.getTravelAgencyByName(travelAgencyName);
							if(travelAgencyObj == null)
							{
								JOptionPane.showMessageDialog(null, "There is not Travel Agency by this name. Please insert a valid Travel Agency name.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								int travelAgencyCVR = travelAgencyObj.getCVR();
								String stringTravelAgencyCVR = String.valueOf(travelAgencyCVR);
								int travelAgencyZipCode = travelAgencyObj.getZipCode();
								String stringTravelAgencyZipCode = String.valueOf(travelAgencyZipCode);
								String travelAgencyAddress = travelAgencyObj.getAddress();
								String travelAgencyCountry = travelAgencyObj.getCountry();
								String travelAgencyPhoneNo = travelAgencyObj.getPhoneNo();
								String travelAgencyEmail = travelAgencyObj.getEmail();
								
								Location locationObj = new Location();
								locationObj = locationCtr.getLocation(travelAgencyZipCode, travelAgencyCountry);
								String travelAgencyCity = locationObj.getCity();
								
								travelAgencyCVRTextField.setText(stringTravelAgencyCVR);
								travelAgencyNameTextField.setText(travelAgencyName);
								travelAgencyAddressTextField.setText(travelAgencyAddress);
								travelAgencyCountryTextField.setText(travelAgencyCountry);
								travelAgencyPhoneNoTextField.setText(travelAgencyPhoneNo);
								travelAgencyEmailTextField.setText(travelAgencyEmail);
								travelAgencyZipcodeTextField.setText(stringTravelAgencyZipCode);
								travelAgencyCityTextField.setText(travelAgencyCity);
								
							}
						}
						else
						{
							if(travelAgencyCVRTextField.getText().equals("") != true && travelAgencyNameTextField.getText().equals("") != true)
							{
								String stringTravelAgencyCVR = travelAgencyCVRTextField.getText();
								int travelAgencyCVR = Integer.parseInt(stringTravelAgencyCVR);
								
								TravelAgency travelAgencyObj = new TravelAgency();
								travelAgencyObj = travelCtr.getTravelAgencyByCVR(travelAgencyCVR);
								
								if(travelAgencyObj == null)
								{
									JOptionPane.showMessageDialog(null, "There is no Travel Agency by this id. Please insert a valid Travel Agency id.", "Error!", JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									String travelAgencyName = travelAgencyObj.getName();
									String travelAgencyAddress = travelAgencyObj.getAddress();
									String travelAgencyCountry = travelAgencyObj.getCountry();
									String travelAgencyPhoneNo = travelAgencyObj.getPhoneNo();
									String travelAgencyEmail = travelAgencyObj.getEmail();
									int travelAgencyZipCode = travelAgencyObj.getZipCode();
									String stringTravelAgencyZipCode = String.valueOf(travelAgencyZipCode);
									
									Location locationObj = new Location();
									locationObj = locationCtr.getLocation(travelAgencyZipCode, travelAgencyCountry);
									String travelAgencyCity = locationObj.getCity();
									
									travelAgencyCVRTextField.setText(stringTravelAgencyCVR);
									travelAgencyNameTextField.setText(travelAgencyName);
									travelAgencyAddressTextField.setText(travelAgencyAddress);
									travelAgencyCountryTextField.setText(travelAgencyCountry);
									travelAgencyPhoneNoTextField.setText(travelAgencyPhoneNo);
									travelAgencyEmailTextField.setText(travelAgencyEmail);
									travelAgencyZipcodeTextField.setText(stringTravelAgencyZipCode);
									travelAgencyCityTextField.setText(travelAgencyCity);
									
								}
							}
						}
					}
				}
			}
		});
		travelAgencySearchButton.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencySearchButton.setBounds(6, 16, 134, 25);
		travelAgencyOptionsPanel.add(travelAgencySearchButton);
		
		JButton travelAgencyCreateButton = new JButton("Create");
		travelAgencyCreateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(travelAgencyCVRTextField.getText().equals("") == true || travelAgencyNameTextField.getText().equals("") == true || 
						travelAgencyZipcodeTextField.getText().equals("") == true || travelAgencyCountryTextField.getText().equals("") == true ||
						travelAgencyCityTextField.getText().equals("") == true || travelAgencyAddressTextField.getText().equals("") == true)
				{
					JOptionPane.showMessageDialog(null, "A travel agency attribute might be missing. Please insert all necessary travel agency attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String stringtTravelAgencyCVR = travelAgencyCVRTextField.getText();
					int travelAgencyCVR = Integer.parseInt(stringtTravelAgencyCVR);
					
					String travelAgencyName = travelAgencyNameTextField.getText();
					String travelAgencyAddress = travelAgencyAddressTextField.getText();
					
					String stringtravelAgencyZipcode = travelAgencyZipcodeTextField.getText();
					int travelAgencyZipcode = Integer.parseInt(stringtravelAgencyZipcode);
					
					String travelAgencyCountry = travelAgencyCountryTextField.getText();
					String travelAgencyCity = travelAgencyCityTextField.getText();
					String travelAgencyPhoneNo = travelAgencyPhoneNoTextField.getText();
					String travelAgencyEmail = travelAgencyEmailTextField.getText();
					
					if(travelCtr.getTravelAgencyByName(travelAgencyName) != null)
					{
						JOptionPane.showMessageDialog(null, "Cannot register the same travel agency twice.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						Location locationObj = locationCtr.getLocation(travelAgencyZipcode, travelAgencyCountry);
						if(locationObj == null)
						{
							locationCtr.insertLocation(travelAgencyZipcode, travelAgencyCountry, travelAgencyCity);
						}
						
						travelCtr.insertTravelAgency(travelAgencyCVR, travelAgencyName, travelAgencyZipcode, travelAgencyCountry, travelAgencyAddress, travelAgencyPhoneNo, travelAgencyEmail);
						clearTable();
						clearValues();
						
						guestTravelAgencyComboBox.removeAllItems();
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
						JOptionPane.showMessageDialog(null, "Travel agency has been successfully inserted.", "Info", JOptionPane.INFORMATION_MESSAGE);
						
						clearTravelAgencyPanel();
						clearTravelAgencyTable();
						travelAgencyTable.setModel(getTravelAgencyTableModel());
						
					}
				}
			}
		});
		travelAgencyCreateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyCreateButton.setBounds(6, 52, 134, 25);
		travelAgencyOptionsPanel.add(travelAgencyCreateButton);
		
		JButton travelAgencyUpdateButton = new JButton("Update");
		travelAgencyUpdateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(travelAgencyCVRTextField.getText().equals("") == true || travelAgencyNameTextField.getText().equals("") == true || 
						travelAgencyZipcodeTextField.getText().equals("") == true || travelAgencyCountryTextField.getText().equals("") == true
						|| travelAgencyCityTextField.getText().equals("") == true || travelAgencyAddressTextField.getText().equals("") == true)
				{
					JOptionPane.showMessageDialog(null, "Some travel agency attributes have not been inserted. Please insert all necessary travel agency attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(travelAgencyCVRTextField.getText().equals("") != true)
					{
						String stringtravelAgencyCVRTextField = travelAgencyCVRTextField.getText();
						int travelAgencyCVR = Integer.parseInt(stringtravelAgencyCVRTextField);
						TravelAgency travelAgencyObj = new TravelAgency();
						travelAgencyObj = travelCtr.getTravelAgencyByCVR(travelAgencyCVR);
						
						if(travelAgencyObj == null)
						{
							JOptionPane.showMessageDialog(null, "The wanted travel agency does not exist. Please check travel agency list.", "Error!", JOptionPane.ERROR_MESSAGE);
							
						}
						else
						{
							String travelAgencyName = travelAgencyNameTextField.getText();
							String travelAgencyAddress = travelAgencyAddressTextField.getText();
							
							String stringtravelAgencyZipcode = travelAgencyZipcodeTextField.getText();
							int travelAgencyZipcode = Integer.parseInt(stringtravelAgencyZipcode);
							
							String travelAgencyCountry = travelAgencyCountryTextField.getText();
							String travelAgencyCity = travelAgencyCityTextField.getText();					
							String travelAgencyPhoneNo = travelAgencyPhoneNoTextField.getText();
							String travelAgencyEmail = travelAgencyEmailTextField.getText();
							
							if(travelCtr.checkTravelAgencyInstanceCount(travelAgencyCVR, travelAgencyName, travelAgencyZipcode, travelAgencyCountry, travelAgencyAddress) == false)
							{
								JOptionPane.showMessageDialog(null, "You may not update with an already existing travel agency on this cvr.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								Location locationObj =  new Location();
								locationObj = locationCtr.getLocation(travelAgencyZipcode, travelAgencyCountry);
								if(locationObj == null)
								{
									locationCtr.insertLocation(travelAgencyZipcode, travelAgencyCountry, travelAgencyCity);
								}
								
								travelCtr.updateTravelAgency(travelAgencyCVR, travelAgencyName, travelAgencyZipcode, travelAgencyCountry, travelAgencyAddress, travelAgencyPhoneNo, travelAgencyEmail);
								clearTable();
								clearValues();
								
								guestTravelAgencyComboBox.removeAllItems();
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
								
								JOptionPane.showMessageDialog(null, "Travel agency updated successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
								clearTravelAgencyPanel();
								clearTravelAgencyTable();
								travelAgencyTable.setModel(getTravelAgencyTableModel());
							}
						}
					}
				}
			}
		});
		travelAgencyUpdateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyUpdateButton.setBounds(6, 88, 134, 25);
		travelAgencyOptionsPanel.add(travelAgencyUpdateButton);
		
		JButton travelAgencyAllButton = new JButton("All");
		travelAgencyAllButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					clearTravelAgencyPanel();
					clearTravelAgencyTable();
					
					travelAgencyTable.setModel(getTravelAgencyTableModel());
				}
		});
		travelAgencyAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyAllButton.setBounds(6, 124, 134, 25);
		travelAgencyOptionsPanel.add(travelAgencyAllButton);
		
		JScrollPane travelAgencyScrollPane = new JScrollPane();
		travelAgencyScrollPane.setBounds(166, 90, 779, 361);
		TravelAgencyPanel.add(travelAgencyScrollPane);
		
		travelAgencyTable = new JTable();
		travelAgencyTable.setFillsViewportHeight(true);
		travelAgencyScrollPane.setViewportView(travelAgencyTable);
		
		JButton travelAgencyClearAllButton = new JButton("Clear all");
		travelAgencyClearAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				clearTable();
				clearValues();
			}
		});
		travelAgencyClearAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyClearAllButton.setBounds(10, 259, 146, 35);
		TravelAgencyPanel.add(travelAgencyClearAllButton);
		//end travel agency panel
		
		//begin CRUD room panel
		JPanel RoomPanel = new JPanel();
		tabbedPane.addTab("Room menu", null, RoomPanel, null);
		RoomPanel.setLayout(null);
		
		JPanel panelRoom = new JPanel();
		panelRoom.setBorder(new TitledBorder(null, "Room", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelRoom.setBounds(10, 11, 278, 109);
		RoomPanel.add(panelRoom);
		panelRoom.setLayout(null);
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setBounds(10, 28, 55, 14);
		panelRoom.add(lblNumber);
		
		txtNumber = new JTextField();
		txtNumber.setBounds(75, 25, 86, 20);
		panelRoom.add(txtNumber);
		txtNumber.setColumns(10);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(10, 53, 46, 14);
		panelRoom.add(lblType);
		
		txtRoomType = new JTextField();
		txtRoomType.setBounds(75, 50, 86, 20);
		panelRoom.add(txtRoomType);
		txtRoomType.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeCtr empCtr = new EmployeeCtr();
				
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
				model2.setColumnIdentifiers(new String[] {"Room No", "Room type", "Room price"});
				
				
				if(!txtNumber.getText().equals(""))
				{
					int number = Integer.parseInt(txtNumber.getText());
					Room room = empCtr.getRoomByNumber(number);					
					
					try
					{
						model2.addRow(new String[]
						{
							String.valueOf(room.getNumber()),
							room.getRoomType().getCategory(),
							String.valueOf(room.getRoomType().getPrice())
						});
						
						roomTable.setModel(model2);
					}				
					catch(Exception ex)
					{
						System.out.println("Exception: " + ex);
					}
				}
				else if(!txtRoomType.getText().equals(""))
				{
					String type = txtRoomType.getText();
					LinkedList<Room> rooms = empCtr.getRoomsByType(type);
					
					try
					{
						for(Room room : rooms)
						{
							model2.addRow(new String[]
								{
									String.valueOf(room.getNumber()),
									room.getRoomType().getCategory(),
									String.valueOf(room.getRoomType().getPrice())
								});
						}
						roomTable.setModel(model2);
					}				
					catch(Exception ex)
					{
						System.out.println("Exception: " + ex);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please insert number or type of the room/s.", "Error!", JOptionPane.ERROR_MESSAGE);			
				}
			}
		});
		btnSearch.setBounds(171, 11, 89, 23);
		panelRoom.add(btnSearch);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				EmployeeCtr empCtr = new EmployeeCtr();
				
				if(txtNumber.getText().equals("") || txtRoomType.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please insert number and type of the room.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					int number = Integer.parseInt(txtNumber.getText());
					String type = txtRoomType.getText();
					
					if(empCtr.checkRoomType(type)==true)
					{
						empCtr.createRoom(number, type);
						JOptionPane.showMessageDialog(null, "Room successfully created.", "Success!", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Room type does not exist.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnCreate.setBounds(171, 49, 89, 23);
		panelRoom.add(btnCreate);
		
		JButton btnUpdate_1 = new JButton("Update");
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeCtr empCtr = new EmployeeCtr();
				
				if(txtNumber.getText().equals("") && txtRoomType.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please insert number and type of the room.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					int number = Integer.parseInt(txtNumber.getText());
					String type = txtRoomType.getText();
					
					empCtr.updateRoom(number, type);
					JOptionPane.showMessageDialog(null, "Room updated successfully.", "Success!", JOptionPane.INFORMATION_MESSAGE);
				}
				
				DefaultTableModel tdm=(DefaultTableModel)roomTable.getModel();
				tdm.getDataVector().removeAllElements();
				tdm.fireTableDataChanged();
			}
		});
		btnUpdate_1.setBounds(171, 75, 89, 23);
		panelRoom.add(btnUpdate_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Room Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(298, 11, 647, 109);
		RoomPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(10, 28, 67, 14);
		panel.add(lblCategory);
		
		txtCategory = new JTextField();
		txtCategory.setBounds(87, 25, 86, 20);
		panel.add(txtCategory);
		txtCategory.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(10, 53, 46, 14);
		panel.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(87, 50, 86, 20);
		panel.add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(200, 28, 72, 14);
		panel.add(lblDescription);
		
		final JTextPane txtDescription = new JTextPane();
		txtDescription.setBounds(282, 22, 259, 66);
		panel.add(txtDescription);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeCtr empCtr = new EmployeeCtr();
				
				if(txtCategory.getText().equals("") || txtPrice.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please insert category and price.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					float price = Float.parseFloat(txtPrice.getText());
					String category = txtCategory.getText();
					
					if(txtDescription.getText().equals(""))
					{
						empCtr.updateRoomType(category, null, price);
						JOptionPane.showMessageDialog(null, "Room type updated successfully.", "Success!", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						empCtr.updateRoomType(category, txtDescription.getText(), price);
						JOptionPane.showMessageDialog(null, "Room type updated successfully.", "Success!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnUpdate.setBounds(551, 76, 86, 23);
		panel.add(btnUpdate);
		
		JButton btnSearchRT = new JButton("Search");
		btnSearchRT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeCtr empCtr = new EmployeeCtr();
				
				if(!txtCategory.getText().equals(""))
				{
					String category = txtCategory.getText();
					RoomType rt = empCtr.getRoomType(category);
					
					txtDescription.setText(rt.getDescription());
					txtPrice.setText(String.valueOf(rt.getPrice()));
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please insert category.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSearchRT.setBounds(551, 11, 86, 23);
		panel.add(btnSearchRT);
		
		JButton btnCreate_1 = new JButton("Create");
		btnCreate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeCtr empCtr = new EmployeeCtr();
				
				if(txtCategory.getText().equals("") || txtPrice.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please insert category and price, at least.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String category = txtCategory.getText();
					String description = txtDescription.getText();
					float price = Float.parseFloat(txtPrice.getText());
					
					empCtr.createRoomType(category, description, price);
					JOptionPane.showMessageDialog(null, "Room type was successfully created.", "Success!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnCreate_1.setBounds(551, 44, 86, 23);
		panel.add(btnCreate_1);
		
		roomScrollPane = new JScrollPane();
		roomScrollPane.setBounds(10, 131, 935, 320);
		RoomPanel.add(roomScrollPane);
		
		roomTable = new JTable();
		roomScrollPane.setViewportView(roomTable);
		roomTable.setFillsViewportHeight(true);
		//end room
		
		JPanel ActivityTypePanel = new JPanel();
		tabbedPane.addTab("Activity type menu", null, ActivityTypePanel, null);
		ActivityTypePanel.setLayout(null);
		
		JPanel activityTypeAttributesPanel = new JPanel();
		activityTypeAttributesPanel.setBorder(new TitledBorder(null, "Activity type attributes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityTypeAttributesPanel.setBounds(10, 11, 636, 43);
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
		activityTypeNameTextField.setBounds(252, 16, 180, 20);
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
		activityTypesOptionsPanel.setBounds(10, 65, 150, 168);
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
						
						clearActivityTypePanel();
						clearActivityTypeTable();
						
						clearInstructorPanel();
						clearInstructorTable();
						
						clearFacilityPanel();
						clearFacilityTable();
						
						JOptionPane.showMessageDialog(null, "Activity type successfully inserted.", "Info", JOptionPane.INFORMATION_MESSAGE);
						
						activityTypeTable.setModel(getActivityTypeTableModel());
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
							
							activityTypeComboBox.removeAllItems();
							allActivityTypesList = activityTypeCtr.getAllActivityTypes();
							if(allActivityTypesList.isEmpty()==false)
							{
								for(ActivityType activityObj : allActivityTypesList)
								{
									String comboBoxItem = activityObj.getName();
									activityTypeComboBox.addItem(comboBoxItem);
								}
							}
							activityTypeComboBox.setSelectedItem(null);
							
							clearActivityTypePanel();
							clearActivityTypeTable();
							
							clearInstructorPanel();
							clearInstructorTable();
							
							clearFacilityPanel();
							clearFacilityTable();
							
							JOptionPane.showMessageDialog(null, "Activity type updated successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
							activityTypeTable.setModel(getActivityTypeTableModel());
						}
					}
				}
			}
		});
		activityTypeUpdateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		activityTypeUpdateButton.setBounds(6, 96, 134, 25);
		activityTypesOptionsPanel.add(activityTypeUpdateButton);
		
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
		activityTypeAllButton.setBounds(6, 132, 134, 25);
		activityTypesOptionsPanel.add(activityTypeAllButton);
		
		JScrollPane activityTypeTableScrollPane = new JScrollPane();
		activityTypeTableScrollPane.setBounds(170, 65, 775, 386);
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
		activityTypeClearAllButton.setBounds(10, 238, 150, 35);
		ActivityTypePanel.add(activityTypeClearAllButton);
		
		JPanel EmployeePanel = new JPanel();
		tabbedPane.addTab("Employee menu", null, EmployeePanel, null);
		EmployeePanel.setLayout(null);
		
		/*
		JLabel lblEmployeeAttributes = DefaultComponentFactory.getInstance().createTitle("Employee attributes");
		lblEmployeeAttributes.setBounds(10, 0, 122, 14);
		*/
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(213, 107, 732, 344);
		EmployeePanel.add(scrollPane);
		
		employeeTable = new JTable();
		employeeTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(employeeTable);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Employee", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 108, 193, 212);
		EmployeePanel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnEmployeeSearch = new JButton("Search");
		btnEmployeeSearch.setBounds(6, 16, 177, 29);
		panel_1.add(btnEmployeeSearch);
		
		JButton btnEmployeeCreate = new JButton("Create");
		btnEmployeeCreate.setBounds(6, 56, 177, 29);
		panel_1.add(btnEmployeeCreate);
		
		JButton btnEmployeeUpdate = new JButton("Update");
		btnEmployeeUpdate.setBounds(6, 96, 177, 29);
		panel_1.add(btnEmployeeUpdate);
		
		JButton btnEmployeeAll = new JButton("All");
		btnEmployeeAll.setBounds(6, 136, 177, 29);
		panel_1.add(btnEmployeeAll);
		
		JButton btnClearAll = new JButton("Clear All");
		btnClearAll.setBounds(6, 176, 177, 29);
		panel_1.add(btnClearAll);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 935, 79);
		EmployeePanel.add(panel_2);
		panel_2.setBorder(new TitledBorder(null, "Employee attributes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setLayout(null);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(6, 19, 46, 14);
		panel_2.add(lblId);
		
		employeeIdTextField = new JTextField();
		employeeIdTextField.setBounds(26, 16, 86, 20);
		panel_2.add(employeeIdTextField);
		employeeIdTextField.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(122, 19, 46, 14);
		panel_2.add(lblName);
		
		employeeNameTextField = new JTextField();
		employeeNameTextField.setBounds(156, 16, 86, 20);
		panel_2.add(employeeNameTextField);
		employeeNameTextField.setColumns(10);
		
		JLabel lblZipcode = new JLabel("Zipcode:");
		lblZipcode.setBounds(252, 19, 80, 14);
		panel_2.add(lblZipcode);
		
		employeeZipcodeTextField = new JTextField();
		employeeZipcodeTextField.setBounds(298, 16, 86, 20);
		panel_2.add(employeeZipcodeTextField);
		employeeZipcodeTextField.setColumns(10);
		
		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setBounds(394, 19, 73, 14);
		panel_2.add(lblCountry);
		
		employeeCountryTextField = new JTextField();
		employeeCountryTextField.setBounds(439, 16, 86, 20);
		panel_2.add(employeeCountryTextField);
		employeeCountryTextField.setColumns(10);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(535, 19, 46, 14);
		panel_2.add(lblCity);
		
		employeeCityTextField = new JTextField();
		employeeCityTextField.setBounds(562, 16, 86, 20);
		panel_2.add(employeeCityTextField);
		employeeCityTextField.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(658, 19, 86, 14);
		panel_2.add(lblAddress);
		
		employeeAddressTextField = new JTextField();
		employeeAddressTextField.setBounds(714, 16, 86, 20);
		panel_2.add(employeeAddressTextField);
		employeeAddressTextField.setColumns(10);
		
		JLabel lblPhoneNo = new JLabel("PhoneNo:");
		lblPhoneNo.setBounds(6, 51, 86, 14);
		panel_2.add(lblPhoneNo);
		
		employeePhoneNoTextField = new JTextField();
		employeePhoneNoTextField.setBounds(59, 48, 86, 20);
		panel_2.add(employeePhoneNoTextField);
		employeePhoneNoTextField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(156, 51, 86, 14);
		panel_2.add(lblEmail);
		
		employeeEmailTextField = new JTextField();
		employeeEmailTextField.setBounds(190, 48, 86, 20);
		panel_2.add(employeeEmailTextField);
		employeeEmailTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(282, 51, 80, 14);
		panel_2.add(lblPassword);
		
		employeePasswordTextField = new JTextField();
		employeePasswordTextField.setBounds(342, 48, 86, 20);
		panel_2.add(employeePasswordTextField);
		employeePasswordTextField.setColumns(10);
		
		JLabel lblSalary = new JLabel("Salary:");
		lblSalary.setBounds(439, 51, 64, 14);
		panel_2.add(lblSalary);
		
		employeeSalaryTextField = new JTextField();
		employeeSalaryTextField.setBounds(480, 48, 86, 20);
		panel_2.add(employeeSalaryTextField);
		employeeSalaryTextField.setColumns(10);
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				clearEmployeePanel();
				clearEmployeeTable();
			}
		});
		btnEmployeeAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearEmployeePanel();
				clearEmployeeTable();
				
				employeeTable.setModel(getEmployeeTableModel());
			}
		});
		btnEmployeeUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(employeeIdTextField.getText().equals("") == true || employeeNameTextField.getText().equals("") == true ||
						employeeCityTextField.getText().equals("") == true || employeeCountryTextField.getText().equals("") == true ||
						employeeZipcodeTextField.getText().equals("") == true || employeeAddressTextField.getText().equals("") == true ||
						employeeSalaryTextField.getText().equals("") == true || employeePasswordTextField.getText().equals("") == true )
				{
					JOptionPane.showMessageDialog(null, "An employee attribute might be missing. Please insert all needed employee attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String stringEmployeeId = employeeIdTextField.getText();
					int employeeId = Integer.parseInt(stringEmployeeId);
					
					Employee employeeObj = new Employee();
					employeeObj = employeeCtr.getEmployeeById(employeeId);	
				
					if(employeeObj == null)
					{
						JOptionPane.showMessageDialog(null, "The wanted employee does not exist in the system. Please check employee list.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						String employeeName = employeeNameTextField.getText();
						String employeeCiy = employeeCityTextField.getText();
						String employeeCountry = employeeCountryTextField.getText();
						String stringEmployeeZipcode = employeeZipcodeTextField.getText();
						int employeeZipcode = Integer.parseInt(stringEmployeeZipcode);
						String employeeAddress = employeeAddressTextField.getText();
						String employeePhoneNo = employeePhoneNoTextField.getText();
						String employeeEmail = employeeEmailTextField.getText();
						String employeePassword = employeePasswordTextField.getText();
						String stringEmployeeSalary = employeeSalaryTextField.getText();
						double employeeSalary = Double.parseDouble(stringEmployeeSalary);
						String personType = employeeObj.getPersonType();
						
						if(personCtr.checkPersonInstanceCount(employeeId, employeeName, employeeZipcode, employeeCountry, employeeAddress) == false)
						{
							JOptionPane.showMessageDialog(null, "You may not update with an already existing employee on this id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							Location locationObj =  new Location();
							locationObj = locationCtr.getLocation(employeeZipcode, employeeCountry);
							if(locationObj == null)
							{
								locationCtr.insertLocation(employeeZipcode, employeeCountry, employeeCiy);
							}
							personCtr.updatePerson(employeeId, employeeName, employeeAddress,employeeZipcode, employeeCountry, employeePhoneNo, employeeEmail, personType, employeePassword);
							employeeCtr.updateEmployee(employeeId, employeeSalary);
							JOptionPane.showMessageDialog(null, "Employee updated successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
							clearInstructorPanel();
							clearInstructorTable();
							clearEmployeePanel();
							clearEmployeeTable();
							employeeTable.setModel(getEmployeeTableModel());
						}
					}
				}
			}
		});
		btnEmployeeCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(employeeIdTextField.getText().equals("") == true || employeeNameTextField.getText().equals("") == true || employeeCityTextField.getText().equals("") == true ||
						employeeCountryTextField.getText().equals("") == true || employeeZipcodeTextField.getText().equals("") == true ||
								employeeAddressTextField.getText().equals("") == true || employeeSalaryTextField.getText().equals("") == true || employeePasswordTextField.getText().equals("") == true ||
								employeePhoneNoTextField.getText().equals("") == true || employeeEmailTextField.getText().equals("") == true)
				{
					JOptionPane.showMessageDialog(null, "An employee attribute might be missing. Please insert all needed employee attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String employeeName =  employeeNameTextField.getText();
					String employeeCiy =  employeeCityTextField.getText();
					String employeeCountry =  employeeCountryTextField.getText();
					String stringEmployeeZipcode =  employeeZipcodeTextField.getText();
					int employeeZipcode = Integer.parseInt(stringEmployeeZipcode);
					String employeeAddress =  employeeAddressTextField.getText();
					String employeePhoneNo =  employeePhoneNoTextField.getText();
					String employeeEmail =  employeeEmailTextField.getText();
					String employeePassword = employeePasswordTextField.getText();
					String stringEmployeeSalary = employeeSalaryTextField.getText();
					double employeeSalary = Double.parseDouble(stringEmployeeSalary);
					
					if(personCtr.searchPersonByName(employeeName) != null)
					{
						JOptionPane.showMessageDialog(null, "Cannot register the same employee twice.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						Location locationObj = locationCtr.getLocation( employeeZipcode,  employeeCountry);
						if(locationObj == null)
						{
							locationCtr.insertLocation( employeeZipcode,  employeeCountry, employeeCiy);
						}
						
						personCtr.insertPerson(employeeName, employeeAddress, employeeZipcode, employeeCountry, employeePhoneNo, employeeEmail, "Employee", employeePassword);
						employeeCtr.insertEmployee(employeeName, employeeSalary);
						
						JOptionPane.showMessageDialog(null, "Employee successfully inserted", "Info", JOptionPane.INFORMATION_MESSAGE);
						clearInstructorPanel();
						clearInstructorTable();
						clearEmployeePanel();
						clearEmployeeTable();
						employeeTable.setModel(getEmployeeTableModel());
					}
				}
			}
		});
		btnEmployeeSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(employeeIdTextField.getText().equals("")==true && employeeNameTextField.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the wanted employee.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(employeeNameTextField.getText().equals("") == true)
					{
						String stringEmployeeId = employeeIdTextField.getText();
						int employeeId = Integer.parseInt(stringEmployeeId);
						
						Employee employeeObj = new Employee();
						employeeObj = employeeCtr.getEmployeeById(employeeId);
						
						if(employeeObj == null)
						{
							JOptionPane.showMessageDialog(null, "There is no employee by this id. Please insert a valid employee id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							String employeeName = employeeObj.getName();
							String employeeCountry = employeeObj.getCountry();
							int employeeZipcode = employeeObj.getZipcode();
							String stringEmployeeZipcode = String.valueOf(employeeZipcode);
							String employeeAddress = employeeObj.getAddress();
							String employeePhoneNo = employeeObj.getPhoneNo();
							String employeeEmail = employeeObj.getEmail();
							String employeePassword = employeeObj.getPassword();
							double employeeSalary = employeeObj.getSalary();
							String stringEmployeeSalary = String.valueOf(employeeSalary);
							
							Location locationObj = new Location();
							locationObj = locationCtr.getLocation(employeeZipcode, employeeCountry);
							String employeeCity = locationObj.getCity();
							
							employeeIdTextField.setText(stringEmployeeId);
							employeeNameTextField.setText(employeeName);
							employeeCityTextField.setText(employeeCity);
							employeeCountryTextField.setText(employeeCountry);
							employeeZipcodeTextField.setText(stringEmployeeZipcode);
							employeeAddressTextField.setText(employeeAddress);
							employeePhoneNoTextField.setText(employeePhoneNo);
							employeeEmailTextField.setText(employeeEmail);
							employeePasswordTextField.setText(employeePassword);
							employeeSalaryTextField.setText(stringEmployeeSalary);
							
						}
					}
					else
					{
						if(employeeIdTextField.getText().equals("") == true)
						{
							String employeeName = employeeNameTextField.getText();
							
							Person personObj = new Person();
							personObj = personCtr.searchPersonByName(employeeName);
							
							if(personObj == null)
							{
								JOptionPane.showMessageDialog(null, "There is no employee by this name. Please insert a valid employee name.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								int employeeId = personObj.getId();
								Employee employeeObj = employeeCtr.getEmployeeById(employeeId);
								
								String stringEmployeeId = String.valueOf(employeeId);		
								String employeeCountry = employeeObj.getCountry();
								int employeeZipcode = employeeObj.getZipcode();
								String stringEmployeeZipcode = String.valueOf(employeeZipcode);
								String employeeAddress = employeeObj.getAddress();
								String employeePhoneNo = employeeObj.getPhoneNo();
								String employeeEmail = employeeObj.getEmail();
								String employeePassword = employeeObj.getPassword();
								double employeeSalary = employeeObj.getSalary();
								String stringEmployeeSalary = String.valueOf(employeeSalary);

								Location locationObj = new Location();
								locationObj = locationCtr.getLocation(employeeZipcode, employeeCountry);
								String employeeCity = locationObj.getCity();
								
								employeeIdTextField.setText(stringEmployeeId);
								employeeNameTextField.setText(employeeName);
								employeeCityTextField.setText(employeeCity);
								employeeCountryTextField.setText(employeeCountry);
								employeeZipcodeTextField.setText(stringEmployeeZipcode);
								employeeAddressTextField.setText(employeeAddress);
								employeePhoneNoTextField.setText(employeePhoneNo);
								employeeEmailTextField.setText(employeeEmail);
								employeePasswordTextField.setText(employeePassword);
								employeeSalaryTextField.setText(stringEmployeeSalary);
								
							}
						}
						else
						{
							if(employeeIdTextField.getText().equals("") != true && employeeNameTextField.getText().equals("") != true)
							{
								String stringEmployeeId = employeeIdTextField.getText();
								int employeeId = Integer.parseInt(stringEmployeeId);
								
								Employee employeeObj = new Employee();
								employeeObj = employeeCtr.getEmployeeById(employeeId);
								
								if(employeeObj == null)
								{
									JOptionPane.showMessageDialog(null, "There is no employee by this id. Please insert a valid employee id.", "Error!", JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									String employeeName = employeeObj.getName();
									String employeeCountry = employeeObj.getCountry();
									int employeeZipcode = employeeObj.getZipcode();
									String stringEmployeeZipcode = String.valueOf(employeeZipcode);
									String employeeAddress = employeeObj.getAddress();
									String employeePhoneNo = employeeObj.getPhoneNo();
									String employeeEmail = employeeObj.getEmail();
									String employeePassword = employeeObj.getPassword();
									double employeeSalary = employeeObj.getSalary();
									String stringEmployeeSalary = String.valueOf(employeeSalary);
									
									Location locationObj = new Location();
									locationObj = locationCtr.getLocation(employeeZipcode, employeeCountry);
									String employeeCity = locationObj.getCity();
									
									employeeIdTextField.setText(stringEmployeeId);
									employeeNameTextField.setText(employeeName);
									employeeCityTextField.setText(employeeCity);
									employeeCountryTextField.setText(employeeCountry);
									employeeZipcodeTextField.setText(stringEmployeeZipcode);
									employeeAddressTextField.setText(employeeAddress);
									employeePhoneNoTextField.setText(employeePhoneNo);
									employeeEmailTextField.setText(employeeEmail);
									employeePasswordTextField.setText(employeePassword);
									employeeSalaryTextField.setText(stringEmployeeSalary);
									
								}
							}
						}
					}
				}
			}
		});
		
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
		instructorNameTextField.setBounds(216, 16, 80, 20);
		instructorAttributePanel.add(instructorNameTextField);
		
		JLabel instructorCityLabel = new JLabel("City:");
		instructorCityLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorCityLabel.setBounds(306, 22, 86, 14);
		instructorAttributePanel.add(instructorCityLabel);
		
		instructorCityTextField = new JTextField();
		instructorCityTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorCityTextField.setColumns(10);
		instructorCityTextField.setBounds(348, 19, 80, 20);
		instructorAttributePanel.add(instructorCityTextField);
		
		JLabel instructorCountryLabel = new JLabel("Country:");
		instructorCountryLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorCountryLabel.setBounds(438, 22, 86, 14);
		instructorAttributePanel.add(instructorCountryLabel);
		
		instructorCountryTextField = new JTextField();
		instructorCountryTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorCountryTextField.setColumns(10);
		instructorCountryTextField.setBounds(507, 19, 80, 20);
		instructorAttributePanel.add(instructorCountryTextField);
		
		JLabel instructorZipcodeLabel = new JLabel("Zipcode:");
		instructorZipcodeLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorZipcodeLabel.setBounds(597, 22, 86, 14);
		instructorAttributePanel.add(instructorZipcodeLabel);
		
		instructorZipcodeTextField = new JTextField();
		instructorZipcodeTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorZipcodeTextField.setColumns(10);
		instructorZipcodeTextField.setBounds(658, 19, 80, 20);
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
		instructorPhoneNoTextField.setBounds(216, 41, 80, 20);
		instructorAttributePanel.add(instructorPhoneNoTextField);
		
		JLabel instructorEmailLabel = new JLabel("E-mail:");
		instructorEmailLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorEmailLabel.setBounds(306, 47, 86, 14);
		instructorAttributePanel.add(instructorEmailLabel);
		
		instructorEmailTextField = new JTextField();
		instructorEmailTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorEmailTextField.setColumns(10);
		instructorEmailTextField.setBounds(348, 44, 80, 20);
		instructorAttributePanel.add(instructorEmailTextField);
		
		JLabel instructorPasswordLabel = new JLabel("Password:");
		instructorPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorPasswordLabel.setBounds(438, 47, 86, 14);
		instructorAttributePanel.add(instructorPasswordLabel);
		
		instructorPasswordTextField = new JTextField();
		instructorPasswordTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorPasswordTextField.setColumns(10);
		instructorPasswordTextField.setBounds(507, 44, 80, 20);
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
		instructorActivityTypeComboBox.setBounds(426, 67, 222, 20);
		instructorAttributePanel.add(instructorActivityTypeComboBox);
		
		JLabel instructorSalaryLabel = new JLabel("Salary:");
		instructorSalaryLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorSalaryLabel.setBounds(597, 47, 86, 14);
		instructorAttributePanel.add(instructorSalaryLabel);
		
		instructorSalaryTextField = new JTextField();
		instructorSalaryTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorSalaryTextField.setColumns(10);
		instructorSalaryTextField.setBounds(658, 44, 80, 20);
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
		instructorStatusComboBox.setBounds(216, 67, 122, 20);
		instructorAttributePanel.add(instructorStatusComboBox);
		
		JPanel instructorOptionsPanel = new JPanel();
		instructorOptionsPanel.setLayout(null);
		instructorOptionsPanel.setBorder(new TitledBorder(null, "Instructor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		instructorOptionsPanel.setBounds(10, 122, 130, 159);
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
							
							String activityTypeName = new String("");
							ActivityType activityTypeObj = instructorObj.getActivityType();
							if(activityTypeObj != null)
							{
								activityTypeName = activityTypeObj.getName();
							}
							
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
							
							if(activityTypeName.equals("") == true)
							{
								instructorActivityTypeComboBox.setSelectedItem(null);
							}
							else
							{
								instructorActivityTypeComboBox.setSelectedItem(activityTypeName);
							}
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
								
								String activityTypeName = new String("");
								ActivityType activityTypeObj = instructorObj.getActivityType();
								if(activityTypeObj != null)
								{
									activityTypeName = activityTypeObj.getName();
								}
								
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
								
								if(activityTypeName.equals("") == true)
								{
									instructorActivityTypeComboBox.setSelectedItem(null);
								}
								else
								{
									instructorActivityTypeComboBox.setSelectedItem(activityTypeName);
								}
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
									
									String activityTypeName = new String("");
									ActivityType activityTypeObj = instructorObj.getActivityType();
									if(activityTypeObj != null)
									{
										activityTypeName = activityTypeObj.getName();
									}
									
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
									
									if(activityTypeName.equals("") == true)
									{
										instructorActivityTypeComboBox.setSelectedItem(null);
									}
									else
									{
										instructorActivityTypeComboBox.setSelectedItem(activityTypeName);
									}
								}
							}
						}
					}
				}
			}
		});
		instructorSearchButton.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorSearchButton.setBounds(6, 16, 114, 25);
		instructorOptionsPanel.add(instructorSearchButton);
		
		JButton instructorCreateButton = new JButton("Create");
		instructorCreateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(instructorNameTextField.getText().equals("") == true || instructorCityTextField.getText().equals("") == true ||
						instructorCountryTextField.getText().equals("") == true || instructorZipcodeTextField.getText().equals("") == true ||
						instructorAddressTextField.getText().equals("") == true || instructorSalaryTextField.getText().equals("") == true ||
						instructorPriceTextField.getText().equals("") == true || instructorStatusComboBox.getSelectedItem() == null ||
						instructorActivityTypeComboBox.getSelectedItem() == null)
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
					double instructorSalary = Double.parseDouble(stringInstructorSalary);
					String stringInstructorPrice = instructorPriceTextField.getText();
					double instructorPrice = Double.parseDouble(stringInstructorPrice);
					String instructorStatus = (String) instructorStatusComboBox.getSelectedItem();
					
					int activityTypeId = 0;
					if(instructorActivityTypeComboBox.getSelectedItem() != null)
					{
						String activityTypeName = (String) instructorActivityTypeComboBox.getSelectedItem();
						ActivityType activityTypeObj = activityTypeCtr.getActivityTypeByName(activityTypeName);
						activityTypeId = activityTypeObj.getID();
					}
					
					if(personCtr.searchPersonByName(instructorName) != null)
					{
						JOptionPane.showMessageDialog(null, "Cannot register the same instructor twice.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						Location locationObj = locationCtr.getLocation(instructorZipcode, instructorCountry);
						if(locationObj == null)
						{
							locationCtr.insertLocation(instructorZipcode, instructorCountry, instructorCiy);
						}
						
						personCtr.insertPerson(instructorName, instructorAddress, instructorZipcode, instructorCountry, instructorPhoneNo, instructorEmail, "Instructor", instructorPassword);
						employeeCtr.insertEmployee(instructorName, instructorSalary);
						instructorCtr.insertInstructor(instructorName, activityTypeId, instructorPrice, instructorStatus);
						JOptionPane.showMessageDialog(null, "Instructor successfully inserted", "Info", JOptionPane.INFORMATION_MESSAGE);
						clearInstructorPanel();
						clearInstructorTable();
						clearEmployeeTable();
						instructorTable.setModel(getInstructorTableModel());
					}
				}
			}
		});
		instructorCreateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorCreateButton.setBounds(6, 52, 114, 25);
		instructorOptionsPanel.add(instructorCreateButton);
		
		JButton instructorUpdateButton = new JButton("Update");
		instructorUpdateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(instructorIdTextField.getText().equals("") == true || instructorNameTextField.getText().equals("") == true ||
						instructorCityTextField.getText().equals("") == true || instructorCountryTextField.getText().equals("") == true ||
						instructorZipcodeTextField.getText().equals("") == true || instructorAddressTextField.getText().equals("") == true ||
						instructorSalaryTextField.getText().equals("") == true || instructorPriceTextField.getText().equals("") == true ||instructorPasswordTextField.getText().equals("") == true ||
						instructorStatusComboBox.getSelectedItem() == null || instructorActivityTypeComboBox.getSelectedItem() == null)
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
						String instructorPassword = instructorPasswordTextField.getText();
						String stringInstructorSalary = instructorSalaryTextField.getText();
						double instructorSalary = Double.parseDouble(stringInstructorSalary);
						String stringInstructorPrice = instructorPriceTextField.getText();
						double instructorPrice = Double.parseDouble(stringInstructorPrice);
						String instructorStatus = (String) instructorStatusComboBox.getSelectedItem();
						
						int activityTypeId = 0;
						if(instructorActivityTypeComboBox.getSelectedItem() != null)
						{
							String activityTypeName = (String) instructorActivityTypeComboBox.getSelectedItem();
							ActivityType activityTypeObj = activityTypeCtr.getActivityTypeByName(activityTypeName);
							activityTypeId = activityTypeObj.getID();
						}
						
						if(personCtr.checkPersonInstanceCount(instructorId, instructorName, instructorZipcode, instructorCountry, instructorAddress) == false)
						{
							JOptionPane.showMessageDialog(null, "You may not update with an already existing instructor on this id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							Location locationObj =  new Location();
							locationObj = locationCtr.getLocation(instructorZipcode, instructorCountry);
							if(locationObj == null)
							{
								locationCtr.insertLocation(instructorZipcode, instructorCountry, instructorCiy);
							}
							personCtr.updatePerson(instructorId, instructorName, instructorAddress, instructorZipcode, instructorCountry, instructorPhoneNo, instructorEmail, "Instructor", instructorPassword);
							employeeCtr.updateEmployee(instructorId, instructorSalary);
							instructorCtr.updateInstructor(instructorId, activityTypeId, instructorPrice, instructorStatus);
							JOptionPane.showMessageDialog(null, "Instructor updated successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
							clearInstructorPanel();
							clearInstructorTable();
							clearEmployeeTable();
							instructorTable.setModel(getInstructorTableModel());
						}
					}
				}
			}
		});
		instructorUpdateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorUpdateButton.setBounds(6, 88, 114, 25);
		instructorOptionsPanel.add(instructorUpdateButton);
		
		JButton instructorAllButton = new JButton("All");
		instructorAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				clearInstructorPanel();
				clearInstructorTable();
				
				instructorTable.setModel(getInstructorTableModel());
			}
		});
		instructorAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorAllButton.setBounds(6, 124, 114, 25);
		instructorOptionsPanel.add(instructorAllButton);
		
		JButton instructorClearAllButton = new JButton("Clear all");
		instructorClearAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				clearInstructorPanel();
				clearInstructorTable();
			}
		});
		instructorClearAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		instructorClearAllButton.setBounds(10, 285, 130, 39);
		InstructorPanel.add(instructorClearAllButton);
		
		JScrollPane instructorTableScrollPane = new JScrollPane();
		instructorTableScrollPane.setBounds(150, 122, 795, 329);
		InstructorPanel.add(instructorTableScrollPane);
		
		instructorTable = new JTable();
		instructorTable.setFillsViewportHeight(true);
		instructorTableScrollPane.setViewportView(instructorTable);
		
		JPanel FacilityPanel = new JPanel();
		tabbedPane.addTab("Facility menu", null, FacilityPanel, null);
		FacilityPanel.setLayout(null);
		
		JPanel facilityAttributesPanel = new JPanel();
		facilityAttributesPanel.setBorder(new TitledBorder(null, "Facility attributes:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		facilityAttributesPanel.setBounds(10, 11, 650, 80);
		FacilityPanel.add(facilityAttributesPanel);
		facilityAttributesPanel.setLayout(null);
		
		JLabel facilityIdLabel = new JLabel("Facility id:");
		facilityIdLabel.setBounds(6, 19, 86, 14);
		facilityAttributesPanel.add(facilityIdLabel);
		facilityIdLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		facilityIdTextField = new JTextField();
		facilityIdTextField.setBounds(70, 16, 220, 20);
		facilityAttributesPanel.add(facilityIdTextField);
		facilityIdTextField.setColumns(10);
		
		JLabel facilityNameLabel = new JLabel("Facility name:");
		facilityNameLabel.setBounds(323, 19, 86, 14);
		facilityAttributesPanel.add(facilityNameLabel);
		facilityNameLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		facilityNameTextField = new JTextField();
		facilityNameTextField.setBounds(398, 16, 220, 20);
		facilityAttributesPanel.add(facilityNameTextField);
		facilityNameTextField.setColumns(10);
		
		JLabel activityTypeLabel = new JLabel("Activity type:");
		activityTypeLabel.setBounds(323, 47, 86, 14);
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
		activityTypeComboBox.setBounds(398, 44, 220, 20);
		facilityAttributesPanel.add(activityTypeComboBox);
		
		JLabel statusLabel = new JLabel("Status:");
		statusLabel.setBounds(6, 47, 86, 14);
		facilityAttributesPanel.add(statusLabel);
		statusLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		statusComboBox = new JComboBox<String>();
		statusComboBox.addItem("Available");
		statusComboBox.addItem("Under construction");
		statusComboBox.addItem("Cleaning");
		statusComboBox.setSelectedItem(null);
		statusComboBox.setBounds(70, 44, 220, 20);
		facilityAttributesPanel.add(statusComboBox);
		
		JPanel facilityOptionsPanel = new JPanel();
		facilityOptionsPanel.setBorder(new TitledBorder(null, "Facility", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		facilityOptionsPanel.setBounds(10, 102, 146, 159);
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
							String status = facilityObj.getStatus();
							
							String activityTypeName = new String("");
							ActivityType activityTypeObj = new ActivityType();
							activityTypeObj = facilityObj.getActivity();
							if(activityTypeObj != null)
							{
								activityTypeName = activityTypeObj.getName();
							}
							
							facilityIdTextField.setText(stringFacilityId);
							facilityNameTextField.setText(facilityName);
							statusComboBox.setSelectedItem(status);
							
							if(activityTypeName.equals("") == true)
							{
								activityTypeComboBox.setSelectedItem(null);
							}
							else
							{
								activityTypeComboBox.setSelectedItem(activityTypeName);
							}
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
								String status = facilityObj.getStatus();
								
								String activityTypeName = new String("");
								ActivityType activityTypeObj = new ActivityType();
								activityTypeObj = facilityObj.getActivity();
								if(activityTypeObj != null)
								{
									activityTypeName = activityTypeObj.getName();
								}
								
								facilityIdTextField.setText(stringFacilityId);
								facilityNameTextField.setText(facilityName);
								statusComboBox.setSelectedItem(status);
								
								if(activityTypeName.equals("") == true)
								{
									activityTypeComboBox.setSelectedItem(null);
								}
								else
								{
									activityTypeComboBox.setSelectedItem(activityTypeName);
								}
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
									String status = facilityObj.getStatus();
									
									String activityTypeName = new String("");
									ActivityType activityTypeObj = new ActivityType();
									activityTypeObj = facilityObj.getActivity();
									if(activityTypeObj != null)
									{
										activityTypeName = activityTypeObj.getName();
									}
									
									facilityIdTextField.setText(stringFacilityId);
									facilityNameTextField.setText(facilityName);
									statusComboBox.setSelectedItem(status);
									
									if(activityTypeName.equals("") == true)
									{
										activityTypeComboBox.setSelectedItem(null);
									}
									else
									{
										activityTypeComboBox.setSelectedItem(activityTypeName);
									}
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
				if(facilityNameTextField.getText().equals("") == true || statusComboBox.getSelectedItem() == null || activityTypeComboBox.getSelectedItem() == null)
				{
					JOptionPane.showMessageDialog(null, "A facility attribute might be missing. Please insert all needed facility attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String facilityName = facilityNameTextField.getText();
					
					ActivityType activityTypeObj = new ActivityType();
					
					int activityId = 0;
					if(activityTypeComboBox.getSelectedItem() != null)
					{
						String activityTypeName = (String) activityTypeComboBox.getSelectedItem();
						activityTypeObj = activityTypeCtr.getActivityTypeByName(activityTypeName);
						activityId = activityTypeObj.getID();
					}
					
					String status = String.valueOf(statusComboBox.getSelectedItem());
					
					if(facilityCtr.getFacilityByName(facilityName) != null)
					{
						JOptionPane.showMessageDialog(null, "Cannot register the same facility twice.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						facilityCtr.insertFacility(facilityName, activityId, status);
						JOptionPane.showMessageDialog(null, "Facility successfully inserted.", "Info", JOptionPane.INFORMATION_MESSAGE);
						clearFacilityTable();
						clearFacilityPanel();
						facilityTable.setModel(getFacilityTableModel());
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
				if(facilityIdTextField.getText().equals("") == true || facilityNameTextField.getText().equals("") == true ||
						statusComboBox.getSelectedItem() == null || activityTypeComboBox.getSelectedItem() == null)
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
						int activityId = 0;
						if(activityTypeComboBox.getSelectedItem() != null)
						{
							String activityName = String.valueOf(activityTypeComboBox.getSelectedItem());
							activityTypeObj = activityTypeCtr.getActivityTypeByName(activityName);
							activityId = activityTypeObj.getID();
						}
						
						String status = String.valueOf(statusComboBox.getSelectedItem());
						
						if(facilityCtr.checkFacilityInstanceCount(facilityId, facilityName) == false)
						{
							JOptionPane.showMessageDialog(null, "You may not update with an already existing facility on this id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							facilityCtr.updateFacility(facilityId, facilityName, activityId, status);
							JOptionPane.showMessageDialog(null, "Facility updated successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
							clearFacilityTable();
							clearFacilityPanel();
							facilityTable.setModel(getFacilityTableModel());
						}
					}
				}
			}
		});
		facilityUpdateButton.setBounds(6, 88, 134, 25);
		facilityOptionsPanel.add(facilityUpdateButton);
		facilityUpdateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
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
		facilityAllButton.setBounds(6, 124, 134, 25);
		facilityOptionsPanel.add(facilityAllButton);
		facilityAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		facilityTableScrollPane = new JScrollPane();
		facilityTableScrollPane.setBounds(166, 102, 779, 349);
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
		facilityClearAllButton.setBounds(10, 264, 146, 36);
		FacilityPanel.add(facilityClearAllButton);
		
		JButton logOffButton = new JButton("Log Off");
		logOffButton.setBounds(849, 512, 125, 39);
		frame.getContentPane().add(logOffButton);
		logOffButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				frame.dispose();
				LoginMenu loginMenu = LoginMenu.getInstance();
				loginMenu.frame.setVisible(true);
			}
		});
		logOffButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		//frame.setUndecorated(true);
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
				int activityTypeId = 0;
				String activityTypeName = new String("None - please select");
				ActivityType activityTypeObj = new ActivityType();
				activityTypeObj = facilityObj.getActivity();
				if(activityTypeObj != null)
				{
					activityTypeName = activityTypeObj.getName();
					activityTypeId = activityTypeObj.getID();
				}
				facilityTableModel.addRow(new String[]
						{
						String.valueOf(facilityObj.getId()),
						facilityObj.getName(),
						String.valueOf(activityTypeId),
						activityTypeName,
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
			
			
			public void valueChanged(ListSelectionEvent e)
			{
				Facility facilityObj = new Facility();
				
				int selectedRow = facilityTable.getSelectedRow();
				if(selectedRow > -1)
				{
					String selectedData = (String) facilityTable.getValueAt(selectedRow, 0);
					
					int facilityId = Integer.parseInt(selectedData);
					String stringFacilityId = String.valueOf(facilityId);
					facilityObj = facilityCtr.getFacilityById(facilityId);
					
					String facilityName = facilityObj.getName();
					String status = facilityObj.getStatus();
					
					String activityTypeName = new String("");
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = facilityObj.getActivity();
					if(activityTypeObj != null)
					{
						activityTypeName = activityTypeObj.getName();
					}
					
					facilityIdTextField.setText(stringFacilityId);
					facilityNameTextField.setText(facilityName);
					statusComboBox.setSelectedItem(status);
					
					if(activityTypeName.equals("") == true)
					{
						activityTypeComboBox.setSelectedItem(null);
					}
					else
					{
						activityTypeComboBox.setSelectedItem(activityTypeName);
					}
				}
				
			}
		});
		
		
		return facilityTableModel;
	}
	
	private void clearFacilityPanel()
	{
		facilityIdTextField.setText("");
		facilityNameTextField.setText("");
		activityTypeComboBox.setSelectedItem(null);
		statusComboBox.setSelectedItem(null);
	}
	
	private void clearFacilityTable()
	{
		facilityTable.setCellSelectionEnabled(false);
		facilityTable.setModel(new DefaultTableModel());
	}
	
	private DefaultTableModel getActivityTypeTableModel()
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
	
	private void clearActivityTypePanel()
	{
		activityTypeIdTextField.setText("");
		activityTypeNameTextField.setText("");
		maxParticipantsTextField.setText("");
	}
	
	private void clearActivityTypeTable()
	{
		activityTypeTable.setCellSelectionEnabled(false);
		activityTypeTable.setModel(new DefaultTableModel());
	}
	
	private DefaultTableModel getGuestTableModel()
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
	
	private void clearGuestPanel()
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
	
	private void clearGuestTable()
	{
		guestTable.setCellSelectionEnabled(false);
		guestTable.setModel(new DefaultTableModel());
	}
	
	private DefaultTableModel getEmployeeTableModel()
	{
		LinkedList<Employee> completeEmployeeList = new LinkedList<Employee>();
		completeEmployeeList = employeeCtr.getAllEmployees();
		
		DefaultTableModel employeeTableModel = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column)
			{
				//all cells false
				return false;
			}
		};
		
		
		employeeTableModel.setColumnIdentifiers(new String[] {"Id", "Name","Employee Type", "PhoneNo", "Email", "Password", "Salary"});
		
		for(Employee employeeObj : completeEmployeeList)
		{
			
			employeeTableModel.addRow(new String[]
					{
					String.valueOf(employeeObj.getId()),
					employeeObj.getName(),
					employeeObj.getPersonType(),
					employeeObj.getPhoneNo(),
					employeeObj.getEmail(),
					employeeObj.getPassword(),
					String.valueOf(employeeObj.getSalary())
					});
		}
		
		employeeTable.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = employeeTable.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				Employee employeeObj = new Employee();
				
				int selectedRow = employeeTable.getSelectedRow();
				if(selectedRow > -1)
				{
					String selectedData = (String) employeeTable.getValueAt(selectedRow, 0);
					int employeeId = Integer.parseInt(selectedData);
					String stringEmployeeId = String.valueOf(employeeId);
					
					employeeObj = employeeCtr.getEmployeeById(employeeId);
					
					String employeeName = employeeObj.getName();
					String employeeCountry = employeeObj.getCountry();
					int employeeZipcode = employeeObj.getZipcode();
					String stringEmployeeZipcode = String.valueOf(employeeZipcode);
					String employeeAddress = employeeObj.getAddress();
					String employeePhoneNo = employeeObj.getPhoneNo();
					String employeeEmail = employeeObj.getEmail();
					String employeePassword = employeeObj.getPassword();
					double employeeSalary = employeeObj.getSalary();
					String stringEmployeeSalary = String.valueOf(employeeSalary);
					
					Location locationObj = new Location();
					locationObj = locationCtr.getLocation(employeeZipcode, employeeCountry);
					String employeeCity = locationObj.getCity();
					
					employeeIdTextField.setText(stringEmployeeId);
					employeeNameTextField.setText(employeeName);
					employeeCityTextField.setText(employeeCity);
					employeeCountryTextField.setText(employeeCountry);
					employeeZipcodeTextField.setText(stringEmployeeZipcode);
					employeeAddressTextField.setText(employeeAddress);
					employeePhoneNoTextField.setText(employeePhoneNo);
					employeeEmailTextField.setText(employeeEmail);
					employeePasswordTextField.setText(employeePassword);
					employeeSalaryTextField.setText(stringEmployeeSalary);
				}
			}
		});
		
		return employeeTableModel;
	}
	
	private void clearInstructorPanel()
	{
		instructorIdTextField.setText("");
		instructorNameTextField.setText("");
		instructorCityTextField.setText("");
		instructorCountryTextField.setText("");
		instructorZipcodeTextField.setText("");
		instructorAddressTextField.setText("");
		instructorPhoneNoTextField.setText("");
		instructorEmailTextField.setText("");
		instructorPasswordTextField.setText("");
		instructorSalaryTextField.setText("");
		instructorPriceTextField.setText("");
		instructorStatusComboBox.setSelectedItem(null);
		instructorActivityTypeComboBox.setSelectedItem(null);
	}
	
	private void clearInstructorTable()
	{
		instructorTable.setCellSelectionEnabled(false);
		instructorTable.setModel(new DefaultTableModel());
	}
	
	private DefaultTableModel getTravelAgencyTableModel()
	{
		LinkedList<TravelAgency> completeTravelAgencyList = new LinkedList<TravelAgency>();
		completeTravelAgencyList = travelCtr.getAllTravelAgencies();
		
		DefaultTableModel travelAgencyTableModel = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column)
			{
				//all cells false
				return false;
			}
		};
		
		//travelAgencyTableModel.setColumnIdentifiers(new String[] {"CVR", "Name", "ZIP code", "Country", "City", "Address", "PhoneNo", "Email"});
		travelAgencyTableModel.setColumnIdentifiers(new String[] {"CVR", "Name", "PhoneNo", "Email"});
		
		for(TravelAgency travelAgencyObj : completeTravelAgencyList)
		{
			/*int travelAgencyZipcode = travelAgencyObj.getZipCode();
			String travelAgencyCountry = travelAgencyObj.getCountry();
			Location locationObj = new Location();
			locationObj = locationCtr.getLocation(travelAgencyZipcode, travelAgencyCountry);
			String travelAgencyCity = new String("");
			if(locationObj != null)
			{
				travelAgencyCity = locationObj.getCity();
			}*/
			
			travelAgencyTableModel.addRow(new String[]
					{
					String.valueOf(travelAgencyObj.getCVR()),
					travelAgencyObj.getName(),
					//String.valueOf(travelAgencyZipcode),
					//travelAgencyCountry,
					//travelAgencyCity,
					//travelAgencyObj.getAddress(),
					travelAgencyObj.getPhoneNo(),
					travelAgencyObj.getEmail()
					});
		}
		
		travelAgencyTable.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = travelAgencyTable.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent arg0)
			{
				TravelAgency travelAgencyObj = new TravelAgency();
				Location locationObj = new Location();
				
				int selectedRow = travelAgencyTable.getSelectedRow();
				if(selectedRow > -1)
				{
					String selectedData = (String) travelAgencyTable.getValueAt(selectedRow, 0);
					int travelAgencyCVR = Integer.parseInt(selectedData);
					String stringTravelAgencyCVR = String.valueOf(travelAgencyCVR);
					
					travelAgencyObj = travelCtr.getTravelAgencyByCVR(travelAgencyCVR);
					String travelAgencyName = travelAgencyObj.getName();
					int travelAgencyZipcode = travelAgencyObj.getZipCode();
					String stringTravelAgencyZipcode = String.valueOf(travelAgencyZipcode);
					String travelAgencyCountry = travelAgencyObj.getCountry();
					String travelAgencyAddress = travelAgencyObj.getAddress();
					String travelAgencyPhoneNo = travelAgencyObj.getPhoneNo();
					String travelAgencyEmail = travelAgencyObj.getEmail();
					
					locationObj = locationCtr.getLocation(travelAgencyZipcode, travelAgencyCountry);
					String travelAgencyCity = new String("");
					if(locationObj != null)
					{
						travelAgencyCity = locationObj.getCity();
					}
					
					travelAgencyCVRTextField.setText(stringTravelAgencyCVR);
					travelAgencyNameTextField.setText(travelAgencyName);
					travelAgencyCityTextField.setText(travelAgencyCity);
					travelAgencyCountryTextField.setText(travelAgencyCountry);
					travelAgencyZipcodeTextField.setText(stringTravelAgencyZipcode);
					travelAgencyAddressTextField.setText(travelAgencyAddress);
					travelAgencyPhoneNoTextField.setText(travelAgencyPhoneNo);
					travelAgencyEmailTextField.setText(travelAgencyEmail);
				}
			}
		});
		
		return travelAgencyTableModel;
	}
	
	private void clearTravelAgencyPanel()
	{
		travelAgencyCVRTextField.setText("");
		travelAgencyNameTextField.setText("");
		travelAgencyCityTextField.setText("");
		travelAgencyCountryTextField.setText("");
		travelAgencyZipcodeTextField.setText("");
		travelAgencyAddressTextField.setText("");
		travelAgencyPhoneNoTextField.setText("");
		travelAgencyEmailTextField.setText("");
	}
	
	private void clearTravelAgencyTable()
	{
		travelAgencyTable.setCellSelectionEnabled(false);
		travelAgencyTable.setModel(new DefaultTableModel());
	}
	
	private void clearEmployeePanel()
	{
		employeeIdTextField.setText("");
		employeeNameTextField.setText("");
		employeeZipcodeTextField.setText("");
		employeeCountryTextField.setText("");
		employeeCityTextField.setText("");
		employeeAddressTextField.setText("");
		employeePhoneNoTextField.setText("");
		employeeEmailTextField.setText("");
		employeePasswordTextField.setText("");
		employeeSalaryTextField.setText("");
		
	}
	
	private void clearEmployeeTable()
	{
		employeeTable.setCellSelectionEnabled(false);
		employeeTable.setModel(new DefaultTableModel());
	}
	
	private DefaultTableModel getInstructorTableModel()
	{
		LinkedList<Instructor> completeInstructorList = new LinkedList<Instructor>();
		completeInstructorList = instructorCtr.getAllInstructors();
		
		DefaultTableModel instructorTableModel = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column)
			{
				//all cells false
				return false;
			}
		};
		
		instructorTableModel.setColumnIdentifiers(new String[] {"Id", "Name", "Password", "Salary", "Activity", "Price", "Status"});
		
		for(Instructor instructorObj : completeInstructorList)
		{
			String activityTypeName = new String("None - please select");
			ActivityType activityTypeObj = new ActivityType();
			activityTypeObj = instructorObj.getActivityType();
			if(activityTypeObj != null)
			{
				activityTypeName = activityTypeObj.getName();
			}
			
			instructorTableModel.addRow(new String[]
					{
					String.valueOf(instructorObj.getId()),
					instructorObj.getName(),
					instructorObj.getPassword(),
					String.valueOf(instructorObj.getSalary()),
					activityTypeName,
					String.valueOf(instructorObj.getPrice()),
					instructorObj.getStatus()
					});
		}
		
		instructorTable.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = instructorTable.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				Instructor instructorObj = new Instructor();
				
				int selectedRow = instructorTable.getSelectedRow();
				if(selectedRow > -1)
				{
					String selectedData = (String) instructorTable.getValueAt(selectedRow, 0);
					int instructorId = Integer.parseInt(selectedData);
					String stringInstructorId = String.valueOf(instructorId);
					
					instructorObj = instructorCtr.getInstructorById(instructorId);
					
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
					
					String activityTypeName = new String("");
					ActivityType activityTypeObj = instructorObj.getActivityType();
					if(activityTypeObj != null)
					{
						activityTypeName = activityTypeObj.getName();
					}
					
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
					
					if(activityTypeName.equals("") == true)
					{
						instructorActivityTypeComboBox.setSelectedItem(null);
					}
					else
					{
						instructorActivityTypeComboBox.setSelectedItem(activityTypeName);
					}
				}
			}
		});
		
		return instructorTableModel;
	}
}