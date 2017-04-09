import java.awt.event.WindowStateListener;

import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.Toolkit;

public class TestJFrame extends JFrame implements WindowStateListener
	{	TestJFrame(String str)
			{	super.setTitle(str);
				setSize(200,200);
				setLocation(100,100);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				addWindowStateListener(this);
			}

		public static void main(String[] args)
			{	TestJFrame jf = new TestJFrame("DeIconified");
				jf.getRootPane().setLayout(new FlowLayout());
				jf.setLayout(new FlowLayout());
				jf.setVisible(true);
			}

		public void windowStateChanged(WindowEvent we)
			{	
				if (getState()==JFrame.ICONIFIED)
					{	setTitle("Iconified.");
						Toolkit.getDefaultToolkit().beep();
					}
				else
					{	setTitle("DeIconified.");}
			}
	}
