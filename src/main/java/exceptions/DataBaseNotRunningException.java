package exceptions;

import org.hibernate.exception.JDBCConnectionException;

public class DataBaseNotRunningException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7351621479980262659L;

	public DataBaseNotRunningException(JDBCConnectionException e) {
		super(e);
		System.out.println("Database not running. Please start the database... Exiting for now");
	}
}
