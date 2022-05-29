package capstone.hospital.exception;

public class SearchException extends IllegalStateException {

    public SearchException() {
        super();
    }

    public SearchException(String s) {
        super(s);
    }

    public SearchException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchException(Throwable cause) {
        super(cause);
    }
}
