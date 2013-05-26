package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

import Controller.RoomBookingCtr;
import Model.RoomBooking;

public class RoomBookingMenu {
	private static RoomBookingMenu instance=null;
	
	private JFrame frame;
	private JTextField txtRoomBookingId;
	private JTextField txtArrivalDate;
	private JTextField txtNumberOfChildren;
	private JTextField txtStatus;
	private JTextField txtDepartureDate;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomBookingMenu window = new RoomBookingMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		frame.setBounds(100, 100, 900, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Booking data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 7, 735, 120);
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
		
		JLabel lblRoomType = new JLabel("Room type:");
		lblRoomType.setBounds(375, 88, 100, 25);
		panel_1.add(lblRoomType);
		lblRoomType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtRoomBookingId = new JTextField();
		txtRoomBookingId.setBounds(96, 20, 240, 20);
		panel_1.add(txtRoomBookingId);
		txtRoomBookingId.setColumns(10);
		
		txtArrivalDate = new JTextField();
		txtArrivalDate.setBounds(96, 56, 240, 20);
		panel_1.add(txtArrivalDate);
		txtArrivalDate.setColumns(10);
		
		txtNumberOfChildren = new JTextField();
		txtNumberOfChildren.setBounds(96, 92, 240, 20);
		panel_1.add(txtNumberOfChildren);
		txtNumberOfChildren.setColumns(10);
		
		txtStatus = new JTextField();
		txtStatus.setBounds(485, 20, 240, 20);
		panel_1.add(txtStatus);
		txtStatus.setColumns(10);
		
		txtDepartureDate = new JTextField();
		txtDepartureDate.setBounds(485, 56, 240, 20);
		panel_1.add(txtDepartureDate);
		txtDepartureDate.setColumns(10);
		
		JComboBox cmbRoomType = new JComboBox();
		cmbRoomType.setBounds(485, 92, 240, 20);
		panel_1.add(cmbRoomType);
		
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
				
				if(txtRoomBookingId.getText().equals("")!=true)
				{
					String stringID=txtRoomBookingId.getText();
					System.out.println(stringID);
					int id=Integer.parseInt(stringID);
					RoomBooking rb = rbCtr.findRoomBookingByID(id);
					putValuesOnTheScreen(rb);
					
					DefaultTableModel tdm=(DefaultTableModel)table.getModel();
					tdm.getDataVector().removeAllElements();
					tdm.fireTableDataChanged();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please insert either id of the booking.", "Error!", JOptionPane.ERROR_MESSAGE);			
				}
				
				DefaultTableModel tdm=(DefaultTableModel)table.getModel();
				tdm.getDataVector().removeAllElements();
				tdm.fireTableDataChanged();
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
							txtDepartureDate.getText().equals("")!=true)
						{
							String arrival=txtArrivalDate.getText();
							int children=Integer.parseInt(txtNumberOfChildren.getText());
							String status=txtStatus.getText();
							String departure=txtDepartureDate.getText();
							
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
					JOptionPane.showMessageDialog(null, "Please insert either id of the booking.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				
				DefaultTableModel tdm=(DefaultTableModel)table.getModel();
				tdm.getDataVector().removeAllElements();
				tdm.fireTableDataChanged();
			}
		});
		btnUpdate.setBounds(6, 108, 110, 35);
		panel.add(btnUpdate);
		
		//delete room booking button
		JButton btnDelete = new JButton("Delete");
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
		panel.add(btnDelete);
	}
	
	private void putValuesOnTheScreen(RoomBooking rbObj)
	{
		txtRoomBookingId.setText(String.valueOf(rbObj.getId()));
		txtArrivalDate.setText(rbObj.getArrivalDate());
		txtNumberOfChildren.setText(String.valueOf(rbObj.getNumberOfChildren()));
		txtStatus.setText(rbObj.getStatus());
		txtDepartureDate.setText(rbObj.getDepartureDate());
	}

	private void clearValues()
	{
		txtRoomBookingId.setText(null);
		txtArrivalDate.setText(null);
		txtNumberOfChildren.setText(null);
		txtStatus.setText(null);
		txtDepartureDate.setText(null);
		
		DefaultTableModel tdm=(DefaultTableModel)table.getModel();
		tdm.getDataVector().removeAllElements();
		tdm.fireTableDataChanged();
	}

}
