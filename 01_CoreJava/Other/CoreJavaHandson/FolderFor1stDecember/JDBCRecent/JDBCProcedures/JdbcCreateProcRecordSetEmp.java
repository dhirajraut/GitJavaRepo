
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcCreateProcRecordSetEmp
	{	public static void main(String[] args)
			{	try {	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						Connection cn = DriverManager.getConnection("jdbc:odbc:javaDSN", "scott", "tiger");
						CallableStatement cs = cn.prepareCall("create or replace procedure procRecordSetEmp is "+
								//"empn number; "+
								//"empnm varchar2(10); "+
								//"begin "+
									"select * from emp;"
									//"select empno, ename into empn, empnm from emp where ename='BLAKE'; "+
									//"dbms_output.put_line('No:'||empn||'   Name:'||empnm); "+
								//"end; "
									);
						
						cs.execute();
						System.out.println("Executed");
					}
				catch (ClassNotFoundException e)
					{	e.printStackTrace();	}
				catch (SQLException e)
					{	e.printStackTrace();	}
			}
	}
