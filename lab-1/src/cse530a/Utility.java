package cse530a;



import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Holds generic utility methods that don't fit anywhere else.
 */
public class Utility {
	
	private static final Logger LOGGER = Logger.getLogger(Utility.class.getName());

	
	/**
	 * Quietly closes a Closeable object.
	 * 
	 * @param closeable the Closeable to close
	 */
	public static void closeQuietly(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				LOGGER.log(Level.WARNING, "error closing resource", e);
			}
		}
	}

	
	/**
	 * Read and process in the input command
	 */
	public static void start() {
		/** System.in std input stream already opened by default.
		 Wrap in a new reader stream to obtain 16 bit capability.
		 */
		InputStreamReader reader = new InputStreamReader (System.in);
		/** Wrap the reader with a buffered reader.*/
		BufferedReader buf_in = new BufferedReader (reader);
		/** Declare the string readIn */
		String readIn;
		try {
			/** Read a whole line a time. Check the string for
			 the "\q" input to jump from the loop.
			 */
			while (true) {
				/** Prompt the user to enter commands or statement */
				System.out.println("Please input command or statement:");
				/** Read text from keyboard */
				readIn = buf_in.readLine ();
				/** Check the break string */
				if(readIn.equals("\\q")) {
					break;
				}
				/** Do the command processing */
				processCommandAndStatement(readIn);
			}
		}
		catch  (IOException e) {
			System.out.println ("IO exception = " + e );
		}			
	}

	
	/**
	 * The function do the command processing
	 */
	public static void processCommandAndStatement(String userInput) {

		/** Check if the command is \set AUTOCOMMIT on */
		if(userInput.equals("\\set AUTOCOMMIT on")) {
			ExecuteCommand.setAutoCommitOn();
		}
		/** Check if the command is \set AUTOCOMMIT off */
		else if(userInput.equals("\\set AUTOCOMMIT off")) {
			ExecuteCommand.setAutoCommitOff();
		}
		/** Check if the command is begin */
		else if(userInput.equals("begin")) {
			ExecuteStatement.executeBegin();

		}
		/** Check if the command is commit */
		else if(userInput.equals("commit")) {
			ExecuteStatement.executeCommit();

		}
		/** Check if the command is commit */
		else if(userInput.equals("rollback")) {
			ExecuteStatement.executeRollback();

		}
		/**If the line is not one of the commands or special SQL statements 
		then you need to see if the line begins with "select".
		*/
		else{
			/** 1. it does begin with "select" then you need to use 
			 executeQuery() to execute it on the database.
			 */
			String [] splitString = userInput.split(" ");
			if(splitString[0].equals("select")) {
				/** execute select statements */
				ExecuteStatement.executeSelect(userInput);
			}
			/** 2.  If it does not start with "select" then you should use 
			 executeUpdate() to execute it on the database.
			 */
			else if (userInput.length() != 0) {
				ExecuteStatement.executeUpdate(userInput);
			}
			/** 3. if the command is empty */
			else {
				System.out.println("No command or statement entered");
			}
		}
	}

}
