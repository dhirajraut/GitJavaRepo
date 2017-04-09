import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class TestGridBagDefault
	{	public static void main(String[] args)
			{	JFrame fr1 = new JFrame();//"Example1, tyrue, true");
				fr1.setBounds(10, 10, 270, 100);
				Container cn=fr1.getContentPane();
				
				cn.setLayout(new GridBagLayout());
				cn.add(new JButton("Wonderful."));
				cn.add(new JButton("World"));
				cn.add(new JButton("of"));
				cn.add(new JButton("Swing."));
				fr1.setVisible(true);
			}
	}