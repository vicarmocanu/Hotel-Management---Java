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
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controller.ActivityBookingCtr;
import Controller.ActivityCtr;
import Controller.DateCheck;
import Controller.FacilityCtr;
import Controller.InstructorCtr;
import Controller.PersonCtr;
import Controller.TeamCtr;
import Model.ActivityLine;
import Model.ActivityType;
import Model.Facility;
import Model.Instructor;
import Model.Team;

public class ActivityLinesMenu
{
	private ActivityCtr activityCtr = new ActivityCtr();
	private DateCheck dateCheck = new DateCheck();
	private TeamCtr teamCtr = new TeamCtr();
	private InstructorCtr instructorCtr = new InstructorCtr();
	private FacilityCtr facilityCtr = new FacilityCtr();
	private PersonCtr personCtr = new PersonCtr();
	private ActivityBookingCtr activityBookingCtr= new ActivityBookingCtr();

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton button_3;
	private JButton button_1;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_3;
	private JComboBox<String> comboBox_2;
	private JComboBox<String> comboBox_4;
	private JComboBox<String> comboBox_1;
	private JTable table;
	/**
	 * Create the application.
	 */
	public ActivityLinesMenu()
	{
		initialize();
		frame.setVisible(true);
	}
	
	public void setActivityBookingAttributes(String stringBookingId, String stringDate, String stringGuestId)
	{
		textField.setText(stringBookingId);
		textField_1.setText(stringDate);
		textField_2.setText(stringGuestId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame("Activity lines");
		frame.setBounds(100, 100, 810, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Activity booking attributes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 11, 218, 93);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Booking ID:");
		lblNewLabel.setBounds(6, 19, 81, 14);
		panel.add(lblNewLabel);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(6, 44, 81, 14);
		panel.add(lblDate);
		
		JLabel lblGuestId = new JLabel("Guest ID:");
		lblGuestId.setBounds(6, 69, 81, 14);
		panel.add(lblGuestId);
		
		textField = new JTextField();
		textField.setBounds(75, 16, 133, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(75, 41, 133, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(75, 66, 133, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Activity", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(6, 111, 218, 97);
		frame.getContentPane().add(panel_1);
		
		JLabel label = new JLabel("Activity:");
		label.setBounds(6, 19, 66, 14);
		panel_1.add(label);
		
		comboBox = new JComboBox<String>();
		LinkedList<ActivityType> allActivityTypeList = new LinkedList<ActivityType>();
		allActivityTypeList = activityCtr.getAllActivityTypes();
		for(ActivityType activityTypeObj : allActivityTypeList)
		{
			String activityTypeName = activityTypeObj.getName();
			comboBox.addItem(activityTypeName);
		}
		comboBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				comboBox_2.removeAllItems();
				comboBox_4.removeAllItems();
				if(comboBox.getSelectedItem().equals(null) != true)
				{
					String activityName = (String) comboBox.getSelectedItem();
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
		comboBox.setSelectedItem(null);
		comboBox.setBounds(67, 16, 140, 20);
		panel_1.add(comboBox);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(112, 41, 97, 20);
		panel_1.add(textField_3);
		
		JLabel label_1 = new JLabel("Max participants:");
		label_1.setBounds(6, 44, 113, 14);
		panel_1.add(label_1);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(112, 66, 97, 20);
		LinkedList<String> activityBookingHours = new LinkedList<String>();
		activityBookingHours = dateCheck.getStartHours();
		for(String hour : activityBookingHours)
		{
			String insertedHour = hour;
			comboBox_1.addItem(insertedHour);
		}
		comboBox_1.setSelectedItem(null);
		panel_1.add(comboBox_1);
		
		JLabel label_2 = new JLabel("Start hour:");
		label_2.setBounds(6, 69, 66, 14);
		panel_1.add(label_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Facility", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(6, 213, 218, 84);
		frame.getContentPane().add(panel_2);
		
		JLabel label_3 = new JLabel("Facility:");
		label_3.setBounds(6, 19, 66, 14);
		panel_2.add(label_3);
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(65, 16, 143, 20);
		panel_2.add(comboBox_2);
		
		JButton button = new JButton("Get facilities");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(comboBox.getSelectedItem().equals(null) == true)
				{
					JOptionPane.showMessageDialog(null,  "Please insert the activity first.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String activityName = (String) comboBox.getSelectedItem();
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
							comboBox_2.addItem(facilityName);
						}
					}
				}
			}
		});
		button.setBounds(6, 44, 202, 23);
		panel_2.add(button);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Team", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(6, 295, 247, 130);
		frame.getContentPane().add(panel_3);
		
		comboBox_3 = new JComboBox<String>();
		comboBox_3.setSelectedItem(null);
		comboBox_3.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(comboBox_3.getSelectedItem().equals(null) != true)
				{
					String stringTeamId = (String) comboBox_3.getSelectedItem();
					int teamId = Integer.parseInt(stringTeamId);
					Team teamObj = new Team();
					teamObj = teamCtr.getTeamById(teamId);
					int numberOfParticipants = teamObj.getNumberOfParticipants();
					String stringNumberOfParticipants = String.valueOf(numberOfParticipants);
					textField_4.setText(stringNumberOfParticipants);
				}
				else
				{
					textField_4.setText("");
				}

			}
		});
		comboBox_3.setBounds(137, 14, 100, 20);
		panel_3.add(comboBox_3);
		
