package GUI;

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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Controller.ActivityBookingCtr;
import Controller.ActivityCtr;
import Controller.DateCheck;
import Controller.FacilityCtr;
import Controller.InstructorCtr;
import Controller.PersonCtr;
import Controller.TeamCtr;
import Model.ActivityBooking;
import Model.ActivityType;
import Model.Facility;
import Model.Instructor;
import Model.Team;

public class GuestActivityBookingMenu
{
	private int universalId;
	private int bookingId;
	
	private DateCheck dateCheck = new DateCheck();
	private TeamCtr teamCtr = new TeamCtr();
	private ActivityCtr activityCtr = new ActivityCtr();
	private ActivityBookingCtr activityBookingCtr = new ActivityBookingCtr();
	private FacilityCtr facilityCtr = new FacilityCtr();
	private InstructorCtr instructorCtr = new InstructorCtr();
	private PersonCtr personCtr = new PersonCtr();
	

	private JFrame frame;
	private JTextField textField_1;
	private JLabel lblTeamId;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;
	private JLabel lblParticipants;
	private JTextField textField_2;
	private JLabel lblDate;
	private JPanel panel_4;
	private JComboBox<String> comboBox_4;
	private JPanel panel_5;
	private JComboBox<String> comboBox_5;
	private JPanel panel_6;
	private JComboBox<String> comboBox_6;
	private JPanel panel;
	private JButton btnNewButton_1;
	private JTextField textField_3;
	private JLabel lblMaxParticipants;
	private JComboBox<String> comboBox_3;
	private JLabel lblInstructorId;
	private JComboBox<String> comboBox_7;
	private JPanel panel_3;
	private JButton btnGetInstructors;
	private JPanel panel_9;
	private JButton btnSchedule;
	private JButton btnGetInstructors_1;
	private JButton btnRemove_1;
	private JTable table_1;
	private JButton button_1;
	private JButton button_3;

	/**
	 * Create the application.
	 */
	public GuestActivityBookingMenu()
	{
		initialize();
	}
	
