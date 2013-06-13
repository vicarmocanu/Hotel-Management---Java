package GUI;

import java.awt.Color;
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
import javax.swing.JTabbedPane;
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

public class ManagerMenu
{
	private ActivityCtr activityTypeCtr = new ActivityCtr();
	private FacilityCtr facilityCtr = new FacilityCtr();

	private JFrame frame;
	private JLabel dinamicLabel;
	private JTextField facilityIdTextField;
	private JTextField facilityNameTextField;
	private JComboBox<String> activityTypeComboBox;
	private JComboBox<String> statusComboBox;
	private JTable facilityTable;
	private JScrollPane facilityTableScrollPane;

	
	public ManagerMenu()
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
		logOffButton.setBounds(715, 550, 125, 39);
		frame.getContentPane().add(logOffButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 830, 528);
		frame.getContentPane().add(tabbedPane);
		
		JPanel WelcomePanel = new JPanel();
		tabbedPane.addTab("Main menu", null, WelcomePanel, null);
		WelcomePanel.setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome,");
		welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		welcomeLabel.setBounds(34, 37, 115, 25);
		WelcomePanel.add(welcomeLabel);
		
		dinamicLabel = new JLabel("MANAGER");
		dinamicLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		dinamicLabel.setBounds(137, 37, 216, 25);
		WelcomePanel.add(dinamicLabel);
		
		JPanel ActivityTypePanel = new JPanel();
		tabbedPane.addTab("Activity type menu", null, ActivityTypePanel, null);
		
		JPanel FacilityPanel = new JPanel();
		tabbedPane.addTab("Facility menu", null, FacilityPanel, null);
		FacilityPanel.setLayout(null);
		
