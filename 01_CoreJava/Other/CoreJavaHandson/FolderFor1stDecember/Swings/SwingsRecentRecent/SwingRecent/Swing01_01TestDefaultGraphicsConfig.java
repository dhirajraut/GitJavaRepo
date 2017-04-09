import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Swing01_01TestDefaultGraphicsConfig
	{	public static void main(String [] arg)
			{	new MyDefaultFrame();	}
	}

class MyDefaultFrame extends JFrame
	{	JButton but1, but2, but3;
		
		public MyDefaultFrame()
			{	
				but1 = new JButton("1");
				but2 = new JButton("2");
				but3 = new JButton("3");
				
				but1.setBounds(10, 10, 150, 50);
				but2.setBounds(180, 10, 150, 50);
				but3.setBounds(350, 10, 150, 50);
				
				Container c = this.getContentPane();
				c.setLayout(null);
				c.add(but1);
				c.add(but2);
				c.add(but3);
				
				this.setSize(200, 100);
				this.setVisible(true);
				this.validate();
			}
	}
