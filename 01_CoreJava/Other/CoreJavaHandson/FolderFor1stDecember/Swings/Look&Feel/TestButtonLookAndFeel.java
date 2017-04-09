
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class TestButtonLookAndFeel
	{	public static void main(String[] argv)
		{	JFrame jf = new JFrame();
			    
			
			JPanel jp = new JPanel();
			JButton jb = new JButton("Click Me");
			jp.add(jb);
			jf.getContentPane().add(jp);
			jf.setSize(100, 100);
			jf.setVisible(true);
		}
	}	
