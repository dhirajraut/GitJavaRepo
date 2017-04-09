//JDBCcallingCreateProcedure
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCCreateHello
	{	public static void main(String [] argv)
		{	try
				{  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				   Connection cn = DriverManager.getConnection("jdbc:odbc:javaDSN", "scott", "tiger");
				   System.out.println("Creating Procedure string.");
				   String createProcedure = "create or replace procedure procHelloWorld is "+
				       					    "begin "+
				         						"dbms_output.put_line('Hello World Ha Ha Ha'); "+
				     						"end; ";

				   Statement stmt = cn.createStatement();
				   stmt.executeUpdate(createProcedure);
				  																									
				   stmt.close();
				   cn.close();
				}
			catch(SQLException e)
				{ System.out.println("Error :"+e); }
			catch(ClassNotFoundException e)
				{ System.out.println("Error :"+e); }
		}
	}