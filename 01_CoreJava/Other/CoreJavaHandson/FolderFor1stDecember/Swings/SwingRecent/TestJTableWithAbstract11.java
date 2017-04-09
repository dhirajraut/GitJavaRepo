/** This program is demonstrating TableModel interface and AbstractTableModel class */
/** Your model might hold its data in an array, vector, or hash map, or it might get the
 *  data from an outside source such as a database. It might even generate the data at
 *  execution time. */
import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

class MyTableModel extends AbstractTableModel
	{	private String [] columnNames;
		private Object [][] tableContents;
		//private int columnFix = 0;
		//private int rowFix = 0;
		
		MyTableModel(String[] columnNames, Object[][] tableContents)//, int columnFix, int rowFix)
			{	this.columnNames = columnNames;
				this.tableContents = tableContents;
				//this.columnFix = columnFix;
				//this.rowFix = rowFix;
			}
		
		public int getColumnCount()
			{ return	tableContents[0].length;	}

		public int getRowCount()
			{	return tableContents.length; 	}

		public Object getValueAt(int x, int y)
			{	//return "A";
				return tableContents[x][y];
			}

		public void setValueAt(Object val,int x, int y )
			{	if (isCellEditable(x, y))
					{	tableContents[x][y] = val;
						fireTableCellUpdated(x, y);
					}
			}

		public String getColumnName(int n)
			{	return(columnNames[n]);	}

		public void setColumnName(String nm, int n)
			{	columnNames[n] = new String(nm);	}
		
		// To make first two columns uneditable.
		public boolean isCellEditable(int x, int y)
			{	//if ((x>=rowFix)&&(y>=columnFix))
					return true;
				//else
					//return false;
			}
		
		/*public void setColumnFix(int c)
			{	columnFix = c; }
		
		public int getColumnFix()
			{	return columnFix;	}
		
		public void setRowFix(int r)
			{	rowFix = r;	}
		
		public int getRowFix()
			{	return rowFix; 	}*/
	}
class MyTableWithAbstract
	{	JOptionPane jop = new JOptionPane();
		JScrollPane jsp;
		JTable jtDepttTable;
		
		MyTableWithAbstract()
			{	String [] columnNames = { "A", "B", "C", "D" };
				Object [][] tableContents = { 	{"1", "Administration","Soham", "3.5"},
												{"2", "Account Section","Sayali", "4.5"},
												{"3", "Reception","Sukanya", "1.5"},
												{"4", "TechSupport","Satyen", "2.5"},
												{"5", "Marketting", "Sagar", "9.5"}
											};
				int [] columnSizes= {10, 110, 70, 30};
				int columnFix=1;
				int rowFix=1;
				MyTableModel mytmod = new MyTableModel(columnNames, tableContents);//, columnFix, rowFix);
				jtDepttTable = new JTable(mytmod);
				
			 	jtDepttTable.setSize(250,175);
			 	jtDepttTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			 	jtDepttTable.setToolTipText("Department Table View");
			 	jtDepttTable.setName("Table");
			 	jtDepttTable.setRowSelectionAllowed(true);
			 	
			 	int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			 	int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			 	jsp=new JScrollPane(jtDepttTable,v,h );
			}
	}

public class TestJTableWithAbstract11

	{	public static void main(String[] args)
			{	JFrame jf = new JFrame("Test Table Options.");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf.setSize(300, 200);

				// Create Table Object
			 	(jf.getContentPane()).add((new MyTableWithAbstract()).jsp);

				jf.setVisible(true);
			}
	}
