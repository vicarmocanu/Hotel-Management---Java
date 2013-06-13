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
	private JTextField activityTypeIdTextField;
	private JTextField activityTypeNameTextField;
	private JTextField maxParticipantsTextField;
	private JTable activityTypeTable;
	private JTextField travelAgencyCVRTextField;
	private JTextField travelAgencyNameTextField;
	private JTextField travelAgencyTextField;
	private JTextField travelAgencyCountryTextField;
	private JTextField travelAgencyZipcodeTextField;
	private JTextField travelAgencyAddressTextField;
	private JTextField travelAgencyPhoneNoTextField;
	private JTextField travelAgencyEmailTextField;
	private JTable travelAgencyTable;

	
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
		ActivityTypePanel.setLayout(null);
		
		JPanel activityTypeAttributesPanel = new JPanel();
		activityTypeAttributesPanel.setBorder(new TitledBorder(null, "Activity type attributes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityTypeAttributesPanel.setBounds(30, 19, 636, 43);
		ActivityTypePanel.add(activityTypeAttributesPanel);
		activityTypeAttributesPanel.setLayout(null);
		
		JLabel activityTypeIdLabel = new JLabel("Activity type id:");
		activityTypeIdLabel.setBounds(6, 19, 86, 14);
		activityTypeAttributesPanel.add(activityTypeIdLabel);
		activityTypeIdLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		activityTypeIdTextField = new JTextField();
		activityTypeIdTextField.setBounds(93, 16, 50, 20);
		activityTypeAttributesPanel.add(activityTypeIdTextField);
		activityTypeIdTextField.setColumns(10);
		
		JLabel activityTypeNameLabel = new JLabel("Activity type name:");
		activityTypeNameLabel.setBounds(153, 19, 127, 14);
		activityTypeAttributesPanel.add(activityTypeNameLabel);
		activityTypeNameLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		activityTypeNameTextField = new JTextField();
		activityTypeNameTextField.setBounds(255, 16, 180, 20);
		activityTypeAttributesPanel.add(activityTypeNameTextField);
		activityTypeNameTextField.setColumns(10);
		
		JLabel maxParticipantsLabel = new JLabel("Maximum participants:");
		maxParticipantsLabel.setBounds(445, 19, 127, 14);
		activityTypeAttributesPanel.add(maxParticipantsLabel);
		maxParticipantsLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		maxParticipantsTextField = new JTextField();
		maxParticipantsTextField.setBounds(560, 16, 70, 20);
		activityTypeAttributesPanel.add(maxParticipantsTextField);
		maxParticipantsTextField.setColumns(10);
		
		JPanel activityTypesOptionsPanel = new JPanel();
		activityTypesOptionsPanel.setLayout(null);
		activityTypesOptionsPanel.setBorder(new TitledBorder(null, "Activity", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		activityTypesOptionsPanel.setBounds(30, 73, 150, 203);
		ActivityTypePanel.add(activityTypesOptionsPanel);
		
		JButton activityTypeSearchButton = new JButton("Search");
		activityTypeSearchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(activityTypeIdTextField.getText().equals("")==true && activityTypeNameTextField.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the wanted activity type.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(activityTypeNameTextField.getText().equals("") == true)
					{
						String stringActivityTypeId = activityTypeIdTextField.getText();
						int activityTypeId = Integer.parseInt(stringActivityTypeId);
						
						ActivityType activityTypeObj = new ActivityType();
						activityTypeObj = activityTypeCtr.getActivityTypeByID(activityTypeId);
						
						if(activityTypeObj.getID() == 0)
						{
							JOptionPane.showMessageDialog(null, "There is no activity type by this id. Please insert a valid activity type id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							String activityTypeName = activityTypeObj.getName();
							int maxParticipants = activityTypeObj.getMaxParticipants();
							String stringMaxParticipants = String.valueOf(maxParticipants);
							
							activityTypeIdTextField.setText(stringActivityTypeId);
							activityTypeNameTextField.setText(activityTypeName);
							maxParticipantsTextField.setText(stringMaxParticipants);
						}
					}
					else
					{
						if(activityTypeIdTextField.getText().equals("") == true)
						{
							String activityTypeName = activityTypeNameTextField.getText();
							
							ActivityType activityTypeObj = new ActivityType();
							activityTypeObj = activityTypeCtr.getActivityTypeByName(activityTypeName);
							
							if(activityTypeObj.getID() == 0)
							{
								JOptionPane.showMessageDialog(null, "There is no activity type by this name. Please insert a valid activity type name.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								int activityTypeId = activityTypeObj.getID();
								String stringActivityTypeId = String.valueOf(activityTypeId);
								int maxParticipants = activityTypeObj.getMaxParticipants();
								String stringMaxParticipants = String.valueOf(maxParticipants);
								
								activityTypeIdTextField.setText(stringActivityTypeId);
								activityTypeNameTextField.setText(activityTypeName);
								maxParticipantsTextField.setText(stringMaxParticipants);
							}
						}
						else
						{
							if(activityTypeIdTextField.getText().equals("") != true && activityTypeNameTextField.getText().equals("") != true)
							{
								String stringActivityTypeId = activityTypeIdTextField.getText();
								int activityTypeId = Integer.parseInt(stringActivityTypeId);
								
								ActivityType activityTypeObj = new ActivityType();
								activityTypeObj = activityTypeCtr.getActivityTypeByID(activityTypeId);
								
								if(activityTypeObj.getID() == 0)
								{
									JOptionPane.showMessageDialog(null, "There is no activity type by this id. Please insert a valid activity type id.", "Error!", JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									String activityTypeName = activityTypeObj.getName();
									int maxParticipants = activityTypeObj.getMaxParticipants();
									String stringMaxParticipants = String.valueOf(maxParticipants);
									
									activityTypeIdTextField.setText(stringActivityTypeId);
									activityTypeNameTextField.setText(activityTypeName);
									maxParticipantsTextField.setText(stringMaxParticipants);
								}
							}
						}
					}
				}
			}
		});
		activityTypeSearchButton.setFont(new Font("Arial", Font.PLAIN, 11));
		activityTypeSearchButton.setBounds(6, 24, 134, 25);
		activityTypesOptionsPanel.add(activityTypeSearchButton);
		
		JButton activityTypeCreateButton = new JButton("Create");
		activityTypeCreateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(activityTypeNameTextField.getText().equals("") == true || maxParticipantsTextField.getText().equals("") == true)
				{
					JOptionPane.showMessageDialog(null, "A activity type attribute might be missing. Please insert all needed activity type attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String activityTypeName = activityTypeNameTextField.getText();
					String stringMaxParticipants = maxParticipantsTextField.getText();
					int maxParticipants = Integer.parseInt(stringMaxParticipants);
					if(activityTypeCtr.getActivityTypeByName(activityTypeName).getID() != 0)
					{
						JOptionPane.showMessageDialog(null, "Cannot register the same activity type twice.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						activityTypeCtr.insertActivityType(activityTypeName, maxParticipants);
						activityTypeComboBox.removeAllItems();
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
						clearActivityTypePanel();
						clearActivityTypeTable();
						JOptionPane.showMessageDialog(null, "Activity type successfully inserted.", "Info", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		activityTypeCreateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		activityTypeCreateButton.setBounds(6, 60, 134, 25);
		activityTypesOptionsPanel.add(activityTypeCreateButton);
		
		JButton activityTypeUpdateButton = new JButton("Update");
		activityTypeUpdateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(activityTypeIdTextField.getText().equals("") == true || activityTypeNameTextField.getText().equals("") == true || maxParticipantsTextField.getText().equals("") == true)
				{
					JOptionPane.showMessageDialog(null, "Some activity type attributes have not been inserted. Please insert all activity type attributes.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String stringActivityTypeId = activityTypeIdTextField.getText();
					int activityTypeId = Integer.parseInt(stringActivityTypeId);
					
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = activityTypeCtr.getActivityTypeByID(activityTypeId);
					
					if(activityTypeObj.getID() == 0)
					{
						JOptionPane.showMessageDialog(null, "The wanted activity type does not exist in the system. Please check activity type list.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						String activityTypeName = activityTypeNameTextField.getText();
						
						String stringMaxParticipants = maxParticipantsTextField.getText();
						int maxParticipants = Integer.parseInt(stringMaxParticipants);
						
						activityTypeCtr.updateActivityType(activityTypeId, activityTypeName, maxParticipants);
						
						activityTypeComboBox.removeAllItems();
						LinkedList<ActivityType> allActivityTypesList = new LinkedList<ActivityType>();
						allActivityTypesList = activityTypeCtr.getAllActivityTypes();
						if(allActivityTypesList.isEmpty()==false)
						{
							for(ActivityType activityTypeListObj : allActivityTypesList)
							{
								String comboBoxItem = activityTypeListObj.getName();
								activityTypeComboBox.addItem(comboBoxItem);
							}
						}
						activityTypeComboBox.setSelectedItem(null);
						
						clearActivityTypePanel();
						clearActivityTypeTable();
						
						JOptionPane.showMessageDialog(null, "Activity type updated successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		activityTypeUpdateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		activityTypeUpdateButton.setBounds(6, 96, 134, 25);
		activityTypesOptionsPanel.add(activityTypeUpdateButton);
		
		JButton activityTypeDeleteButton = new JButton("Delete");
		activityTypeDeleteButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(activityTypeIdTextField.getText().equals("")==true && activityTypeNameTextField.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert either the id or the name of the wanted activity type.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(activityTypeNameTextField.getText().equals("") == true)
					{
						String stringActivityTypeId = activityTypeIdTextField.getText();
						int activityTypeId = Integer.parseInt(stringActivityTypeId);
						
						ActivityType activityTypeObj = new ActivityType();
						activityTypeObj = activityTypeCtr.getActivityTypeByID(activityTypeId);
						
						if(activityTypeObj.getID() == 0)
						{
							JOptionPane.showMessageDialog(null, "There is no activity type by this id. Please insert a valid activity type id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							activityTypeCtr.deleteActivityTypeByID(activityTypeId);
							
							activityTypeComboBox.removeAllItems();
							LinkedList<ActivityType> allActivityTypesList = new LinkedList<ActivityType>();
							allActivityTypesList = activityTypeCtr.getAllActivityTypes();
							if(allActivityTypesList.isEmpty()==false)
							{
								for(ActivityType activityTypeListObj : allActivityTypesList)
								{
									String comboBoxItem = activityTypeListObj.getName();
									activityTypeComboBox.addItem(comboBoxItem);
								}
							}
							activityTypeComboBox.setSelectedItem(null);
							
							clearActivityTypePanel();
							clearActivityTypeTable();
							
							JOptionPane.showMessageDialog(null, "Activity type successfully removed from the system.", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
					{
						if(activityTypeIdTextField.getText().equals("") == true)
						{
							String activityTypeName = activityTypeNameTextField.getText();
							
							ActivityType activityTypeObj = new ActivityType();
							activityTypeObj = activityTypeCtr.getActivityTypeByName(activityTypeName);
							
							if(activityTypeObj.getID() == 0)
							{
								JOptionPane.showMessageDialog(null, "There is no activity type by this name. Please insert a valid activity type name.", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								activityTypeCtr.deleteActivityTypeByName(activityTypeName);
								
								activityTypeComboBox.removeAllItems();
								LinkedList<ActivityType> allActivityTypesList = new LinkedList<ActivityType>();
								allActivityTypesList = activityTypeCtr.getAllActivityTypes();
								if(allActivityTypesList.isEmpty()==false)
								{
									for(ActivityType activityTypeListObj : allActivityTypesList)
									{
										String comboBoxItem = activityTypeListObj.getName();
										activityTypeComboBox.addItem(comboBoxItem);
									}
								}
								activityTypeComboBox.setSelectedItem(null);
								
								clearActivityTypePanel();
								clearActivityTypeTable();
								
								JOptionPane.showMessageDialog(null, "Activity type successfully removed from the system.", "Info", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else
						{
							if(activityTypeIdTextField.getText().equals("") != true && activityTypeNameTextField.getText().equals("") != true)
							{
								String stringActivityTypeId = activityTypeIdTextField.getText();
								int activityTypeId = Integer.parseInt(stringActivityTypeId);
								
								ActivityType activityTypeObj = new ActivityType();
								activityTypeObj = activityTypeCtr.getActivityTypeByID(activityTypeId);
								
								if(activityTypeObj.getID() == 0)
								{
									JOptionPane.showMessageDialog(null, "There is no activity type by this id. Please insert a valid activity type id.", "Error!", JOptionPane.ERROR_MESSAGE);
								}
								else
								{
									activityTypeCtr.deleteActivityTypeByID(activityTypeId);
									
									activityTypeComboBox.removeAllItems();
									LinkedList<ActivityType> allActivityTypesList = new LinkedList<ActivityType>();
									allActivityTypesList = activityTypeCtr.getAllActivityTypes();
									if(allActivityTypesList.isEmpty()==false)
									{
										for(ActivityType activityTypeListObj : allActivityTypesList)
										{
											String comboBoxItem = activityTypeListObj.getName();
											activityTypeComboBox.addItem(comboBoxItem);
										}
									}
									activityTypeComboBox.setSelectedItem(null);
									
									clearActivityTypePanel();
									clearActivityTypeTable();
									
									JOptionPane.showMessageDialog(null, "Activity type successfully removed from the system.", "Info", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
				}
			}
		});
		activityTypeDeleteButton.setFont(new Font("Arial", Font.PLAIN, 11));
		activityTypeDeleteButton.setBounds(6, 132, 134, 25);
		activityTypesOptionsPanel.add(activityTypeDeleteButton);
		
		JButton activityTypeAllButton = new JButton("All");
		activityTypeAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				clearActivityTypePanel();
				clearActivityTypeTable();
				
				activityTypeTable.setModel(getActivityTypeTableModel());
			}
		});
		activityTypeAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		activityTypeAllButton.setBounds(6, 168, 134, 25);
		activityTypesOptionsPanel.add(activityTypeAllButton);
		
		JScrollPane activityTypeTableScrollPane = new JScrollPane();
		activityTypeTableScrollPane.setBounds(190, 73, 560, 240);
		ActivityTypePanel.add(activityTypeTableScrollPane);
		
		activityTypeTable = new JTable();
		activityTypeTable.setFillsViewportHeight(true);
		activityTypeTableScrollPane.setViewportView(activityTypeTable);
		
		JButton activityTypeClearAllButton = new JButton("Clear all");
		activityTypeClearAllButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearActivityTypePanel();
				clearActivityTypeTable();
			}
		});
		activityTypeClearAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		activityTypeClearAllButton.setBounds(30, 334, 134, 25);
		ActivityTypePanel.add(activityTypeClearAllButton);
		
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
		
		JButton facilityClearAllButton = new JButton("Clear all");
		facilityClearAllButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearFacilityPanel();
				clearFacilityTable();
			}
		});
		facilityClearAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		facilityClearAllButton.setBounds(29, 381, 134, 25);
		FacilityPanel.add(facilityClearAllButton);
		
		JPanel TravelAgencyPanel = new JPanel();
		tabbedPane.addTab("Travel agency menu", null, TravelAgencyPanel, null);
		TravelAgencyPanel.setLayout(null);
		
		JPanel travelAgencyAttributesPanel = new JPanel();
		travelAgencyAttributesPanel.setBorder(new TitledBorder(null, "Travel agency attributes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		travelAgencyAttributesPanel.setBounds(26, 34, 567, 68);
		TravelAgencyPanel.add(travelAgencyAttributesPanel);
		travelAgencyAttributesPanel.setLayout(null);
		
		JLabel travelAgencyCVRLabel = new JLabel("CVR:");
		travelAgencyCVRLabel.setBounds(6, 19, 85, 14);
		travelAgencyAttributesPanel.add(travelAgencyCVRLabel);
		travelAgencyCVRLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyCVRTextField = new JTextField();
		travelAgencyCVRTextField.setBounds(57, 16, 80, 20);
		travelAgencyAttributesPanel.add(travelAgencyCVRTextField);
		travelAgencyCVRTextField.setColumns(10);
		
		JLabel travelAgencyNameLabel = new JLabel("Name:");
		travelAgencyNameLabel.setBounds(147, 19, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyNameLabel);
		travelAgencyNameLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyNameTextField = new JTextField();
		travelAgencyNameTextField.setBounds(201, 16, 80, 20);
		travelAgencyAttributesPanel.add(travelAgencyNameTextField);
		travelAgencyNameTextField.setColumns(10);
		
		JLabel travelAgencyCityLabel = new JLabel("City:");
		travelAgencyCityLabel.setBounds(287, 19, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyCityLabel);
		travelAgencyCityLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyTextField = new JTextField();
		travelAgencyTextField.setBounds(341, 16, 80, 20);
		travelAgencyAttributesPanel.add(travelAgencyTextField);
		travelAgencyTextField.setColumns(10);
		
		JLabel travelAgencyCountryLabel = new JLabel("Country:");
		travelAgencyCountryLabel.setBounds(431, 19, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyCountryLabel);
		travelAgencyCountryLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyCountryTextField = new JTextField();
		travelAgencyCountryTextField.setBounds(481, 16, 80, 20);
		travelAgencyAttributesPanel.add(travelAgencyCountryTextField);
		travelAgencyCountryTextField.setColumns(10);
		
		JLabel travelAgencyZipcodeLabel = new JLabel("Zipcode:");
		travelAgencyZipcodeLabel.setBounds(6, 44, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyZipcodeLabel);
		travelAgencyZipcodeLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyZipcodeTextField = new JTextField();
		travelAgencyZipcodeTextField.setBounds(57, 41, 80, 20);
		travelAgencyAttributesPanel.add(travelAgencyZipcodeTextField);
		travelAgencyZipcodeTextField.setColumns(10);
		
		JLabel travelAgencyAddressLabel = new JLabel("Address:");
		travelAgencyAddressLabel.setBounds(147, 44, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyAddressLabel);
		travelAgencyAddressLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyAddressTextField = new JTextField();
		travelAgencyAddressTextField.setBounds(201, 41, 80, 20);
		travelAgencyAttributesPanel.add(travelAgencyAddressTextField);
		travelAgencyAddressTextField.setColumns(10);
		
		JLabel travelAgencyPhoneNoLabel = new JLabel("Phone no:");
		travelAgencyPhoneNoLabel.setBounds(287, 44, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyPhoneNoLabel);
		travelAgencyPhoneNoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyPhoneNoTextField = new JTextField();
		travelAgencyPhoneNoTextField.setBounds(341, 41, 80, 20);
		travelAgencyAttributesPanel.add(travelAgencyPhoneNoTextField);
		travelAgencyPhoneNoTextField.setColumns(10);
		
		JLabel travelAgencyEmailLabel = new JLabel("E-mail:");
		travelAgencyEmailLabel.setBounds(431, 44, 86, 14);
		travelAgencyAttributesPanel.add(travelAgencyEmailLabel);
		travelAgencyEmailLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		travelAgencyEmailTextField = new JTextField();
		travelAgencyEmailTextField.setBounds(481, 41, 80, 20);
		travelAgencyAttributesPanel.add(travelAgencyEmailTextField);
		travelAgencyEmailTextField.setColumns(10);
		
		JPanel travelAgencyOptionsPanel = new JPanel();
		travelAgencyOptionsPanel.setLayout(null);
		travelAgencyOptionsPanel.setBorder(new TitledBorder(null, "Travel agency", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		travelAgencyOptionsPanel.setBounds(26, 113, 146, 192);
		TravelAgencyPanel.add(travelAgencyOptionsPanel);
		
		JButton travelAgencySearchButton = new JButton("Search");
		travelAgencySearchButton.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencySearchButton.setBounds(6, 16, 134, 25);
		travelAgencyOptionsPanel.add(travelAgencySearchButton);
		
		JButton travelAgencyCreateButton = new JButton("Create");
		travelAgencyCreateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyCreateButton.setBounds(6, 52, 134, 25);
		travelAgencyOptionsPanel.add(travelAgencyCreateButton);
		
		JButton travelAgencyUpdateButton = new JButton("Update");
		travelAgencyUpdateButton.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyUpdateButton.setBounds(6, 88, 134, 25);
		travelAgencyOptionsPanel.add(travelAgencyUpdateButton);
		
		JButton travelAgencyDeleteButton = new JButton("Delete");
		travelAgencyDeleteButton.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyDeleteButton.setBounds(6, 124, 134, 25);
		travelAgencyOptionsPanel.add(travelAgencyDeleteButton);
		
		JButton travelAgencyAllButton = new JButton("All");
		travelAgencyAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyAllButton.setBounds(6, 160, 134, 25);
		travelAgencyOptionsPanel.add(travelAgencyAllButton);
		
		JScrollPane travelAgencyScrollPane = new JScrollPane();
		travelAgencyScrollPane.setBounds(182, 113, 633, 245);
		TravelAgencyPanel.add(travelAgencyScrollPane);
		
		travelAgencyTable = new JTable();
		travelAgencyTable.setFillsViewportHeight(true);
		travelAgencyScrollPane.setViewportView(travelAgencyTable);
		
		JButton travelAgencyClearAllButton = new JButton("Clear all");
		travelAgencyClearAllButton.setFont(new Font("Arial", Font.PLAIN, 11));
		travelAgencyClearAllButton.setBounds(26, 368, 134, 25);
		TravelAgencyPanel.add(travelAgencyClearAllButton);
		
		JPanel EmployeePanel = new JPanel();
		tabbedPane.addTab("Employee menu", null, EmployeePanel, null);
		
		JPanel RoomPanel = new JPanel();
		tabbedPane.addTab("Room menu", null, RoomPanel, null);
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
	
	public DefaultTableModel getActivityTypeTableModel()
	{
		LinkedList<ActivityType> completeActivityTypeList = new LinkedList<ActivityType>();
		completeActivityTypeList = activityTypeCtr.getAllActivityTypes();
		
		DefaultTableModel activityTypeModel = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column)
			{
				//all cells false
				return false;
			}
		};
		
		activityTypeModel.setColumnIdentifiers(new String[] {"ActivityId", "ActivityName", "MaxNumberOfParticipants"});
		
		for(ActivityType activityTypeObj : completeActivityTypeList)
		{
			activityTypeModel.addRow(new String[]
					{
					String.valueOf(activityTypeObj.getID()),
					activityTypeObj.getName(),
					String.valueOf(activityTypeObj.getMaxParticipants())
					});
		}
		
		
		activityTypeTable.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = activityTypeTable.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener()
		{
			
			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				ActivityType activityTypeObj = new ActivityType();
				
				int selectedRow = activityTypeTable.getSelectedRow();
				if(selectedRow > -1)
				{
					String selectedData = (String) activityTypeTable.getValueAt(selectedRow, 0);
					int activityTypeId = Integer.parseInt(selectedData);
					
					activityTypeObj = activityTypeCtr.getActivityTypeByID(activityTypeId);
					
					activityTypeIdTextField.setText(String.valueOf(activityTypeObj.getID()));
					activityTypeNameTextField.setText(activityTypeObj.getName());
					maxParticipantsTextField.setText(String.valueOf(activityTypeObj.getMaxParticipants()));
				}
			}
		});
		
		return activityTypeModel;
	}
	
	public void clearActivityTypePanel()
	{
		activityTypeIdTextField.setText("");
		activityTypeNameTextField.setText("");
		maxParticipantsTextField.setText("");
	}
	
	public void clearActivityTypeTable()
	{
		activityTypeTable.setCellSelectionEnabled(false);
		activityTypeTable.setModel(new DefaultTableModel());
	}
}
