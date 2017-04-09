//package LayoutManager;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.beans.PropertyVetoException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class TestGridBagLayout08 extends JFrame
	{	public TestGridBagLayout08()
			{	super("GridBag Layout Managers.");
				setSize(650, 500);
				JDesktopPane desktop = new JDesktopPane();
				desktop.setSize(500, 300);
				
				Container c;
				GridBagConstraints cn = new GridBagConstraints();
				
				//	Simple GridBag Layout
				JInternalFrame frGB1 = new JInternalFrame("Simple", true, true);
				frGB1.setBounds(10, 10, 180, 150);
				c=frGB1.getContentPane();
				c.setLayout(new GridBagLayout());		// As no Constraints are specified, default one are used.
				c.setBackground(Color.orange);
				
				c.add(new JButton("1"));
				c.add(new JButton("2"));
				c.add(new JButton("3"));
				c.add(new JButton("4"));
				frGB1.setVisible(true);
				
				// GridBagLayout with Simple Constrains
				JInternalFrame frGB2 = new JInternalFrame("With Simple Constrains", true, true);
				frGB2.setBounds(200, 10, 180, 150);
				c=frGB2.getContentPane();
				c.setLayout(new GridBagLayout());
				c.setBackground(Color.blue);
				
				cn.gridx=0; cn.gridy=0;	c.add(new JButton("1"), cn);
				cn.gridx=1; cn.gridy=1; c.add(new JButton("2"), cn);
				cn.gridx=2; cn.gridy=2; c.add(new JButton("3"), cn);
				cn.gridx=3; cn.gridy=3; c.add(new JButton("4"), cn);
				frGB2.setVisible(true);
				
				// GridBag Layout with Padding
				JInternalFrame frGB3 = new JInternalFrame("With Padding", true, true);
				frGB3.setBounds(390, 10, 220, 150);
				c = frGB3.getContentPane();
				c.setLayout(new GridBagLayout());
				c.setBackground(Color.cyan);
				
				cn.gridx=0; cn.gridy=0; cn.ipadx=0;  cn.ipady=0;  c.add(new JButton("1"), cn);
				cn.gridx=1; cn.gridy=0; cn.ipadx=15; cn.ipady=0;  c.add(new JButton("2"), cn);
				cn.gridx=2; cn.gridy=0; cn.ipadx=0;  cn.ipady=15; c.add(new JButton("3"), cn);
				cn.gridx=3; cn.gridy=0; cn.ipadx=30; cn.ipady=15; c.add(new JButton("4"), cn);
				frGB3.setVisible(true);
				
				// GridBag Layout with weights
				JInternalFrame frGB4 = new JInternalFrame("With weights", true, true);
				frGB4.setBounds(10, 170, 190, 120);
				c=frGB4.getContentPane();
				c.setLayout(new GridBagLayout());
				c.setBackground(Color.yellow);
				
				// Common Parameters
				cn.ipadx=0; cn.ipady=0;
				// Varying Parameters
				//cn.gridx=0; cn.gridy=0; cn.weightx=1.0; cn.weighty=0.0; c.add(new JButton("1"), cn);
				cn.gridx=0; cn.gridy=0; cn.weightx=0; cn.weighty=0.0; c.add(new JButton("1"), cn);
				cn.gridx=1; cn.gridy=0; cn.weightx=0; cn.weighty=0.0; c.add(new JButton("2"), cn);
				cn.gridx=2; cn.gridy=0; cn.weightx=0.5; cn.weighty=0.0; c.add(new JButton("3"), cn);
				cn.gridx=3; cn.gridy=0; cn.weightx=1.0; cn.weighty=0.0; c.add(new JButton("4"), cn);
				
				frGB4.setVisible(true);
				
				//	GridBag Layout with Fill
				JInternalFrame frGB5 = new JInternalFrame("With Grid Fill", true, true);
				frGB5.setBounds(210, 170, 230, 120);
				c=frGB5.getContentPane();
				c.setLayout(new GridBagLayout());
				c.setBackground(Color.pink);
				
				// Common Parameters
				cn.ipadx=0; cn.ipady=0;
				cn.weightx=1.0; cn.weighty=1.0;
				cn.gridx=GridBagConstraints.RELATIVE;
				//cn.gridy=GridBagConstraints.VERTICAL;
				// Varying Parameters
				cn.fill=GridBagConstraints.NONE;       c.add(new JButton("1"), cn);
				cn.fill=GridBagConstraints.HORIZONTAL; c.add(new JButton("2"), cn);
				cn.fill=GridBagConstraints.VERTICAL;   c.add(new JButton("3"), cn);
				cn.fill=GridBagConstraints.BOTH;       c.add(new JButton("4"), cn);
				
				frGB5.setVisible(true);
				
				// GridBag Layout with Width and Height
				JInternalFrame frGB6 = new JInternalFrame("With Grid Width and Height", true, true, true, true);
				frGB6.setBounds(10, 300, 150, 150);
				c = frGB6.getContentPane();
				c.setLayout(new GridBagLayout());
				c.setBackground(Color.red);
				
				// Common Parameters
				cn.ipadx=0; cn.ipady=0;
				cn.weightx=1.0; cn.weighty=1.0; 
				// Varying Parameters
				cn.gridx=0; cn.gridy=0; cn.gridheight = 2; cn.gridwidth = 1; cn.fill=GridBagConstraints.BOTH;
				c.add(new JButton("1"), cn);
				cn.gridx=2; cn.gridy=0; cn.gridheight = 1; cn.gridwidth = 2; cn.fill=GridBagConstraints.HORIZONTAL;
				c.add(new JButton("2"), cn);
				cn.gridx=2; cn.gridy=1; cn.gridheight = 1; cn.gridwidth = 1; cn.fill=GridBagConstraints.NONE;
				c.add(new JButton("3"), cn);
				cn.gridx=3; cn.gridy=1; cn.gridheight = 2; cn.gridwidth = 2; cn.fill=GridBagConstraints.BOTH;
				c.add(new JButton("4"), cn);
				
				frGB6.setVisible(true);

				desktop.add(frGB1,0);
				desktop.add(frGB2,0);
				desktop.add(frGB3,0);
				desktop.add(frGB4,0);
				desktop.add(frGB5,0);
				desktop.add(frGB6,0);
				
				getContentPane().add(desktop);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				setVisible(true);
			}

		public static void main(String[] args)
			{	new TestGridBagLayout08();	}
	}