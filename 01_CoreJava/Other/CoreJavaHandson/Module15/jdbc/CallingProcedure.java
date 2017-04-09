package Module15.jdbc;

import java.sql.*;
import java.io.*;

public class  CallingProcedure {
   public static void main (String [ ] args) throws InterruptedException {
   		if (args.length==0) {
             System.out.println ("USAGE : ProCallTest <empno>");
     		     System.exit (1);
   		}
    	try {
           DriverManager.registerDriver (new sun.jdbc.odbc.JdbcOdbcDriver ());
      	   Connection conn = DriverManager.getConnection("jdbc:odbc:mydsn", "scott", "tiger");
      	   CallableStatement cst = conn.prepareCall (" {call GetDetails (?, ?, ?) } ");
			
    	   int eno = Integer.parseInt (args[0]);
    	   cst.setInt (1, eno);
    	   cst.registerOutParameter (2, Types.VARCHAR);
    	   cst.registerOutParameter (3 ,Types.VARCHAR);
    	   cst.executeUpdate ();
    	   String name = cst.getString (2);
    	   String job = cst.getString (3);

    	   System.out.println ("EMP NAME   : " + name);
    	   System.out.println ("EMP JOB    : " + job);
    	   cst.close ();	conn.close ();
    	}
    	catch (Exception e) {
    		System.out.println ("Problem  : "+e);
    	}
    }
}
