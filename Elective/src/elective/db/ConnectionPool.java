 package elective.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class ConnectionPool {
	
//	private MysqlConnectionPoolDataSource poolDataSource;

	private static final String DATASOURCE_NAME = "jdbc/elective_db";
	private static DataSource dataSource;
	static {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup(DATASOURCE_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private ConnectionPool() {
	}

	public static Connection getConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		return connection;
	}
	
	public static void closeConnection(Connection connection) throws SQLException {
		connection.close();
	}

}
