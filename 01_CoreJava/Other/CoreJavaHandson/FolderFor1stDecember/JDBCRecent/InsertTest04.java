import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import ioservices.MyIOService;

public class  InsertTest04
	{	Connection conn;
		
		public void openConnection()  throws ClassNotFoundException, SQLException 
			{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				conn = DriverManager.getConnection("jdbc:odbc:javaDSN","scott","tiger");	
			}
		
		public void insertRecords04() throws SQLException, IOException
			{	PreparedStatement pst =	conn.prepareStatement
					("insert into firstsecond (first,second) values (?,?)", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				
				char flag = 'y';
				System.out.print("To continue?(Y/N) ");
				flag = MyIOService.getChar();
			
				while ((flag=='y')||(flag=='Y'))
					{	System.out.println("Enter first int:");
						int fst = MyIOService.getInt();
						System.out.println("Enter second string:");
						String snd = MyIOService.getString();
						
						pst.setInt(1,fst); 
						pst.setString(2,snd);
						
						int rows = pst.executeUpdate();
						if (rows == 1)
							System.out.println("Yaa. Record inserted.");
						System.out.print("To continue?(Y/N) ");
						flag = MyIOService.getChar();
					}
				pst.close();
			}
		
		public void updateRecords05() throws SQLException, IOException
			{	PreparedStatement pst =	conn.prepareStatement
					("update firstsecond set second = ? where first=?");
				char flag = 'y';
				System.out.print("To continue?(Y/N) ");
				flag = MyIOService.getChar();
			
				while ((flag=='y')||(flag=='Y'))
					{	System.out.println("Enter key value:");
						int fst = MyIOService.getInt();
						System.out.println("Enter second string:");
						String snd = MyIOService.getString();
						pst.setInt(2,fst);
						pst.setString(1,snd);
						int rows = pst.executeUpdate();
						System.out.println (rows+" rows updated");
						System.out.print("To continue?(Y/N) ");
						flag = MyIOService.getChar();
					}
				pst.close();
			}
		
		public void listRecords06() throws SQLException
			{	PreparedStatement pst = conn.prepareStatement("select * from firstsecond", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
				ResultSet rs = pst.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				
				int numOfColumns = rsmd.getColumnCount();
				for(int i=0; i<numOfColumns; i++)
					{	System.out.print(rsmd.getColumnName(i+1)+" ");	}
				System.out.println();
				rs.last();
				int n = rs.getRow();
				rs.beforeFirst();
				while(rs.next())
					{	int first = rs.getInt(1);
						String second = rs.getString(2);
						System.out.println(first+"     "+second);
					}
				pst.close();
			}
		
		public void closeConnection() throws SQLException
			{	conn.close();
				System.out.println("Thanks.");
			}
		
	public static void main (String[] args) throws IOException
		{	InsertTest04 it4 = new InsertTest04();
			
			try {	it4.openConnection();
					it4.insertRecords04();
					//it4.updateRecords05();
					it4.listRecords06();
					it4.closeConnection();
				}
			catch (ClassNotFoundException e)
				{	System.out.println ("Driver loading error "+e);
					System.exit(2);
				}
			catch (SQLException e)
				{	System.out.println ("insert error : "+e);
					e.printStackTrace();
				}
			/*catch( IOException ie)
				{	ie.printStackTrace();	}*/
		}
	}

	

