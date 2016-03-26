package cse530a;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import cse530a.lab1.Main;

public class ExecuteStatement {

	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());


	/** begin statement */
	/**
	 *  1. Issuing a "begin" when auto-commit is on should temporarily set auto-commit 
	 *  to off on the database connection. When a "commit" or "rollback" is subsequently
	 *  entered then the transaction should be committed or rolled back and the connection 
	 *  should have auto-commit turned back on. 
	 *  
	 *  2. When auto-commit is on and a "begin" has not been issued, each statement 
	 *  is its own transcation, so a "commit" or "rollback" will have no effect. 
	 *  However, you should output the warning "No transaction is progress".
	 *  
	 *  3. Issuing a "begin" when auto-commit is off should just output the warning 
	 *  "Transaction already in progress".
	 */
	public static void executeBegin() {
		/** when auto-commit is on should temporarily set auto-commit to off */
		if(Main.autocommitStatus == true) {
			ExecuteCommand.setAutoCommitOff();
			/** autocommitTemp store the status */
			Main.autocommitOffTemp = true;
		} /** if the auto-commit is already off */
		else {
			System.out.println("Transaction already in progress");
		}
	}


	/** commit statement */
	/**
	 *  1. When auto-commit is on and a "begin" has not been issued, each statement is 
	 *  its own transcation, so a "commit" or "rollback" will have no effect. 
	 *  However, you should output the warning "No transaction is progress".
	 *  
	 *  2.Issuing a "commit" or "rollback" when auto-commit is off should commit or 
	 *  roll back the transaction. Note that this immediately opens a new transaction; 
	 *  no "begin" is necessary.
	 * 
	 * */
	public static void executeCommit() {
		/** if  auto-commit is off */
		if (Main.autocommitStatus == false) {
			try {
				/** commit the the transaction */
				Main.conn.commit();
				/** check whether auto-commit is temporarily disabled,
				 *  if it is temporarily disabled, resort to the normal condition
				 *  */
				if(Main.autocommitOffTemp == true) {
					ExecuteCommand.setAutoCommitOn();
					Main.autocommitOffTemp = false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		} /** if auto-commit is on */ 
		else {
			System.out.println("No transaction is progress");
		}
	}


	/** rollback statement */
	public static void executeRollback() {
		/** if  auto-commit is off */
		if (Main.autocommitStatus == false) {
			try {
				/** commit the the transaction */
				Main.conn.rollback();
				/** check whether auto-commit is temporarily disabled,
				 *  if it is temporarily disabled, resort to the normal condition
				 *  */
				if(Main.autocommitOffTemp == true) {
					ExecuteCommand.setAutoCommitOn();
					Main.autocommitOffTemp = false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		} /** if auto-commit is on */ 
		else {
			System.out.println("No transaction is progress");
		}	

	}


	/** select statement */
	public static void executeSelect(String sqlStatement){
		try {
			Statement stmt = Main.conn.createStatement();	
			try {
				ResultSet rs = stmt.executeQuery(sqlStatement);
				try {
					ResultSetMetaData metadata = rs.getMetaData(); 
					int count = metadata.getColumnCount();
					int maxStringCount[] = new int [count];
					int temp = 0;
					/** initialize the array */
					for (int i = 0; i < count; i++) {
						maxStringCount[i] = 0;
					}
					/** store the query content and find the 
					 * string which have max number of characters 
					 * */
					ArrayList<Object[]> results = new ArrayList<Object[]>();
					while (rs.next()) 
					{
						Object[] row = new Object[count];
						for (int i = 0; i < count; ++i) {
							/** add the object to the row */
							row[i] = rs.getObject(i+1);
							/** get the biggest number of the string */
							temp = rs.getObject(i+1).toString().length();
							if (temp > maxStringCount[i]) {
								maxStringCount[i] = temp;
							}
						}
						results.add(row);
					}
					/** increament the counter to fit the display better */
					for (int i = 0; i < count; i++) {
						maxStringCount[i] = maxStringCount[i] + 2;
					}
					/** show the table header */
					for (int i = 0; i < count; ++i) {
						/** set parameter to print string */
						String parameter = "%-" + maxStringCount[i] + "s";
						String label = metadata.getColumnLabel(i+1);
						/** print the table header */
						System.out.printf(parameter,label);
					}
					System.out.println();
					/** print the result of each cell */
					int number_of_rows = results.size();
					for (int i = 0; i < number_of_rows; ++i) {
						Object[] row = results.get(i);
						for (int j = 0; j < count; ++j) {
							/** set parameter to print string */
							String parameter = "%-" + maxStringCount[j] + "s";
							/** print the element in the row */
							System.out.printf(parameter,row[j]);
						}
						System.out.println();
					}
					System.out.println();
				} finally {
					DatabaseManager.closeResultSet(rs);
				}
			} finally {
				DatabaseManager.closeStatement(stmt);
			}
		} catch (Exception e) {
			/** Display the error message */
			LOGGER.log(Level.WARNING, "error", e);
			return;
		}
	}


	/** update statement */
	public static void executeUpdate(String sqlStatement) {
		String [] splitString = sqlStatement.split(" ");
		Statement stmt;
		try {
			stmt = Main.conn.createStatement();
			try {
				/** do the update statement */
				int count = stmt.executeUpdate(sqlStatement);
				/** print the result
				 *  there are 3 possible condition
				 *  1. update  2. insert  3. delete
				 *  also the create table and alter table
				 * */
				if (splitString[0].toLowerCase().equals("update")) {
					System.out.println("UPDATE " + count);
				}
				else if (splitString[0].toLowerCase().equals("delete")) {
					System.out.println("DELETE " + count);
				}
				else if (splitString[0].toLowerCase().equals("insert")) {
					System.out.println("INSERT 0 " + count);
				}
				else if (splitString[0].toLowerCase().equals("alter")) {
						System.out.println("ALTER TABLE");
				}
				else if (splitString[0].toLowerCase().equals("create")) {
					System.out.println("CREATE TABLE");
				}
				else if (splitString[0].toLowerCase().equals("drop")) {
					System.out.println("DROP TABLE");
				}
			} catch (SQLException e) {
				/** Display the error message */
				LOGGER.log(Level.WARNING, "Statement error", e);
			} finally {
				DatabaseManager.closeStatement(stmt);
			}
		} catch (Exception e) {
			/** Display the error message */
			LOGGER.log(Level.WARNING, "error", e);
			return;
		}
	}


}
