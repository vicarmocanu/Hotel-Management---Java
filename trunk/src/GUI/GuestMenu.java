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
import Controller.DateCheck;
import Controller.GuestCtr;
import Controller.TeamCtr;
import Model.ActivityBooking;
import Model.Guest;
import Model.Participant;
import Model.Team;

public class GuestMenu
{
	private int universalId;
	private int bookingId;
	
	private TeamCtr teamCtr = new TeamCtr();
	private GuestCtr guestCtr = new GuestCtr();
	private DateCheck dateCheck = new DateCheck();
	private ActivityBookingCtr activityBookingCtr = new ActivityBookingCtr();
	
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
		frame.setBounds(100, 100, 850, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		
		//frmSupplierInformation.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 835, 535);
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
		activityBookingPanel.setBounds(10, 11, 237, 177);
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
					String date = day + "-" + month + "-" + year;
					
					if(DateCheck.isDateValid(date) != true)
					{
						JOptionPane.showMessageDialog(null, "Inserted date is incorrect. Please insert a valid date", "Error!", JOptionPane.ERROR_MESSAGE);
						activityLineDisabler();
					}
					else
					{
						if(dateCheck.checkIfDateIsOlder(date) != true)
						{
							JOptionPane.showMessageDialog(null, "Cannot insert a date older than the current date.", "Error!", JOptionPane.ERROR_MESSAGE);
							activityLineDisabler();
						}
						else
						{
							if(activityBookingCtr.activityBookingCompleteCheck(date, guestID) != true)
							{
								JOptionPane.showMessageDialog(null, "Cannot make two bookings on the same date.", "Error!", JOptionPane.ERROR_MESSAGE);
								activityLineDisabler();
							}
							else
							{
								activityBookingCtr.insertActivityBooking(guestID, date, activityBookingStatus);
								JOptionPane.showMessageDialog(null, "Activity booking made successfully. You can add up to four activities.", "Info", JOptionPane.INFORMATION_MESSAGE);
								
								ActivityBooking activityBookingObj = new ActivityBooking();
								activityBookingObj = activityBookingCtr.getActivityBookingForDate(guestID, date, activityBookingStatus);
								bookingId = activityBookingObj.getId();
								
								activityLineEnabler();
							}
						}
					}
				}
				
			
			}
		});
		createActivityBookingButton.setBounds(4, 70, 225, 25);
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
					activityLineDisabler();
				}
				else
				{
					String day = (String) dayComboBox.getSelectedItem();
					String month = (String) monthComboBox.getSelectedItem();
					String year = (String) yearComboBox.getSelectedItem();
					String date = day + "-" + month + "-" + year;
					
					if(DateCheck.isDateValid(date) != true)
					{
						JOptionPane.showMessageDialog(null, "Inserted date is incorrect. Please insert a valid date", "Error!", JOptionPane.ERROR_MESSAGE);
						activityLineDisabler();
					}
					else
					{
						ActivityBooking activityBookingObj = new ActivityBooking();
						activityBookingObj = activityBookingCtr.getActivityBookingForDate(guestId, date, "Made");
						if(activityBookingObj == null)
						{
							JOptionPane.showMessageDialog(null, "There is no made activity booking by this date. Please insert a valid activity booking date.", "Error!", JOptionPane.ERROR_MESSAGE);
							activityLineDisabler();
						}
						else
						{
							activityLineEnabler();
							bookingId = activityBookingObj.getId();
						}
					}
				}
			}
		});
		searchActivityBookingButton.setBounds(4, 105, 225, 25);
		activityBookingPanel.add(searchActivityBookingButton);
		searchActivityBookingButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton getAllActivityBookingsButton = new JButton("All");
		getAllActivityBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				activityLineDisabler();
				
				activityBookingsTable.setModel(getActivityBookingTableModel());
			}
		});
		getAllActivityBookingsButton.setBounds(6, 141, 225, 25);
		activityBookingPanel.add(getAllActivityBookingsButton);
		getAllActivityBookingsButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JPanel activityPanel = new JPanel();
		activityPanel.setBorder(new TitledBorder(null, "Activity", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityPanel.setBounds(10, 199, 237, 93);
		panel_2.add(activityPanel);
		activityPanel.setLayout(null);
		
		JLabel activityLabel = new JLabel("Activity:");
		activityLabel.setBounds(6, 19, 90, 14);
		activityPanel.add(activityLabel);
		activityLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		allAvailableActivitiesComboBox = new JComboBox<String>();
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
		getInstructorsButton.setEnabled(false);
		getInstructorsButton.setBounds(6, 40, 225, 25);
		instructorPanel.add(getInstructorsButton);
		getInstructorsButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		instructorClearButton = new JButton("Clear");
		instructorClearButton.setEnabled(false);
		instructorClearButton.setBounds(6, 68, 225, 25);
		instructorPanel.add(instructorClearButton);
		instructorClearButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JPanel teamPanel = new JPanel();
		teamPanel.setLayout(null);
		teamPanel.setBorder(new TitledBorder(null, "Team", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		teamPanel.setBounds(257, 389, 237, 100);
		panel_2.add(teamPanel);
		
		JLabel activityTeamIdLabel = new JLabel("Team:");
		activityTeamIdLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		activityTeamIdLabel.setBounds(6, 19, 90, 14);
		teamPanel.add(activityTeamIdLabel);
		
		activityBookingAllTeamsComboBox = new JComboBox<String>();
		activityBookingAllTeamsComboBox.setEnabled(false);
		activityBookingAllTeamsComboBox.setFont(new Font("Arial", Font.PLAIN, 11));
		activityBookingAllTeamsComboBox.setBounds(60, 16, 170, 20);
		teamPanel.add(activityBookingAllTeamsComboBox);
		
		getTeamsButton = new JButton("Get teams");
		getTeamsButton.setEnabled(false);
		getTeamsButton.setFont(new Font("Arial", Font.PLAIN, 11));
		getTeamsButton.setBounds(6, 40, 225, 25);
		teamPanel.add(getTeamsButton);
		
		teamClearButton = new JButton("Clear");
		teamClearButton.setEnabled(false);
		teamClearButton.setFont(new Font("Arial", Font.PLAIN, 11));
		teamClearButton.setBounds(6, 68, 225, 25);
		teamPanel.add(teamClearButton);
		
		JPanel activityLine = new JPanel();
		activityLine.setBorder(new TitledBorder(null, "Activity line", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityLine.setBounds(583, 389, 237, 107);
		panel_2.add(activityLine);
		activityLine.setLayout(null);
		
		addActivityLineButton = new JButton("Add");
		addActivityLineButton.setEnabled(false);
		addActivityLineButton.setBounds(6, 16, 225, 25);
		activityLine.add(addActivityLineButton);
		addActivityLineButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		cancelActivityLineButton = new JButton("Cancel activity");
		cancelActivityLineButton.setEnabled(false);
		cancelActivityLineButton.setBounds(6, 46, 225, 25);
		activityLine.add(cancelActivityLineButton);
		cancelActivityLineButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		allActivityLinesButton = new JButton("All");
		allActivityLinesButton.setEnabled(false);
		allActivityLinesButton.setBounds(6, 75, 225, 25);
		activityLine.add(allActivityLinesButton);
		allActivityLinesButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JPanel activityBookingTablePanel = new JPanel();
		activityBookingTablePanel.setBorder(new TitledBorder(null, "Activity bookings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityBookingTablePanel.setBounds(257, 11, 563, 155);
		panel_2.add(activityBookingTablePanel);
		activityBookingTablePanel.setLayout(null);
		
		JScrollPane activityBookingsScrollPane = new JScrollPane();
		activityBookingsScrollPane.setBounds(6, 16, 547, 127);
		activityBookingTablePanel.add(activityBookingsScrollPane);
		
		activityBookingsTable = new JTable();
		activityBookingsTable.setFillsViewportHeight(true);
		activityBookingsScrollPane.setViewportView(activityBookingsTable);
		
		JPanel activityLinesTablePanel = new JPanel();
		activityLinesTablePanel.setLayout(null);
		activityLinesTablePanel.setBorder(new TitledBorder(null, "Activity lines", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityLinesTablePanel.setBounds(257, 177, 563, 201);
		panel_2.add(activityLinesTablePanel);
		
		JScrollPane activityLinesScrollPane = new JScrollPane();
		activityLinesScrollPane.setBounds(6, 16, 547, 174);
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
				frame.dispose();
				LoginMenu loginMenu = LoginMenu.getInstance();
				loginMenu.frame.setVisible(true);
			}
		});
		logOffButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		logOffButton.setBounds(715, 550, 125, 39);
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
		allAvailableActivitiesComboBox.setEnabled(true);		
		startHoursComboBox.setEnabled(true);
		
		availableFacilityComboBox.setEnabled(true);
		getAvailableFacilitiesButton.setEnabled(true);
		
		availableInstructorsComboBox.setEnabled(true);
		getInstructorsButton.setEnabled(true);
		instructorClearButton.setEnabled(true);
		
		activityBookingAllTeamsComboBox.setEnabled(true);
		getTeamsButton.setEnabled(true);
		teamClearButton.setEnabled(true);
		
		addActivityLineButton.setEnabled(true);
		cancelActivityLineButton.setEnabled(true);
		allActivityLinesButton.setEnabled(true);
	}
	
	public void activityLineDisabler()
	{
		allAvailableActivitiesComboBox.setEnabled(false);		
		startHoursComboBox.setEnabled(false);
		
		availableFacilityComboBox.setEnabled(false);
		getAvailableFacilitiesButton.setEnabled(false);
		
		availableInstructorsComboBox.setEnabled(false);
		getInstructorsButton.setEnabled(false);
		instructorClearButton.setEnabled(false);
		
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
					String day = date.substring(0,2);
					String month = date.substring(3,5);
					String year = date.substring(6,10);
					
					dayComboBox.setSelectedItem(day);
					monthComboBox.setSelectedItem(month);
					yearComboBox.setSelectedItem(year);
				}
			}
		});
		
		
		return activityBookingTableModel;
	}
}