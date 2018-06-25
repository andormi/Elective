package elective.exception;

public class AppException extends Exception {

	private static final long serialVersionUID = 6531605394375867438L;

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}

}
