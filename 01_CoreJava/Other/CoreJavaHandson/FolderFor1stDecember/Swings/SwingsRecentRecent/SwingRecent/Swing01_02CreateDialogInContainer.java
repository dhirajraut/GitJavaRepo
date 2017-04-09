import java.awt.Container;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Swing01_02CreateDialogInContainer
	{	public static void main(String[] args)
			{	JFrame j = new JFrame();
				j.setBounds(100, 100, 500, 500);
				j.setVisible(true);
				
				System.out.println(getDialog(j));
				j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				j.validate();
			}
		
		public static String getDialog(Container c)
			{	String [] selections = { "Ignore", "Add to log", "Change" };
				String pathToIcon = "E:\\D. Chandra\\Java\\D.Chandra New\\ClassroomExercises\\src\\Swings\\SwingRecent\\";
				Icon icon=new ImageIcon(pathToIcon+"dukeWaveRed.gif");
				
				JOptionPane jop = new JOptionPane("Select Activity", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,icon,selections, selections[2]);
				
				JDialog db = jop.createDialog(c, "Activity");
				jop.setBounds(0, 0, 300, 200);
				db.setVisible(true);
				String value = (String) jop.getValue();
				
				return value;
			}
	}
