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
import Model.Participant;
import Model.Team;

public class GuestTeamMenu
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
	private JTable table_1;
	
	/**
	 * Create the application.
	 */
	public GuestTeamMenu()
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
		panel.setBounds(10, 11, 647, 43);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID of Team:");
		lblNewLabel.setBounds(6, 18, 100, 17);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField = new JTextField();
		textField.setBounds(83, 16, 100, 20);
		panel.add(textField);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		
		JLabel lblIdOfLeader = new JLabel("ID of Leader:");
		lblIdOfLeader.setBounds(196, 18, 100, 17);
		panel.add(lblIdOfLeader);
		lblIdOfLeader.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNumberOfParticipants = new JLabel("Number of participants:");
		lblNumberOfParticipants.setBounds(392, 18, 163, 17);
		panel.add(lblNumberOfParticipants);
		lblNumberOfParticipants.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(282, 16, 100, 20);
		panel.add(textField_1);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(541, 16, 100, 20);
		panel.add(textField_2);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
			}
		});
		btnNewButton.setBounds(667, 11, 117, 43);
		frame.getContentPane().add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Team", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 65, 96, 156);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		//button to create a new team
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				String stringLeaderId = textField_1.getText();
				int leaderId = Integer.parseInt(stringLeaderId);
				teamCtr.insertTeam(leaderId);
				
				JOptionPane.showMessageDialog(null, "Team successfully created.", "Info", JOptionPane.INFORMATION_MESSAGE);
				
				clearLeftTable();
				clearRightTable();
			}
		});
		btnCreate.setBounds(6, 27, 80, 25);
		panel_1.add(btnCreate);
		
		//button to delete a team
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textField.getText().equals("") == true)
				{
					JOptionPane.showMessageDialog(null, "Please insert the id of the team you wish to remove", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String stringTeamId = textField.getText();
					int teamId = Integer.parseInt(stringTeamId);
					
					String stringLeaderId = textField_1.getText();
					int leaderId = Integer.parseInt(stringLeaderId);
					
					teamCtr.deleteTeamByBothIDs(teamId, leaderId);
					teamCtr.deleteTeamParticipants(teamId);
					
					clearLeftTable();
					clearRightTable();
				}
			}
		});
		btnDelete.setBounds(6, 75, 80, 25);
		panel_1.add(btnDelete);
		
		JButton btnAllTeams = new JButton("All teams");
		btnAllTeams.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearLeftTable();
				clearRightTable();
				table.setModel(getModelLeft());
			}
		});
		btnAllTeams.setBounds(6, 124, 80, 25);
		panel_1.add(btnAllTeams);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(116, 65, 270, 156);
		frame.getContentPane().add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Participant", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(396, 65, 164, 156);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblIdOfParticipant = new JLabel("ID:");
		lblIdOfParticipant.setBounds(6, 18, 100, 17);
		panel_2.add(lblIdOfParticipant);
		lblIdOfParticipant.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField_3 = new JTextField();
		textField_3.setBounds(31, 16, 125, 20);
		panel_2.add(textField_3);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_3.setColumns(10);
		
		//add participant button
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textField_3.getText().equals("") == true)
				{
					JOptionPane.showMessageDialog(null, "Please insert the id of the participant you wish to add.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String stringTeamId = textField.getText();
					int teamId = Integer.parseInt(stringTeamId);
					
					String stringParticipantId = textField_3.getText();
					int participantId = Integer.parseInt(stringParticipantId);					
					Guest guestObj = guestCtr.searchGuestById(participantId);
					
					if(guestObj == null)
					{
						JOptionPane.showMessageDialog(null,"Please insert a valid guest id.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						teamCtr.insertTeamParticipant(teamId, participantId);
						JOptionPane.showMessageDialog(null, "Participant has been successfully added to the team.", "Information", JOptionPane.INFORMATION_MESSAGE);
						clearRightTable();
					}
				}
				
				
			}
		});
		btnAdd.setBounds(31, 50, 125, 25);
		panel_2.add(btnAdd);
		
		//button to remove a participant from the team
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textField_3.getText().equals("") == true)
				{
					JOptionPane.showMessageDialog(null, "Please insert the id of the participant you wish to remove.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String stringTeamId = textField.getText();
					int teamId = Integer.parseInt(stringTeamId);
					
					String stringParticipantId = textField_3.getText();
					int participantId = Integer.parseInt(stringParticipantId);					
					Guest guestObj = guestCtr.searchGuestById(participantId);
					
					if(guestObj == null)
					{
						JOptionPane.showMessageDialog(null,"Please insert a valid guest id.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						teamCtr.deleteTeamParticipant(teamId, participantId);
						JOptionPane.showMessageDialog(null, "Participant has been removed from the team.", "Information", JOptionPane.INFORMATION_MESSAGE);
						clearRightTable();						
					}
				}
			}
		});
		btnRemove.setBounds(31, 86, 125, 25);
		panel_2.add(btnRemove);
		
		//button to show all the participants in a team
		JButton btnAll = new JButton("All participants");
		btnAll.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearRightTable();
				table_1.setModel(getModelRight());
			}
		});
		btnAll.setBounds(31, 122, 125, 25);
		panel_2.add(btnAll);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(570, 65, 214, 156);
		frame.getContentPane().add(scrollPane_1);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		table_1 = new JTable();
		table_1.setFillsViewportHeight(true);
		scrollPane_1.setRowHeaderView(table_1);
	}
	
	//method to get the model for the left table
	public DefaultTableModel getModelLeft()
	{
		String stringLeaderId = textField_1.getText();
		int leaderId = Integer.parseInt(stringLeaderId);
		LinkedList<Team> leaderTeamList = teamCtr.getTeamsByLeaderId(leaderId);
		
		DefaultTableModel modelLeft = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			public boolean IsCellEditable(int row, int column)
			{
				//all cells false
				return false;
			}
		};
		
		modelLeft.setColumnIdentifiers(new String[] {"TeamId", "LeaderId", "NumberOfParticipants"});
		
		try
		{
			for(Team teamObj : leaderTeamList)
			{
				modelLeft.addRow(new String[]
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
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() 
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				Team teamObj = new Team();
				
				int selectedRow = table.getSelectedRow();
				String selectedData = (String) table.getValueAt(selectedRow, 0);
				int id = Integer.parseInt(selectedData);
				
				teamObj = teamCtr.getTeamById(id);
				
				textField.setText(String.valueOf(teamObj.getId()));
				textField_2.setText(String.valueOf(teamObj.getNumberOfParticipants()));
			}
		});
		
		return modelLeft;
	}
	
	//method to get the model for the right table
	public DefaultTableModel getModelRight()
	{
		String stringTeamId = textField.getText();
		int teamId = Integer.parseInt(stringTeamId);
		LinkedList<Participant> teamParticipantList = teamCtr.getTeamParticipantsByTeamId(teamId);
		
		DefaultTableModel modelRight = new DefaultTableModel()
		{
			private static final long serialVersionUID = 1L;
			public boolean IsCellEditable(int row, int column)
			{
				//all cells false
				return false;
			}
		};
		
		modelRight.setColumnIdentifiers(new String[] {"TeamId", "ParticipantId"});
		
		try
		{
			for(Participant participantObj : teamParticipantList)
			{
				modelRight.addRow(new String[]
						{
						String.valueOf(participantObj.getTeam().getId()),
						String.valueOf(participantObj.getGuest().getId())
						});
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception: " + e);
		}
		
		table_1.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = table_1.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener()
		{
			
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				Participant participantObj = new Participant();
				
				int selectedRow = table_1.getSelectedRow();
				String selectedData1 = (String) table_1.getValueAt(selectedRow, 0);
				String selectedData2 = (String) table_1.getValueAt(selectedRow, 1);
				int teamId = Integer.parseInt(selectedData1);
				int participantId = Integer.parseInt(selectedData2);
				
				participantObj = teamCtr.getTeamParticipant(teamId, participantId);
				
				textField_3.setText(String.valueOf(participantObj.getGuest().getId()));
			}
		});
		
		return modelRight;
	}
	
	public void clearValues()
	{
		textField.setText("");
		textField_2.setText("");
		textField_3.setText("");
	}
	
	public void putValuesOnTheScreen(Team teamObj)
	{
		String StringTeamId = String.valueOf(teamObj.getId());
		textField.setText(StringTeamId);
		
		String StringNumberOfParticipants = String.valueOf(teamObj.getNumberOfParticipants());
		textField_2.setText(StringNumberOfParticipants);
	}
	
	public void clearRightTable()
	{
		DefaultTableModel tdm2=(DefaultTableModel)table_1.getModel();
		tdm2.getDataVector().removeAllElements();
		tdm2.fireTableDataChanged();
	}
	
	public void clearLeftTable()
	{
		DefaultTableModel tdm1=(DefaultTableModel)table.getModel();
		tdm1.getDataVector().removeAllElements();
		tdm1.fireTableDataChanged();
	}
}
