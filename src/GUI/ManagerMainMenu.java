package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ManagerMainMenu {
	private static ManagerMainMenu instance;
	public int universalId;

	private JFrame frame;
	private final JButton btnActivityBooking = new JButton("Activity booking");
	private final JButton btnRoomBooking = new JButton("Room booking");
	private final JButton btnPerson = new JButton("Person");
	private final JButton btnExit = new JButton("Exit");
	private final JButton btnFacility_1 = new JButton("Facility");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerMainMenu window = new ManagerMainMenu();
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
	public ManagerMainMenu() {
		initialize();
		frame.setVisible(true);	
	}
	
	public static ManagerMainMenu getInstance()
	{
		if (instance==null) {
			instance = new ManagerMainMenu();
		}
		return instance;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		frame = new JFrame("Manager Menu");
		frame.setBounds(100, 100, 395, 176);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		btnActivityBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ActivityBookingMenu.getInstance();
			}
		});
		
		btnActivityBooking.setBounds(6, 16, 178, 41);
		frame.getContentPane().add(btnActivityBooking);
		
		btnRoomBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				RoomBookingMenu.getInstance();
			}
		});
		
		btnRoomBooking.setBounds(190, 16, 178, 41);
		frame.getContentPane().add(btnRoomBooking);
	
		
		
		btnPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				PersonMenu.getInstance();
			}
		});
		btnPerson.setBounds(6, 60, 178, 41);
		frame.getContentPane().add(btnPerson);
		
		
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
			}
		});
		btnExit.setBounds(6, 104, 363, 38);
		frame.getContentPane().add(btnExit);
		
		
		btnFacility_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FacilityMenu.getInstance();
			}
		});
		btnFacility_1.setBounds(190, 63, 178, 38);
		
		frame.getContentPane().add(btnFacility_1);

	}

}