		JLabel label_4 = new JLabel("Participants:");
		label_4.setBounds(6, 45, 90, 14);
		panel_3.add(label_4);
		
		JLabel label_5 = new JLabel("Team ID:");
		label_5.setBounds(6, 17, 90, 14);
		panel_3.add(label_5);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(89, 42, 148, 20);
		panel_3.add(textField_4);
		
		button_1 = new JButton("Get teams");
		button_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String stringGuestId = textField_2.getText();
				int guestId = Integer.parseInt(stringGuestId);
				
				LinkedList<Team> leaderListTeam = new LinkedList<Team>();
				leaderListTeam = teamCtr.getTeamsByLeaderId(guestId);
				
				for(Team teamObj : leaderListTeam)
				{
					int teamId = teamObj.getId();
					String stringTeamId = String.valueOf(teamId);
					comboBox_3.addItem(stringTeamId);
				}
				
				comboBox_4.setSelectedItem(null);
				comboBox_4.removeAll();
				comboBox_4.setEnabled(false);
				
				button_3.setEnabled(false);
			}
		});
		button_1.setBounds(6, 70, 231, 23);
		panel_3.add(button_1);
		
		JButton button_2 = new JButton("Clear");
		button_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				comboBox_3.setSelectedItem(null);
				comboBox_3.removeAll();
				textField_4.setText("");
				
				comboBox_4.setSelectedItem(null);
				comboBox_4.removeAll();
				comboBox_4.setEnabled(true);
				
				button_3.setEnabled(true);
			}
		});
		button_2.setBounds(6, 95, 231, 23);
		panel_3.add(button_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(null, "Instructor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(263, 295, 264, 130);
		frame.getContentPane().add(panel_4);
		
		JLabel label_6 = new JLabel("Instructor:");
		label_6.setBounds(6, 19, 90, 14);
		panel_4.add(label_6);
		
		comboBox_4 = new JComboBox<String>();
		comboBox_4.setBounds(71, 16, 183, 20);
		panel_4.add(comboBox_4);
		
		button_3 = new JButton("Get instructors");
		button_3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(comboBox.getSelectedItem().equals(null) == true)
				{
					JOptionPane.showMessageDialog(null,  "Please insert the activity first.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String activityName = (String) comboBox.getSelectedItem();
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = activityCtr.getActivityTypeByName(activityName);
					int activityId = activityTypeObj.getID();
					
					String desiredStatus = "Available";
					
					LinkedList<Instructor> availableInstructorForActivity = new LinkedList<Instructor>();
					availableInstructorForActivity = instructorCtr.getAvailableInstructorsForActivityList(activityId, desiredStatus);
					
					if(availableInstructorForActivity.isEmpty() == true)
					{
						JOptionPane.showMessageDialog(null, "No instructors.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						for(Instructor instructorObj : availableInstructorForActivity)
						{
							String instructorName = instructorObj.getName();
							comboBox_4.addItem(instructorName);
						}
						
						comboBox_3.setSelectedItem(null);
						comboBox_3.removeAll();
						comboBox_3.setEnabled(false);						
						textField_4.setText("");						
						button_1.setEnabled(false);
					}
				}
			}
			
		});
		button_3.setBounds(6, 61, 248, 23);
		panel_4.add(button_3);
		
		JButton button_4 = new JButton("Clear");
		button_4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				comboBox_4.setSelectedItem(null);
				comboBox_4.removeAll();
				
				comboBox_3.setSelectedItem(null);
				comboBox_3.removeAll();
				comboBox_3.setEnabled(true);
				textField_4.setText("");
				button_1.setEnabled(true);
			}
		});
		button_4.setBounds(6, 95, 248, 23);
		panel_4.add(button_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(537, 295, 247, 130);
		frame.getContentPane().add(panel_5);
		panel_5.setLayout(null);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(comboBox.getSelectedItem().equals(null) == true ||
						comboBox_1.getSelectedItem().equals(null) == true || 
						comboBox_2.getSelectedItem().equals("") == true)
				{
					JOptionPane.showMessageDialog(null, "Please select the activity and the facility for booking.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String stringBookingId = textField.getText();
					int bookingId = Integer.parseInt(stringBookingId);
					
					String stringGuestId = textField_2.getText();
					int guestId = Integer.parseInt(stringGuestId);
					
					//dd-MM-yyyy hh:mm					
					String startHour = (String) comboBox_1.getSelectedItem();
					String date = textField_1.getText();
					String time = date + " " + startHour;
					
					String activityName = (String) comboBox.getSelectedItem();
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = activityCtr.getActivityTypeByName(activityName);
					int activityId = activityTypeObj.getID();
					int maxNumberOfParticipants = activityTypeObj.getMaxParticipants();
					
					String facilityName = (String) comboBox_2.getSelectedItem();
					Facility facilityObj = new Facility();
					facilityObj = facilityCtr.getFacilityByName(facilityName);
					int facilityId = facilityObj.getId();
					
					String stringTeamId = (String) comboBox_3.getSelectedItem();
					Team teamObj = new Team();					
					int teamId = 0;
					int numberOfParticipants = 0;
					if(stringTeamId != null)
					{
						teamId = Integer.parseInt(stringTeamId);
						teamObj = teamCtr.getTeamById(teamId);
						numberOfParticipants = teamObj.getNumberOfParticipants();
					}
					
					String instructorName = (String) comboBox_4.getSelectedItem();
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
									JOptionPane.showMessageDialog(null, "Cannot book the same activity multiple times per day. Check schedule.", "Error!", JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									if(teamId != 0)
									{
										if(numberOfParticipants > maxNumberOfParticipants)
										{
											JOptionPane.showMessageDialog(null, "Too many participants in team. Check team maximum participants property.", "Error!", JOptionPane.ERROR_MESSAGE);
										}
										else
										{
											activityBookingCtr.insertTeamActivityLine(activityId, instructorId, date, startHour, facilityId, teamId);
											JOptionPane.showMessageDialog(null, "Team activity successfully booked.", "Info", JOptionPane.INFORMATION_MESSAGE);
											clearTable();
											clearValues();
										}										
									}
									if(instructorId != 0)
									{
										activityBookingCtr.insertInstructorActivityLine(activityId, bookingId, date, startHour, facilityId, instructorId);
										JOptionPane.showMessageDialog(null, "Instructor activity successfully booked.", "Info", JOptionPane.INFORMATION_MESSAGE);
										clearTable();
										clearValues();
									}
									if(teamId == 0 && instructorId == 0)
									{
										activityBookingCtr.insertSimpleActivityLine(activityId, bookingId, date, startHour, facilityId);
										JOptionPane.showMessageDialog(null, "Activity successfully booked.", "Info", JOptionPane.INFORMATION_MESSAGE);
										clearTable();
										clearValues();
									}
								}
							}
						}
					}
				}
			}
		});
		btnNewButton.setBounds(6, 28, 110, 23);
		panel_5.add(btnNewButton);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(comboBox.getSelectedItem().equals(null) == true || comboBox_1.getSelectedItem().equals(null) == true)
				{
					JOptionPane.showMessageDialog(null, "Please select the activity and starting hour for the activity you wish to remove.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String stringBookingId = textField.getText();
					int bookingId = Integer.parseInt(stringBookingId);
					
					//dd-MM-yyyy hh:mm					
					String startHour = (String) comboBox_1.getSelectedItem();
					String date = textField_1.getText();
					String time = date + " " + startHour;
					
					String activityName = (String) comboBox.getSelectedItem();
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = activityCtr.getActivityTypeByName(activityName);
					int activityId = activityTypeObj.getID();
					
					if(DateCheck.isDateValid(date) != true)
					{
						JOptionPane.showMessageDialog(null, "Inserted date is incorrect. Please insert a valid date", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						ActivityLine activityLineObj = new ActivityLine();
						activityLineObj = activityBookingCtr.getActivityLine(bookingId, startHour);
						{
							if(activityLineObj == null)
							{
								JOptionPane.showMessageDialog(null, "There is no such activity booked at that start hour.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								activityBookingCtr.deleteActivityLine(activityId, bookingId, date, startHour);
								JOptionPane.showMessageDialog(null, "Activity successfully removed from booking.", "Info", JOptionPane.INFORMATION_MESSAGE);
								
								clearValues();
								clearTable();
							}
						}
					}
				}
			}
		});
		btnRemove.setBounds(6, 62, 110, 23);
		panel_5.add(btnRemove);
		
		JButton btnNewButton_1 = new JButton("Get All");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				clearTable();
				table.setModel(getModel());
			}
		});
		btnNewButton_1.setBounds(6, 96, 110, 23);
		panel_5.add(btnNewButton_1);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearValues();
				clearTable();
			}
		});
		btnClear.setBounds(126, 28, 110, 23);
		panel_5.add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
			}
		});
		btnExit.setBounds(126, 96, 110, 23);
		panel_5.add(btnExit);
		
		JButton btnSchedule = new JButton("Schedule");
		btnSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSchedule.setBounds(126, 62, 110, 23);
		panel_5.add(btnSchedule);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(234, 19, 550, 271);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
	}
	
	public void clearValues()
	{
		comboBox.setSelectedItem(null);
		comboBox_1.setSelectedItem(null);
		comboBox_2.removeAll();
		comboBox_3.removeAll();
		comboBox_4.removeAll();
		textField_3.setText("");
		textField_4.setText("");
		button_1.setEnabled(true);
		button_3.setEnabled(true);
	}
	
	private DefaultTableModel getModel()
	{
		String stringBookingId = textField.getText();
		int bookingId = Integer.parseInt(stringBookingId);
		
		LinkedList<ActivityLine> activityBookingLines = new LinkedList<ActivityLine>();
		activityBookingLines = activityBookingCtr.getActivityLinesForActivityBooking(bookingId);
		
		DefaultTableModel model = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			public boolean IsCellEditable(int row, int column)
			{
				//all cells false
				return false;
			}
		};
		
		model.setColumnIdentifiers(new String[] {"BookingId", "Activity", "Facility", "Date", "Start hour", "End hour", "Instructor", "TeamId"});
		
		try
		{
			for(ActivityLine activityLineObj : activityBookingLines)
			{
				String instructorName = "";
				if(activityLineObj.getInstructor() != null)
				{
					instructorName = activityLineObj.getInstructor().getName();
				}
				
				String teamId = "";
				if(activityLineObj.getTeam() != null)
				{
					teamId = String.valueOf(activityLineObj.getTeam().getId());
				}
				
				model.addRow(new String[]
						{
						String.valueOf(bookingId),
						activityLineObj.getActivity().getName(),
						activityLineObj.getFacility().getName(),
						activityLineObj.getDate(),
						activityLineObj.getStartHour(),
						activityLineObj.getEndHour(),
						instructorName,
						teamId
						});
			}
		}
		catch(Exception ex)
		{
			System.out.println("Exception: " + ex);
		}
		
		table.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() 
		{
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) 
			{
				ActivityLine activityLineObj = new ActivityLine();
				
				int selectedRow = table.getSelectedRow();
				
				String stringBookingId = (String) table.getValueAt(selectedRow, 0);
				int bookingId = Integer.parseInt(stringBookingId);
				String startHour = (String) table.getValueAt(selectedRow, 4);
				
				activityLineObj = activityBookingCtr.getActivityLine(bookingId, startHour);
				
				ActivityType activityObj = activityLineObj.getActivity();
				String activityName = activityObj.getName();
				String maxNumberOfParticipants = String.valueOf(activityObj.getMaxParticipants());
				
				Facility facilityObj = activityLineObj.getFacility();
				String facilityName = facilityObj.getName();
				
				String instructorName = "";
				Instructor instructorObj = activityLineObj.getInstructor();
				if(instructorObj != null)
				{
					instructorName = instructorObj.getName();
				}
				
				String stringTeamId = "";
				String stringTeamNumberOfParticipants = "";
				Team teamObj = activityLineObj.getTeam();
				if(teamObj != null)
				{
					stringTeamId = String.valueOf(teamObj.getId());
					stringTeamNumberOfParticipants = String.valueOf(teamObj.getNumberOfParticipants());
				}
				
				comboBox.setSelectedItem(activityName);
				textField_3.setText(maxNumberOfParticipants);
				comboBox_1.setSelectedItem(startHour);
				comboBox_2.setSelectedItem(facilityName);
				
				if(teamObj != null)
				{
					comboBox_3.setSelectedItem(stringTeamId);
					textField_4.setText(stringTeamNumberOfParticipants);
				}
				else
				{
					comboBox_3.removeAll();
					textField_4.setText("");
				}
				
				if(instructorObj != null)
				{
					comboBox_4.setSelectedItem(instructorName);
				}
				else
				{
					comboBox_4.removeAll();
				}
			}
		});
		
		return model;
	}
	
	public void clearTable()
	{
		DefaultTableModel tdm=(DefaultTableModel)table.getModel();
		tdm.getDataVector().removeAllElements();
		tdm.fireTableDataChanged();
	}

}
