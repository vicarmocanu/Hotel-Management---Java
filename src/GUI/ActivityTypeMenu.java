package GUI;

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
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import Controller.ActivityCtr;
import Controller.FacilityCtr;
import Model.ActivityType;
import Model.Facility;

public class ActivityTypeMenu
{
	private ActivityCtr activityCtr = new ActivityCtr();
	
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTextField textField_2;
	
	/**
	 * Create the application.
	 */
	public ActivityTypeMenu()
	{
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame("Activity");
		frame.setBounds(100, 100, 700, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Activity statistics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 542, 83);
		frame.getContentPane().add(panel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(100, 16, 180, 20);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(353, 16, 180, 20);
		panel.add(textField_1);
		
		JLabel lblActivityType = new JLabel("Max participants:");
		lblActivityType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblActivityType.setBounds(10, 47, 163, 17);
		panel.add(lblActivityType);
		
		JLabel lblIdOfFacility = new JLabel("ID of activity:");
		lblIdOfFacility.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdOfFacility.setBounds(10, 16, 100, 17);
		panel.add(lblIdOfFacility);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(290, 18, 100, 17);
		panel.add(lblName);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(130, 47, 150, 20);
		panel.add(textField_2);
		
		JButton button = new JButton("Close");
		button.setBounds(556, 21, 118, 25);
		frame.getContentPane().add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Activity", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 105, 150, 203);
		frame.getContentPane().add(panel_1);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textField.getText().equals("") != true)
				{
					String stringActivityId = textField.getText();
					int activityId = Integer.parseInt(stringActivityId);
					
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = activityCtr.getActivityTypeByID(activityId);
					
					if(activityTypeObj != null)
					{
						String activityName = activityTypeObj.getName();
						
						int maxNumberOfParticipants = activityTypeObj.getMaxParticipants();
						String stringMaxNumberOfParticipants = String.valueOf(maxNumberOfParticipants);
						
						textField.setText(stringActivityId);
						textField_1.setText(activityName);
						textField_2.setText(stringMaxNumberOfParticipants);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "There is no activity by this id. Please insert a valid activity id.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					if(textField_1.getText().equals("") != true)
					{
						String activityName = textField_1.getText();
						ActivityType activityTypeObj = new ActivityType();
						activityTypeObj = activityCtr.getActivityTypeByName(activityName);
						
						if(activityTypeObj != null)
						{
							int activityId = activityTypeObj.getID();
							String stringActivityId = String.valueOf(activityId);
							
							int maxNumberOfParticipants = activityTypeObj.getMaxParticipants();
							String stringMaxNumberOfParticipants = String.valueOf(maxNumberOfParticipants);
							
							textField.setText(stringActivityId);
							textField_1.setText(activityName);
							textField_2.setText(stringMaxNumberOfParticipants);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "There is no activity by this name. Please insert a valid activity name.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the wanted activity.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnSearch.setBounds(6, 24, 134, 25);
		panel_1.add(btnSearch);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textField_1.getText().equals("") == true && textField_2.getText().equals("") == true)
				{
					JOptionPane.showMessageDialog(null, "An activity attribute might be missing. Please insert all necessary activity attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String activityName = textField_1.getText();
					
					String stringMaxNumberOfParticipants = textField_2.getText();
					int maxNumberOfParticipants = Integer.parseInt(stringMaxNumberOfParticipants);
					
					activityCtr.insertActivityType(activityName, maxNumberOfParticipants);
					clearTable();
					clearValues();
					JOptionPane.showMessageDialog(null, "Activity has been successfully inserted.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnCreate.setBounds(6, 60, 134, 25);
		panel_1.add(btnCreate);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textField.getText().equals("") != true)
				{
					String stringActivityId = textField.getText();
					int activityId = Integer.parseInt(stringActivityId);
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = activityCtr.getActivityTypeByID(activityId);
					
					if(activityTypeObj != null)
					{
						if(textField_1.getText().equals("") != true || textField_2.getText().equals("") != true)
						{
							String activityName = textField_1.getText();
							
							String stringMaxNumberOfParticipants = textField_2.getText();
							int maxNumberOfParticipants = Integer.parseInt(stringMaxNumberOfParticipants);
							
							activityCtr.updateActivityType(activityId, activityName, maxNumberOfParticipants);
							clearTable();
							clearValues();
							JOptionPane.showMessageDialog(null, "Facility updated successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Some activity attributes have not been inserted. Please insert all activity attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "The wanted activity does not exist in the system. Please check id/name.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					if(textField_1.getText().equals("") != true)
					{
						String activityName = textField_1.getText();
						ActivityType activityTypeObj = new ActivityType();
						activityTypeObj = activityCtr.getActivityTypeByName(activityName);
						
						if(activityTypeObj != null)
						{
							if(textField_2.getText().equals("") != true)
							{
								int activityId = activityTypeObj.getID();
								
								String stringMaxNumberOfParticipants = textField_2.getText();
								int maxNumberOfParticipants = Integer.parseInt(stringMaxNumberOfParticipants);
								
								activityCtr.updateActivityType(activityId, activityName, maxNumberOfParticipants);
								clearTable();
								clearValues();
								JOptionPane.showMessageDialog(null, "Facility updated successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Some activity attributes have not been inserted. Please insert all activity attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "The wanted activity does not exist in the system. Please check id/name.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the activity you wish to update.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnUpdate.setBounds(6, 96, 134, 25);
		panel_1.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textField.getText().equals("") != true)
				{
					String stringActivityId = textField.getText();
					int activityId = Integer.parseInt(stringActivityId);
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = activityCtr.getActivityTypeByID(activityId);
					
					if(activityTypeObj != null)
					{
						activityCtr.deleteActivityTypeByID(activityId);
						clearTable();
						clearValues();
						JOptionPane.showMessageDialog(null, "Facility deleted successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "The wanted activity does not exist in the system. Please check id/name.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					if(textField_1.getText().equals("") != true)
					{
						String activityName = textField_1.getText();
						ActivityType activityTypeObj = new ActivityType();
						activityTypeObj = activityCtr.getActivityTypeByName(activityName);
						
						if(activityTypeObj != null)
						{
							activityCtr.deleteActivityTypeByName(activityName);
							clearTable();
							clearValues();
							JOptionPane.showMessageDialog(null, "Facility deleted successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "The wanted activity does not exist in the system. Please check id/name.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the activity you wish to delete.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDelete.setBounds(6, 132, 134, 25);
		panel_1.add(btnDelete);
		
		JButton btnAll = new JButton("All");
		btnAll.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearTable();
				table.setModel(getModel());
			}
		});
		btnAll.setBounds(6, 168, 134, 25);
		panel_1.add(btnAll);
		
		JButton btnClearFields = new JButton("Clear fields");
		btnClearFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearTable();
				clearValues(); 
			}
		});
		btnClearFields.setBounds(556, 57, 118, 25);
		frame.getContentPane().add(btnClearFields);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(170, 105, 504, 196);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
	}
	
	public void clearValues()
	{
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
	}
	
	public void clearTable()
	{
		DefaultTableModel tdm=(DefaultTableModel)table.getModel();
		tdm.getDataVector().removeAllElements();
		tdm.fireTableDataChanged();
	}
	
	public DefaultTableModel getModel()
	{
		LinkedList<ActivityType> completeActivityTypeList = new LinkedList<ActivityType>();
		completeActivityTypeList = activityCtr.getAllActivityTypes();
		
		DefaultTableModel model = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			public boolean IsCellEditable(int row, int column)
			{
				//all cells false
				return false;
			}
		};
		
		model.setColumnIdentifiers(new String[] {"ActivityId", "ActivityName", "MaxNumberOfParticipants"});
		
		try
		{
			for(ActivityType activityTypeObj : completeActivityTypeList)
			{
				model.addRow(new String[]
						{
						String.valueOf(activityTypeObj.getID()),
						activityTypeObj.getName(),
						String.valueOf(activityTypeObj.getMaxParticipants())
						});
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception: " + e);
		}
		
		table.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener()
		{
			
			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				ActivityType activityTypeObj = new ActivityType();
				
				int selectedRow = table.getSelectedRow();
				String selectedData = (String) table.getValueAt(selectedRow, 0);
				int id = Integer.parseInt(selectedData);
				
				activityTypeObj = activityCtr.getActivityTypeByID(id);
				
				textField.setText(String.valueOf(activityTypeObj.getID()));
				textField_1.setText(activityTypeObj.getName());
				textField_2.setText(String.valueOf(activityTypeObj.getMaxParticipants()));
			}
		});
		
		return model;
	}
	
}
