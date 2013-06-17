package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Controller.ActivityBookingCtr;
import Controller.DateCheck;
import Model.ActivityLine;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class InstructorMenu
{
	private DateCheck dateCheck= new DateCheck();
	private ActivityBookingCtr activityBookingCtr = new ActivityBookingCtr();

	public JFrame frame;
	private JLabel dinamicLabel;
	int universalId;
	private JTable table;
	JComboBox<String> comboBox;
	JComboBox<String> comboBox_1;
	JComboBox<String> comboBox_2;
	
	public InstructorMenu()
	{
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize()
	{
		frame = new JFrame("Instructor menu");
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 764, 390);
		frame.getContentPane().add(tabbedPane);
		
		JPanel welcomePanel = new JPanel();
		tabbedPane.addTab("Main menu", null, welcomePanel, null);
		welcomePanel.setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome,");
		welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		welcomeLabel.setBounds(32, 28, 115, 25);
		welcomePanel.add(welcomeLabel);
		
		dinamicLabel = new JLabel("INSTRUCTOR");
		dinamicLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		dinamicLabel.setBounds(135, 28, 241, 25);
		welcomePanel.add(dinamicLabel);
		
		JPanel personalSchedulePanel = new JPanel();
		tabbedPane.addTab("Personal schedule menu", null, personalSchedulePanel, null);
		personalSchedulePanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Date", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 239, 66);
		personalSchedulePanel.add(panel);
		
		JLabel label = new JLabel("Date:");
		label.setBounds(6, 31, 90, 14);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "dd", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(39, 16, 57, 43);
		panel.add(panel_1);
		
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
		panel_1.add(comboBox);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "MM", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(97, 16, 56, 43);
		panel.add(panel_2);
		
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
		panel_2.add(comboBox_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "yyyy", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(156, 16, 77, 43);
		panel.add(panel_3);
		
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
		panel_3.add(comboBox_2);
		
		JButton button_1 = new JButton("Get schedule");
		button_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
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
						clearTable();
						LinkedList<ActivityLine> instructorDateActivityLines = new LinkedList<ActivityLine>();
						instructorDateActivityLines = activityBookingCtr.getInstructorActivityLinesSchedule(insertDate, universalId);
						
						if(instructorDateActivityLines.isEmpty() == true)
						{
							JOptionPane.showMessageDialog(null, "There are no activiy bookings on the inserted date.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
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
							
							model.setColumnIdentifiers(new String[] {"Date", "Start Hour", "Facility"});
							
							for(ActivityLine activityLineObj : instructorDateActivityLines)
							{
								model.addRow(new String[]
									{
									activityLineObj.getDate(),
									activityLineObj.getStartHour(),
									activityLineObj.getFacility().getName()
									});
							}
							
							table.setModel(model);
						}
					}
				}
			}
		});
		button_1.setBounds(258, 27, 136, 34);
		personalSchedulePanel.add(button_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 88, 739, 263);
		personalSchedulePanel.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("Log Off");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				frame.dispose();
				LoginMenu loginMenu = LoginMenu.getInstance();
				loginMenu.frame.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 15));
		button.setBounds(649, 412, 125, 39);
		frame.getContentPane().add(button);
	}
	
	public void setInstructorName(String guestName)
	{
		dinamicLabel.setText(guestName);
	}
	
	public void setUniversalId(int newUniversalId)
	{
		universalId = newUniversalId;
	}
	
	public void clearTable()
	{
		table.setModel(new DefaultTableModel());
	}
}
