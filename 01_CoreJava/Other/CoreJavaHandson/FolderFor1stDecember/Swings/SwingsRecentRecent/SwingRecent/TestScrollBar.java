import java.awt.BorderLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class TestScrollBar extends JPanel implements AdjustmentListener
	{	JLabel label;
		public TestScrollBar()
			{ 	super(true);
				label = new JLabel();
				setLayout(new BorderLayout());
				JScrollBar hBar = new JScrollBar(JScrollBar.HORIZONTAL, 30, 20, 0, 300);
				JScrollBar vBar = new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 300);
				hBar.setUnitIncrement(2);
				hBar.setBlockIncrement(1);
				
				hBar.addAdjustmentListener(this);
				vBar.addAdjustmentListener(this);
				
				this.add(vBar, BorderLayout.EAST);
				this.add(hBar, BorderLayout.SOUTH);
				this.add(label, BorderLayout.CENTER);
			}
		public void adjustmentValueChanged(AdjustmentEvent arg0)
			{	label.setText(" New value is "+arg0.getValue());
				this.repaint();
			}	
		public static void main(String[] args)
			{	JFrame fr = new JFrame("Scrollbar Example");
				fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fr.setContentPane(new TestScrollBar());
				fr.setSize(200, 200);
				fr.setVisible(true);
			}
	}

