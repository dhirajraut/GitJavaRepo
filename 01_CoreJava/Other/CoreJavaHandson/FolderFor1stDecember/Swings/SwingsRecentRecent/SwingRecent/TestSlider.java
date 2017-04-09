import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TestSlider extends JPanel implements ChangeListener
	{	JSlider slide;
		public TestSlider()
			{	super(true);
				this.setLayout(new BorderLayout());
				slide = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
				slide.putClientProperty("JSlider.isFilled", Boolean.TRUE);
				slide.addChangeListener(this);
				
				slide.setMinorTickSpacing(2);
				slide.setMajorTickSpacing(10);
				slide.setPaintTicks(true);
				slide.setPaintLabels(true);
				slide.setLabelTable(slide.createStandardLabels(10));
				add(slide, BorderLayout.CENTER);
			}
	
		public void stateChanged(ChangeEvent e)
			{	System.out.println(slide.getValue());
				
			}
	
		public static void main(String [] arg)
			{	JFrame fr = new JFrame("Slider example");
				fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fr.setContentPane(new TestSlider());
				fr.pack();
				fr.setVisible(true);
			}
	}
