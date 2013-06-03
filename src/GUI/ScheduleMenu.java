package GUI;

import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Controller.ActivityBookingCtr;
import Controller.DateCheck;
import Model.ActivityLine;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScheduleMenu 
{
	private DateCheck dateCheck = new DateCheck();
	private ActivityBookingCtr activityBookingCtr = new ActivityBookingCtr();

	private JFrame frame;
	private JTable table;
	JComboBox<String> comboBox;
	JComboBox<String> comboBox_1;
	JComboBox<String> comboBox_2;
	
	

	/**
	 * Create the application.
	 */
	public ScheduleMenu() 
	{
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame("Schedule");
		frame.setBounds(100, 100, 900, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Date", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(16, 11, 239, 66);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label = new JLabel("Date:");
		label.setBounds(6, 31, 90, 14);
		panel_3.add(label);
		
		JPanel panel = new JPanel();
		panel.setBounds(39, 16, 57, 43);
		panel_3.add(panel);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "dd", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		comboBox = new JComboBox<String>();
		LinkedList<String> days = new LinkedList<String>();
		days = dateCheck.getDays();
		for(String day : days)
		{
			String insertedDay = day;
			comboBox.addItem(insertedDay);
		}
		comboBox.setSelectedItem(null);
		comboBox.setBounds(6, 16, 45, 20);
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(97, 16, 56, 43);
		panel_3.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "MM", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		comboBox_1 = new JComboBox<String>();
		LinkedList<String> months = new LinkedList<String>();
		months = dateCheck.getMonths();
		for(String month : months)
		{
			String insertedMonth = month;
			comboBox_1.addItem(insertedMonth);
		}
		comboBox_1.setSelectedItem(null);
		comboBox_1.setBounds(6, 16, 45, 20);
		panel_1.add(comboBox_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(156, 16, 77, 43);
		panel_3.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "yyyy", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		comboBox_2 = new JComboBox<String>();
		LinkedList<String> years = new LinkedList<String>();
		years = dateCheck.getYears();
		for(String year : years)
		{
			String insertedYear = year;
			comboBox_2.addItem(insertedYear);
		}
		comboBox_2.setSelectedItem(null);
		comboBox_2.setBounds(6, 16, 65, 20);
		panel_2.add(comboBox_2);
		
		JButton btnNewButton = new JButton("Get schedule");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(	comboBox.getSelectedItem().equals(null) == true || 
						comboBox_1.getSelectedItem().equals(null)  == true || 
						comboBox_2.getSelectedItem().equals(null) == true )
				{
					JOptionPane.showMessageDialog(null, "Please insert the date for whitch the schedule is to be built.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String day = (String) comboBox.getSelectedItem();
					String month = (String) comboBox_2.getSelectedItem();
					String year = (String) comboBox_1.getSelectedItem();
					String date = day + "-" + month + "-" + year;
					
					if(DateCheck.isDateValid(date) != true)
					{
						JOptionPane.showMessageDialog(null, "Inserted date is incorrect. Please insert a valid date", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						clearTable();
						LinkedList<ActivityLine> dateActivityLines = new LinkedList<ActivityLine>();
						dateActivityLines = activityBookingCtr.getDateActivityLines(date);
						
						if(dateActivityLines.isEmpty() != true)
						{
							DefaultTableModel model = new DefaultTableModel()
							{
								private static final long serialVersionUID = 1L;
								public boolean IsCellEditable(int row, int column)
								{
									//all cells false
									return false;
								}
							};
							
							model.setColumnIdentifiers(new String[] {"Date", "StartHour", "Facility", "Instructor"});
							
							try
							{
									for(ActivityLine activityLineObj : dateActivityLines)
									{
										String instructorName = "";
										if(activityLineObj.getInstructor() != null)
										{
											instructorName = activityLineObj.getInstructor().getName();
										}
										
									
										model.addRow(new String[]
										{
										activityLineObj.getDate(),
										activityLineObj.getStartHour(),
										activityLineObj.getFacility().getName(),
										instructorName
										});
									}
							}
							catch(Exception e)
							{
								System.out.println("Exception: " + e);
							}
							
							table.setModel(model);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "There are no activity bookings on this date", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		btnNewButton.setBounds(260, 30, 126, 23);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 87, 858, 414);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearTable();
				frame.dispose();
			}
		});
		btnExit.setBounds(396, 30, 126, 23);
		frame.getContentPane().add(btnExit);
	}
	
	public void clearTable()
	{
		DefaultTableModel tdm=(DefaultTableModel)table.getModel();
		tdm.getDataVector().removeAllElements();
		tdm.fireTableDataChanged();
	}
}