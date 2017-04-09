package connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ConnectionPool
	{	private HashMap hashconnections;
		private int increment;
		private String dbURL;
		
		public ConnectionPool(String dbURL, String driver, String user, String passwd, int initialconnect, int increment) throws SQLException, ClassNotFoundException
			{	// Load the specific driver
				Class.forName(driver);
				this.dbURL = dbURL;
				this.increment = increment;
				hashconnections = new HashMap();
				
				// Put the pool of connections in the HashMap.  Create connections and add to Hash Map.
				for(int i = 0; i < initialconnect; i++)
					{	hashconnections.put(DriverManager.getConnection(dbURL, user, passwd), Boolean.FALSE);	}
			}
		
		public Connection getConnection() throws SQLException
			{	Connection con = null;
			
				Set cons = hashconnections.keySet();
				Iterator it = cons.iterator();
				
				synchronized(hashconnections)
					{	while(it.hasNext())
							{	con = (Connection) it.next();
								Boolean b = (Boolean) hashconnections.get(con);
								if (b == Boolean.FALSE)
									{	try {con.setAutoCommit(true);	}
										catch(SQLException e)
											{	con = DriverManager.getConnection(dbURL);	}
										hashconnections.put(con, Boolean.TRUE);
										return(con);
									}
							}
					}
				// Control comes here when there is no vacant connection in the pool.
				// Create new 'increment' number of connections and add them to the pool.
				for(int i = 0; i<increment; i++)
					hashconnections.put(DriverManager.getConnection(dbURL), Boolean.FALSE);

				// Recurse same function to return one connection.
				return getConnection();
			}
		
		public void returnConnection(Connection returned)
			{	Connection con = null;
				Set cons = hashconnections.keySet();
				Iterator it = cons.iterator();
				
				while(it.hasNext())
					{	con = (Connection) it.next();
						if ( con == returned)
							{	hashconnections.put(con, Boolean.FALSE);
								break;
							}
					}
			}
	}
