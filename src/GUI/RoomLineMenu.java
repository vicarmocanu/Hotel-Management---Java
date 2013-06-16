package GUI;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

import Controller.GuestCtr;
import Controller.LocationCtr;
import Controller.PersonCtr;
import Controller.RoomBookingCtr;
import Controller.TravelAgencyCtr;
import DBLayer.GetMax;
import Model.Guest;
import Model.Location;
import Model.Room;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RoomLineMenu {
	private static RoomLineMenu instance;
	private JFrame frame;
	private JTextField txtGuestid;
	private JTextField txtName;
	private JTextField txtZipcode;
	private JTextField txtCountry;
	private JTextField txtAddress;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtPassword;
	private JTextField txtConfirmPassword;
	private JTextField txtGuestType;
	private JTextField txtTravelAgency;
	private JTextField txtRoomNumber;
	private JTextField txtBookingId;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField txtCity;

	/**
	 * Create the application.
	 */
	private RoomLineMenu(int bookingId, int arrival, int departure) {
		initialize(bookingId, arrival, departure);
		frame.setVisible(true);
	}

	public static RoomLineMenu getInstance(int bookingId, int arrival, int departure)
	{
		if(instance==null)
		{
			instance = new RoomLineMenu(bookingId, arrival, departure);
		}
		return instance;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int bookingId, final int arrival, final int departure) {
		frame = new JFrame("Room line menu");
		frame.setBounds(100, 100, 480, 530);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Guest data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 7, 444, 231);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblGuestId = new JLabel("Guest ID:");
		lblGuestId.setBounds(10, 28, 46, 14);
		panel_1.add(lblGuestId);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 53, 46, 14);
		panel_1.add(lblName);
		
		JLabel lblNewLabel = new JLabel("zip code:");
		lblNewLabel.setBounds(10, 78, 46, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setBounds(10, 110, 46, 14);
		panel_1.add(lblCountry);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(10, 142, 46, 14);
		panel_1.add(lblAddress);
		
		JLabel lblPhoneNumber = new JLabel("Phone #:");
		lblPhoneNumber.setBounds(10, 175, 46, 14);
		panel_1.add(lblPhoneNumber);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 206, 46, 14);
		panel_1.add(lblEmail);
		
		txtGuestid = new JTextField();
		txtGuestid.setBounds(66, 25, 130, 20);
		panel_1.add(txtGuestid);
		txtGuestid.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(66, 50, 130, 20);
		panel_1.add(txtName);
		txtName.setColumns(10);
		
		txtZipcode = new JTextField();
		txtZipcode.setBounds(66, 75, 130, 20);
		panel_1.add(txtZipcode);
		txtZipcode.setColumns(10);
		
		txtCountry = new JTextField();
		txtCountry.setBounds(66, 107, 130, 20);
		panel_1.add(txtCountry);
		txtCountry.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(66, 139, 130, 20);
		panel_1.add(txtAddress);
		txtAddress.setColumns(10);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(66, 172, 130, 20);
		panel_1.add(txtPhone);
		txtPhone.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(66, 203, 130, 20);
		panel_1.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(216, 53, 64, 14);
		panel_1.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(304, 50, 130, 20);
		panel_1.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblConfirm = new JLabel("Confirm:");
		lblConfirm.setBounds(216, 78, 46, 14);
		panel_1.add(lblConfirm);
		
		txtConfirmPassword = new JTextField();
		txtConfirmPassword.setText("");
		txtConfirmPassword.setBounds(304, 75, 130, 20);
		panel_1.add(txtConfirmPassword);
		txtConfirmPassword.setColumns(10);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(216, 142, 46, 14);
		panel_1.add(lblType);
		
		txtGuestType = new JTextField();
		txtGuestType.setBounds(304, 139, 130, 20);
		panel_1.add(txtGuestType);
		txtGuestType.setColumns(10);
		
		JLabel lblTravelAgency = new JLabel("Travel agency:");
		lblTravelAgency.setBounds(215, 175, 79, 14);
		panel_1.add(lblTravelAgency);
		
		txtTravelAgency = new JTextField();
		txtTravelAgency.setBounds(304, 172, 130, 20);
		panel_1.add(txtTravelAgency);
		txtTravelAgency.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuestCtr guestCtr = new GuestCtr();
				
				if(!txtGuestid.getText().equals(""))
				{
					String stringID=txtGuestid.getText();
					System.out.println(stringID);
					int id=Integer.parseInt(stringID);
					Guest guest = guestCtr.searchGuestById(id);
					putValuesOnTheScreen(guest);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please insert id of the guest.", "Error!", JOptionPane.ERROR_MESSAGE);			
				}
			}
		});
		btnSearch.setBounds(246, 202, 89, 23);
		panel_1.add(btnSearch);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtAddress.getText().equals("") || txtConfirmPassword.getText().equals("") || txtCountry.getText().equals("") || txtEmail.getText().equals("") ||
						txtGuestType.getText().equals("") || txtName.getText().equals("") || txtPassword.getText().equals("") || txtPhone.getText().equals("")
						 || txtZipcode.getText().equals("") || txtTravelAgency.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please insert all data!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					GuestCtr guestCtr = new GuestCtr();
					PersonCtr personCtr = new PersonCtr();
					LocationCtr locationCtr = new LocationCtr();
					TravelAgencyCtr travelAgencyCtr = new TravelAgencyCtr();
					
					String name = txtName.getText();
					String guestAddress = txtAddress.getText();
					String guestCountry = txtCountry.getText();
					String guestCity = txtCity.getText();
					String guestEmail = txtEmail.getText();
					String guestPhoneNo = txtPhone.getText();
					int guestZipcode = Integer.parseInt(txtZipcode.getText());
					int travelAgency = travelAgencyCtr.getTravelAgencyByName(txtTravelAgency.getText()).getCVR();
					String guestPassword=txtPassword.getText();
					String guestType=txtGuestType.getText();				
					
					if(personCtr.searchPersonByName(name) != null)
					{
						JOptionPane.showMessageDialog(null, "Cannot register the same guest twice.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						Location locationObj = locationCtr.getCompleteLocation(guestZipcode, guestCountry, guestCity);
						if(locationObj == null)
						{
							locationCtr.insertLocation(guestZipcode, guestCountry, guestCity);
						}
						
						personCtr.insertPerson(name, guestAddress, guestZipcode, guestCountry, guestPhoneNo, guestEmail, "Guest", guestPassword);
						guestCtr.insertGuest(name, travelAgency, guestType);
						JOptionPane.showMessageDialog(null, "Guest successfully inserted", "Info", JOptionPane.INFORMATION_MESSAGE);
					}			
					txtGuestid.setText(String.valueOf(GetMax.getMaxId("Select max(personId) from Guest")));								
				}
			}
		});
		btnCreate.setBounds(345, 202, 89, 23);
		panel_1.add(btnCreate);
		
		/*JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuestCtr guestCtr = new GuestCtr();
				
				if(txtGuestid.getText().equals("")!=true)
				{
					String stringID=txtGuestid.getText();
					int rbId=Integer.parseInt(stringID);
					
					Guest guest=guestCtr.searchGuestById(rbId);
					if(guest!=null)
					{
						if(txtAddress.getText().equals("") || txtConfirmPassword.getText().equals("") || txtCountry.getText().equals("") || txtEmail.getText().equals("") ||
								txtGuestType.getText().equals("") || txtName.getText().equals("") || txtPassword.getText().equals("") || txtPhone.getText().equals("")
								 || txtZipcode.getText().equals("") || txtTravelAgency.getText().equals(""))
						{
							TravelAgencyCtr travelAgencyCtr = new TravelAgencyCtr();
							
							String name = txtName.getText();
							String address = txtAddress.getText();
							String country = txtCountry.getText();
							String email = txtEmail.getText();
							String phone = txtPhone.getText();
							int zipcode = Integer.parseInt(txtZipcode.getText());
							int travelAgency = travelAgencyCtr.getTravelAgencyByName(txtTravelAgency.getText()).getCVR();
							String password;
							if (txtPassword!=txtConfirmPassword) {
								JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error!", JOptionPane.ERROR_MESSAGE);
							}
							else{
								password=txtPassword.getText();
								String guestType=txtGuestType.getText();
								
								guestCtr.updateGuest(rbId, guestType, travelAgency);	
								clearValues();
							
								JOptionPane.showMessageDialog(null, "Guest has been successfully updated.", "Information", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Some fields may be empty!", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "There is no such guest!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please insert id of the guest.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				
				DefaultTableModel tdm=(DefaultTableModel)table.getModel();
				tdm.getDataVector().removeAllElements();
				tdm.fireTableDataChanged();
			}
		});
		btnUpdate.setBounds(326, 202, 89, 23);
		panel_1.add(btnUpdate);*/
		
		JLabel lblBookingId = new JLabel("Booking ID:");
		lblBookingId.setBounds(216, 28, 64, 14);
		panel_1.add(lblBookingId);
		
		txtBookingId = new JTextField();
		txtBookingId.setBounds(304, 25, 130, 20);
		panel_1.add(txtBookingId);
		txtBookingId.setColumns(10);
		txtBookingId.setText(String.valueOf(bookingId));
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(216, 110, 46, 14);
		panel_1.add(lblCity);
		
		txtCity = new JTextField();
		txtCity.setBounds(304, 107, 130, 20);
		panel_1.add(txtCity);
		txtCity.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Room information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 257, 444, 194);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblRoomNumber = new JLabel("Room number:");
		lblRoomNumber.setBounds(10, 24, 97, 14);
		panel.add(lblRoomNumber);
		
		txtRoomNumber = new JTextField();
		txtRoomNumber.setBounds(117, 21, 97, 20);
		panel.add(txtRoomNumber);
		txtRoomNumber.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoomBookingCtr rbCtr = new RoomBookingCtr();
				
				if(txtGuestid.getText().equals("") || txtRoomNumber.getText().equals("") || txtBookingId.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Data missing!");
				}
				else
				{
					int guest = Integer.parseInt(txtGuestid.getText());
					int room = Integer.parseInt(txtRoomNumber.getText());
					int booking = Integer.parseInt(txtBookingId.getText());
					
					rbCtr.createRoomLine(guest, room, booking);
					
					clearValues();					

					DefaultTableModel tdm=(DefaultTableModel)table.getModel();
					tdm.getDataVector().removeAllElements();
					tdm.fireTableDataChanged();
				}
			}
		});
		btnAdd.setBounds(266, 458, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnClose.setBounds(365, 458, 89, 23);
		frame.getContentPane().add(btnClose);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 424, 131);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
			
		JButton btnShowAvailableRooms = new JButton("Show available rooms");
		btnShowAvailableRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoomBookingCtr rbCtr = new RoomBookingCtr();
				ArrayList<Room> rooms = rbCtr.findAvailableRooms(arrival, departure);
				
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
			
				model2.setColumnIdentifiers(new String[] {"Room", "Room type", "Room price"});
			
				try
				{
					for(Room roomObj : rooms)
					{
						model2.addRow(new String[]
							{
								String.valueOf(roomObj.getNumber()),
								roomObj.getRoomType().getCategory(),
								String.valueOf(roomObj.getRoomType().getPrice())
							});
					}
					table.setModel(model2);
				}
				catch(Exception e1)
				{
					System.out.println("Exception: " + e1);
				}					
			}
		});
		btnShowAvailableRooms.setBounds(286, 20, 148, 23);
		panel.add(btnShowAvailableRooms);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(20, 458, 89, 23);
		frame.getContentPane().add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearValues();
			}
		});
	}
	
	private void putValuesOnTheScreen(Guest guest)
	{
		txtName.setText(guest.getName());
		txtAddress.setText(guest.getAddress());
		txtCountry.setText(guest.getCountry());
		txtEmail.setText(guest.getEmail());
		txtPhone.setText(guest.getPhoneNo());
		txtZipcode.setText(String.valueOf(guest.getZipCode()));
		txtGuestType.setText(guest.getGuestType());
		txtTravelAgency.setText(guest.getTravelAgency().getName());
	}

	private void clearValues()
	{
		txtGuestid.setText(null);
		txtName.setText(null);
		txtAddress.setText(null);
		txtCountry.setText(null);
		txtCity.setText(null);
		txtEmail.setText(null);
		txtPhone.setText(null);
		txtGuestType.setText(null);
		txtZipcode.setText(null);
		txtTravelAgency.setText(null);
		txtPassword.setText(null);
		txtConfirmPassword.setText(null);
		
		txtRoomNumber.setText(null);
		
		DefaultTableModel tdm=(DefaultTableModel)table.getModel();
		tdm.getDataVector().removeAllElements();
		tdm.fireTableDataChanged();
	}
}
