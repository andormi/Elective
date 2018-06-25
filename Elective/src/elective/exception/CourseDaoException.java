package elective.exception;

public class CourseDaoException extends AppException {

	private static final long serialVersionUID = 7269673516283218941L;

	public CourseDaoException() {
		super();
	}
	
	public CourseDaoException(String message) {
		super(message);
	}

	public CourseDaoException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
