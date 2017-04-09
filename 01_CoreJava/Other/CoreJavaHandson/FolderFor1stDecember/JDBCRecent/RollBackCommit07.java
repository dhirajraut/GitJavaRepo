import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import ioservices.MyIOService;

public class  RollBackCommit07
	{	Connection conn;
		
		public void openConnection()  throws ClassNotFoundException, SQLException 
			{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				conn = DriverManager.getConnection("jdbc:odbc:javaDSN","scott","tiger");	
			}
		
		// Only insert method is developed for Rollback.
		// Other methods are yet to be developed.
		public boolean insertRecords07(EmpBean07 e, EmpFamilyBean07[] ef) throws SQLException, IOException
			{	String insertEmp = "insert into empBean (empNo, ename, sal) values (?,?,?)";
				String insertEmpFamily = "insert into empFamily(empNo, memberId,memberName, memberRelation) values (?,?,?,?)";
				
				PreparedStatement pst1 = conn.prepareStatement(insertEmp, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				PreparedStatement pst2 = conn.prepareStatement(insertEmpFamily, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				
				try	{	conn.setAutoCommit(false);
						pst1.setInt(1, e.getEmpNo());
						pst1.setString(2, e.getEName());
						pst1.setFloat(3, e.getSal());
						pst1.executeUpdate();
					
						for(int i=0; i<ef.length; i++)
							{	pst2.setInt(1,ef[i].getEmpNo()); 
								pst2.setInt(2, ef[i].getMemberId());
								pst2.setString(3,ef[i].getMemberName());
								pst2.setString(4, ef[i].getMemberRelation());
								pst2.executeUpdate();
							}
						conn.commit();
						conn.setAutoCommit(true);
						return true;
					}
				catch(SQLException sq)
					{	conn.rollback();
						return false;
					}
				finally
					{	pst1.close();
						pst2.close();
					}
			}
		
		public void closeConnection() throws SQLException
			{	conn.close();
				System.out.println("Thanks.");
			}
	
		public static void main (String[] args) throws IOException
			{	RollBackCommit07 it = new RollBackCommit07();
				
				EmpBean07 e = new EmpBean07(101, "Ramesh", 5000);
				EmpFamilyBean07[] ef = { new EmpFamilyBean07(101, 1, "Runita", "wife"),
										 new EmpFamilyBean07(101, 2, "Mahendra Singh", "father"),
										 new EmpFamilyBean07(101, 3, "Mithibai", "mother"),
										 new EmpFamilyBean07(101, 4, "Boby", "son")
										};
			
				try {	it.openConnection();
						
						if (it.insertRecords07(e, ef))
							System.out.println("Data written successfully");
						else
							System.out.println("Not written");
						
						it.closeConnection();
					}
				catch (ClassNotFoundException ce)
					{	System.out.println ("Driver loading error "+ce);
						System.exit(2);
					}
				catch (SQLException ce)
					{	System.out.println ("insert error : "+ce); }
				/*catch( IOException ie)
					{	ie.printStackTrace();	}*/
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
			{	PreparedStatement pst = conn.prepareStatement("select * from firstsecond");
			
				ResultSet rs = pst.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				
				int numOfColumns = rsmd.getColumnCount();
				for(int i=0; i<numOfColumns; i++)
					{	System.out.print(rsmd.getColumnName(i+1)+" ");	}
				System.out.println();
				while(rs.next())
					{	int first = rs.getInt(1);
						String second = rs.getString(2);
						System.out.println(first+"     "+second);
					}
				pst.close();
			}
	}

