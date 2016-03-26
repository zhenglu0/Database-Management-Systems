package cse530a.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class DatabaseManager {
    private static final Logger LOGGER = Logger.getLogger(DatabaseManager.class.getName());
    
    private DataSource source;

	public DatabaseManager(DataSource source) {
	    this.source = source;
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
