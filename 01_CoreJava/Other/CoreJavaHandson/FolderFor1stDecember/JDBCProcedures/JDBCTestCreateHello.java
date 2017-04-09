import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTestCreateHello
	{	public static void main(String[] args)
			{	try
					{  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					   Connection cn = DriverManager.getConnection("jdbc:odbc:javaDSN", "scott", "tiger");
					   System.out.println("Calling Procedure")	;	   			
					   //CallableStatement cs = cn.prepareCall("{call procHelloWorld}");
					   CallableStatement cs = cn.prepareCall("{call procMax}");
					   cs.execute();
					   System.out.println("Procedure Called");
						cs.close();
						cn.close();		
					}
				catch(SQLException e)
					{ System.out.println("Error :"+e); }
				catch(ClassNotFoundException e)
					{ System.out.println("Error :"+e); }
			}
		}
