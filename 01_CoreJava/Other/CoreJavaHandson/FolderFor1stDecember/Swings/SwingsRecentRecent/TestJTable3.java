/** This program is demonstrating TableModel interface and AbstractTableModel class */
/** Your model might hold its data in an array, vector, or hash map, or it might get the
 *  data from an outside source such as a database. It might even generate the data at
 *  execution time. */
import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

class MyTableMode3 extends AbstractTableModel
	{	private String [] columnNames;
		private Object [] tableContents;
		private int rows;
		private int cols;

		MyTableMode3(String[] columnNames, Object[] tableContents, int rows, int cols)
			{	this.columnNames = columnNames;
				this.tableContents = tableContents;
				this.rows = rows;
				this.cols = cols;
			}

		public int getColumnCount()
			{ return	cols;	}

		public int getRowCount()
			{	return rows; 	}

		public Object getValueAt(int x, int y)
			{	if ((x>=rows)||(y>=cols))
					return null;
				return tableContents[x*cols+y];
			}

		public void setValueAt(Object val,int x, int y )
			{	if (isCellEditable(x, y))
					{	tableContents[x*cols+y] = val;
						fireTableCellUpdated(x, y);
					}
			}

		public String getColumnName(int n)
			{	return(columnNames[n]);	}

		public void setColumnName(String nm, int n)
			{	columnNames[n] = new String(nm);	}

		public Class getColumnClass(int n)
			{	return getValueAt(0, n).getClass();	}

		// To make first two columns uneditable.
		public boolean isCellEditable(int x, int y)
			{	if ((x<=rows)&&(y<=cols))
					return true;
				else
					return false;
			}
		public void addNewRow()
			{	rows++;	}
	}

class MyTable3
	{	JOptionPane jop = new JOptionPane();
		JScrollPane jsp;
		JTable jtDepttTable;

		MyTable3()
			{	String [] columnNames = { "A", "B", "C", "D" };
				Object [] tableContents = {" 1", " 2", " 3"," 4", " 5", " 6"," 7", " 8", " 9","10", "11", "12","13", "14", "15", "16", "17", "18",};
				int [] columnSizes= {10, 110, 70, 30};
				int column = 3;
				int row = 4;
				MyTableMode3 mytmod = new MyTableMode3(columnNames, tableContents,  row, column);
				jtDepttTable = new JTable(mytmod);

			 	jtDepttTable.setSize(250,175);
			 	jtDepttTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			 	jtDepttTable.setToolTipText("Department Table View");
			 	jtDepttTable.setName("Table");
			 	jtDepttTable.setRowSelectionAllowed(true);

			 	//	Setting column size
			 	TableColumnModel com = jtDepttTable.getColumnModel();
			 	TableColumn [] col = new TableColumn[jtDepttTable.getColumnCount()];
			 	for(int i=0; i< col.length; i++)
			 		{	col[i]= com.getColumn(i);
			 			col[i].setPreferredWidth(columnSizes[i]);
			 		}

			 	// Getting Table model information
			 	System.out.println("Column count :"+jtDepttTable.getColumnCount());
			 	System.out.println("Row count :"+jtDepttTable.getRowCount());
			 	System.out.println("Get column names :");
			 	for(int i =0; i<jtDepttTable.getColumnCount(); i++)	
			 		System.out.print(jtDepttTable.getColumnName(i)+" ");
			 	jtDepttTable.setValueAt("Liza", 4, 2);
			 	jtDepttTable.setValueAt("6", 4, 0);
			 	

			 	int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			 	int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			 	jsp=new JScrollPane(jtDepttTable,v,h );
			}
	}

class JTable3 implements ActionListener
	{	JFrame jf;
		MyTable3 mt3;
		JButton mb3;
		JTable3()
			{	jf = new JFrame("Test Table Options.");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf.setSize(300, 200);
				jf.getContentPane().setLayout(new FlowLayout());
				mt3 = new MyTable3();
				
				// Create Table Object
			 	(jf.getContentPane()).add(mt3.jsp);

			 	JButton jb = new JButton("Add");
			 	jb.addActionListener(this);
			 	
			 	(jf.getContentPane()).add(jb);

				jf.setVisible(true);
			}
		
		public void actionPerformed(ActionEvent e)
			{ MyTableMode3 tm = (MyTableMode3) mt3.jtDepttTable.getModel();
			  tm.addNewRow();
			  tm.fireTableRowsInserted(0,5);
			}
	}

public class TestJTable3
	{	
		public static void main(String[] args)
			{	JTable3 jt3 = new JTable3();	}
	}

