package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
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

import Controller.GuestCtr;
import Controller.TeamCtr;
import Model.Guest;
import Model.Team;


public class TeamMenu
{
	public int universalId;
	private TeamCtr teamCtr = new TeamCtr();
	private GuestCtr guestCtr = new GuestCtr();
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	
	/**
	 * Create the application.
	 */
	public TeamMenu()
	{
		initialize();
		frame.setVisible(true);
	}
	
	public void setUniversalId(int newUniversalId)
	{
		this.universalId = newUniversalId;
		String stringId = String.valueOf(newUniversalId);
		textField_1.setText(stringId);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame("Team");
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 270);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Team statistics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 244, 142);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(100, 16, 134, 20);
		panel.add(textField);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(100, 47, 134, 20);
		panel.add(textField_1);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(100, 109, 134, 20);
		panel.add(textField_2);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_3.setColumns(10);
		textField_3.setBounds(100, 78, 134, 20);
		panel.add(textField_3);
		
		JLabel lblLeaderName = new JLabel("Leader name:");
		lblLeaderName.setBounds(10, 78, 163, 17);
		panel.add(lblLeaderName);
		lblLeaderName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNumberOfParticipants = new JLabel("Participants:");
		lblNumberOfParticipants.setBounds(10, 109, 163, 17);
		panel.add(lblNumberOfParticipants);
		lblNumberOfParticipants.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel = new JLabel("ID of Team:");
		lblNewLabel.setBounds(10, 16, 100, 17);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblIdOfLeader = new JLabel("ID of Leader:");
		lblIdOfLeader.setBounds(10, 47, 100, 17);
		panel.add(lblIdOfLeader);
		lblIdOfLeader.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				clearTable();
				clearValues();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(120, 164, 117, 61);
		frame.getContentPane().add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Team", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(261, 11, 155, 170);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		//button to create a new team
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(textField_1.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert a valid guest id.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String stringLeaderId = textField_1.getText();
					int leaderId = Integer.parseInt(stringLeaderId);
					
					Guest guestObj = new Guest();
					guestObj = guestCtr.searchGuestById(leaderId);
					if(guestObj == null)
					{
						JOptionPane.showMessageDialog(null, "There is no guest by this id. Please insert a valid guest id.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						teamCtr.insertTeam(leaderId);
						JOptionPane.showMessageDialog(null, "Team has been successfully created for the guest: " + guestObj.getName() + ".", "Info", JOptionPane.INFORMATION_MESSAGE);
						clearTable();
						clearValues();
					}
				}
			}
		});
		btnCreate.setBounds(6, 22, 139, 25);
		panel_1.add(btnCreate);
		
		//button to delete a team
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textField_1.getText().equals("")==true && textField.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Plese insert both the team and leader id for team you wish to remove", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{	
					Guest guestObj = new Guest();
					Team teamObj = new Team();
					
					String stringTeamId = textField.getText();
					int teamId = Integer.parseInt(stringTeamId);
					teamObj = teamCtr.getTeamById(teamId);
					
					String stringLeaderId = textField_1.getText();
					int leaderId = Integer.parseInt(stringLeaderId);
					guestObj = guestCtr.searchGuestById(leaderId);
					
					if(teamObj == null)
					{
						JOptionPane.showMessageDialog(null, "There is no such team registered. Please insert a valid team id.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					if(guestObj == null)
					{
						JOptionPane.showMessageDialog(null, "There is no guest by this id. Please insert a valid guest id,", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						teamCtr.deleteTeamByBothIDs(teamId, leaderId);
						teamCtr.deleteTeamParticipants(teamId);
						JOptionPane.showMessageDialog(null, "The team has been removed successfully.");
						clearTable();
						clearValues();
					}
				}
			}
		});
		btnDelete.setBounds(6, 58, 139, 25);
		panel_1.add(btnDelete);
		
		JButton btnAllTeams = new JButton("Delete leader teams");
		btnAllTeams.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textField_1.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Plese insert a valid team leader id", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String stringLeaderId = textField_1.getText();
					int leaderId = Integer.parseInt(stringLeaderId);
					Guest guestObj = new Guest();
					guestObj = guestCtr.searchGuestById(leaderId);
					if(guestObj == null)
					{
						JOptionPane.showMessageDialog(null, "There is no guest by this id. Please insert a valid guest id,", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						teamCtr.deleteTeamParticipants(leaderId);
						teamCtr.deleteTeamParticipants(leaderId);
						JOptionPane.showMessageDialog(null, "All the teams for the inserted leader have been removed.", "Info", JOptionPane.INFORMATION_MESSAGE);
						clearTable();
						clearValues();
					}
				}
			}
		});
		btnAllTeams.setBounds(6, 94, 139, 25);
		panel_1.add(btnAllTeams);
		
		JButton btnRetrieveAllTeams = new JButton("Retrieve all teams");
		btnRetrieveAllTeams.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearTable();
				table.setModel(getModel());
			}
		});
		btnRetrieveAllTeams.setBounds(6, 130, 139, 25);
		panel_1.add(btnRetrieveAllTeams);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(426, 11, 358, 220);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		JButton btnFindLeader = new JButton("Search leader");
		btnFindLeader.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textField_1.getText().equals("") == true && textField_3.getText().equals("") == true)
				{
					JOptionPane.showMessageDialog(null, "Please insert either a valid guest id or name.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(textField_1.getText().equals("")!=true)
					{
						String stringLeaderId = textField_1.getText();
						int leaderId = Integer.parseInt(stringLeaderId);
						Guest leader = new Guest();
						leader = guestCtr.searchGuestById(leaderId);
						if(leader != null)
						{
							String leaderName = leader.getName();
							textField_3.setText(leaderName);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "There is no guest by this name. Please insert a valid guest id.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						if(textField_3.getText().equals("")!=true)
						{
							String leaderName = textField_3.getText();
							Guest leader = new Guest();
							leader = guestCtr.searchGuestByName(leaderName);
							if(leader !=null)
							{
								int leaderId = leader.getId();
								String stringLeaderId = String.valueOf(leaderId);
								textField_1.setText(stringLeaderId);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "There is no guest by this name. Please insert a valid guest name.", "Error!", JOptionPane.ERROR_MESSAGE);
							}							
						}
					}
				}
				
			}
		});
		btnFindLeader.setBounds(10, 164, 100, 25);
		frame.getContentPane().add(btnFindLeader);
		
		JButton btnClearFields = new JButton("Clear fields");
		btnClearFields.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				clearTable();
				clearValues();
			}
		});
		btnClearFields.setBounds(10, 200, 100, 25);
		frame.getContentPane().add(btnClearFields);
	}
	
	//method to get a model for the table
	public DefaultTableModel getModel()
	{
		LinkedList<Team > teamCompleteList = new LinkedList<Team>();
		teamCompleteList = teamCtr.getAllTeams();
		
		DefaultTableModel model = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			public boolean IsCellEditable(int row, int column)
			{
				//all cells false
				return false;
			}
		};
		
		model.setColumnIdentifiers(new String[] {"TeamId", "LeaderId", "NumberOfParticipants"});
		
		try
		{
			for(Team teamObj : teamCompleteList)
			{
				model.addRow(new String[]
						{
						String.valueOf(teamObj.getId()),
						String.valueOf(teamObj.getLeader().getId()),
						String.valueOf(teamObj.getNumberOfParticipants())
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
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				Team teamObj = new Team();
				
				int selectedRow = table.getSelectedRow();
				String selectedData = (String) table.getValueAt(selectedRow, 0);
				int id = Integer.parseInt(selectedData);
				
				teamObj = teamCtr.getTeamById(id);
				
				textField.setText(String.valueOf(teamObj.getId()));
				textField_1.setText(String.valueOf(teamObj.getLeader().getId()));
				textField_3.setText(teamObj.getLeader().getName());
				textField_2.setText(String.valueOf(teamObj.getNumberOfParticipants()));
			}
		});
		
		return model;
	}
	
	public void clearValues()
	{
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
	}
	
	public void clearTable()
	{
		DefaultTableModel tdm=(DefaultTableModel)table.getModel();
		tdm.getDataVector().removeAllElements();
		tdm.fireTableDataChanged();
	}
}