	public void setUniversalId(int newUniversalId)
	{
		this.universalId = newUniversalId;
		String stringId = String.valueOf(newUniversalId);
		textField_1.setText(stringId);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame("Activity booking");
		frame.setBounds(100, 100, 834, 637);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Team", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(6, 465, 247, 130);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(137, 14, 100, 20);
		LinkedList<Team> leaderTeamList = new LinkedList<Team>();
		leaderTeamList = teamCtr.getTeamsByLeaderId(universalId);
		for(Team teamObj : leaderTeamList)
		{
			int leaderId = teamObj.getId();
			String stringLeaderId = String.valueOf(leaderId);
			comboBox.addItem(stringLeaderId);
		}
		comboBox.setSelectedItem(null);
		comboBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(comboBox.getSelectedItem().equals(null) != true)
				{
					String stringTeamId = (String) comboBox.getSelectedItem();
					int teamId = Integer.parseInt(stringTeamId);
					Team teamObj = new Team();
					teamObj = teamCtr.getTeamById(teamId);
					int numberOfParticipants = teamObj.getNumberOfParticipants();
					String stringNumberOfParticipants = String.valueOf(numberOfParticipants);
					textField_2.setText(stringNumberOfParticipants);
				}
				else
				{
					textField_2.setText("");
				}
			}
		});
		panel_1.add(comboBox);
		
		lblParticipants = new JLabel("Participants:");
		lblParticipants.setBounds(6, 45, 90, 14);
		panel_1.add(lblParticipants);
		
		lblTeamId = new JLabel("Team ID:");
		lblTeamId.setBounds(6, 17, 90, 14);
		panel_1.add(lblTeamId);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(121, 42, 116, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		final JButton btnNewButton = new JButton("Get teams");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				String stringGuestId = textField_1.getText();
				int guestId = Integer.parseInt(stringGuestId);
				
				LinkedList<Team> leaderListTeam = new LinkedList<Team>();
				leaderListTeam = teamCtr.getTeamsByLeaderId(guestId);
				
				for(Team teamObj : leaderListTeam)
				{
					int teamId = teamObj.getId();
					String stringTeamId = String.valueOf(teamId);
					comboBox.addItem(stringTeamId);
				}
				
				comboBox_7.setSelectedItem(null);
				comboBox_7.removeAll();
				comboBox_7.setEnabled(false);
				
				btnGetInstructors_1.setEnabled(false);
				
			}
		});
		btnNewButton.setBounds(6, 70, 231, 23);
		panel_1.add(btnNewButton);
		
		JButton btnClear_2 = new JButton("Clear");
		btnClear_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				comboBox.setSelectedItem(null);
				comboBox.removeAll();
				textField_2.setText("");
				
				comboBox_7.setSelectedItem(null);
				comboBox_7.removeAll();
				comboBox_7.setEnabled(true);
				
				btnGetInstructors_1.setEnabled(true);
			}
		});
		btnClear_2.setBounds(6, 95, 231, 23);
		panel_1.add(btnClear_2);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 11, 247, 103);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblGuestId = new JLabel("Guest ID:");
		lblGuestId.setBounds(15, 22, 90, 14);
		panel.add(lblGuestId);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(76, 19, 165, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		lblDate = new JLabel("Date:");
		lblDate.setBounds(15, 68, 90, 14);
		panel.add(lblDate);
		
		panel_4 = new JPanel();
		panel_4.setBounds(49, 50, 57, 43);
		panel.add(panel_4);
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(null, "dd", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		comboBox_4 = new JComboBox<String>();
		comboBox_4.setBounds(6, 16, 45, 20);
		LinkedList<String> days = new LinkedList<String>();
		days = dateCheck.getDays();
		for(String day : days)
		{
			String insertedDay = day;
			comboBox_4.addItem(insertedDay);
		}
		comboBox_4.setSelectedItem(null);
		panel_4.add(comboBox_4);
		
		panel_5 = new JPanel();
		panel_5.setBounds(164, 50, 77, 43);
		panel.add(panel_5);
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(null, "yyyy", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		comboBox_5 = new JComboBox<String>();
		comboBox_5.setBounds(6, 16, 65, 20);
		LinkedList<String> years = new LinkedList<String>();
		years = dateCheck.getYears();
		for(String year : years)
		{
			String insertedYear = year;
			comboBox_5.addItem(insertedYear);
		}
		comboBox_5.setSelectedItem(null);
		panel_5.add(comboBox_5);
		
		panel_6 = new JPanel();
		panel_6.setBounds(108, 50, 56, 43);
		panel.add(panel_6);
		panel_6.setLayout(null);
		panel_6.setBorder(new TitledBorder(null, "MM", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		comboBox_6 = new JComboBox<String>();
		comboBox_6.setBounds(6, 16, 45, 20);
		LinkedList<String> months = new LinkedList<String>();
		months = dateCheck.getMonths();
		for(String month : months)
		{
			String insertedMonth = month;
			comboBox_6.addItem(insertedMonth);
		}
		comboBox_6.setSelectedItem(null);
		panel_6.add(comboBox_6);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Activity booking", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(6, 125, 247, 117);
		frame.getContentPane().add(panel_7);
		panel_7.setLayout(null);
		
		btnNewButton_1 = new JButton("Create");
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String stringGuestId = textField_1.getText();
				int guestId = Integer.parseInt(stringGuestId);
				String status = "Made";
				
				if(comboBox_4.getSelectedItem().equals(null) == true || comboBox_6.getSelectedItem().equals(null)  == true || comboBox_5.getSelectedItem().equals(null) == true )
				{
					JOptionPane.showMessageDialog(null, "Please insert the date of booking.", "Error!", JOptionPane.ERROR_MESSAGE);
					
					button_3.setEnabled(false);
					button_1.setEnabled(false);
					btnRemove_1.setEnabled(false);
				}
				else
				{
					String day = (String) comboBox_4.getSelectedItem();
					String month = (String) comboBox_6.getSelectedItem();
					String year = (String) comboBox_5.getSelectedItem();
					String date = day + "-" + month + "-" + year;
					
					if(DateCheck.isDateValid(date) != true)
					{
						JOptionPane.showMessageDialog(null, "Inserted date is incorrect. Please insert a valid date", "Error!", JOptionPane.ERROR_MESSAGE);
						
						button_3.setEnabled(false);
						button_1.setEnabled(false);
						btnRemove_1.setEnabled(false);
					}
					else
					{
						if(dateCheck.checkIfDateIsOlder(date) != true)
						{
							JOptionPane.showMessageDialog(null, "Cannot insert a date older than the current date.", "Error!", JOptionPane.ERROR_MESSAGE);
							
							button_3.setEnabled(false);
							button_1.setEnabled(false);
							btnRemove_1.setEnabled(false);
						}
						else
						{
							if(activityBookingCtr.activityBookingCompleteCheck(date, guestId) != true)
							{
								JOptionPane.showMessageDialog(null, "Cannot make two bookings on the same date.", "Error!", JOptionPane.ERROR_MESSAGE);
								
								button_3.setEnabled(false);
								button_1.setEnabled(false);
								btnRemove_1.setEnabled(false);
							}
							else
							{
								activityBookingCtr.insertActivityBooking(guestId, date, status);
								JOptionPane.showMessageDialog(null, "Complete activity booking made successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);								
								
								button_1.setEnabled(true);
								button_3.setEnabled(true);
								btnRemove_1.setEnabled(true);
								
								ActivityBooking activityBookingObj = new ActivityBooking();
								activityBookingObj = activityBookingCtr.getActivityBookingForDate(guestId, date);
								bookingId = activityBookingObj.getId();
							}
						}
					}
				}
			}
		});
		btnNewButton_1.setBounds(10, 16, 227, 23);
		panel_7.add(btnNewButton_1);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String stringGuestId = textField_1.getText();
				int guestId = Integer.parseInt(stringGuestId);
				
				if(comboBox_4.getSelectedItem().equals(null) == true || comboBox_6.getSelectedItem().equals(null)  == true ||
						comboBox_5.getSelectedItem().equals(null) == true )
				{
					JOptionPane.showMessageDialog(null, "Please insert the date of booking.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String day = (String) comboBox_4.getSelectedItem();
					String month = (String) comboBox_6.getSelectedItem();
					String year = (String) comboBox_5.getSelectedItem();
					String date = day + "-" + month + "-" + year;
					
					if(DateCheck.isDateValid(date) != true)
					{
						JOptionPane.showMessageDialog(null, "Inserted date is incorrect. Please insert a valid date", "Error!", JOptionPane.ERROR_MESSAGE);
						button_3.setEnabled(false);
						button_1.setEnabled(false);
						btnRemove_1.setEnabled(false);
					}
					else
					{
						ActivityBooking activityBookingObj = new ActivityBooking();
						activityBookingObj = activityBookingCtr.getActivityBookingForDate(guestId, date);
						if(activityBookingObj == null)
						{
							JOptionPane.showMessageDialog(null, "There is no activity booking by this date. Please insert a valid activity booking date.", "Error!", JOptionPane.ERROR_MESSAGE);
							button_3.setEnabled(false);
							button_1.setEnabled(false);
							btnRemove_1.setEnabled(false);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Activity booking search successful.", "Info", JOptionPane.INFORMATION_MESSAGE);
							button_3.setEnabled(true);
							button_1.setEnabled(true);
							btnRemove_1.setEnabled(true);
							
							bookingId = activityBookingObj.getId();
						}
					}
				}
			}
		});
		btnSearch.setBounds(10, 50, 227, 23);
		panel_7.add(btnSearch);
		
		JButton btnAll = new JButton("All");
		btnAll.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				table_1.setModel(getUpModel());
			}
		});
		btnAll.setBounds(10, 84, 227, 23);
		panel_7.add(btnAll);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Activity", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(6, 253, 218, 97);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Activity:");
		lblNewLabel_1.setBounds(6, 19, 66, 14);
		panel_2.add(lblNewLabel_1);
		
		comboBox_1 = new JComboBox<String>();
		LinkedList<ActivityType> allActivityTypeList = new LinkedList<ActivityType>();
		allActivityTypeList = activityCtr.getAllActivityTypes();
		for(ActivityType activityTypeObj : allActivityTypeList)
		{
			String activityTypeName = activityTypeObj.getName();
			comboBox_1.addItem(activityTypeName);
		}
		comboBox_1.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				comboBox_2.removeAllItems();
				comboBox_7.removeAllItems();
				if(comboBox_1.getSelectedItem().equals(null) != true)
				{
					String activityName = (String) comboBox_1.getSelectedItem();
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = activityCtr.getActivityTypeByName(activityName);
					int maxNumberOfParticipants = activityTypeObj.getMaxParticipants();
					String stringMaxNumberOfParticipants = String.valueOf(maxNumberOfParticipants);
					textField_3.setText(stringMaxNumberOfParticipants);
				}
				else
				{
					textField_3.setText("");
				}
			}
		});
		comboBox_1.setSelectedItem(null);
		comboBox_1.setBounds(67, 16, 140, 20);
		panel_2.add(comboBox_1);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(112, 41, 97, 20);
		panel_2.add(textField_3);
		
		lblMaxParticipants = new JLabel("Max participants:");
		lblMaxParticipants.setBounds(6, 44, 113, 14);
		panel_2.add(lblMaxParticipants);
		
		comboBox_3 = new JComboBox<String>();
		LinkedList<String> activityBookingHours = new LinkedList<String>();
		activityBookingHours = dateCheck.getStartHours();
		for(String hour : activityBookingHours)
		{
			String insertedHour = hour;
			comboBox_3.addItem(insertedHour);
		}
		comboBox_3.setSelectedItem(null);
		comboBox_3.setBounds(112, 66, 97, 20);
		panel_2.add(comboBox_3);
		
		JLabel lblStartHour = new JLabel("Start hour:");
		lblStartHour.setBounds(6, 69, 66, 14);
		panel_2.add(lblStartHour);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(263, 21, 442, 207);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		table_1.setFillsViewportHeight(true);
		scrollPane.setViewportView(table_1);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(715, 21, 90, 31);
		frame.getContentPane().add(btnClose);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(715, 63, 90, 31);
		frame.getContentPane().add(btnClear);
		
		panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setBounds(6, 361, 218, 84);
		frame.getContentPane().add(panel_9);
		panel_9.setLayout(null);
		
		JLabel lblFacility = new JLabel("Facility:");
		lblFacility.setBounds(6, 19, 66, 14);
		panel_9.add(lblFacility);
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(65, 16, 143, 20);
		panel_9.add(comboBox_2);
		
		btnGetInstructors = new JButton("Get facilities");
		btnGetInstructors.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(comboBox_1.getSelectedItem().equals(null) == true)
				{
					JOptionPane.showMessageDialog(null,  "Please insert the activity first.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String activityName = (String) comboBox_1.getSelectedItem();
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = activityCtr.getActivityTypeByName(activityName);
					int activityId = activityTypeObj.getID();
					
					String desiredStatus = "Available";
					
					LinkedList<Facility> availableFacilitiesForActivity = new LinkedList<Facility>();
					availableFacilitiesForActivity = facilityCtr.getAvailableFacilitiesForActivity(activityId, desiredStatus);
					
					for(Facility facilityObj : availableFacilitiesForActivity)
					{
						String facilityName = facilityObj.getName();
						comboBox_2.addItem(facilityName);
					}
				}
			}
		});
		btnGetInstructors.setBounds(6, 44, 202, 23);
		panel_9.add(btnGetInstructors);
		
		btnSchedule = new JButton("Schedule");
		btnSchedule.setBounds(715, 105, 90, 31);
		frame.getContentPane().add(btnSchedule);
		
		panel_3 = new JPanel();
		panel_3.setBounds(263, 465, 264, 130);
		frame.getContentPane().add(panel_3);
		panel_3.setBorder(new TitledBorder(null, "Instructor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setLayout(null);
		
		lblInstructorId = new JLabel("Instructor:");
		lblInstructorId.setBounds(6, 19, 90, 14);
		panel_3.add(lblInstructorId);
		
		comboBox_7 = new JComboBox<String>();
		comboBox_7.setBounds(62, 16, 192, 20);
		panel_3.add(comboBox_7);
		
		btnGetInstructors_1 = new JButton("Get instructors");
		btnGetInstructors_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(comboBox_1.getSelectedItem().equals(null) == true)
				{
					JOptionPane.showMessageDialog(null,  "Please insert the activity first.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String activityName = (String) comboBox_1.getSelectedItem();
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = activityCtr.getActivityTypeByName(activityName);
					int activityId = activityTypeObj.getID();
					
					String desiredStatus = "Available";
					
					LinkedList<Instructor> availableInstructorForActivity = new LinkedList<Instructor>();
					availableInstructorForActivity = instructorCtr.getAvailableInstructorsForActivityList(activityId, desiredStatus);
					
					for(Instructor instructorObj : availableInstructorForActivity)
					{
						String instructorName = instructorObj.getName();
						comboBox_7.addItem(instructorName);
					}
					
					comboBox.setSelectedItem(null);
					comboBox.removeAll();
					comboBox.setEnabled(false);
					
					textField_2.setText("");
					
					btnNewButton.setEnabled(false);
				}
			}
		});
		btnGetInstructors_1.setBounds(6, 61, 248, 23);
		panel_3.add(btnGetInstructors_1);
		
		JButton btnClear_1 = new JButton("Clear");
		btnClear_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				comboBox_7.setSelectedItem(null);
				comboBox_7.removeAll();
				
				comboBox.setSelectedItem(null);
				comboBox.removeAll();
				comboBox.setEnabled(true);
				
				textField_2.setText("");
				
				btnNewButton.setEnabled(true);
			}
		});
		btnClear_1.setBounds(6, 95, 248, 23);
		panel_3.add(btnClear_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(234, 264, 571, 190);
		frame.getContentPane().add(scrollPane_1);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(537, 465, 257, 130);
		frame.getContentPane().add(panel_8);
		panel_8.setLayout(null);
		panel_8.setBorder(new TitledBorder(null, "Activity line", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		button_1 = new JButton("Create");
		button_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(comboBox_1.getSelectedItem().equals(null) == true || comboBox_3.getSelectedItem().equals(null) == true || comboBox_2.getSelectedItem().equals("") == true ||
						comboBox_4.getSelectedItem().equals(null) == true || comboBox_5.getSelectedItem().equals(null) == true || comboBox_6.getSelectedItem().equals(null) == true)
				{
					JOptionPane.showMessageDialog(null, "Please select the activity, facility, date and starting hour for the activity you wish to book.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					//dd-MM-yyyy hh:mm
					String day = (String) comboBox_4.getSelectedItem();
					String month = (String) comboBox_6.getSelectedItem();
					String year = (String) comboBox_5.getSelectedItem();
					String startHour = (String) comboBox_3.getSelectedItem();
					String date = day + "-" + month + "-" + year;
					String time = day + "-" + month + "-" + year + " " + startHour;
					
					String activityName = (String) comboBox_1.getSelectedItem();
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = activityCtr.getActivityTypeByName(activityName);
					int activityId = activityTypeObj.getID();
					
					String facilityName = (String) comboBox_2.getSelectedItem();
					Facility facilityObj = new Facility();
					facilityObj = facilityCtr.getFacilityByName(facilityName);
					int facilityId = facilityObj.getId();
					
					String stringTeamId = (String) comboBox.getSelectedItem();
					int teamId = 0;
					if(stringTeamId != null)
					{
						teamId = Integer.parseInt(stringTeamId);
					}
					
					String instructorName = (String) comboBox_7.getSelectedItem();
					int instructorId = 0;
					if(instructorName != null)
					{
						Instructor instructorObj = new Instructor();
						instructorObj = (Instructor) personCtr.searchPersonByName(instructorName);
						instructorId = instructorObj.getId();
					}
					
					if(DateCheck.isDateValid(date) != true)
					{
						JOptionPane.showMessageDialog(null, "Inserted date is incorrect. Please insert a valid date", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						if(dateCheck.checkIfTimeIsOlder(time) != true)
						{
							JOptionPane.showMessageDialog(null, "Cannot insert a time before current time", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							if(activityBookingCtr.checkNumberOfActivityLinesInActivityBooking(bookingId, date) != true)
							{
								JOptionPane.showMessageDialog(null, "Cannot book more than 4 activities per day.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								if(activityBookingCtr.checkActivityLineInstances1(activityId, bookingId, date, startHour, facilityId) != true)
								{
									JOptionPane.showMessageDialog(null, "Cannot book the same activity multiple times per day.", "Error!", JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									if(teamId != 0)
									{
										activityBookingCtr.insertTeamActivityLine(activityId, instructorId, date, startHour, facilityId, teamId);
										JOptionPane.showMessageDialog(null, "Team activity successfully booked.", "Info", JOptionPane.INFORMATION_MESSAGE);
									}
									if(instructorId != 0)
									{
										activityBookingCtr.insertInstructorActivityLine(activityId, bookingId, date, startHour, facilityId, instructorId);
										JOptionPane.showMessageDialog(null, "Instructor activity successfully booked.", "Info", JOptionPane.INFORMATION_MESSAGE);
									}
									if(teamId == 0 && instructorId == 0)
									{
										activityBookingCtr.insertSimpleActivityLine(activityId, bookingId, date, startHour, facilityId);
										JOptionPane.showMessageDialog(null, "Activity successfully booked.", "Info", JOptionPane.INFORMATION_MESSAGE);
									}
								}
							}
						}
					}
				}
			}
		});
		button_1.setEnabled(false);
		button_1.setBounds(10, 16, 237, 23);
		panel_8.add(button_1);
		
		button_3 = new JButton("All");
		button_3.setEnabled(false);
		button_3.setBounds(10, 50, 237, 23);
		panel_8.add(button_3);
		
		btnRemove_1 = new JButton("Remove");
		btnRemove_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(comboBox_1.getSelectedItem().equals(null) == true || comboBox_3.getSelectedItem().equals(null) == true || comboBox_4.getSelectedItem().equals(null) == true || comboBox_5.getSelectedItem().equals(null) == true || comboBox_6.getSelectedItem().equals(null) == true)
				{
					JOptionPane.showMessageDialog(null, "Please select the activity, date and starting hour for the activity you wish to remove.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					//dd-MM-yyyy hh:mm
					String day = (String) comboBox_4.getSelectedItem();
					String month = (String) comboBox_6.getSelectedItem();
					String year = (String) comboBox_5.getSelectedItem();
					String startHour = (String) comboBox_3.getSelectedItem();
					String date = day + "-" + month + "-" + year;
					String time = day + "-" + month + "-" + year + " " + startHour;
					
					String activityName = (String) comboBox_1.getSelectedItem();
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = activityCtr.getActivityTypeByName(activityName);
					int activityId = activityTypeObj.getID();
					
					if(DateCheck.isDateValid(date) != true)
					{
						JOptionPane.showMessageDialog(null, "Inserted date is incorrect. Please insert a valid date", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						if(dateCheck.checkIfTimeIsOlder(time) != true)
						{
							JOptionPane.showMessageDialog(null, "Cannot remove activities from past bookings.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							activityBookingCtr.deleteActivityLine(activityId, bookingId, date, startHour);
							JOptionPane.showMessageDialog(null, "Activity successfully removed from booking.", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
		btnRemove_1.setEnabled(false);
		btnRemove_1.setBounds(10, 84, 237, 23);
		panel_8.add(btnRemove_1);
	}
	
	public DefaultTableModel getUpModel()
	{
		String stringGuestId = textField_1.getText();
		int guestId = Integer.parseInt(stringGuestId);
		
		LinkedList<ActivityBooking> activityBookingList = new LinkedList<ActivityBooking>();
		activityBookingList = activityBookingCtr.getActivityBookingsForGuest(guestId);
				
		DefaultTableModel modelUp = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			public boolean IsCellEditable(int row, int column)
			{
				//all cells false
				return false;
			}
		};
		
		modelUp.setColumnIdentifiers(new String[] {"BookingId", "GuestId", "Date"});
		
		try
		{
			for(ActivityBooking activityBookingObj : activityBookingList)
			{
				modelUp.addRow(new String[]
						{
						String.valueOf(activityBookingObj.getId()),
						String.valueOf(activityBookingObj.getGuest().getId()),
						activityBookingObj.getDate()
						});
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception: " + e);
		}
		
		return modelUp;

	}
}
