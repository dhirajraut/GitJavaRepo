import java.sql.CallableStatement;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;

//	 A program operating on Stored Procedures receiveing one parameter
//	 A program needs corrections.  Not giving results.
	public class EmpRecordSetProc
		{ 	public static void main(String[] args)
				{	try
						{  Class.forName("oracle.jdbc.driver.OracleDriver");
						   Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@rac940:1521:CHANDRA", "scott", "tiger");
						   
						   System.out.println("Calling Procedure.")	;	   			
						   CallableStatement cs = cn.prepareCall("{call procRecordSetEmp()}");
						  
						   cs.execute();
						   ResultSet rs =cs.getResultSet();
						   if (rs == null)
							   { 	System.out.println("Empty null set.");
							   		return;
							   }
						   rs.next();
						   int eno = cs.getInt(1);
						   System.out.println("eno:"+eno);
						   	cs.close();
							cn.close();		
						}
					catch(SQLException e)
						{ System.out.println("Error :"+e); }
					catch(ClassNotFoundException e)
						{ System.out.println("Error :"+e); }
				}
			}
