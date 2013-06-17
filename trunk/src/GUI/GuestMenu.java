package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
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
import javax.swing.JTextArea;
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
import Controller.TeamCtr;
import Model.ActivityBooking;
import Model.ActivityLine;
import Model.ActivityType;
import Model.Facility;
import Model.Guest;
import Model.Instructor;
import Model.Participant;
import Model.Person;
import Model.Team;

public class GuestMenu
{
	private int universalId;
	private int bookingId;
	
	private TeamCtr teamCtr = new TeamCtr();
	private GuestCtr guestCtr = new GuestCtr();
	private DateCheck dateCheck = new DateCheck();
	private ActivityBookingCtr activityBookingCtr = new ActivityBookingCtr();
	private ActivityCtr activityCtr = new ActivityCtr();
	private FacilityCtr facilityCtr = new FacilityCtr();
	private InstructorCtr instructorCtr = new InstructorCtr();
	private PersonCtr personCtr = new PersonCtr();
	
	private JFrame frame;
	private JTable teamTable;
	private JTextField participantIdTextField;
	private JTable participantsTable;
	private JTextField maxParticipantsTextBox;
	private JTable activityBookingsTable;
	private JTable activityLinesTable;
	
	private JLabel dinamicLabel;
	private JComboBox<String> activityBookingAllTeamsComboBox;
	private JComboBox<String> allTeamsComboBox;
	private JComboBox<String> dayComboBox;
	private JComboBox<String> monthComboBox;
	private JComboBox<String> yearComboBox;
	private JComboBox<String> allAvailableActivitiesComboBox;
	private JComboBox<String> startHoursComboBox;
	private JComboBox<String> availableFacilityComboBox;
	private JComboBox<String> availableInstructorsComboBox;
	private JButton getAvailableFacilitiesButton;
	private JButton getInstructorsButton;
	private JButton instructorClearButton;
	private JButton getTeamsButton;
	private JButton teamClearButton;
	private JButton addActivityLineButton;
	private JButton cancelActivityLineButton;
	private JButton allActivityLinesButton;
	private JTextField teamNumberOfParticipantsTextField;
	private JTextField selectedDateTextField;

	public GuestMenu()
	{
		initialize();
		frame.setVisible(true);
	}

