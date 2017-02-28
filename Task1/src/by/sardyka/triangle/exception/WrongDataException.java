package by.sardyka.triangle.exception;

public class WrongDataException extends Exception {

	private static final long serialVersionUID = 1L;

	public WrongDataException() {
		super();
	}

	public WrongDataException(String message, Exception e) {
		super(message, e);
	}

	public WrongDataException(String message) {
		super(message);
	}

	public WrongDataException(Exception e) {
		super(e);
	}
}
