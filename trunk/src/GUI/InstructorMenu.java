package GUI;

import javax.swing.JFrame;

public class InstructorMenu {

	public JFrame frame;

	

	
	public InstructorMenu()
	{
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
