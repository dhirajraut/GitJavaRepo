import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class ResourceMode
	{	public static void main(String [] argv)
			{	Border border = BorderFactory.createRaisedBevelBorder();
				Border tripleBorder = new CompoundBorder(new CompoundBorder(border, border), border);
				
				UIManager.put("Button.border", tripleBorder);
				
				/*String pathToIcons = "C:\\D. Chandra\\Java\\D.Chandra New\\ClassroomExercises\\classes\\Icons";
				ImageIcon closeIcon = new ImageIcon(pathToIcons+"cross.gif");
				ImageIcon iconizeIcon = new ImageIcon(pathToIcons+"web.gif");
				ImageIcon warningIcon = new ImageIcon(pathToIcons+"warning.gif");
				
				// Custom icons for internal frame
				UIManager.put("InternalFrameTitlePane.closeIcon", closeIcon);
				
				UIManager.put("InternalFrameTitlePane.iconizeIcon", iconizeIcon);
				
				UIManager.put("InternalFrameTitlePane.maximizeIcon", warningIcon);
				
				UIManager.put("InternalFrameTitlePane.altMaximizeIcon", new ImageIcon(pathToIcons+"javalogo52x88.gif"));
				
				UIManager.put("InternalFrameTitlePane.font", new Font("Serif", Font.ITALIC, 12));
				
				UIManager.put("ScrollBar.width", new Integer(30));*/
				
				JFrame f = new JFrame();
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				Container c = f.getContentPane();
					
				JDesktopPane desk = new JDesktopPane();
				
				c.add(desk, BorderLayout.CENTER);
				
				JButton cut = new JButton("Cut");
				JButton copy = new JButton("Copy");
				JButton paste = new JButton("Paste");
				
				JPanel p = new JPanel(new FlowLayout());
				
				p.add(cut);
				p.add(copy);
				p.add(paste);
				
				c.add(p, BorderLayout.SOUTH);
				
				JInternalFrame inf = new JInternalFrame("MyFrame", true, true, true, true);
				JLabel l = new JLabel(new ImageIcon("Water lilies.jpg"));
				JScrollPane scroll = new JScrollPane(l);
				inf.setContentPane(scroll);
				
				inf.setBounds(10, 10, 200, 200);
				desk.add(inf);
				
				f.setSize(250, 350);
				f.setVisible(true);
				
			}

	}
