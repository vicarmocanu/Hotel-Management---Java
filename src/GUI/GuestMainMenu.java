package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GuestMainMenu
{
	public int universalId;
	private JFrame frame;
	private final JTextField textField = new JTextField();

	
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
	private void initialize() {
		textField.setBounds(48, 55, 57, 23);
		textField.setColumns(10);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GuestTeamMenu guestTeamMenu = new GuestTeamMenu();
				guestTeamMenu.setUniversalId(universalId);
			}
		});
		btnNewButton.setBounds(174, 73, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		frame.getContentPane().add(textField);
		
	}

}
