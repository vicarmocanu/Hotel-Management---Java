package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
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

import Model.Guest;
import Model.Location;
import Model.Person;
import Controller.PersonCtr;
import Controller.GuestCtr;
import Controller.TravelAgencyCtr;
import Controller.LocationCtr;
import Model.TravelAgency;
import javax.swing.JComboBox;

public class EmployeeMenu
{
	private int bookingId;
	private int guestId;
	private LinkedList<ActivityBooking> activityBookingList = new LinkedList<ActivityBooking>();
	
	
	private GuestCtr guestCtr = new GuestCtr();
	private ActivityBookingCtr activityBookingCtr = new ActivityBookingCtr();
	private DateCheck dateCheck = new DateCheck();
	private ActivityCtr activityCtr = new ActivityCtr();
	private FacilityCtr facilityCtr = new FacilityCtr();
	private InstructorCtr instructorCtr = new InstructorCtr();
	private TeamCtr teamCtr = new TeamCtr();
	private PersonCtr personCtr = new PersonCtr();

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
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTable table_1;
	
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
		tabbedPane.addTab("Main menu", null, WelcomePanel, null);
		
		JPanel GuestPanel = new JPanel();
		tabbedPane.addTab("Guest", null, GuestPanel, null);
		GuestPanel.setLayout(null);
		
		JLabel label_14 = new JLabel("Travel agency:");
		label_14.setFont(new Font("Arial", Font.PLAIN, 11));
		label_14.setBounds(6, 64, 86, 14);
		GuestPanel.add(label_14);
		
		JLabel label_15 = new JLabel("Address:");
		label_15.setFont(new Font("Arial", Font.PLAIN, 11));
		label_15.setBounds(16, 39, 86, 14);
		GuestPanel.add(label_15);
		
		JLabel label_16 = new JLabel("Id:");
		label_16.setFont(new Font("Arial", Font.PLAIN, 11));
		label_16.setBounds(16, 14, 46, 14);
		GuestPanel.add(label_16);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_4.setColumns(10);
		textField_4.setBounds(70, 11, 100, 20);
		GuestPanel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_5.setColumns(10);
		textField_5.setBounds(70, 36, 100, 20);
		GuestPanel.add(textField_5);
		
		JComboBox<String> comboBox_8 = new JComboBox<String>();
		comboBox_8.setBounds(84, 61, 122, 20);
		GuestPanel.add(comboBox_8);
		
		JButton button_11 = new JButton("No travel agency");
		button_11.setFont(new Font("Arial", Font.PLAIN, 11));
		button_11.setBounds(216, 59, 146, 25);
		GuestPanel.add(button_11);
		
		JLabel label_17 = new JLabel("Phone no:");
		label_17.setFont(new Font("Arial", Font.PLAIN, 11));
		label_17.setBounds(186, 39, 80, 14);
		GuestPanel.add(label_17);
		
		JLabel label_18 = new JLabel("Name:");
		label_18.setFont(new Font("Arial", Font.PLAIN, 11));
		label_18.setBounds(186, 14, 46, 14);
		GuestPanel.add(label_18);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_6.setColumns(10);
		textField_6.setBounds(239, 11, 100, 20);
		GuestPanel.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_7.setColumns(10);
		textField_7.setBounds(239, 36, 100, 20);
		GuestPanel.add(textField_7);
		
		JLabel label_19 = new JLabel("City:");
		label_19.setFont(new Font("Arial", Font.PLAIN, 11));
		label_19.setBounds(401, 14, 80, 14);
		GuestPanel.add(label_19);
		
		JLabel label_20 = new JLabel("E-mail:");
		label_20.setFont(new Font("Arial", Font.PLAIN, 11));
		label_20.setBounds(401, 39, 80, 14);
		GuestPanel.add(label_20);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_8.setColumns(10);
		textField_8.setBounds(439, 36, 100, 20);
		GuestPanel.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_9.setColumns(10);
		textField_9.setBounds(439, 11, 100, 20);
		GuestPanel.add(textField_9);
		
		JLabel label_21 = new JLabel("Country:");
		label_21.setFont(new Font("Arial", Font.PLAIN, 11));
		label_21.setBounds(577, 14, 80, 14);
		GuestPanel.add(label_21);
		
		JLabel label_22 = new JLabel("Password:");
		label_22.setFont(new Font("Arial", Font.PLAIN, 11));
		label_22.setBounds(577, 39, 80, 14);
		GuestPanel.add(label_22);
		
		textField_10 = new JTextField();
		textField_10.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_10.setColumns(10);
		textField_10.setBounds(639, 36, 100, 20);
		GuestPanel.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_11.setColumns(10);
		textField_11.setBounds(639, 11, 100, 20);
		GuestPanel.add(textField_11);
		
		JLabel label_23 = new JLabel("Zipcode:");
		label_23.setFont(new Font("Arial", Font.PLAIN, 11));
		label_23.setBounds(749, 14, 80, 14);
		GuestPanel.add(label_23);
		
		JLabel label_24 = new JLabel("Guest type:");
		label_24.setFont(new Font("Arial", Font.PLAIN, 11));
		label_24.setBounds(749, 39, 80, 14);
		GuestPanel.add(label_24);
		
		textField_12 = new JTextField();
		textField_12.setFont(new Font("Arial", Font.PLAIN, 11));
		textField_12.setColumns(10);
		textField_12.setBounds(815, 11, 100, 20);
		GuestPanel.add(textField_12);
		
		JComboBox<String> comboBox_9 = new JComboBox<String>();
		comboBox_9.setBounds(815, 36, 100, 20);
		GuestPanel.add(comboBox_9);
		
		JPanel panel_13 = new JPanel();
		panel_13.setLayout(null);
		panel_13.setBorder(new TitledBorder(null, "Guest", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_13.setBounds(0, 92, 140, 157);
		GuestPanel.add(panel_13);
		
		JButton button_12 = new JButton("Search");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_12.setFont(new Font("Arial", Font.PLAIN, 11));
		button_12.setBounds(6, 16, 124, 25);
		panel_13.add(button_12);
		
		JButton button_13 = new JButton("Create");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_13.setFont(new Font("Arial", Font.PLAIN, 11));
		button_13.setBounds(6, 52, 124, 25);
		panel_13.add(button_13);
		
		JButton button_14 = new JButton("Update");
		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_14.setFont(new Font("Arial", Font.PLAIN, 11));
		button_14.setBounds(6, 88, 124, 25);
		panel_13.add(button_14);
		
		JButton button_15 = new JButton("All");
		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_15.setFont(new Font("Arial", Font.PLAIN, 11));
		button_15.setBounds(6, 124, 124, 25);
		panel_13.add(button_15);
		
		JButton button_16 = new JButton("Clear all");
		button_16.setFont(new Font("Arial", Font.PLAIN, 11));
		button_16.setBounds(0, 260, 140, 25);
		GuestPanel.add(button_16);
		
		table_1 = new JTable();
		table_1.setFillsViewportHeight(true);
		table_1.setBounds(151, 92, 787, 342);
		GuestPanel.add(table_1);
		
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
}
