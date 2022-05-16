package capstone.hospital.exception;

public class JoinException extends IllegalStateException {
    public JoinException() {
        super();
    }

    public JoinException(String s) {
        super(s);
    }

    public JoinException(String message, Throwable cause) {
        super(message, cause);
    }

    public JoinException(Throwable cause) {
        super(cause);
    }
}
