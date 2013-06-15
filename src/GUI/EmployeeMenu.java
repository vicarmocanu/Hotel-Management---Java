package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

public class EmployeeMenu
{

	private JFrame frame;
	
	public EmployeeMenu()
	{
		initialize();
	}
	
	private void initialize()
	{
		frame = new JFrame();
		frame.setFont(new Font("Dialog", Font.PLAIN, 14));
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		
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
		
		JPanel RoomBookingPanel = new JPanel();
		tabbedPane.addTab("Room booking", null, RoomBookingPanel, null);
		
		JPanel ActivityBookingPanel = new JPanel();
		tabbedPane.addTab("Activity booking", null, ActivityBookingPanel, null);
		
		
	}
}
