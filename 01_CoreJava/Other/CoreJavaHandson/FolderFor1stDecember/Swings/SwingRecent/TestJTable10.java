/** This program is demonstrating TableColumnModel interface and TableColumn class */
//import java.awt.BorderLayout;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;

class MyTable0  implements KeyListener, MouseListener
	{	JOptionPane jop = new JOptionPane();
		JScrollPane jsp;
		JTable jtDepttTable;
		MyTable0()
			{	String [] DepttTableheader={"Column1", "Column2"};
				Object [][]DepttTablecells = { {"Administration","Som"},
										 	   {"Account Section","Satyen"},
										 	   {"Reception","Sayali"},
										 	   {"TechSupport","Shyamal"},
										 	   {"Marketting", "Sunaina"}
											 };
				
				//Object [][] DepttTablecells=new Object[depttlist.length][2];
				//for(int i=0; i<depttlist.length; i++)
					//DepttTablecells[i][0]=(Object)depttlist[i];
				
				jtDepttTable=new JTable(DepttTablecells, DepttTableheader);
				
			 	jtDepttTable.setSize(250,175);
			 	jtDepttTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			 	jtDepttTable.setToolTipText("Department Table View");
			 	jtDepttTable.setName("Table");
			 	
			 	jtDepttTable.addKeyListener(this);
			 	jtDepttTable.addMouseListener(this);
			 	
			 	int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			 	int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			 	jsp=new JScrollPane(jtDepttTable,v,h );
			 	//jsp.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			}
		
		public void mouseClicked(MouseEvent me)
			{	if (me.getComponent().getName().equalsIgnoreCase("Table"))
					{	int row=jtDepttTable.getSelectedRow();
			 			int col=jtDepttTable.getSelectedColumn();
			 			int i=jop.showConfirmDialog(null, row + " "+col, "Option Pane",JOptionPane.OK_OPTION);
					}
			}
		public void mousePressed(MouseEvent me)
			{}
		public void mouseReleased(MouseEvent me)
			{}
		public void mouseExited(MouseEvent me)
			{}
		public void mouseEntered(MouseEvent me)
			{}
	 	public void keyPressed(KeyEvent ke)
	 		{	if (ke.getKeyText(ke.getKeyCode()).compareToIgnoreCase("Enter")==0)
	 				{	int row=jtDepttTable.getSelectedRow();
	 					int col=jtDepttTable.getSelectedColumn();
	 					int i=jop.showConfirmDialog(null, row + " "+col, "Option Pane",JOptionPane.OK_OPTION);
	 					//TestJTable.dpm.oldDepttName=(String)jtDepttTable.getValueAt(row, col);
	 				}
	 		}
	 	public void keyTyped(KeyEvent ke)
	 		{}
	 	public void keyReleased(KeyEvent ke)
	 		{}
	}

public class TestJTable10
	{	public static void main(String[] args)
			{	JFrame jf = new JFrame("Test Table Options.");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf.setSize(300, 200);
				
				// Create Table Object
			 	(jf.getContentPane()).add((new MyTable0()).jsp);
			 	
				jf.setVisible(true);
			}
	}