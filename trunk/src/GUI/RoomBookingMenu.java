package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Controller.RoomBookingCtr;
import Model.Room;
import Model.RoomBooking;
import Model.RoomLine;

public class RoomBookingMenu {
	private static RoomBookingMenu instance=null;
	
	private JFrame frame;
	private JTextField txtRoomBookingId;
	private JTextField txtNumberOfChildren;
	private JTextField txtStatus;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField txtArrivalDate;
	private JTextField txtDeparturedate;

	/**
	 * Create the application.
	 */
	private RoomBookingMenu() {
		initialize();
		frame.setVisible(true);
	}

	public static RoomBookingMenu getInstance()
	{
		if(instance==null)
		{
			instance=new RoomBookingMenu();
		}
		return instance;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Room booking");
		frame.setBounds(100, 100, 913, 530);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Booking data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 7, 759, 120);
		frame.getContentPane().add(panel_1);
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
		
		JLabel lblDdmmyyyy = new JLabel("YYYY:MM:DD");
		lblDdmmyyyy.setBounds(278, 59, 76, 14);
		panel_1.add(lblDdmmyyyy);
		
		JLabel lblDdmmyyyy_1 = new JLabel("YYYY:MM:DD");
		lblDdmmyyyy_1.setBounds(679, 59, 70, 14);
		panel_1.add(lblDdmmyyyy_1);
		
		txtArrivalDate = new JTextField();
		txtArrivalDate.setBounds(96, 56, 172, 20);
		panel_1.add(txtArrivalDate);
		txtArrivalDate.setColumns(10);
		
		txtDeparturedate = new JTextField();
		txtDeparturedate.setBounds(485, 56, 184, 20);
		panel_1.add(txtDeparturedate);
		txtDeparturedate.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 142, 125, 338);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//search button
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				RoomBookingCtr rbCtr=new RoomBookingCtr();
				
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
				else if(!txtArrivalDate.getText().equals(""))
				{
					int arrival = Integer.parseInt(txtArrivalDate.getText());
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
				else if(!txtDeparturedate.getText().equals(""))
				{
					int departure = Integer.parseInt(txtDeparturedate.getText());
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
				
				if(txtRoomBookingId.getText().equals("")!=true)
				{
					String stringID=txtRoomBookingId.getText();
					int rbId=Integer.parseInt(stringID);
					
					RoomBooking rb1=rbCtr.findRoomBookingByID(rbId);
					if(rb1!=null)
					{
						if(txtArrivalDate.getText().equals("")!=true || 
							txtNumberOfChildren.getText().equals("")!=true ||
							txtStatus.getText().equals("")!=true ||
							txtDeparturedate.getText().equals("")!=true)
						{
							int arrival=Integer.parseInt(txtArrivalDate.getText());
							int children=Integer.parseInt(txtNumberOfChildren.getText());
							String status=txtStatus.getText();
							int departure=Integer.parseInt(txtDeparturedate.getText());
							
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
	
		//create button
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(txtArrivalDate.getText().equals("")==true || txtDeparturedate.getText().equals("")==true || txtNumberOfChildren.getText().equals("")==true || txtStatus.getText().equals("")==true)
				{
					JOptionPane.showMessageDialog(null, "Please insert all data!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					RoomBookingCtr rbCtr=new RoomBookingCtr();
					int arrival=Integer.parseInt(txtArrivalDate.getText());
					int departure=Integer.parseInt(txtDeparturedate.getText());
					String status=txtStatus.getText();
					int numberOfChildren=Integer.parseInt(txtNumberOfChildren.getText());
					System.out.println(txtArrivalDate.getText());
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
				
				if(txtArrivalDate.getText().equals("") || txtDeparturedate.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please insert arrival date and departure date.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					int arrival=Integer.parseInt(txtArrivalDate.getText());
					int departure=Integer.parseInt(txtDeparturedate.getText());
					
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
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);

}
	
	private void putValuesOnTheScreen(RoomBooking rbObj)
	{
		txtRoomBookingId.setText(String.valueOf(rbObj.getId()));
		txtArrivalDate.setText(String.valueOf(rbObj.getArrivalDate()));
		txtDeparturedate.setText(String.valueOf(rbObj.getDepartureDate()));
		txtNumberOfChildren.setText(String.valueOf(rbObj.getNumberOfChildren()));
		txtStatus.setText(rbObj.getStatus());
	}

	private void clearValues()
	{
		txtRoomBookingId.setText(null);
		txtArrivalDate.setText(null);
		txtDeparturedate.setText(null);
		txtNumberOfChildren.setText(null);
		txtStatus.setText(null);
		
		DefaultTableModel tdm=(DefaultTableModel)table.getModel();
		tdm.getDataVector().removeAllElements();
		tdm.fireTableDataChanged();
	}
}
