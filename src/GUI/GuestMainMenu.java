package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

public class GuestMainMenu
{
	public int universalId;
	public String welcomeName;
	
	private JFrame frame;
	private final JTextField textField = new JTextField();
	private final JLabel lblNewLabel = new JLabel("User Id:");
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JButton btnActivityBooking = new JButton("Activity booking");
	private final JPanel panel_2 = new JPanel();

	
	public GuestMainMenu()
	{
		initialize();		
		frame.setVisible(true);
		
		
	}
	
	public void setUniversalId(int newUniversalId)
	{
		this.universalId = newUniversalId;
		String stringid = String.valueOf(universalId);
		textField.setText(stringid);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame("Guest main menu");
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Team menu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(6, 81, 190, 64);
		
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Team");
		btnNewButton.setBounds(6, 16, 178, 41);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GuestTeamMenu guestTeamMenu = new GuestTeamMenu();
				guestTeamMenu.setUniversalId(universalId);
			}
		});
		panel.setBorder(new TitledBorder(null, "Welcome", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 16, 183, 53);
		
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		textField.setBounds(82, 16, 95, 29);
		panel.add(textField);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setEditable(false);
		textField.setColumns(10);
		lblNewLabel.setBounds(6, 16, 77, 30);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.setBorder(new TitledBorder(null, "Activity booking menu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(6, 147, 190, 64);
		
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		btnActivityBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GuestActivityBookingMenu activityBookingMenu = new GuestActivityBookingMenu();
				activityBookingMenu.setUniversalId(universalId);
			}
		});
		btnActivityBooking.setBounds(6, 16, 178, 41);
		panel_2.add(btnActivityBooking);
		
		JTextPane txtpnMenuToBook = new JTextPane();
		txtpnMenuToBook.setEditable(false);
		txtpnMenuToBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnMenuToBook.setText("Menu to book various activities, review\r\n bookings or remove booked activites");
		txtpnMenuToBook.setBounds(202, 156, 250, 53);
		frame.getContentPane().add(txtpnMenuToBook);
		
		JTextPane txtpnMenuToReview = new JTextPane();
		txtpnMenuToReview.setText("Menu to review, create, update or delete teams");
		txtpnMenuToReview.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnMenuToReview.setEditable(false);
		txtpnMenuToReview.setBounds(202, 92, 250, 53);
		frame.getContentPane().add(txtpnMenuToReview);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
			}
		});
		btnClose.setBounds(346, 310, 178, 41);
		frame.getContentPane().add(btnClose);
		
	}
	
	
	
}
