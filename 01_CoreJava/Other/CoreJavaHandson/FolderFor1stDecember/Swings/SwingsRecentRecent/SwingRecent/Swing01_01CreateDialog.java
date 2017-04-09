import java.awt.Container;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Swing01_01CreateDialog
	{	public static void main (String [] args)
			{	JOptionPane.showMessageDialog(null,"This is message dialog box");
	
				System.out.println(getConfirmation());
				System.out.println(getInput());
				
				System.out.println(getOption());
			}
	
		public static String getConfirmation()
			{	int n = JOptionPane.showConfirmDialog(null,"Alert! Stock level going below Red level", "Issue Item", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
				if (n == JOptionPane.YES_OPTION)
					return "Yes";
				else
					if (n == JOptionPane.NO_OPTION)
						return "No";
					else
						return "Cancel";
			}
		
		public static String getInput()
			{	String select = JOptionPane.showInputDialog(null, "Enter Account Number");
				if (select==null)
					return "NULL";
				else
					return select;
			}
		
		public static String getOption()
			{	String [] selections = { "Ignore", "Add to log", "Change" };
				
				String pathToIcon = "E:\\D. Chandra\\Java\\D.Chandra New\\ClassroomExercises\\src\\Swings\\SwingRecent\\";
				Icon icon=new ImageIcon(pathToIcon+"dukeWaveRed.gif");
				int options = JOptionPane.showOptionDialog(null, "Select Activity", "Activity", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,icon,selections, selections[2] );
				if (options==-1)
					return "Close";
				return  selections[options];
			}
	}

