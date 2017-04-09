
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class ButtonsFrame03 extends JFrame
	{	JButton  labelButton, imageButton, labelImageButton ;
		JLabel simpleLabel, imageLabel;
		Container c;	                                                                                                            
	
		public ButtonsFrame03(String title)
			{	super(title);
				c = getContentPane();
				//Icon name and path;
				String pathToIcon = "C:\\D. Chandra\\Java\\D.Chandra New\\ClassroomExercises\\src\\Swings\\";
				Icon icon=new ImageIcon(pathToIcon+"dukeWaveRed.gif");
				
				Icon icon1 = new ImageIcon("classes/dukeWaveRed.gif");
				// Creating buttons
				labelButton=new JButton("Label Button");
				imageButton=new JButton("Button testing Icon", icon);
				//imageButton = new JButton();
				//Icon icon2 = imageButton.cre
				labelImageButton=new JButton("Label with image" ,icon);
				JPanel forButtons = new JPanel();
				forButtons.add(labelButton);
				forButtons.add(imageButton);
				forButtons.add(labelImageButton);
				
				// Creating labels
				simpleLabel = new JLabel("Simple Label");simpleLabel.setBorder(new EtchedBorder());
				imageLabel = new JLabel("Icon Label", icon, JLabel.CENTER);imageLabel.setIconTextGap(0);
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
			ButtonsFrame03 obj=new ButtonsFrame03("Buttons Frame");
			obj.setSize(450,200);
			obj.setVisible(true);                                                                                         
		  }                                                                                                                            
		}
