package GUI;

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

	public JFrame frame;
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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
				if(	comboBox.getSelectedItem() == null || comboBox_1.getSelectedItem() == null || comboBox_2.getSelectedItem() == null)
				{
					JOptionPane.showMessageDialog(null, "Please insert the date for whitch the schedule is to be built.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String day = (String) comboBox.getSelectedItem();
					String month = (String) comboBox_1.getSelectedItem();
					String year = (String) comboBox_2.getSelectedItem();
					String inputDate = day + "-" + month + "-" + year;
					String insertDate = year + "-" + month + "-" + day;
					
					if(DateCheck.isDateValid(inputDate) != true)
					{
						JOptionPane.showMessageDialog(null, "Inserted date is incorrect. Please insert a valid date", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						if(dateCheck.checkIfDateIsOlder(inputDate) != true)
						{
							JOptionPane.showMessageDialog(null, "Cannot insert a date older than the current date.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							clearTable();
							LinkedList<ActivityLine> dateActivityLines = new LinkedList<ActivityLine>();
							dateActivityLines = activityBookingCtr.getDateActivityLines(insertDate);
						
							if(dateActivityLines.isEmpty() == false)
							{
								DefaultTableModel model = new DefaultTableModel()
								{
									private static final long serialVersionUID = 1L;
									@Override
									public boolean isCellEditable(int row, int column)
									{
										//all cells false
										return false;
									}
								};
								
								model.setColumnIdentifiers(new String[] {"Date", "Start Hour", "Facility", "Instructor"});
								
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
								
								table.setModel(model);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "There are no activity bookings on this date", "Error!", JOptionPane.ERROR_MESSAGE);
							}
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
	}
	
	public void clearTable()
	{
		table.setModel(new DefaultTableModel());
	}
}
