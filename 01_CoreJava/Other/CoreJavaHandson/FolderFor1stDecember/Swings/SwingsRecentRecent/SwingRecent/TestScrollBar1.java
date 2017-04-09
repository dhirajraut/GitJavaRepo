import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

public class TestScrollBar1 extends JPanel  implements AdjustmentListener
	{	JScrollBar redScroll;
		JScrollBar blueScroll;
		JScrollBar greenScroll;
		JTextArea redColor, greenColor, blueColor, allColor;
		int red, blue, green;
		
		public TestScrollBar1()
			{	redScroll = new JScrollBar(JScrollBar.HORIZONTAL, 30, 20, 0, 255);
				blueScroll = new JScrollBar(JScrollBar.HORIZONTAL, 30, 20, 0, 255);
				greenScroll = new JScrollBar(JScrollBar.HORIZONTAL, 30, 20, 0, 255);
				
				redScroll.addAdjustmentListener(this);
				blueScroll.addAdjustmentListener(this);
				greenScroll.addAdjustmentListener(this);
				
				redScroll.setUnitIncrement(2); redScroll.setBlockIncrement(1);
				blueScroll.setUnitIncrement(2); blueScroll.setBlockIncrement(1);
				greenScroll.setUnitIncrement(2); greenScroll.setBlockIncrement(1);
				
				JLabel redLabel = new JLabel("Red");
				JLabel blueLabel = new JLabel("Blue");
				JLabel greenLabel = new JLabel("Green");
				JPanel scrollPanel = new JPanel(new GridLayout(3, 2));
				scrollPanel.add(redLabel); scrollPanel.add(redScroll);
				scrollPanel.add(blueLabel);scrollPanel.add(blueScroll);
				scrollPanel.add(greenLabel);scrollPanel.add(greenScroll);
				
				Color col = new Color(0, 0, 0);
				redColor = new JTextArea(30, 3); redColor.setBackground(col);
				blueColor = new JTextArea(30, 3);blueColor.setBackground(col);
				greenColor = new JTextArea(30, 3);greenColor.setBackground(col);
				
				JPanel colorPanel = new JPanel();
				colorPanel.add(redColor);
				colorPanel.add(blueColor);
				colorPanel.add(greenColor);
				
				allColor = new JTextArea(30, 3); allColor.setBackground(col);
				allColor.setBorder(new EtchedBorder());
				JPanel allColorPanel = new JPanel();
				allColorPanel.add(allColor);
				
				this.setLayout(new BorderLayout());
				this.add(scrollPanel, BorderLayout.SOUTH);
				this.add(colorPanel, BorderLayout.CENTER);
				this.add(allColorPanel, BorderLayout.EAST);
				
				this.setSize(300, 200);
				this.setVisible(true);
			}
		
		public void adjustmentValueChanged(AdjustmentEvent arg0)
			{	if (arg0.getSource()== redScroll)
					{	red = redScroll.getValue();
						redColor.setBackground(new Color(red, 0, 0));
					}
				else if (arg0.getSource()== blueScroll)
					{	blue = blueScroll.getValue();
						blueColor.setBackground(new Color(0, 0 ,blue));
					}
				else
					{	green = greenScroll.getValue();
						greenColor.setBackground(new Color(0, green, 0));
					}
				allColor.setBackground(new Color(red, green, blue));
				redColor.validate();
				blueColor.validate();
				greenColor.validate();
				allColor.validate();
			}
		
		public static void main(String[] args)
			{	JFrame fr = new JFrame();
				fr.getContentPane().add(new TestScrollBar1());
				fr.setSize(300, 200);
				fr.setVisible(true);
			}

	}
