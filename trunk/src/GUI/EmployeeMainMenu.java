package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class EmployeeMainMenu {
	private static EmployeeMainMenu instance;
	public int universalId;
	
	private JFrame frame;
	private final JButton btnActivityBooking = new JButton("Activity booking");
	private final JButton btnRoomBooking = new JButton("Room booking");
	private final JButton btnGuest = new JButton("Guests");
	private final JButton btnExit = new JButton("Exit");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeMainMenu window = new EmployeeMainMenu();
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
	private EmployeeMainMenu() {
		initialize();
		frame.setVisible(true);	
	}
	
	public static EmployeeMainMenu getInstance()
	{
		if (instance==null) {
			instance = new EmployeeMainMenu();
		}
		return instance;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Employee Menu");
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
		
		btnGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				PersonMenu.getInstance();
			}
		});
		btnGuest.setBounds(6, 60, 178, 41);
		frame.getContentPane().add(btnGuest);
	
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
			}
		});
		btnExit.setBounds(190, 60, 178, 41);
		frame.getContentPane().add(btnExit);
	}

}
