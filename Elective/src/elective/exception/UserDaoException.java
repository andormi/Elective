package elective.exception;

public class UserDaoException extends AppException {

	private static final long serialVersionUID = 7537760742944432500L;

	public UserDaoException() {
		super();
	}
	
	public UserDaoException(String message) {
		super(message);
	}

	public UserDaoException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
