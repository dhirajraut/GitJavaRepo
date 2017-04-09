/** This program is demonstrating TableModel interface and AbstractTableModel class */
/** Your model might hold its data in an array, vector, or hash map, or it might get the
 *  data from an outside source such as a database. It might even generate the data at
 *  execution time. */
import javax.swing.*;
//import javax.swing.table.TableColumnModel;
import javax.swing.table.AbstractTableModel;
//import javax.swing.table.TableColumn;
//import javax.swing.table.TableModel;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.FlowLayout;

class MyTableMode4 extends AbstractTableModel
	{	private String [] columnNames;
		//private Object [] tableContents;
		private int rows;
		//private int cols;

		MyTableMode4(String [] columnNames, int rows)
			{	this.columnNames = columnNames;
				this.rows = rows;
			}

		public int getColumnCount()
			{ return	columnNames.length;	}

		public int getRowCount()
			{	return rows; 	}

		public Object getValueAt(int x, int y)
			{	return new Integer(x*y);}
		
		public void setValueAt(Object x)
			{}

		public String getColumnName(int n)
			{	return(columnNames[n]);	}

		public void setColumnName(String nm, int n)
			{	columnNames[n] = new String(nm);	}

		public Class getColumnClass(int n)
			{	return getValueAt(0, n).getClass();	}
		
		public void increRow()
			{	rows++; this.fireTableRowsInserted(rows-1, rows);
			}
	}

class MyTable4
	{	JScrollPane jsp;
		JTable jtDepttTable;

		MyTable4()
			{	String [] columnNames = { "A", "B", "C", "D" };
				
				//int column = 3;
				int row = 4;
				MyTableMode4 mytmod = new MyTableMode4(columnNames, row);
				jtDepttTable = new JTable(mytmod);

			 	jtDepttTable.setSize(150,175);
			 	jtDepttTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			 	jtDepttTable.setToolTipText("Department Table View");
			 	jtDepttTable.setName("Table");
			 	jtDepttTable.setRowSelectionAllowed(true);

			 	int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			 	int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			 	jsp=new JScrollPane(jtDepttTable,v,h );
			 	jsp.setSize(300, 200);
			}
	}

class JTable4
	{	JFrame jf;
		MyTable4 mt4;
		JButton but;
		JTable4()
			{	jf = new JFrame("Test Table Options.");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf.setSize(300, 200);
				jf.getContentPane().setLayout(new FlowLayout());
				mt4 = new MyTable4();
				
				// Create Table Object
			 	(jf.getContentPane()).add(mt4.jsp);
			 	jf.getContentPane().add(new JButton());
				jf.setVisible(true);
			}
	}

public class TestJTable4
	{	
		public static void main(String[] args)
			{	JTable4 jt4 = new JTable4();	}
	}