		JPanel facilityAttributesPanel = new JPanel();
		facilityAttributesPanel.setBorder(new TitledBorder(null, "Facility attributes:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		facilityAttributesPanel.setBounds(29, 20, 700, 80);
		FacilityPanel.add(facilityAttributesPanel);
		facilityAttributesPanel.setLayout(null);
		
		JLabel facilityIdLabel = new JLabel("Facility id:");
		facilityIdLabel.setBounds(6, 19, 86, 14);
		facilityAttributesPanel.add(facilityIdLabel);
		facilityIdLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		facilityIdTextField = new JTextField();
		facilityIdTextField.setBounds(91, 16, 220, 20);
		facilityAttributesPanel.add(facilityIdTextField);
		facilityIdTextField.setColumns(10);
		
		JLabel facilityNameLabel = new JLabel("Facility name:");
		facilityNameLabel.setBounds(388, 19, 86, 14);
		facilityAttributesPanel.add(facilityNameLabel);
		facilityNameLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		facilityNameTextField = new JTextField();
		facilityNameTextField.setBounds(470, 16, 220, 20);
		facilityAttributesPanel.add(facilityNameTextField);
		facilityNameTextField.setColumns(10);
		
		JLabel activityTypeLabel = new JLabel("Activity type:");
		activityTypeLabel.setBounds(6, 56, 86, 14);
		facilityAttributesPanel.add(activityTypeLabel);
		activityTypeLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		activityTypeComboBox = new JComboBox<String>();
		LinkedList<ActivityType> allActivityTypesList = new LinkedList<ActivityType>();
		allActivityTypesList = activityTypeCtr.getAllActivityTypes();
		if(allActivityTypesList.isEmpty()==false)
		{
			for(ActivityType activityTypeObj : allActivityTypesList)
			{
				String comboBoxItem = activityTypeObj.getName();
				activityTypeComboBox.addItem(comboBoxItem);
			}
		}
		activityTypeComboBox.setSelectedItem(null);
		activityTypeComboBox.setBounds(91, 53, 220, 20);
		facilityAttributesPanel.add(activityTypeComboBox);
		
		JLabel statusLabel = new JLabel("Status:");
		statusLabel.setBounds(388, 56, 86, 14);
		facilityAttributesPanel.add(statusLabel);
		statusLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		statusComboBox = new JComboBox<String>();
		statusComboBox.addItem("Available");
		statusComboBox.addItem("Under construction");
		statusComboBox.addItem("Cleaning");
		statusComboBox.setSelectedItem(null);
		statusComboBox.setBounds(470, 53, 220, 20);
		facilityAttributesPanel.add(statusComboBox);
		
		JPanel facilityOptionsPanel = new JPanel();
		facilityOptionsPanel.setBorder(new TitledBorder(null, "Facility", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		facilityOptionsPanel.setBounds(29, 111, 146, 192);
		FacilityPanel.add(facilityOptionsPanel);
		facilityOptionsPanel.setLayout(null);
		
		JButton facilitySearchButton = new JButton("Search");
		facilitySearchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(facilityIdTextField.getText().equals("")==true && facilityNameTextField.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the wanted facility.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(facilityNameTextField.getText().equals("") == true)
					{
						String stringFacilityId = facilityIdTextField.getText();
						int facilityId = Integer.parseInt(stringFacilityId);
						
						Facility facilityObj = new Facility();
						facilityObj = facilityCtr.getFacilityById(facilityId);
						
						if(facilityObj.getId() == 0)
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
							
							facilityIdTextField.setText(stringFacilityId);
							facilityNameTextField.setText(facilityName);
							activityTypeComboBox.setSelectedItem(activityTypeName);
							statusComboBox.setSelectedItem(status);
						}
					}
					else
					{
						if(facilityIdTextField.getText().equals("") == true)
						{
							String facilityName = facilityNameTextField.getText();
							
							Facility facilityObj = new Facility();
							facilityObj = facilityCtr.getFacilityByName(facilityName);
							if(facilityObj.getId() == 0)
							{
								JOptionPane.showMessageDialog(null, "There is not facility by this name. Please insert a valid facility name.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								int facilityId = facilityObj.getId();
								String stringFacilityId = String.valueOf(facilityId);
								ActivityType activityTypeObj = new ActivityType();
								activityTypeObj = facilityObj.getActivity();
								String activityTypeName = activityTypeObj.getName();
								String status = facilityObj.getStatus();
								
								facilityIdTextField.setText(stringFacilityId);
								facilityNameTextField.setText(facilityName);
								activityTypeComboBox.setSelectedItem(activityTypeName);
								statusComboBox.setSelectedItem(status);
							}
						}
						else
						{
							if(facilityIdTextField.getText().equals("") != true && facilityNameTextField.getText().equals("") != true)
							{
								String stringFacilityId = facilityIdTextField.getText();
								int facilityId = Integer.parseInt(stringFacilityId);
								
								Facility facilityObj = new Facility();
								facilityObj = facilityCtr.getFacilityById(facilityId);
								
								if(facilityObj.getId() == 0)
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
									
									facilityIdTextField.setText(stringFacilityId);
									facilityNameTextField.setText(facilityName);
									activityTypeComboBox.setSelectedItem(activityTypeName);
									statusComboBox.setSelectedItem(status);
								}
							}
						}
					}
				}
			}
		});
		facilitySearchButton.setBounds(6, 16, 134, 25);
		facilityOptionsPanel.add(facilitySearchButton);
		facilitySearchButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton facilityCreateButton = new JButton("Create");
		facilityCreateButton.setBounds(6, 52, 134, 25);
		facilityOptionsPanel.add(facilityCreateButton);
		facilityCreateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(facilityNameTextField.getText().equals("") == true || activityTypeComboBox.getSelectedItem().equals(null) == true || statusComboBox.getSelectedItem().equals(null) ==true)
				{
					JOptionPane.showMessageDialog(null, "A facility attribute might be missing. Please insert all needed facility attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String facilityName = facilityNameTextField.getText();
					
					ActivityType activityTypeObj = new ActivityType();
					String activityName = String.valueOf(activityTypeComboBox.getSelectedItem());
					activityTypeObj = activityTypeCtr.getActivityTypeByName(activityName);
					int activityTypeId = activityTypeObj.getID();
					
					String status = String.valueOf(statusComboBox.getSelectedItem());
					
					if(facilityCtr.getFacilityByName(facilityName).getId() != 0)
					{
						JOptionPane.showMessageDialog(null, "Cannot register the same facility twice.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						
						facilityCtr.insertFacility(facilityName, activityTypeId, status);
						clearFacilityTable();
						clearFacilityPanel();
						JOptionPane.showMessageDialog(null, "Facility successfully inserted.", "Info", JOptionPane.INFORMATION_MESSAGE);
						
					}
				}
			}
		});
		facilityCreateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton facilityUpdateButton = new JButton("Update");
		facilityUpdateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(facilityIdTextField.getText().equals("") == true || facilityNameTextField.getText().equals("") == true || activityTypeComboBox.getSelectedItem().equals(null) == true || statusComboBox.getSelectedItem().equals(null) == true)
				{
					JOptionPane.showMessageDialog(null, "Some facility attributes have not been inserted. Please insert all facility attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String stringFacilityId = facilityIdTextField.getText();
					int facilityId = Integer.parseInt(stringFacilityId);
					Facility facilityObj = new Facility();
					facilityObj = facilityCtr.getFacilityById(facilityId);
					
					if(facilityObj.getId() == 0)
					{
						JOptionPane.showMessageDialog(null, "The wanted facility does not exist in the system. Please check facility list.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						String facilityName = facilityNameTextField.getText();
						
						ActivityType activityTypeObj = new ActivityType();
						String activityName = String.valueOf(activityTypeComboBox.getSelectedItem());
						activityTypeObj = activityTypeCtr.getActivityTypeByName(activityName);
						int activityId = activityTypeObj.getID();
						
						String status = String.valueOf(statusComboBox.getSelectedItem());
						
						
						
						facilityCtr.updateFacility(facilityId, facilityName, activityId, status);
						clearFacilityTable();
						clearFacilityPanel();
						JOptionPane.showMessageDialog(null, "Facility updated successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		facilityUpdateButton.setBounds(6, 88, 134, 25);
		facilityOptionsPanel.add(facilityUpdateButton);
		facilityUpdateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton facilityDeleteButton = new JButton("Delete");
		facilityDeleteButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(facilityIdTextField.getText().equals("")==true && facilityNameTextField.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the wanted facility.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(facilityNameTextField.getText().equals("") == true)
					{
						String stringFacilityId = facilityIdTextField.getText();
						int facilityId = Integer.parseInt(stringFacilityId);
						
						Facility facilityObj = new Facility();
						facilityObj = facilityCtr.getFacilityById(facilityId);
						
						if(facilityObj.getId() == 0)
						{
							JOptionPane.showMessageDialog(null, "There is no facility by this id. Please insert a valid facility id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							
							
							facilityCtr.deleteFacility(facilityId);
							clearFacilityTable();
							clearFacilityPanel();
							JOptionPane.showMessageDialog(null, "Facility successfully removed from the system.", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
					{
						if(facilityIdTextField.getText().equals("") == true)
						{
							String facilityName = facilityNameTextField.getText();
							
							Facility facilityObj = new Facility();
							facilityObj = facilityCtr.getFacilityByName(facilityName);
							if(facilityObj.getId() == 0)
							{
								JOptionPane.showMessageDialog(null, "There is not facility by this name. Please insert a valid facility name.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								int facilityId = facilityObj.getId();
								
								
								
								facilityCtr.deleteFacility(facilityId);
								clearFacilityTable();
								clearFacilityPanel();
								JOptionPane.showMessageDialog(null, "Facility successfully removed from the system.", "Info", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else
						{
							if(facilityIdTextField.getText().equals("") != true && facilityNameTextField.getText().equals("") != true)
							{
								String stringFacilityId = facilityIdTextField.getText();
								int facilityId = Integer.parseInt(stringFacilityId);
								
								Facility facilityObj = new Facility();
								facilityObj = facilityCtr.getFacilityById(facilityId);
								
								if(facilityObj.getId() == 0)
								{
									JOptionPane.showMessageDialog(null, "There is no facility by this id. Please insert a valid facility id.", "Error!", JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									
									
									facilityCtr.deleteFacility(facilityId);
									clearFacilityTable();
									clearFacilityPanel();
									JOptionPane.showMessageDialog(null, "Facility successfully removed from the system.", "Info", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
				}
			}
		});
		facilityDeleteButton.setBounds(6, 124, 134, 25);
		facilityOptionsPanel.add(facilityDeleteButton);
		facilityDeleteButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JButton facilityAllButton = new JButton("All");
		facilityAllButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearFacilityPanel();
				clearFacilityTable();
				
				facilityTable.setModel(getFacilityTableModel());
			}
		});
		facilityAllButton.setBounds(6, 160, 134, 25);
		facilityOptionsPanel.add(facilityAllButton);
		facilityAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		
		facilityTableScrollPane = new JScrollPane();
		facilityTableScrollPane.setBounds(185, 125, 630, 230);
		FacilityPanel.add(facilityTableScrollPane);
		
		facilityTable = new JTable();
		facilityTable.setFillsViewportHeight(true);
		facilityTableScrollPane.setViewportView(facilityTable);
		
		JButton clearAllButton = new JButton("Clear all");
		clearAllButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearFacilityPanel();
				clearFacilityTable();
			}
		});
		clearAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		clearAllButton.setBounds(29, 381, 134, 25);
		FacilityPanel.add(clearAllButton);
		frame.setUndecorated(true);
	}
	
	public void setManagerName(String managerName)
	{
		dinamicLabel.setText(managerName);
	}
	
	private DefaultTableModel getFacilityTableModel()
	{
		LinkedList<Facility> facilityCompleteList = new LinkedList<Facility>();
		facilityCompleteList = facilityCtr.getAllFacilities();
		
		DefaultTableModel facilityTableModel = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column)
			{
				//all cells false
				return false;
			}
		};
		
		facilityTableModel.setColumnIdentifiers(new String[] {"FacilityId", "FacilityName", "ActivityId", "ActivityName", "Status"});
		
		try
		{
			for(Facility facilityObj : facilityCompleteList)
			{
				facilityTableModel.addRow(new String[]
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
		
		facilityTable.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = facilityTable.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener()
		{
			
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				Facility facilityObj = new Facility();
				
				int selectedRow = facilityTable.getSelectedRow();
				if(selectedRow > -1)
				{
					String selectedData = (String) facilityTable.getValueAt(selectedRow, 0);
					
					int facilityId = Integer.parseInt(selectedData);
					
					facilityObj = facilityCtr.getFacilityById(facilityId);
					facilityIdTextField.setText(String.valueOf(facilityObj.getId()));
					facilityNameTextField.setText(facilityObj.getName());
					activityTypeComboBox.setSelectedItem(facilityObj.getActivity().getName());
					statusComboBox.setSelectedItem(facilityObj.getStatus());
				}
				
			}
		});
		
		
		return facilityTableModel;
	}
	
	public void clearFacilityPanel()
	{
		facilityIdTextField.setText("");
		facilityNameTextField.setText("");
		activityTypeComboBox.setSelectedItem(null);
		statusComboBox.setSelectedItem(null);
	}
	
	public void clearFacilityTable()
	{
		facilityTable.setCellSelectionEnabled(false);
		facilityTable.setModel(new DefaultTableModel());
	}
}
