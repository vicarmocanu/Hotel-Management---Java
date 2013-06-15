package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Controller.RoomBookingCtr;
import Model.Room;
import Model.RoomBooking;
import Model.RoomLine;

public class EmployeeMenu
{

	private JFrame frame;
	private JTextField txtRoomBookingId;
	private JTextField txtNumberOfChildren;
	private JTextField txtStatus;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField txtDd;
	private JTextField txtMm;
	private JTextField txtYyyy;
	private JTextField txtDepDD;
	private JTextField txtDepmm;
	private JTextField txtDepyyyy;
	
	public EmployeeMenu()
	{
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize()
	{
		frame = new JFrame();
		frame.setFont(new Font("Dialog", Font.PLAIN, 14));
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1000, 600);
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
		logOffButton.setBounds(865, 550, 125, 39);
		frame.getContentPane().add(logOffButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 980, 528);
		frame.getContentPane().add(tabbedPane);
		
		JPanel WelcomePanel = new JPanel();
		tabbedPane.addTab("Main menu", null, WelcomePanel, null);
		
		JPanel GuestPanel = new JPanel();
		tabbedPane.addTab("Guest", null, GuestPanel, null);
		
		//delete room booking button
		/*JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				RoomBookingCtr rbCtr=new RoomBookingCtr();
				
				if(txtRoomBookingId.getText().equals("")!=true)
				{
					String stringID=txtRoomBookingId.getText();
					int bookingId=Integer.parseInt(stringID);
					
					RoomBooking rb=rbCtr.findRoomBookingByID(bookingId);
					if(rb!=null)
					{
						rbCtr.deleteBooking(bookingId);
						
						clearValues();
						
						JOptionPane.showMessageDialog(null, "Booking has been deleted.", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "There is no such booking!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please insert either id of the booking.", "Error!", JOptionPane.ERROR_MESSAGE);
				}						
			}
		});
		btnDelete.setBounds(6, 154, 110, 35);
		panel.add(btnDelete);*/
		
		JPanel RoomBookingPanel = new JPanel();
		tabbedPane.addTab("Room booking", null, RoomBookingPanel, null);	
		RoomBookingPanel.setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Booking data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 7, 759, 120);
		RoomBookingPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblBookingId = new JLabel("Booking ID:");
		lblBookingId.setBounds(6, 16, 100, 25);
		panel_1.add(lblBookingId);
		lblBookingId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblArrivalDate = new JLabel("Arrival date:");
		lblArrivalDate.setBounds(6, 52, 100, 25);
		panel_1.add(lblArrivalDate);
		lblArrivalDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNumberOfChildren = new JLabel("Children #:");
		lblNumberOfChildren.setBounds(6, 88, 100, 25);
		panel_1.add(lblNumberOfChildren);
		lblNumberOfChildren.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(375, 16, 100, 25);
		panel_1.add(lblStatus);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblDepartureDate = new JLabel("Departure date:");
		lblDepartureDate.setBounds(375, 52, 100, 25);
		panel_1.add(lblDepartureDate);
		lblDepartureDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtRoomBookingId = new JTextField();
		txtRoomBookingId.setBounds(96, 20, 240, 20);
		panel_1.add(txtRoomBookingId);
		txtRoomBookingId.setColumns(10);
		
		txtNumberOfChildren = new JTextField();
		txtNumberOfChildren.setBounds(96, 92, 240, 20);
		panel_1.add(txtNumberOfChildren);
		txtNumberOfChildren.setColumns(10);
		
		txtStatus = new JTextField();
		txtStatus.setBounds(485, 20, 240, 20);
		panel_1.add(txtStatus);
		txtStatus.setColumns(10);
		
		JLabel lblDdmmyyyy = new JLabel("DD:MM:YYYY");
		lblDdmmyyyy.setBounds(278, 59, 76, 14);
		panel_1.add(lblDdmmyyyy);
		
		JLabel lblDdmmyyyy_1 = new JLabel("DD:MM:YYYY");
		lblDdmmyyyy_1.setBounds(679, 59, 70, 14);
		panel_1.add(lblDdmmyyyy_1);
		
		txtDd = new JTextField();
		txtDd.setBounds(96, 56, 28, 20);
		panel_1.add(txtDd);
		txtDd.setColumns(10);
		
		JLabel label = new JLabel(":");
		label.setBounds(134, 59, 10, 14);
		panel_1.add(label);
		
