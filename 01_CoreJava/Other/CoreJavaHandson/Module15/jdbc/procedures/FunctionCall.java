package Module15.jdbc.procedures;

import java.sql.*;
import java.io.*;

public class  FunctionCall{
	public static void main (String[] args) throws InterruptedException {
		if (args.length==0){
			System.out.println ("USAGE : FunCallTest <empno>");
			System.exit(1);
		}
		try {
			DriverManager.registerDriver (new sun.jdbc.odbc.JdbcOdbcDriver ());
			Connection conn =DriverManager.getConnection ("jdbc:odbc:mydsn", "scott", "tiger");
			CallableStatement cst = conn.prepareCall (" { ? = call GetNet (?) } ");
			int eno = Integer.parseInt (args[0]);
			cst.setInt (2,eno);
			cst.registerOutParameter (1,Types.FLOAT);
			cst.executeUpdate ();
			float net = cst.getFloat (1);
			System.out.println ("EMP NET SALARY : " + net);
			cst.close (); conn.close ();
		}
		catch (Exception e) {
			System.out.println ("Problem  : " + e);
		}
	}
}
