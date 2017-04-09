
import javax.swing.*;

import java.awt.*;

class MessageDialog02 extends JDialog {
	public MessageDialog02()  {
		String pathToIcon = "C:\\D. Chandra\\Java\\D.Chandra New\\ClassroomExercises\\src\\Swings\\";
		Icon icon=new ImageIcon(pathToIcon+"dukeWaveRed.gif");
		
		Container c=getContentPane();
		JButton okButton=new JButton("Ok", icon);
		JButton clearButton = new JButton("Clear");
		JButton cancelButton = new JButton("Cancel");
		JLabel message=new JLabel("Welcome to Swings! This is a dialog box",JLabel.CENTER);
		JPanel southpanel=new JPanel();
		southpanel.add(okButton);
		southpanel.add(clearButton);
		southpanel.add(cancelButton);
		
		c.add(southpanel,"South");
		c.add(message,"Center");
		this.setTitle("My Dialog Box");
		setSize(400,200);
		setVisible(true);
	}
	public static void main (String[] args) 	{
		 new MessageDialog02();
	}
}