	private void initialize()
	{
		frame = new JFrame();
		frame.setFont(new Font("Dialog", Font.PLAIN, 14));
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//frmSupplierInformation.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 960, 535);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Main menu", null, panel, "Main menu");
		panel.setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome,");
		welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		welcomeLabel.setBounds(48, 37, 115, 25);
		panel.add(welcomeLabel);
		
		dinamicLabel = new JLabel("GUEST");
		dinamicLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		dinamicLabel.setBounds(151, 37, 241, 25);
		panel.add(dinamicLabel);
		
		JTextArea informationText = new JTextArea();
		informationText.setEditable(false);
		informationText.setBackground(SystemColor.control);
		informationText.setFont(new Font("Arial", Font.BOLD, 21));
		informationText.setText("Through this menu you are able to form and \r\nmanage your own teams as well as book activities \r\nand facilities. \r\n\r\nNotice that you may do activities with your \r\nteams but there are no instructors for team activities, \r\nin case you wish to book an instructor.\r\n\r\nFor team management select Team menu tab. \r\nFor activity booking select Activity booking menu tab.");
		informationText.setBounds(138, 106, 613, 308);
		panel.add(informationText);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Activity booking menu", null, panel_2, "Menu for booking activities");
		panel_2.setLayout(null);
		
		JPanel activityBookingPanel = new JPanel();
		activityBookingPanel.setBorder(new TitledBorder(null, "Activity booking", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityBookingPanel.setBounds(10, 11, 237, 180);
		panel_2.add(activityBookingPanel);
		activityBookingPanel.setLayout(null);
		
		JLabel dateLabel = new JLabel("Date:");
		dateLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		dateLabel.setBounds(6, 33, 90, 14);
		activityBookingPanel.add(dateLabel);
		
		JPanel day = new JPanel();
		day.setBounds(39, 16, 57, 43);
		activityBookingPanel.add(day);
		day.setLayout(null);
		day.setBorder(new TitledBorder(null, "dd", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
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
		day.add(dayComboBox);
		
		JPanel month = new JPanel();
		month.setBounds(97, 16, 56, 43);
		activityBookingPanel.add(month);
		month.setLayout(null);
		month.setBorder(new TitledBorder(null, "MM", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
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
		month.add(monthComboBox);
		
		JPanel year = new JPanel();
		year.setBounds(152, 16, 77, 43);
		activityBookingPanel.add(year);
		year.setLayout(null);
		year.setBorder(new TitledBorder(null, "yyyy", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
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
		year.add(yearComboBox);
		
		JButton createActivityBookingButton = new JButton("Create");
		createActivityBookingButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				int guestID = universalId;
				String activityBookingStatus = "Made";
				
				if(dayComboBox.getSelectedItem() == null || monthComboBox.getSelectedItem() == null || yearComboBox.getSelectedItem() == null)
				{
					JOptionPane.showMessageDialog(null, "Please insert the date of booking.", "Error!", JOptionPane.ERROR_MESSAGE);
					activityLineDisabler();
				}
				else
				{
					String day = (String) dayComboBox.getSelectedItem();
					String month = (String) monthComboBox.getSelectedItem();
					String year = (String) yearComboBox.getSelectedItem();
					String inputDate = day + "-" + month + "-" + year;
					String insertDate = year + "-" + month + "-" + day;
					
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
							if(activityBookingCtr.activityBookingCompleteCheck(insertDate, guestID) != true)
							{
								JOptionPane.showMessageDialog(null, "Cannot make two bookings on the same date.", "Error!", JOptionPane.ERROR_MESSAGE);
								clearActivityLinePanels();
								clearActivityLinesTable();
								activityLineDisabler();
							}
							else
							{
								activityBookingCtr.insertActivityBooking(guestID, insertDate, activityBookingStatus);
								JOptionPane.showMessageDialog(null, "Activity booking made successfully. You can add up to four activities.", "Info", JOptionPane.INFORMATION_MESSAGE);
								
								ActivityBooking activityBookingObj = new ActivityBooking();
								activityBookingObj = activityBookingCtr.getActivityBookingForDate(guestID, insertDate, activityBookingStatus);
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
		});
		createActivityBookingButton.setBounds(6, 86, 225, 25);
		activityBookingPanel.add(createActivityBookingButton);
		createActivityBookingButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton searchActivityBookingButton = new JButton("Activate activity lines");
		searchActivityBookingButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				int guestId = universalId;
				if(dayComboBox.getSelectedItem() == null || monthComboBox.getSelectedItem() ==null || yearComboBox.getSelectedItem() == null)
				{
					JOptionPane.showMessageDialog(null, "Please insert the date of booking.", "Error!", JOptionPane.ERROR_MESSAGE);
					clearActivityLinePanels();
					clearActivityLinesTable();
					activityLineDisabler();
				}
				else
				{
					String day = (String) dayComboBox.getSelectedItem();
					String month = (String) monthComboBox.getSelectedItem();
					String year = (String) yearComboBox.getSelectedItem();
					String inputDate = day + "-" + month + "-" + year;
					String insertDate = year + "-" + month + "-" + day;
					
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
		});
		searchActivityBookingButton.setBounds(6, 114, 225, 25);
		activityBookingPanel.add(searchActivityBookingButton);
		searchActivityBookingButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton getAllActivityBookingsButton = new JButton("All");
		getAllActivityBookingsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				clearActivityLinePanels();
				clearActivityLinesTable();
				activityLineDisabler();
				
				activityBookingsTable.setModel(getActivityBookingTableModel());
			}
		});
		getAllActivityBookingsButton.setBounds(6, 144, 225, 25);
		activityBookingPanel.add(getAllActivityBookingsButton);
		getAllActivityBookingsButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JLabel currentDateLabel = new JLabel("Selected date:");
		currentDateLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		currentDateLabel.setBounds(6, 67, 122, 14);
		activityBookingPanel.add(currentDateLabel);
		
		selectedDateTextField = new JTextField();
		selectedDateTextField.setEditable(false);
		selectedDateTextField.setBounds(91, 64, 138, 20);
		activityBookingPanel.add(selectedDateTextField);
		selectedDateTextField.setColumns(10);
		
		JPanel activityPanel = new JPanel();
		activityPanel.setBorder(new TitledBorder(null, "Activity", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityPanel.setBounds(10, 202, 237, 93);
		panel_2.add(activityPanel);
		activityPanel.setLayout(null);
		
		JLabel activityLabel = new JLabel("Activity:");
		activityLabel.setBounds(6, 19, 90, 14);
		activityPanel.add(activityLabel);
		activityLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
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
		allAvailableActivitiesComboBox.setEnabled(false);
		allAvailableActivitiesComboBox.setBounds(57, 16, 170, 20);
		activityPanel.add(allAvailableActivitiesComboBox);
		allAvailableActivitiesComboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JLabel maxParticipantsLabel = new JLabel("Max participants:");
		maxParticipantsLabel.setBounds(6, 44, 90, 14);
		activityPanel.add(maxParticipantsLabel);
		maxParticipantsLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		maxParticipantsTextBox = new JTextField();
		maxParticipantsTextBox.setBounds(104, 41, 123, 20);
		activityPanel.add(maxParticipantsTextBox);
		maxParticipantsTextBox.setEditable(false);
		maxParticipantsTextBox.setColumns(10);
		
		JLabel startHourLabel = new JLabel("Start hour:");
		startHourLabel.setBounds(6, 69, 90, 14);
		activityPanel.add(startHourLabel);
		startHourLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
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
		facilityPanel.setBorder(new TitledBorder(null, "Facility", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		facilityPanel.setBounds(10, 303, 237, 75);
		panel_2.add(facilityPanel);
		facilityPanel.setLayout(null);
		
		JLabel facilityLabel = new JLabel("Facility:");
		facilityLabel.setBounds(6, 19, 90, 14);
		facilityPanel.add(facilityLabel);
		facilityLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		availableFacilityComboBox = new JComboBox<String>();
		availableFacilityComboBox.setEnabled(false);
		availableFacilityComboBox.setBounds(57, 16, 170, 20);
		facilityPanel.add(availableFacilityComboBox);
		availableFacilityComboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		
		getAvailableFacilitiesButton = new JButton("Get facilities");
		getAvailableFacilitiesButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
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
		getAvailableFacilitiesButton.setEnabled(false);
		getAvailableFacilitiesButton.setBounds(6, 43, 225, 25);
		facilityPanel.add(getAvailableFacilitiesButton);
		getAvailableFacilitiesButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JPanel instructorPanel = new JPanel();
		instructorPanel.setBorder(new TitledBorder(null, "Instructor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		instructorPanel.setBounds(10, 389, 237, 100);
		panel_2.add(instructorPanel);
		instructorPanel.setLayout(null);
		
		JLabel instructorNameLabel = new JLabel("Instructor:");
		instructorNameLabel.setBounds(6, 19, 90, 14);
		instructorPanel.add(instructorNameLabel);
		instructorNameLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		availableInstructorsComboBox = new JComboBox<String>();
		availableInstructorsComboBox.setEnabled(false);
		availableInstructorsComboBox.setBounds(60, 16, 170, 20);
		instructorPanel.add(availableInstructorsComboBox);
		availableInstructorsComboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		
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
		getInstructorsButton.setEnabled(false);
		getInstructorsButton.setBounds(6, 40, 225, 25);
		instructorPanel.add(getInstructorsButton);
		getInstructorsButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
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
		instructorClearButton.setEnabled(false);
		instructorClearButton.setBounds(6, 68, 225, 25);
		instructorPanel.add(instructorClearButton);
		instructorClearButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JPanel teamPanel = new JPanel();
		teamPanel.setLayout(null);
		teamPanel.setBorder(new TitledBorder(null, "Team", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		teamPanel.setBounds(257, 389, 272, 100);
		panel_2.add(teamPanel);
		
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
		
		activityBookingAllTeamsComboBox.setEnabled(false);
		activityBookingAllTeamsComboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		activityBookingAllTeamsComboBox.setBounds(60, 16, 65, 20);
		teamPanel.add(activityBookingAllTeamsComboBox);
		
		getTeamsButton = new JButton("Get teams");
		getTeamsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int guestID = universalId;
				
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
		getTeamsButton.setEnabled(false);
		getTeamsButton.setFont(new Font("Arial", Font.PLAIN, 11));
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
		teamClearButton.setEnabled(false);
		teamClearButton.setFont(new Font("Arial", Font.PLAIN, 11));
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
		
		JPanel activityLine = new JPanel();
		activityLine.setBorder(new TitledBorder(null, "Activity line", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityLine.setBounds(708, 389, 237, 107);
		panel_2.add(activityLine);
		activityLine.setLayout(null);
		
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
		addActivityLineButton.setEnabled(false);
		addActivityLineButton.setBounds(6, 16, 225, 25);
		activityLine.add(addActivityLineButton);
		addActivityLineButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		cancelActivityLineButton = new JButton("Cancel activity");
		cancelActivityLineButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
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
		cancelActivityLineButton.setEnabled(false);
		cancelActivityLineButton.setBounds(6, 46, 225, 25);
		activityLine.add(cancelActivityLineButton);
		cancelActivityLineButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		allActivityLinesButton = new JButton("All");
		allActivityLinesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				clearActivityLinePanels();
				clearActivityLinesTable();
				activityLinesTable.setModel(getActivityLinesTableModel());
			}
		});
		allActivityLinesButton.setEnabled(false);
		allActivityLinesButton.setBounds(6, 75, 225, 25);
		activityLine.add(allActivityLinesButton);
		allActivityLinesButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JPanel activityBookingTablePanel = new JPanel();
		activityBookingTablePanel.setBorder(new TitledBorder(null, "Activity bookings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityBookingTablePanel.setBounds(257, 11, 688, 194);
		panel_2.add(activityBookingTablePanel);
		activityBookingTablePanel.setLayout(null);
		
		JScrollPane activityBookingsScrollPane = new JScrollPane();
		activityBookingsScrollPane.setBounds(6, 16, 672, 167);
		activityBookingTablePanel.add(activityBookingsScrollPane);
		
		activityBookingsTable = new JTable();
		activityBookingsTable.setFillsViewportHeight(true);
		activityBookingsScrollPane.setViewportView(activityBookingsTable);
		
		JPanel activityLinesTablePanel = new JPanel();
		activityLinesTablePanel.setLayout(null);
		activityLinesTablePanel.setBorder(new TitledBorder(null, "Activity lines", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityLinesTablePanel.setBounds(257, 216, 688, 162);
		panel_2.add(activityLinesTablePanel);
		
		JScrollPane activityLinesScrollPane = new JScrollPane();
		activityLinesScrollPane.setBounds(10, 21, 672, 130);
		activityLinesTablePanel.add(activityLinesScrollPane);
		
		activityLinesTable = new JTable();
		activityLinesTable.setFillsViewportHeight(true);
		activityLinesScrollPane.setViewportView(activityLinesTable);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Team menu", null, panel_1, "Menu for the management of teams");
		panel_1.setLayout(null);
		
		JLabel teamIdLabel = new JLabel("Team:");
		teamIdLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		teamIdLabel.setBounds(31, 31, 75, 15);
		panel_1.add(teamIdLabel);
		
		allTeamsComboBox = new JComboBox<String>();
		allTeamsComboBox.setSelectedItem(null);
		allTeamsComboBox.setBounds(77, 28, 100, 20);
		panel_1.add(allTeamsComboBox);
		
		JPanel teamOptions = new JPanel();
		teamOptions.setBorder(new TitledBorder(null, "Team options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		teamOptions.setBounds(31, 57, 136, 120);
		panel_1.add(teamOptions);
		teamOptions.setLayout(null);
		
		JButton createTeamButton = new JButton("Create");
		createTeamButton.setBounds(6, 16, 125, 25);
		teamOptions.add(createTeamButton);
		createTeamButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				teamCtr.insertTeam(universalId);
				clearTeamTab();
				clearParticipantsTable();
				clearTeamTable();
				JOptionPane.showMessageDialog(null, "Team successfully created.", "Info", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		createTeamButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton deleteTeamButton = new JButton("Delete");
		deleteTeamButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(allTeamsComboBox.getSelectedItem() == null)
				{
					JOptionPane.showMessageDialog(null, "Please insert the id of the team you wish to remove.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String stringTeamId = String.valueOf(allTeamsComboBox.getSelectedItem());
					int teamId =  Integer.parseInt(stringTeamId);
					
					teamCtr.deleteTeamParticipants(teamId);
					teamCtr.deleteTeamByBothIDs(teamId, universalId);
					clearTeamTab();
					clearParticipantsTable();
					clearTeamTable();
					JOptionPane.showMessageDialog(null, "Team has been removed successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		deleteTeamButton.setBounds(6, 52, 125, 25);
		teamOptions.add(deleteTeamButton);
		deleteTeamButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton getAllTeamsButton = new JButton("All");
		getAllTeamsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearTeamTab();
				clearTeamTable();
				clearParticipantsTable();
				
				teamTable.setModel(getTeamTableModel());
				
				allTeamsComboBox.removeAllItems();
				LinkedList<Team> allLeaderTeams = new LinkedList<Team>();
				allLeaderTeams = teamCtr.getTeamsByLeaderId(universalId);
				if(allLeaderTeams.isEmpty() == false)
				{
					for(Team teamObj : allLeaderTeams)
					{
						allTeamsComboBox.addItem(String.valueOf(teamObj.getId()));
					}
				}
				else
				{
					allTeamsComboBox.removeAll();
				}
				allTeamsComboBox.setSelectedItem(null);
			}
		});
		getAllTeamsButton.setBounds(6, 88, 125, 25);
		teamOptions.add(getAllTeamsButton);
		getAllTeamsButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JScrollPane teamScrollPane = new JScrollPane();
		teamScrollPane.setBounds(177, 57, 440, 120);
		panel_1.add(teamScrollPane);
		
		teamTable = new JTable();
		teamTable.setEnabled(false);
		teamTable.setFillsViewportHeight(true);
		teamScrollPane.setViewportView(teamTable);
		
		JLabel participantIdLabel = new JLabel("Participant id:");
		participantIdLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		participantIdLabel.setBounds(31, 209, 75, 15);
		panel_1.add(participantIdLabel);
		
		participantIdTextField = new JTextField();
		participantIdTextField.setFont(new Font("Arial", Font.PLAIN, 11));
		participantIdTextField.setBounds(119, 206, 130, 20);
		panel_1.add(participantIdTextField);
		participantIdTextField.setColumns(10);
		
		JPanel participantsOptions = new JPanel();
		participantsOptions.setBorder(new TitledBorder(null, "Participants options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		participantsOptions.setBounds(31, 235, 137, 120);
		panel_1.add(participantsOptions);
		participantsOptions.setLayout(null);
		
		JButton addParticipantButton = new JButton("Add");
		addParticipantButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(allTeamsComboBox.getSelectedItem() == null || participantIdTextField.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert both the team id and the participant id", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String stringTeamId = String.valueOf(allTeamsComboBox.getSelectedItem());
					int teamId = Integer.parseInt(stringTeamId);
					
					String stringParticipantId = participantIdTextField.getText();
					int participantId = Integer.parseInt(stringParticipantId);
					
					Guest guestObj = guestCtr.searchGuestById(participantId);
					
					if(guestObj == null)
					{
						JOptionPane.showMessageDialog(null, "There is no guest by this id. Please insert a valid guest id.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						if(teamCtr.checkTeamParticipantInstances(teamId, participantId) == false)
						{
							JOptionPane.showMessageDialog(null, "That particular participant is already in the team.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							teamCtr.insertTeamParticipant(teamId, participantId);
							JOptionPane.showMessageDialog(null, "Participant has been successfully added to your team.", "Info", JOptionPane.INFORMATION_MESSAGE);
							
							clearTeamTab();
							clearParticipantsTable();
							clearTeamTable();
						}
					}
				}
			}
		});
		addParticipantButton.setBounds(6, 16, 125, 25);
		participantsOptions.add(addParticipantButton);
		addParticipantButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton removeParticipantButton = new JButton("Remove");
		removeParticipantButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(allTeamsComboBox.getSelectedItem() == null || participantIdTextField.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert both the team id and the participant id", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String stringTeamId = String.valueOf(allTeamsComboBox.getSelectedItem());
					int teamId = Integer.parseInt(stringTeamId);
					
					String stringParticipantId = participantIdTextField.getText();
					int participantId = Integer.parseInt(stringParticipantId);
					
					Guest guestObj = guestCtr.searchGuestById(participantId);
					
					if(guestObj == null)
					{
						JOptionPane.showMessageDialog(null, "There is no guest by this id. Please insert a valid guest id.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						if(participantId == universalId)
						{
							JOptionPane.showMessageDialog(null, "You may not delete yourself from your own team.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							teamCtr.deleteTeamParticipant(teamId, participantId);
							JOptionPane.showMessageDialog(null, "Participant has been successfully removed from your team.", "Info", JOptionPane.INFORMATION_MESSAGE);
							
							clearTeamTab();
							clearParticipantsTable();
							clearTeamTable();
						}
					}
				}
			}
		});
		removeParticipantButton.setBounds(6, 52, 125, 25);
		participantsOptions.add(removeParticipantButton);
		removeParticipantButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton getAllTeamParticipantsButton = new JButton("All");
		getAllTeamParticipantsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(allTeamsComboBox.getSelectedItem() == null)
				{
					JOptionPane.showMessageDialog(null, "Please select the team first.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					clearTeamTable();
					clearParticipantsTable();
					
					participantsTable.setModel(getParticipantsTableModel());
				}
			}
		});
		getAllTeamParticipantsButton.setBounds(6, 88, 125, 25);
		participantsOptions.add(getAllTeamParticipantsButton);
		getAllTeamParticipantsButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JScrollPane participantsScrollPane = new JScrollPane();
		participantsScrollPane.setBounds(177, 237, 440, 120);
		panel_1.add(participantsScrollPane);
		
		participantsTable = new JTable();
		participantsTable.setEnabled(false);
		participantsTable.setFillsViewportHeight(true);
		participantsScrollPane.setViewportView(participantsTable);
		
		JButton teamClearAllButton = new JButton("Clear all");
		teamClearAllButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearTeamTab();
				clearParticipantsTable();
				clearTeamTable();
			}
		});
		teamClearAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		teamClearAllButton.setBounds(31, 382, 125, 25);
		panel_1.add(teamClearAllButton);
		
		JButton getLeaderTeamsButton = new JButton("Get teams");
		getLeaderTeamsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				allTeamsComboBox.removeAllItems();
				LinkedList<Team> allLeaderTeams = new LinkedList<Team>();
				allLeaderTeams = teamCtr.getTeamsByLeaderId(universalId);
				if(allLeaderTeams.isEmpty() == false)
				{
					for(Team teamObj : allLeaderTeams)
					{
						allTeamsComboBox.addItem(String.valueOf(teamObj.getId()));
					}
				}
				else
				{
					allTeamsComboBox.removeAll();
				}
			}
		});
		getLeaderTeamsButton.setFont(new Font("Arial", Font.PLAIN, 11));
		getLeaderTeamsButton.setBounds(187, 27, 125, 25);
		panel_1.add(getLeaderTeamsButton);
		
		JButton logOffButton = new JButton("Log Off");
		logOffButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				bookingId = 0;
				frame.dispose();
				LoginMenu loginMenu = LoginMenu.getInstance();
				loginMenu.frame.setVisible(true);
			}
		});
		logOffButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		logOffButton.setBounds(849, 562, 125, 39);
		frame.getContentPane().add(logOffButton);
	}
	
	public void setGuestName(String guestName)
	{
		dinamicLabel.setText(guestName);
	}
	
	public void setUniversalId(int newUniversalId)
	{
		universalId = newUniversalId;
	}
	
	//model for the teams table
	private DefaultTableModel getTeamTableModel()
	{
		int leaderId = universalId;
		LinkedList<Team> leaderTeams = teamCtr.getTeamsByLeaderId(leaderId);
		
		DefaultTableModel teamTableModel = new DefaultTableModel();
		
		//the team table heads
		teamTableModel.setColumnIdentifiers(new String[] {"Team id", "Number of participants"});
		
		//the team table contents
		if(leaderTeams.isEmpty() == false)
		{
			for(Team teamObj : leaderTeams)
			{
				teamTableModel.addRow(new String[]
						{
							String.valueOf(teamObj.getId()),
							String.valueOf(teamObj.getNumberOfParticipants())
						});
			}
		}
		
		return teamTableModel;
	}
	
	//model for the team participants table
	private DefaultTableModel getParticipantsTableModel()
	{
		String stringTeamId = String.valueOf(allTeamsComboBox.getSelectedItem());
		int teamId = Integer.parseInt(stringTeamId);
		
		LinkedList<Participant> teamParticipants = teamCtr.getTeamParticipantsByTeamId(teamId);
		
		DefaultTableModel participantsTableModel = new DefaultTableModel();
		
		//the team table heads
		participantsTableModel.setColumnIdentifiers(new String[] {"Team id", "Participant id", "Participant"});
		
		//the team table contents
		if(teamParticipants.isEmpty() == false)
		{
			for(Participant participantObj : teamParticipants)
			{
				participantsTableModel.addRow(new String[]
						{
							String.valueOf(participantObj.getTeam().getId()),
							String.valueOf(participantObj.getGuest().getId()),
							participantObj.getGuest().getName()
						});
			}
		}
		
		return participantsTableModel;
	}
	
	//clear the team table
	private void clearTeamTable()
	{
		teamTable.setModel(new DefaultTableModel());		
	}
	
	//clear the team participants table
	private void clearParticipantsTable()
	{
		participantsTable.setModel(new DefaultTableModel());	
	}
	
	//clear the team tab fields
	private void clearTeamTab()
	{
		allTeamsComboBox.setSelectedItem(null);
		participantIdTextField.setText("");
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
	
	private DefaultTableModel getActivityBookingTableModel()
	{
		int guestId = universalId;
		
		LinkedList<ActivityBooking> activityBookingList = new LinkedList<ActivityBooking>();
		activityBookingList = activityBookingCtr.getActivityBookingsForGuest(guestId);
				
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
	
}