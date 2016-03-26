package cse530a;


import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

public class DatabaseManager {
    private static final Logger LOGGER = Logger.getLogger(DatabaseManager.class.getName());

    private String host;
    private int port;
    private String database;
	private String username;
	private String password;

	private String url;
	private ComboPooledDataSource source;

	public DatabaseManager(Properties properties) throws PropertyVetoException {
        this.host = properties.getProperty("host");
        this.port = Integer.parseInt(properties.getProperty("port"));
        this.database = properties.getProperty("database");
        this.username = properties.getProperty("username");
        this.password = properties.getProperty("password");
        
        url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
        
        source = new ComboPooledDataSource();
        source.setDriverClass("org.postgresql.Driver");
        source.setJdbcUrl(url);
        source.setUser(username);
        source.setPassword(password);
	}

	@Override
    protected void finalize() throws Throwable {
	    DataSources.destroy(source);
        super.finalize();
    }

    public Connection getConnection() {
	    Connection conn = null;
	    
	    try {
			conn = source.getConnection();
		} catch (SQLException e) {
		    LOGGER.log(Level.SEVERE, "error getting database connection", e);
		}
	    
	    return conn;
	}

	public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "error closing Connection", e);
            }
        }
    }
    
    public static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "error closing Statement", e);
            }
        }
    }
    
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "error closing ResultSet", e);
            }
        }
    }
}
