import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JOptionPane;

import javax.swing.table.AbstractTableModel;

class MyDynamicTable extends AbstractTableModel		
	{	Vector cache;
		Vector column;
		
		public MyDynamicTable(Vector columnHeads)
			{	cache = new Vector();
				column = columnHeads;
			}
		
		public void addNewRecord(Object[] o)
			{	int fromRow = getRowCount()-1;
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
			{	return column.size();	}
		
		public Object getValueAt(int row, int col)
			{	Object [] r = (Object[]) cache.elementAt(row);
				Object o = r[col];
				return o;
			}
		
		public boolean isCellEditable(int row, int col)
			{	return true;	}
		
		public String getColumnName(int col)
			{	return (String)column.elementAt(col);	}
	}

class MyFrame extends JFrame implements ActionListener, MouseListener
	{ 	JButton butAdd;
		JButton butDel;
		JTable jt;
		MyDynamicTable mdt;
		public MyFrame()
			{	setSize(500, 500);
			
				Container c = getContentPane();
			
				c.setLayout(new BorderLayout());
			
				Vector columnHead = new Vector();
				columnHead.add("EmpNo");
				columnHead.add("EmpNm");
				jt  = new JTable(new MyDynamicTable(columnHead));
				jt.addMouseListener(this);
				mdt = (MyDynamicTable)jt.getModel();
				
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

public class TestDynamicTable
	{
		public static void main(String [] argv) 
			{	MyFrame jf = new MyFrame();
			}
	}
