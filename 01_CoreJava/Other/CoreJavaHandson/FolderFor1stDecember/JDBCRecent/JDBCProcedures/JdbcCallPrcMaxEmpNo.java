import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// A program operating on Stored Procedures receiveing one parameter
// A program needs corrections.  Not giving results.
public class JdbcCallPrcMaxEmpNo
	{ 	public static void main(String[] args)
			{	try
					{  Class.forName("oracle.jdbc.driver.OracleDriver");
					   Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@rac940:1521:CHANDRA", "scott", "tiger");
					   
					   System.out.println("Calling Procedure.")	;	   			
					   CallableStatement cs = cn.prepareCall("{call procMaxEmpNo(?)}");
				
					   System.out.println("Parameter Registered.");
					   cs.registerOutParameter(1, java.sql.Types.NUMERIC);
					   
					   System.out.println("Procedure Called.");
					   cs.execute();
					   int eno = cs.getInt(1);
					   System.out.println("eno:"+eno);
						
					   System.out.println("Closing All");
					   	cs.close();
						cn.close();		
					}
				catch(SQLException e)
					{ System.out.println("Error :"+e); }
				catch(ClassNotFoundException e)
					{ System.out.println("Error :"+e); }
			}
		}
