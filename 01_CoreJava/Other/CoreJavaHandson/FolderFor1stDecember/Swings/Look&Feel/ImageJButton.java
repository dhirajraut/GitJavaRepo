// This program is not taking custom look and feel.  So, not working properly.
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

class CustomButtonImageUI extends BasicButtonUI
	{

    public static final float[] BLUR3x3 = {
        0.1f, 0.1f, 0.1f,
        0.1f, 0.2f, 0.1f,
        0.1f, 0.1f, 0.1f };
            
    public static ComponentUI createUI(JComponent c)
    	{	return new CustomButtonImageUI();	}
   
    
    public void paint(Graphics g, JComponent comp)
    	{	Graphics2D panelG2 = (Graphics2D)g;

	        BufferedImage image = new BufferedImage (	comp.getWidth(), comp.getHeight(), BufferedImage.TYPE_INT_ARGB);

	        Graphics2D g2 = image.createGraphics();
	        g2.setColor(g.getColor());
	        super.paint(g2, comp);

        
	        if (!comp.isEnabled())
	        	{	Kernel kernel = new Kernel(3, 3, BLUR3x3);
	            	ConvolveOp cop = new ConvolveOp(kernel,
	                                            ConvolveOp.EDGE_ZERO_FILL,
	                                            null);
	            
	               Image newImage = cop.filter(image, null);
	               panelG2.drawImage(newImage, 0, 0, null);
	        	}
	        else
		        {
		            panelG2.drawImage(image, 0, 0, null);
		        }
    }
}

public class ImageJButton
	{	
		public static void main(String[] args)
			{	UIManager.put("ButtonUI", "CustomButtonImageUI");
	
		        JFrame frame = new JFrame("Button test");
		        frame.getContentPane().setBackground(Color.black);
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        
		        JButton button = new JButton("Test Enabled");
		        frame.getContentPane().add(button, BorderLayout.NORTH);
	
		        JButton button2 = new JButton("Test Disabled");
		        button2.setEnabled(false);
		        frame.getContentPane().add(button2, BorderLayout.SOUTH);
		        
		        frame.pack();
		        frame.setSize(200, 100);
		        frame.setVisible(true);
			}
	}
	
