/*
 * Created on Feb 1, 2006
 *
 * It is for comparing different Layouts
 */
//package LayoutManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class CommonLayout07 extends JFrame
	{	public CommonLayout07()
			{	super("Common Layout Managers.");
				setSize(500, 500);
				JDesktopPane desktop = new JDesktopPane(); 
				desktop.setSize(500, 300);
				//desktop.m
				
				Container c;
				//component are added to pane and not Frame.				
				// FlowLayout
				JInternalFrame frFlow = new JInternalFrame("FlowLayout", true, true, true, true);
				
				frFlow.setBounds(10, 10, 150, 150);
				c = frFlow.getContentPane();
				c.setLayout(new FlowLayout());
				c.setBackground(Color.red);
				
				c.add(new JButton("1"));
				c.add(new JButton("2"));
				c.add(new JButton("3"));
				c.add(new JButton("4"));
				frFlow.setVisible(true);
				
				// GridLayout
				JInternalFrame frGrid = new JInternalFrame("GridLayout", true, true, true, true);
				frGrid.setBounds(170, 10, 150, 150);
				c=frGrid.getContentPane();
				c.setLayout(new GridLayout(2, 2));
				c.setBackground(Color.blue);
				
				c.add(new JButton("1"));
				c.add(new JButton("2"));
				c.add(new JButton("3"));
				c.add(new JButton("4"));
				frGrid.setVisible(true);
				
				// Border Layout
				JInternalFrame frBorder = new JInternalFrame("BorderLayout", true, true);
				frBorder.setBounds(330, 10, 150, 150);
				c = frBorder.getContentPane();
				c.setLayout(new BorderLayout());
				c.setBackground(Color.cyan);
				
				c.add(new JButton("1"), BorderLayout.NORTH);
				c.add(new JButton("2"), BorderLayout.EAST);
				c.add(new JButton("3"), BorderLayout.SOUTH);
				c.add(new JButton("4"), BorderLayout.WEST);
				c.add(new JButton("5"), BorderLayout.CENTER);
				frBorder.setVisible(true);
				
				// Box Layout Horizontal
				JInternalFrame frBoxX = new JInternalFrame("BoxLayout.X", true, true);
				frBoxX.setBounds(10, 170, 190, 120);
				c=frBoxX.getContentPane();
				c.setLayout(new BoxLayout(c,BoxLayout.X_AXIS));
				c.setBackground(Color.yellow);
				
				c.add(new JButton("1"));
				c.add(Box.createHorizontalStrut(12)); // size of Strut does not change
				c.add(new JButton("2"));
				c.add(Box.createGlue()); // Glue will change its size but does not change the components size
				c.add(new JButton("3"));
				c.add(Box.createHorizontalGlue());
				c.add(new JButton("4"));
				frBoxX.setVisible(true);
				
				//	Box Layout Vertical
				JInternalFrame frBoxY = new JInternalFrame("BoxLayout.Y", true, true);
				frBoxY.setBounds(330, 170, 150, 150);
				c=frBoxY.getContentPane();
				c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));
				c.setBackground(Color.green);
				
				c.add(new JButton("1"));
				c.add(Box.createVerticalStrut(12));
				c.add(new JButton("2"));
				c.add(Box.createGlue());
				c.add(new JButton("3"));
				c.add(Box.createVerticalGlue());
				c.add(new JButton("4"));
				frBoxY.setVisible(true);
				
				// GridBag Layout
				JInternalFrame frGB = new JInternalFrame("GridBagLayout", true, true);
				frGB.setBounds(10, 310, 180, 130);
				c=frGB.getContentPane();
				c.setLayout(new GridBagLayout());
				c.setBackground(Color.orange);
				
				c.add(new JButton("1"));
				c.add(new JButton("2"));
				c.add(new JButton("3"));
				c.add(new JButton("4"));
				frGB.setVisible(true);
				
				desktop.add(frFlow,0);
				desktop.add(frGrid,1);
				desktop.add(frBorder,2);
				desktop.add(frBoxX,3);
				desktop.add(frBoxY,4);
				desktop.add(frGB, 5);
				
				getContentPane().add(desktop);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				setVisible(true);
			}

		public static void main(String[] args)
			{	new CommonLayout07();	}
	}
