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
import Controller.DateCheck;
import Model.ActivityBooking;
import Model.ActivityLine;

public class ActivityBookingMenu
{
	private DateCheck dateCheck = new DateCheck();

	private int bookingId;
	private ActivityBookingCtr activityBookingCtr;
	
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox<String> comboBox_2;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_3;
	private JTable table;
	
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public ActivityBookingMenu()
	{
		initialize();
	}
	
	public int getBookingId()
	{
		return bookingId;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame("Activity booking");
		frame.setBounds(100, 100, 800, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Activity booking", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 247, 153);
		frame.getContentPane().add(panel);
		
		JLabel lblBookingId_1 = new JLabel("Booking ID:");
		lblBookingId_1.setBounds(10, 22, 90, 14);
		panel.add(lblBookingId_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(101, 19, 140, 20);
		panel.add(textField);
		
		JLabel label_1 = new JLabel("Date:");
		label_1.setBounds(10, 90, 90, 14);
		panel.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "dd", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(43, 75, 57, 43);
		panel.add(panel_1);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(6, 16, 45, 20);
		LinkedList<String> days = new LinkedList<String>();
		days = dateCheck.getDays();
		for(String day : days)
		{
			String insertedDay = day;
			comboBox.addItem(insertedDay);
		}
		comboBox.setSelectedItem(null);
		panel_1.add(comboBox);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "yyyy", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(160, 75, 77, 43);
		panel.add(panel_2);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(6, 16, 65, 20);
		LinkedList<String> years = new LinkedList<String>();
		years = dateCheck.getYears();
		for(String year : years)
		{
			String insertedYear = year;
			comboBox_1.addItem(insertedYear);
		}
		comboBox_1.setSelectedItem(null);
		panel_2.add(comboBox_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "MM", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(101, 75, 56, 43);
		panel.add(panel_3);
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(6, 16, 45, 20);
		LinkedList<String> months = new LinkedList<String>();
		months = dateCheck.getMonths();
		for(String month : months)
		{
			String insertedMonth = month;
			comboBox_2.addItem(insertedMonth);
		}
		comboBox_2.setSelectedItem(null);
		panel_3.add(comboBox_2);
		
		JLabel lblBookingId = new JLabel("GuestID:");
		lblBookingId.setBounds(10, 50, 90, 14);
		panel.add(lblBookingId);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(101, 47, 140, 20);
		panel.add(textField_1);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(10, 128, 90, 14);
		panel.add(lblStatus);
		
		comboBox_3 = new JComboBox<String>();
		comboBox_3.setBounds(72, 125, 165, 20);
		comboBox_3.addItem("Made");
		comboBox_3.addItem("Completed");
		panel.add(comboBox_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(10, 175, 247, 326);
		frame.getContentPane().add(panel_4);
		
		JButton button = new JButton("Create");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(textField_1.getText().equals("") == true ||
						comboBox.getSelectedItem().equals(null) == true || 
						comboBox_1.getSelectedItem().equals(null)  == true || 
						comboBox_2.getSelectedItem().equals(null) == true || 
						comboBox_3.getSelectedItem().equals(null) == true)
				{
					JOptionPane.showMessageDialog(null, "Please insert all the necessary attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String day = (String) comboBox.getSelectedItem();
					String month = (String) comboBox_2.getSelectedItem();
					String year = (String) comboBox_1.getSelectedItem();
					String date = day + "-" + month + "-" + year;
					
					String stringGuestId = textField_1.getText();
					int guestId = Integer.parseInt(stringGuestId);
					
					String status = (String) comboBox_3.getSelectedItem();
					
					
					if(DateCheck.isDateValid(date) != true)
					{
						JOptionPane.showMessageDialog(null, "Inserted date is incorrect. Please insert a valid date", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						if(activityBookingCtr.activityBookingCompleteCheck(date, guestId) != true)
						{
							JOptionPane.showMessageDialog(null, "Cannot make two bookings on the same date.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							activityBookingCtr.insertActivityBooking(guestId, date, status);
							JOptionPane.showMessageDialog(null, "Activity booking made successfully. You can add up to four activities.", "Info", JOptionPane.INFORMATION_MESSAGE);
							
							ActivityBooking activityBookingObj = new ActivityBooking();
							activityBookingObj = activityBookingCtr.getActivityBookingForDate(guestId, date);
							bookingId = activityBookingObj.getId();
						}
					}
				}
			}
		});
		button.setBounds(10, 16, 227, 23);
		panel_4.add(button);
		
		JButton button_1 = new JButton("Search");
		button_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(textField_1.getText().equals("") == true ||
						comboBox.getSelectedItem().equals(null) == true || 
						comboBox_1.getSelectedItem().equals(null)  == true || 
						comboBox_2.getSelectedItem().equals(null) == true )
				{
					JOptionPane.showMessageDialog(null, "Please insert all the necessary attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String day = (String) comboBox.getSelectedItem();
					String month = (String) comboBox_2.getSelectedItem();
					String year = (String) comboBox_1.getSelectedItem();
					String date = day + "-" + month + "-" + year;
					
					String stringGuestId = textField_1.getText();
					int guestId = Integer.parseInt(stringGuestId);
					
					if(DateCheck.isDateValid(date) != true)
					{
						JOptionPane.showMessageDialog(null, "Inserted date is incorrect. Please insert a valid date", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						ActivityBooking activityBookingObj = new ActivityBooking();
						activityBookingObj = activityBookingCtr.getActivityBookingForDate(guestId, date);
						if(activityBookingObj == null)
						{
							JOptionPane.showMessageDialog(null, "There is no activity booking by this date. Please insert a valid guest id and activity booking date.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							comboBox_3.setSelectedItem(activityBookingObj.getStatus());
							textField.setText(String.valueOf(activityBookingObj.getId()));
							bookingId = activityBookingObj.getId();
						}
					}
				}
			}
		});
		button_1.setBounds(10, 50, 227, 23);
		panel_4.add(button_1);
		
		JButton btnGetAll = new JButton("Get all");
		btnGetAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				clearTable();
				table.setModel(getModel());
			}
		});
		btnGetAll.setBounds(10, 152, 227, 23);
		panel_4.add(btnGetAll);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textField_1.getText().equals("") == true ||
						comboBox.getSelectedItem().equals(null) == true || 
						comboBox_1.getSelectedItem().equals(null)  == true || 
						comboBox_2.getSelectedItem().equals(null) == true )
				{
					JOptionPane.showMessageDialog(null, "Please insert all the necessary attributes.", "Error!", JOptionPane.ERROR_MESSAGE);					
				}
				else
				{
					String day = (String) comboBox.getSelectedItem();
					String month = (String) comboBox_2.getSelectedItem();
					String year = (String) comboBox_1.getSelectedItem();
					String date = day + "-" + month + "-" + year;
					
					String stringGuestId = textField_1.getText();
					int guestId = Integer.parseInt(stringGuestId);
					
					if(DateCheck.isDateValid(date) != true)
					{
						JOptionPane.showMessageDialog(null, "Inserted date is incorrect. Please insert a valid date", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						ActivityBooking activityBookingObj = new ActivityBooking();
						activityBookingObj = activityBookingCtr.getActivityBookingForDate(guestId, date);
						if(activityBookingObj == null)
						{
							JOptionPane.showMessageDialog(null, "There is no activity booking by this date. Please insert a valid guest id and activity booking date.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							bookingId = activityBookingObj.getId();
							activityBookingCtr.deleteActivityLinesFromActivityBooking(bookingId);
							activityBookingCtr.deleteActivityBookingForDate(guestId, date);
							
							JOptionPane.showMessageDialog(null, "Activity booking deleted successfully. All booked activities have been removed.", "Info", JOptionPane.INFORMATION_MESSAGE);
							clearValues();
						}
					}
				}
				
			}
		});
		btnDelete.setBounds(10, 118, 227, 23);
		panel_4.add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				clearValues();
				clearTable();
				frame.dispose();
			}
		});
		btnExit.setBounds(10, 288, 227, 23);
		panel_4.add(btnExit);
		
		JButton btnClearValues = new JButton("Clear all");
		btnClearValues.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearTable();
				clearValues();
				bookingId = 0;
			}
		});
		btnClearValues.setBounds(10, 254, 227, 23);
		panel_4.add(btnClearValues);
		
		JButton button_2 = new JButton("Schedule");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ScheduleMenu scheduleMenu = new ScheduleMenu();
			}
		});
		button_2.setBounds(10, 186, 227, 23);
		panel_4.add(button_2);
		
		JButton btnGetActivityLines = new JButton("Get activity lines");
		btnGetActivityLines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(textField_1.getText().equals("") == true ||
						comboBox.getSelectedItem().equals(null) == true || 
						comboBox_1.getSelectedItem().equals(null)  == true || 
						comboBox_2.getSelectedItem().equals(null) == true )
				{
					JOptionPane.showMessageDialog(null, "Please insert all the necessary attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String day = (String) comboBox.getSelectedItem();
					String month = (String) comboBox_2.getSelectedItem();
					String year = (String) comboBox_1.getSelectedItem();
					String date = day + "-" + month + "-" + year;
					
					String stringGuestId = textField_1.getText();
					int guestId = Integer.parseInt(stringGuestId);
					
					if(DateCheck.isDateValid(date) != true)
					{
						JOptionPane.showMessageDialog(null, "Inserted date is incorrect. Please insert a valid date", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						ActivityBooking activityBookingObj = new ActivityBooking();
						activityBookingObj = activityBookingCtr.getActivityBookingForDate(guestId, date);
						if(activityBookingObj == null)
						{
							JOptionPane.showMessageDialog(null, "There is no activity booking by this date. Please insert a valid guest id and activity booking date.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							bookingId = activityBookingObj.getId();
							String stringBookingId = String.valueOf(bookingId);
							
							ActivityLinesMenu activityLinesMenu = new ActivityLinesMenu();
							activityLinesMenu.setActivityBookingAttributes(stringBookingId, date, stringGuestId);
							
						}
					}
				}
			}
		});
		btnGetActivityLines.setBounds(10, 220, 227, 23);
		panel_4.add(btnGetActivityLines);
		
		JButton btnUpdate = new JButton("Update status");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(textField_1.getText().equals("") == true ||
						comboBox.getSelectedItem().equals(null) == true || 
						comboBox_1.getSelectedItem().equals(null)  == true || 
						comboBox_2.getSelectedItem().equals(null) == true || 
						comboBox_3.getSelectedItem().equals(null) == true)
				{
					JOptionPane.showMessageDialog(null, "Please insert all the necessary attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String day = (String) comboBox.getSelectedItem();
					String month = (String) comboBox_2.getSelectedItem();
					String year = (String) comboBox_1.getSelectedItem();
					String date = day + "-" + month + "-" + year;
					
					String stringGuestId = textField_1.getText();
					int guestId = Integer.parseInt(stringGuestId);
					
					String status = (String) comboBox_3.getSelectedItem();
					
					
					if(DateCheck.isDateValid(date) != true)
					{
						JOptionPane.showMessageDialog(null, "Inserted date is incorrect. Please insert a valid date", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						ActivityBooking activityBookingObj = new ActivityBooking();
						activityBookingObj = activityBookingCtr.getActivityBookingForDate(guestId, date);
						if(activityBookingObj == null)
						{
							JOptionPane.showMessageDialog(null, "There is no activity booking by this date. Please insert a valid guest id and activity booking date.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							bookingId = activityBookingObj.getId();
							guestId = activityBookingObj.getGuest().getId();
							
							activityBookingCtr.updateActivityBooking(bookingId, guestId, date, status);
							
							JOptionPane.showMessageDialog(null, "Status of activity booking successfully updated.", "Info", JOptionPane.INFORMATION_MESSAGE);
							clearValues();
						}
					}
				}
			}
		});
		btnUpdate.setBounds(10, 84, 227, 23);
		panel_4.add(btnUpdate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(267, 11, 507, 490);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
	}
	
	private void clearValues()
	{
		textField.setText("");
		textField_1.setText("");
		comboBox.setSelectedItem(null);
		comboBox_1.setSelectedItem(null);
		comboBox_2.setSelectedItem(null);
		comboBox_3.setSelectedItem(null);
	}
	
	private void clearTable()
	{
		DefaultTableModel tdm=(DefaultTableModel)table.getModel();
		tdm.getDataVector().removeAllElements();
		tdm.fireTableDataChanged();
	}
	
	private DefaultTableModel getModel()
	{
		LinkedList<ActivityBooking> activityBookingList = new LinkedList<ActivityBooking>();
		activityBookingList = activityBookingCtr.getAllActivityBookings();
				
		DefaultTableModel model = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			public boolean IsCellEditable(int row, int column)
			{
				//all cells false
				return false;
			}
		};
		
		model.setColumnIdentifiers(new String[] {"BookingId", "Guest", "Date", "Status"});
		
		try
		{
			for(ActivityBooking activityBookingObj : activityBookingList)
			{
				model.addRow(new String[]
						{
						String.valueOf(activityBookingObj.getId()),
						activityBookingObj.getGuest().getName(),
						activityBookingObj.getDate(),
						activityBookingObj.getStatus()
						});
			}
		}
		catch(Exception e)
		{
			System.out.println("Table creation exception: " + e);
		}
		
		table.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener()
		{
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) 
			{
				ActivityBooking activityBookingObj = new ActivityBooking();
				
				int selectedRow = table.getSelectedRow();
				
				String stringBookingId = (String) table.getValueAt(selectedRow, 0);
				bookingId = Integer.parseInt(stringBookingId);
				
				activityBookingObj = activityBookingCtr.getActivityBookingById(bookingId);
				
				int guestID = activityBookingObj.getId();
				String stringGuestId = String.valueOf(guestID);
				String date = activityBookingObj.getDate();
				String day = date.substring(0,2);
				String month = date.substring(3,5);
				String year = date.substring(6,10);				
				String status = activityBookingObj.getStatus();
				
				textField.setText(stringBookingId);
				textField_1.setText(stringGuestId);
				comboBox.setSelectedItem(day);
				comboBox_2.setSelectedItem(month);
				comboBox_1.setSelectedItem(year);
				comboBox_3.setSelectedItem(status);
			}
		});
		
		return model;
	}
}
