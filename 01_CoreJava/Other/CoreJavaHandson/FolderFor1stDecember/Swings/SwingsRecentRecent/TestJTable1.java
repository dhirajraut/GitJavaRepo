/** This program is demonstrating TableColumnModel interface and TableColumn class */
//import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.table.*;

class MyTable1
	{	JScrollPane jsp;
		JTable jtDepttTable;
		MyTable1()
			{	String [] DepttTableheader={"Column1", "Column2"};
				Object [][]DepttTablecells = { {"Administration","Ashutosh"},
										 	   {"Account Section","Sahani"},
										 	   {"Reception","Laxman"},
										 	   {"TechSupport","Sam"},
										 	   {"Marketting", "Rozi"}
											 };
				
				jtDepttTable=new JTable(DepttTablecells, DepttTableheader);
				
			 	jtDepttTable.setSize(250,175);
			 	jtDepttTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			 	jtDepttTable.setToolTipText("Department Table View");
			 	jtDepttTable.setName("Table");
			 	jtDepttTable.setRowSelectionAllowed(true);
			 	
			 	TableColumnModel tcm = jtDepttTable.getColumnModel();
			 	TableColumn col1, col2;
			 	col1 = tcm.getColumn(0);
			 	col2 = tcm.getColumn(1);
			 	
			 	col1.setPreferredWidth(50);
			 	col2.setPreferredWidth(100);
			 	int n = tcm.getColumnCount();
			 	JOptionPane jop = new JOptionPane();
			 	jop.showMessageDialog(null, n+" ");
			 	int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			 	int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			 	jsp=new JScrollPane(jtDepttTable,v,h );
			}
	}

public class TestJTable1
	{	public static void main(String[] args)
			{	JFrame jf = new JFrame("Test Table Options.");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf.setSize(300, 200);
				
				// Create Table Object
			 	(jf.getContentPane()).add((new MyTable1()).jsp);
			 	
				jf.setVisible(true);
			}
	}