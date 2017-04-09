import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcCreateProcMaxEmpNo
	{	public static void main(String[] args)
			{	try {	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						Connection cn = DriverManager.getConnection("jdbc:odbc:javaDSN", "scott", "tiger");
						CallableStatement cs = cn.prepareCall(
			"create or replace procedure procMaxEmpNo(MaxENo out number) is "+
					"meno number; "+
					"begin "+
						"select max(empno) into meno from emp; "+
						"MaxEno:=meno; "+
						"end; ");
						cs.execute();
						System.out.println("Executed");
					}
				catch (ClassNotFoundException e)
					{	e.printStackTrace();	}
				catch (SQLException e)
					{	e.printStackTrace();	}
			}
	}
