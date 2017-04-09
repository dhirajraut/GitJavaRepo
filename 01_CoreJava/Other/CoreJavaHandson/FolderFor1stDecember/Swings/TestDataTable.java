

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JOptionPane;

import javax.swing.table.AbstractTableModel;

class MyDataTable extends AbstractTableModel		
	{	Vector cache;
		String [] column;
		Connection con;
		Statement st;
		ResultSet rs;
		ResultSetMetaData rsmd;
		public MyDataTable(String driver, String url, String userName, String passWd)
			{	cache = new Vector();
				
				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url, userName, passWd);
					st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					
					rs = st.executeQuery("SELECT EMPNO, ENAME FROM EMP");
					
					// Creating column array
					rsmd = rs.getMetaData();
					int colCount = rsmd.getColumnCount();
					column = new String[colCount];
					for(int i=0; i<column.length; i++)
						column[i] = rsmd.getColumnName(i+1);
					
					// Creating Data Vector
					Object [] o;
					while(rs.next())
						{	o = new Object[colCount];
							o[0] = new Integer(rs.getInt("EMPNO"));
							o[1] = rs.getString("ENAME");
							cache.addElement(o);
						}
					}
				catch(ClassNotFoundException s)
					{System.out.println("Driver Missing");}
				catch(SQLException s)
					{	System.out.println("UserNam, PassWd Mismatched.");}
			}
		
		public void addNewRecord(Object[] o)
			{	
				try {
				rs.moveToInsertRow();
				rs.updateInt(1, 100);
				rs.updateString(2, "pqr");
				rs.insertRow();
					}
				catch (SQLException e)
					{	System.out.println("Adding new record fails.");
						return;
					}
				int fromRow = getRowCount()-1;
				cache.addElement(o);
				int toRow = getRowCount()-1;
				this.fireTableRowsInserted(fromRow, toRow);
				
			}
		
		public  void delOldRecord(int row)
			{	cache.remove(row);
				this.fireTableRowsDeleted(row, getRowCount()-1);
			}
		public int getRowCount()
			{	return cache.size();	}
		
		public int getColumnCount()
			{	return column.length;	}
		
		public Object getValueAt(int row, int col)
			{	Object [] r = (Object[]) cache.elementAt(row);
				Object o = r[col];
				return o;
			}
		
		public boolean isCellEditable(int row, int col)
			{	return true;	}
		
		public String getColumnName(int col)
			{	return (String)column[col];	}
	}

class MyDataFrame extends JFrame implements ActionListener, MouseListener
	{ 	JButton butAdd;
		JButton butDel;
		JTable jt;
		MyDataTable mdt;
		public MyDataFrame()
			{	String driver = "oracle.jdbc.driver.OracleDriver";
				String url = "jdbc:oracle:thin:@rac940:1521:CHANDRA";
				String userName = "scott";
				String passWd = "tiger";
				
				setSize(500, 500);
			
				Container c = getContentPane();
			
				c.setLayout(new BorderLayout());
				
				jt  = new JTable(new MyDataTable(driver, url, userName, passWd));
				jt.addMouseListener(this);
				mdt = (MyDataTable)jt.getModel();
				
				int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
				int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		 	
				JScrollPane jsp=new JScrollPane(jt,v,h );
				jsp.setSize(200, 100);
				JPanel jp1 = new JPanel();
				jp1.setSize(200, 100);
				jp1.add(jsp);
				
				butAdd = new JButton("Add");
				butDel = new JButton("Del");
				
				butAdd.addActionListener(this);
				butDel.addActionListener(this);
				
				JPanel jp2 = new JPanel();
				jp2.setSize(150, 50);
				jp2.add(butAdd);
				jp2.add(butDel);
				
				c.add(jp1, BorderLayout.NORTH);
				c.add(jp2, BorderLayout.SOUTH);
				setVisible(true);
			}
		
		public void actionPerformed(ActionEvent e)
			{	if (e.getSource()== butAdd)
					{	int row = mdt.getRowCount();
						System.out.println(row);
						String key = "A"+String.valueOf(row+1);
						Object [] o = {key, "pqr"};
						mdt.addNewRecord(o);
					}
				else
					{	int row = jt.getSelectedRow();
						if (row>=0)
							mdt.delOldRecord(row);
						else
							JOptionPane.showMessageDialog(null, "No row selected");
					}
			}

		public void mouseClicked(MouseEvent e)
			{	}

		public void mouseEntered(MouseEvent e)
			{	}

		public void mouseExited(MouseEvent e)
			{	}

		public void mousePressed(MouseEvent e)
			{	}

		public void mouseReleased(MouseEvent e)
			{	}
	}

public class TestDataTable
	{
		public static void main(String [] argv) 
			{	MyDataFrame jf = new MyDataFrame();
			}
	}