		txtMm = new JTextField();
		txtMm.setBounds(144, 56, 28, 20);
		panel_1.add(txtMm);
		txtMm.setColumns(10);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(182, 59, 10, 14);
		panel_1.add(label_1);
		
		txtYyyy = new JTextField();
		txtYyyy.setBounds(202, 56, 66, 20);
		panel_1.add(txtYyyy);
		txtYyyy.setColumns(10);
		
		txtDepDD = new JTextField();
		txtDepDD.setBounds(485, 56, 28, 20);
		panel_1.add(txtDepDD);
		txtDepDD.setColumns(10);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(523, 59, 10, 14);
		panel_1.add(label_2);
		
		txtDepmm = new JTextField();
		txtDepmm.setBounds(533, 56, 28, 20);
		panel_1.add(txtDepmm);
		txtDepmm.setColumns(10);
		
		JLabel label_3 = new JLabel(":");
		label_3.setBounds(571, 59, 10, 14);
		panel_1.add(label_3);
		
		txtDepyyyy = new JTextField();
		txtDepyyyy.setBounds(583, 56, 86, 20);
		panel_1.add(txtDepyyyy);
		txtDepyyyy.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 142, 125, 338);
		RoomBookingPanel.add(panel);
		panel.setLayout(null);
		
		//search button
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				RoomBookingCtr rbCtr=new RoomBookingCtr();
				
				String txtArrivalDate = txtYyyy.getText()+txtMm.getText()+txtDd.getText();
				String txtDeparturedate = txtDepyyyy.getText()+txtDepmm.getText()+txtDepDD.getText();
				
				if(!txtRoomBookingId.getText().equals(""))
				{
					String stringID=txtRoomBookingId.getText();
					System.out.println(stringID);
					int id=Integer.parseInt(stringID);
					RoomBooking rb = rbCtr.findRoomBookingByID(id);
					putValuesOnTheScreen(rb);
					
					ArrayList<RoomLine> rlList=new ArrayList<RoomLine>();
					rlList=rbCtr.findRoomLinesForBooking(id);
					
					DefaultTableModel model2 = new DefaultTableModel()
					{
						private static final long serialVersionUID = 1L;

						@Override
						public boolean isCellEditable(int row, int column)
						{
							//all cells false
							return false;
						}
					};
				
					model2.setColumnIdentifiers(new String[] {"Booking id", "Guest", "Guest type", "Room", "Room type", "Room price"});
				
					try
					{
						for(RoomLine rlObj : rlList)
						{
							model2.addRow(new String[]
								{
									String.valueOf(rlObj.getBooking().getId()),
									rlObj.getGuest().getName(),
									rlObj.getGuest().getGuestType(),
									String.valueOf(rlObj.getRoom().getNumber()),
									rlObj.getRoom().getRoomType().getCategory(),
									String.valueOf(rlObj.getRoom().getRoomType().getPrice())
								});
						}
						table.setModel(model2);
					}
				
					catch(Exception e)
					{
						System.out.println("Exception: " + e);
					}
				}
				else if(!txtArrivalDate.equals(""))
				{
					int arrival = Integer.parseInt(txtArrivalDate);
					ArrayList<RoomBooking> rbList=new ArrayList<RoomBooking>();
					rbList=rbCtr.findRoomBookingByArrival(arrival);
					
					DefaultTableModel model2 = new DefaultTableModel()
					{
						private static final long serialVersionUID = 1L;

						@Override
						public boolean isCellEditable(int row, int column)
						{
							//all cells false
							return false;
						}
					};
				
					model2.setColumnIdentifiers(new String[] {"Booking id", "Guest", "Guest type", "Room", "Room type", "Room price"});
				
					try
					{
						for(RoomBooking rbObj : rbList)
						{
							ArrayList<RoomLine> rlList = new ArrayList<>();
							rlList=rbCtr.findRoomLinesForBooking(rbObj.getId());
							try
							{
								for(RoomLine rlObj : rlList)
								{
									model2.addRow(new String[]
										{
											String.valueOf(rlObj.getBooking().getId()),
											rlObj.getGuest().getName(),
											rlObj.getGuest().getGuestType(),
											String.valueOf(rlObj.getRoom().getNumber()),
											rlObj.getRoom().getRoomType().getCategory(),
											String.valueOf(rlObj.getRoom().getRoomType().getPrice())
										});
								}
								table.setModel(model2);
							}						
							catch(Exception e)
							{
								System.out.println("Exception: " + e);
							}
						}
					}
					catch(Exception e)
					{
						System.out.println("Exception: " + e);
					}
				}
				else if(!txtDeparturedate.equals(""))
				{
					int departure = Integer.parseInt(txtDeparturedate);
					ArrayList<RoomBooking> rbList=new ArrayList<RoomBooking>();
					rbList=rbCtr.findRoomBookingByDeparture(departure);
					
					DefaultTableModel model2 = new DefaultTableModel()
					{
						private static final long serialVersionUID = 1L;

						@Override
						public boolean isCellEditable(int row, int column)
						{
							//all cells false
							return false;
						}
					};
				
					model2.setColumnIdentifiers(new String[] {"Booking id", "Guest", "Guest type", "Room", "Room type", "Room price"});
				
					try
					{
						for(RoomBooking rbObj : rbList)
						{
							ArrayList<RoomLine> rlList = new ArrayList<>();
							rlList=rbCtr.findRoomLinesForBooking(rbObj.getId());
							try
							{
								for(RoomLine rlObj : rlList)
								{
									model2.addRow(new String[]
										{
											String.valueOf(rlObj.getBooking().getId()),
											rlObj.getGuest().getName(),
											rlObj.getGuest().getGuestType(),
											String.valueOf(rlObj.getRoom().getNumber()),
											rlObj.getRoom().getRoomType().getCategory(),
											String.valueOf(rlObj.getRoom().getRoomType().getPrice())
										});
								}
								table.setModel(model2);
							}
						
							catch(Exception e)
							{
								System.out.println("Exception: " + e);
							}
						}
					}
					catch(Exception e)
					{
						System.out.println("Exception: " + e);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please insert id of the booking.", "Error!", JOptionPane.ERROR_MESSAGE);			
				}
			}
		});
		btnNewButton.setBounds(6, 16, 110, 35);
		panel.add(btnNewButton);
		
		//update room booking button
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				RoomBookingCtr rbCtr=new RoomBookingCtr();				

				String txtArrivalDate = txtYyyy.getText()+txtMm.getText()+txtDd.getText();
				String txtDeparturedate = txtDepyyyy.getText()+txtDepmm.getText()+txtDepDD.getText();
				
				if(txtRoomBookingId.getText().equals("")!=true)
				{
					String stringID=txtRoomBookingId.getText();
					int rbId=Integer.parseInt(stringID);
					
					RoomBooking rb1=rbCtr.findRoomBookingByID(rbId);
					if(rb1!=null)
					{
						if(txtArrivalDate.equals("")!=true || 
							txtNumberOfChildren.getText().equals("")!=true ||
							txtStatus.getText().equals("")!=true ||
							txtDeparturedate.equals("")!=true)
						{
							int arrival=Integer.parseInt(txtArrivalDate);
							int children=Integer.parseInt(txtNumberOfChildren.getText());
							String status=txtStatus.getText();
							int departure=Integer.parseInt(txtDeparturedate);
							
							rbCtr.updateBooking(rbId, arrival, departure, status, children);
							
							clearValues();
							
							JOptionPane.showMessageDialog(null, "Booking has been successfully updated.", "Information", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Some fields may be empty!", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "There is no such booking!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please insert id of the booking.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				
				DefaultTableModel tdm=(DefaultTableModel)table.getModel();
				tdm.getDataVector().removeAllElements();
				tdm.fireTableDataChanged();
			}
		});
		btnUpdate.setBounds(6, 108, 110, 35);
		panel.add(btnUpdate);
		
			//create button
			JButton btnCreate = new JButton("Create");
			btnCreate.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					String txtArrivalDate = txtYyyy.getText()+txtMm.getText()+txtDd.getText();
					String txtDeparturedate = txtDepyyyy.getText()+txtDepmm.getText()+txtDepDD.getText();
					
					if(txtArrivalDate.equals("")==true || txtDeparturedate.equals("")==true || txtNumberOfChildren.getText().equals("")==true || txtStatus.getText().equals("")==true)
					{
						JOptionPane.showMessageDialog(null, "Please insert all data!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						RoomBookingCtr rbCtr=new RoomBookingCtr();
						int arrival=Integer.parseInt(txtArrivalDate);
						int departure=Integer.parseInt(txtDeparturedate);
						String status=txtStatus.getText();
						int numberOfChildren=Integer.parseInt(txtNumberOfChildren.getText());
						System.out.println(txtArrivalDate);
						int bookingId = rbCtr.createNewBooking(arrival, departure, status, numberOfChildren);
						
						DefaultTableModel tdm=(DefaultTableModel)table.getModel();
						tdm.getDataVector().removeAllElements();
						tdm.fireTableDataChanged();
						
						RoomLineMenu.getInstance(bookingId, arrival, departure);
					}
				}
			});
			btnCreate.setBounds(6, 62, 110, 35);
			panel.add(btnCreate);
			
			//get available room lines 
			JButton btnGetRoomsAvailable = new JButton("Available rooms");
			btnGetRoomsAvailable.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					RoomBookingCtr rbCtr=new RoomBookingCtr();
					String txtArrivalDate = txtYyyy.getText()+txtMm.getText()+txtDd.getText();
					String txtDeparturedate = txtDepyyyy.getText()+txtDepmm.getText()+txtDepDD.getText();
					
					if(txtArrivalDate.equals("") || txtDeparturedate.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Please insert arrival date and departure date.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						int arrival=Integer.parseInt(txtArrivalDate);
						int departure=Integer.parseInt(txtDeparturedate);
						
						ArrayList<Room> rlList=new ArrayList<Room>();
						rlList=rbCtr.findAvailableRooms(arrival, departure);
						
						DefaultTableModel model2 = new DefaultTableModel()
						{
							private static final long serialVersionUID = 1L;

							@Override
							public boolean isCellEditable(int row, int column)
							{
								//all cells false
								return false;
							}
						};
					
						model2.setColumnIdentifiers(new String[] {"Booking id", "Guest", "Guest type", "Room", "Room type", "Room price"});
					
						try
						{
							for(Room rlObj : rlList)
							{
								model2.addRow(new String[]
									{
										"None",//String.valueOf(rlObj.getBooking().getId()),//None
										"None",//rlObj.getGuest().getName(),//None
										"None",//rlObj.getGuest().getGuestType(),//None
										String.valueOf(rlObj.getNumber()),
										rlObj.getRoomType().getCategory(),
										String.valueOf(rlObj.getRoomType().getPrice())
									});
							}
							table.setModel(model2);
						}
					
						catch(Exception e)
						{
							System.out.println("Exception: " + e);
						}
					}
				}
			});
			btnGetRoomsAvailable.setBounds(6, 200, 110, 35);
			panel.add(btnGetRoomsAvailable);
			
			//clear window data button
			JButton btnClear = new JButton("Clear");
			btnClear.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					clearValues();
				}
			});
			btnClear.setBounds(6, 246, 110, 35);
			panel.add(btnClear);
			
			JButton btnClose = new JButton("Close");
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
				}
			});
			btnClose.setBounds(6, 292, 110, 35);
			panel.add(btnClose);
			
			//scroll pane and table creation
			scrollPane = new JScrollPane();
			scrollPane.setBounds(146, 142, 744, 346);
			RoomBookingPanel.add(scrollPane);
			
			table = new JTable();
			scrollPane.setViewportView(table);
			table.setFillsViewportHeight(true);
		//end room booking panel
		
		JPanel ActivityBookingPanel = new JPanel();
		tabbedPane.addTab("Activity booking", null, ActivityBookingPanel, null);
		
		
	}
	
	private void putValuesOnTheScreen(RoomBooking rbObj)
	{
		txtRoomBookingId.setText(String.valueOf(rbObj.getId()));
		txtDd.setText(String.valueOf(rbObj.getArrivalDate()).substring(6, 8));
		txtMm.setText(String.valueOf(rbObj.getArrivalDate()).substring(4, 6));
		txtYyyy.setText(String.valueOf(rbObj.getArrivalDate()).substring(0, 4));
		txtDepDD.setText(String.valueOf(rbObj.getDepartureDate()).substring(6, 8));
		txtDepmm.setText(String.valueOf(rbObj.getDepartureDate()).substring(4, 6));
		txtDepyyyy.setText(String.valueOf(rbObj.getDepartureDate()).substring(0, 4));
		txtNumberOfChildren.setText(String.valueOf(rbObj.getNumberOfChildren()));
		txtStatus.setText(rbObj.getStatus());
	}

	private void clearValues()
	{
		txtRoomBookingId.setText(null);
		txtDd.setText(null);
		txtMm.setText(null);
		txtYyyy.setText(null);
		txtDepDD.setText(null);
		txtDepmm.setText(null);
		txtDepyyyy.setText(null);
		txtNumberOfChildren.setText(null);
		txtStatus.setText(null);
		
		DefaultTableModel tdm=(DefaultTableModel)table.getModel();
		tdm.getDataVector().removeAllElements();
		tdm.fireTableDataChanged();
	}
}
