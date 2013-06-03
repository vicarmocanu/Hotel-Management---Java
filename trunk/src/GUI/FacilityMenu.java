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

public class FacilityMenu
{	
	private static FacilityMenu instance;
	private FacilityCtr facilityCtr = new FacilityCtr();
	private ActivityCtr activityTypeCtr = new ActivityCtr();

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	
	/**
	 * Create the application.
	 */
	public FacilityMenu()
	{
		initialize();
		frame.setVisible(true);
	}

	public static FacilityMenu getInstance()
	{
		if (instance==null) {
			instance = new FacilityMenu();
		}
		return instance;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame("Facility");
		frame.setBounds(100, 100, 700, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Facility statistics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		JLabel lblActivityType = new JLabel("Activity type:");
		lblActivityType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblActivityType.setBounds(10, 47, 163, 17);
		panel.add(lblActivityType);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStatus.setBounds(290, 47, 163, 17);
		panel.add(lblStatus);
		
		JLabel lblIdOfFacility = new JLabel("ID of facility:");
		lblIdOfFacility.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdOfFacility.setBounds(10, 16, 100, 17);
		panel.add(lblIdOfFacility);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(290, 18, 100, 17);
		panel.add(lblName);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(100, 47, 180, 20);
		LinkedList<ActivityType> allActivityTypesList = new LinkedList<ActivityType>();
		allActivityTypesList = activityTypeCtr.getAllActivityTypes();
		for(ActivityType activityTypeObj : allActivityTypesList)
		{
			String comboBoxItem = activityTypeObj.getName();
			comboBox.addItem(comboBoxItem);
		}
		comboBox.setSelectedItem(null);
		panel.add(comboBox);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(353, 47, 180, 20);
		comboBox_1.addItem("Available");
		comboBox_1.addItem("Unavailable");
		panel.add(comboBox_1);
		
		JButton button = new JButton("Close");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
			}
		});
		button.setBounds(556, 21, 118, 25);
		frame.getContentPane().add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Facility", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 105, 150, 203);
		frame.getContentPane().add(panel_1);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textField.getText().equals("")==true && textField_1.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the wanted facility.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(textField_1.getText().equals("") == true)
					{
						String stringFacilityId = textField.getText();
						int facilityId = Integer.parseInt(stringFacilityId);
						
						Facility facilityObj = new Facility();
						facilityObj = facilityCtr.getFacilityById(facilityId);
						if(facilityObj == null)
						{
							JOptionPane.showMessageDialog(null, "There is no facility by this id. Please insert a valid facility id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							String facilityName = facilityObj.getName();							
							ActivityType activityTypeObj = new ActivityType();
							activityTypeObj = facilityObj.getActivity();
							String activityTypeName = activityTypeObj.getName();
							String status = facilityObj.getStatus();
							
							textField.setText(stringFacilityId);
							textField_1.setText(facilityName);
							comboBox.setSelectedItem(activityTypeName);
							comboBox_1.setSelectedItem(status);
						}
					}
					else
					{
						if(textField.getText().equals("") == true)
						{
							String facilityName = textField_1.getText();
							
							Facility facilityObj = new Facility();
							facilityObj = facilityCtr.getFacilityByName(facilityName);
							if(facilityObj == null)
							{
								JOptionPane.showMessageDialog(null, "There is not facility by this name. Please insert a valid facility name.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								int facilityId = facilityObj.getId();
								String stringFacilityId = String.valueOf(facilityId);
								ActivityType activityTypeObj = new ActivityType();
								String activityTypeName = activityTypeObj.getName();
								String status = facilityObj.getStatus();
								
								textField.setText(stringFacilityId);
								textField_1.setText(facilityName);
								comboBox.setSelectedItem(activityTypeName);
								comboBox_1.setSelectedItem(status);
							}
						}
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
				if(textField_1.getText().equals("") == true || comboBox.getSelectedItem().equals(null) == true || comboBox_1.getSelectedItem().equals(null) ==true)
				{
					JOptionPane.showMessageDialog(null, "A facility attribute might be missing. Please insert all facility attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String facilityName = textField_1.getText();
					
					ActivityType activityTypeObj = new ActivityType();
					String activityName = String.valueOf(comboBox.getSelectedItem());
					activityTypeObj = activityTypeCtr.getActivityTypeByName(activityName);
					int activityTypeId = activityTypeObj.getID();
					String status = String.valueOf(comboBox_1.getSelectedItem());
					
					facilityCtr.insertFacility(facilityName, activityTypeId, status);
					clearTable();
					clearValues();
					JOptionPane.showMessageDialog(null, "Facility successfully inserted.", "Info", JOptionPane.INFORMATION_MESSAGE);
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
					String stringFacilityId = textField.getText();
					int facilityId = Integer.parseInt(stringFacilityId);					
					Facility facilityObj = new Facility();
					facilityObj = facilityCtr.getFacilityById(facilityId);
					
					if(facilityObj != null)
					{
						if(		textField_1.getText().equals("")!=true ||
								comboBox.getSelectedItem().equals(null)!=true ||
								comboBox_1.getSelectedItem().equals(null))
						{
							String facilityName = textField_1.getText();
							
							ActivityType activityTypeObj = new ActivityType();
							String activityName = String.valueOf(comboBox.getSelectedItem());
							activityTypeObj = activityTypeCtr.getActivityTypeByName(activityName);
							int activityId = activityTypeObj.getID();
							
							String status = String.valueOf(comboBox_1.getSelectedItem());
							
							facilityCtr.updateFacility(facilityId, facilityName, activityId, status);
							clearTable();
							clearValues();
							JOptionPane.showMessageDialog(null, "Facility updated successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Some facility attributes have not been inserted. Please insert all facility attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "The wanted facility does not exist in the system. Please check id/name.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					if(textField_1.getText().equals("") != true)
					{
						String facilityName = textField_1.getText();
						Facility facilityObj = new Facility();
						facilityObj = facilityCtr.getFacilityByName(facilityName);
						if(facilityObj != null)
						{
							if(		textField_1.getText().equals("")!=true ||
									comboBox.getSelectedItem().equals(null)!=true ||
									comboBox_1.getSelectedItem().equals(null))
							{
								int facilityId = facilityObj.getId();
								
								ActivityType activityTypeObj = new ActivityType();
								String activityName = String.valueOf(comboBox.getSelectedItem());
								activityTypeObj = activityTypeCtr.getActivityTypeByName(activityName);
								int activityId = activityTypeObj.getID();
								
								String status = String.valueOf(comboBox_1.getSelectedItem());
							
								facilityCtr.updateFacility(facilityId, facilityName, activityId, status);
								clearTable();
								clearValues();
								JOptionPane.showMessageDialog(null, "Facility updated successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Some facility attributes have not been inserted. Please insert all facility attribute.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "The wanted facility does not exist in the system. Please check id/name.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the facility you wish to update.", "Error!", JOptionPane.ERROR_MESSAGE);
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
					String stringFacilityId = textField.getText();
					int facilityId = Integer.parseInt(stringFacilityId);
					Facility facilityObj = new Facility();
					facilityObj = facilityCtr.getFacilityById(facilityId);
					
					if(facilityObj != null)
					{
						facilityCtr.deleteFacility(facilityId);
						clearTable();
						clearValues();
						JOptionPane.showMessageDialog(null, "Facility has been deleted successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "The wanted facility does not exist in the system. Please check id/name.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					if(textField_1.getText().equals("") != true)
					{
						String facilityName = textField_1.getText();
						Facility facilityObj = new Facility();
						facilityObj = facilityCtr.getFacilityByName(facilityName);
						
						if(facilityObj != null)
						{
							int facilityId = facilityObj.getId();
							
							facilityCtr.deleteFacility(facilityId);
							clearTable();
							clearValues();
							JOptionPane.showMessageDialog(null, "Facility has been deleted successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "The wanted facility does not exist in the system. Please check id/name.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the facility you wish to delete.", "Error!", JOptionPane.ERROR_MESSAGE);
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
		
		JButton btnClearFields = new JButton("Clear all");
		btnClearFields.addActionListener(new ActionListener()
		{
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
		comboBox.setSelectedItem(null);
		comboBox_1.setSelectedItem(null);		
	}
	
	public void clearTable()
	{
		DefaultTableModel tdm=(DefaultTableModel)table.getModel();
		tdm.getDataVector().removeAllElements();
		tdm.fireTableDataChanged();
	}
	
	public DefaultTableModel getModel()
	{
		LinkedList<Facility> facilityCompleteList = new LinkedList<Facility>();
		facilityCompleteList = facilityCtr.getAllFacilities();
		
		DefaultTableModel model = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			public boolean IsCellEditable(int row, int column)
			{
				//all cells false
				return false;
			}
		};
		
		model.setColumnIdentifiers(new String[] {"FacilityId", "FacilityName", "ActivityId", "ActivityName", "Status"});
		
		try
		{
			for(Facility facilityObj : facilityCompleteList)
			{
				model.addRow(new String[]
						{
						String.valueOf(facilityObj.getId()),
						facilityObj.getName(),
						String.valueOf(facilityObj.getActivity().getID()),
						facilityObj.getActivity().getName(),
						facilityObj.getStatus()
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
			public void valueChanged(ListSelectionEvent e)
			{
				Facility facilityObj = new Facility();
				
				int selectedRow = table.getSelectedRow();
				String selectedData = (String) table.getValueAt(selectedRow, 0);
				int id = Integer.parseInt(selectedData);
				
				facilityObj = facilityCtr.getFacilityById(id);
				
				textField.setText(String.valueOf(facilityObj.getId()));
				textField_1.setText(facilityObj.getName());
				comboBox.setSelectedItem(facilityObj.getActivity().getName());
				comboBox_1.setSelectedItem(facilityObj.getStatus());
			}
		});
		
		return model;
	}

	

	
	
}
