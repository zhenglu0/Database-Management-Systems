package cse530a;

import java.sql.*;

import cse530a.lab1.Main;

public class ExecuteCommand {
	
	
	/** "/set AUTOCOMMIT off" command
	 * */
	public static void setAutoCommitOff() {
		/**
		 * If "/set AUTOCOMMIT off" is specified then the database connection 
		 * should have auto-commit turned off.
		 *  If auto-commit is already off then this is a no-op.
		 */	
		if(Main.autocommitStatus == false){
			System.out.println("The auto-commit is already OFF, No operation now");
		} else {
			try {
				Main.conn.setAutoCommit(false);
				Main.autocommitStatus = false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("The auto-commit is turned OFF");
		}
	} 
	
	
	/** "/set AUTOCOMMIT on" command*/
	public static void setAutoCommitOn() {
		/**
		 * If auto-commit is off then this will turn it on. (This will implicitly cause a commit.) 
		 * If auto-commit is already on then this is a no-op.
		 */	
		if(Main.autocommitStatus == true){
			System.out.println("The auto-commit is already ON, No operation now");
		} else {
			try {
				Main.conn.setAutoCommit(true);
				Main.autocommitStatus = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("The auto-commit is turned ON");
		}
	}
	
	
}
