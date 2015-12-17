package database;

@SuppressWarnings("serial")
class DatabaseException extends RuntimeException {
	
	public DatabaseException() {
		super();
	}
	
	public DatabaseException(String message) {
		super(message);
	}

	public DatabaseException(Throwable cause) {
		super(cause);
	}
	
	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

}
