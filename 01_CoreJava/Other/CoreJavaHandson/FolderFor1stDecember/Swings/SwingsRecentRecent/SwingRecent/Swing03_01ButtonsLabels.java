
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
public class Swing03_01ButtonsLabels extends JFrame
	{	JButton  labelButton, imageButton, labelImageButton ;
		JLabel simpleLabel, imageLabel;
		Container c;	                                                                                                            
	
		public Swing03_01ButtonsLabels(String title)
			{	super(title);
				c = getContentPane();
				
				Icon icon1 = new ImageIcon("classes/dukeWaveRed.gif");
				
				// Creating buttons
				labelButton=new JButton("Label Button");
				imageButton=new JButton( icon1);
				labelImageButton=new JButton("Label with image" ,icon1);
				
				JPanel forButtons = new JPanel();
				forButtons.add(labelButton);
				forButtons.add(imageButton);
				forButtons.add(labelImageButton);
				
				// Creating labels
				simpleLabel = new JLabel("Simple Label");
				//simpleLabel.setBorder(new EtchedBorder());
				imageLabel = new JLabel("Icon Label", icon1, JLabel.CENTER);imageLabel.setIconTextGap(0);
				//imageLabel.setBorder(new EtchedBorder());
				JPanel forLabels = new JPanel();
				forLabels.add(simpleLabel);
				forLabels.add(imageLabel);
				
				// Create and add all things to Panel
				JPanel panel=new JPanel();
				panel.add(forButtons);
				panel.add(forLabels);
				
		        c.add(panel);
		    }

		public static void main (String[] args) {
			Swing03_01ButtonsLabels obj=new Swing03_01ButtonsLabels("Buttons Frame");
			obj.setSize(450,200);
			obj.setVisible(true);                                                                                         
		  }                                                                                                                            
		}
