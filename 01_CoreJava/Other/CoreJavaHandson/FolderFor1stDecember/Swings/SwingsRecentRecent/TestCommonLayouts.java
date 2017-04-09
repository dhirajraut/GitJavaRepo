import java.awt.Container;
import java.awt.FlowLayout;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class TestCommonLayouts
	{		public static void main(String[] args) throws PropertyVetoException
				{	CommonLayouts cl = new CommonLayouts();
					//JFrame jf = new JFrame("Common Layouts.");
					//jf.setSize(400,400);
					JDesktopPane jd = new JDesktopPane();
					
					//jf.getContentPane().add(jd);
					
					Container c = jd.getRootPane();//.getRootPane());//.getContentPane();
					c.setLayout(new FlowLayout());
					c.add(cl.createWithFlowLayout());
					jd.setVisible(true);
					//jf.setVisible(true);
				}
	}
class CommonLayouts
	{	public JInternalFrame  createWithFlowLayout() throws PropertyVetoException
			{	JButton but1=new JButton("Button-1");
				JButton but2=new JButton("Button-2");;
				JButton but3=new JButton("Button-3");;
				JButton but4=new JButton("Button-4");;
				JInternalFrame jif = new JInternalFrame("FlowLayout", true, true);
				jif.setBounds(10,10, 150, 150);
				JComponent c = (JComponent) jif.getContentPane();

				//Container c = jif.getContentPane();
				c.setLayout(new FlowLayout());
				c.add(but1);
				c.add(but2);
				c.add(but3);
				c.add(but4);
				jif.setSelected(true);
				jif.setVisible(true);
				return(jif);
			}
	}