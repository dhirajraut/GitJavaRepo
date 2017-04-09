import java.sql.*;

public class ScrollingOnEmployee02 {

	public static void main(String args[]) {

		// OCI/Thin Driver
		String ociThinDriver = "oracle.jdbc.driver.OracleDriver";
				
		// Thin url
		String thinUrl = "jdbc:oracle:thin:@rac940:1521:CHANDRA";
		
		Connection con;
		Statement stmt;
		try {	Class.forName(ociThinDriver);	}
		catch(java.lang.ClassNotFoundException e)
			{	e.printStackTrace();	}

		try {

			con = DriverManager.getConnection(thinUrl, "scott", "tiger");

			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
     									ResultSet.CONCUR_READ_ONLY);

            ResultSet srs = stmt.executeQuery("SELECT * FROM emp");

			srs.absolute(4); 
			int rowNum = srs.getRow(); // rowNum should be 4
			System.out.println("rowNum should be 4 " + rowNum);
			srs.relative(-2); 
			rowNum = srs.getRow(); // rowNum should be 2
			System.out.println("rowNum should be 2 " + rowNum);
			srs.relative(2); 
			rowNum = srs.getRow(); // rowNum should be 4
			System.out.println("rowNum should be 4 " + rowNum);

			srs.absolute(1);
			System.out.println("after last? " + srs.isAfterLast() );
			while (srs.next()) {
				String name = srs.getString("ENAME");
				int empno = srs.getInt("empno");
				System.out.println(name + "     " + empno);
			}
			System.out.println("----------------");
			srs.afterLast();
			while (srs.previous()) {
				String name = srs.getString("ENAME");
				int deptno = srs.getInt("empno");
				System.out.println(name + "     " + deptno);
			}

			srs.close();
			stmt.close();
			con.close();

		} catch(SQLException ex) {
			System.err.println("-----SQLException-----");
			
			// Returns a string idendifying error according to SQL convensions.
			System.err.println("SQLState:  " + ex.getSQLState());
			
			//	Returns String type error message
			System.err.println("Message:  " + ex.getMessage());
			
			// Returns vendor error code
			System.err.println("Vendor:  " + ex.getErrorCode());
		}
	}
}

