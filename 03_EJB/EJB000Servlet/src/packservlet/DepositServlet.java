package packservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


 public class DepositServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
			try {
				int accNo = Integer.parseInt(request.getParameter("number"));
				float amt = Float.parseFloat(request.getParameter("amount"));
					
				String driver = "oracle.jdbc.driver.OracleDriver";
				String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
				//------------------------------------------------------------------------
				Class.forName(driver);
				Connection conn = DriverManager.getConnection(url, "scott", "tiger");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery
				("Select ACCNM, BALANCE from AccMaster where ACCNO="+accNo);
				rs.next();
				String accNm = rs.getString("ACCNM");
				float accBal = rs.getFloat("ACCBAL");

				SavingsAccount holder = new SavingsAccount (accNo, accNm, accBal);
				holder.deposit(amt);
				PreparedStatement pstmt = conn.prepareStatement
					("update AccMaster set BALANCE = ? where ACCNO = ?");
				pstmt.setFloat(1, accBal);
				pstmt.setInt(2, accNo);
				pstmt.executeUpdate();
				out.println("Record Updated.");
				pstmt.close();
				stmt.close();
				conn.close();
			}
			catch (NumberFormatException e) {	e.printStackTrace();	}
			catch (ClassNotFoundException e) {	e.printStackTrace();	}
			catch (SQLException e) {	e.printStackTrace();	}
			out.close();
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}   	  	    
}