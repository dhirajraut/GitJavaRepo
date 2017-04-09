/** This program is demonstrating TableModel interface and AbstractTableModel class */
/** Your model might hold its data in an array, vector, or hash map, or it might get the
 *  data from an outside source such as a database. It might even generate the data at
 *  execution time. */
import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

class MyTableMode2 extends AbstractTableModel
	{	private String [] columnNames;
		private Object [][] tableContents;
		private int columnFix = 0;
		private int rowFix = 0;
		
		MyTableMode2(String[] columnNames, Object[][] tableContents, int columnFix, int rowFix)
			{	this.columnNames = columnNames;
				this.tableContents = tableContents;
				this.columnFix = columnFix;
				this.rowFix = rowFix;
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
		
		public Class getColumnClass(int n)
			{	return getValueAt(0, n).getClass();	}
		
		// To make first two columns uneditable.
		public boolean isCellEditable(int x, int y)
			{	if ((x>=rowFix)&&(y>=columnFix))
					return true;
				else
					return false;
			}
		
		public void setColumnFix(int c)
			{	columnFix = c; }
		
		public int getColumnFix()
			{	return columnFix;	}
		
		public void setRowFix(int r)
			{	rowFix = r;	}
		
		public int getRowFix()
			{	return rowFix; 	}
	}
class MyTable2
	{	JOptionPane jop = new JOptionPane();
		JScrollPane jsp;
		JTable jtDepttTable;
		
		MyTable2()
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
				MyTableMode2 mytmod = new MyTableMode2(columnNames, tableContents, columnFix, rowFix);
				jtDepttTable = new JTable(mytmod);
				
			 	jtDepttTable.setSize(250,175);
			 	jtDepttTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			 	jtDepttTable.setToolTipText("Department Table View");
			 	jtDepttTable.setName("Table");
			 	jtDepttTable.setRowSelectionAllowed(true);
			 	
			 	//	Setting column size
			 	/*TableColumnModel com = jtDepttTable.getColumnModel();
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
			 	System.out.println("Fix column :"+mytmod.getColumnFix());
			 	jtDepttTable.setValueAt("Liza", 4, 2);
			 	jtDepttTable.setValueAt("6", 4, 0);*/
			 		
			 	int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			 	int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			 	jsp=new JScrollPane(jtDepttTable,v,h );
			}
	}

public class TestJTable2
	{	public static void main(String[] args)
			{	JFrame jf = new JFrame("Test Table Options.");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf.setSize(300, 200);

				// Create Table Object
			 	(jf.getContentPane()).add((new MyTable2()).jsp);

				jf.setVisible(true);
			}
	}
