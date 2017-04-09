import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCCreateProc
	{	public static void main(String[] args)
			{	try {	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						Connection cn = DriverManager.getConnection("jdbc:odbc:javaDSN", "scott", "tiger");
						CallableStatement cs = cn.prepareCall("create or replace procedure procHelloWorld is "+
									   					    "begin "+
									   					    	"dbms_output.put_line('Hello World Ha Ha Ha'); "+
															"end; ");
					}
				catch (ClassNotFoundException e)
					{	e.printStackTrace();
					}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			}
	}
