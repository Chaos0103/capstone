package capstone.hospital.exception;

public class AppointmentException extends IllegalStateException {

    public AppointmentException() {
        super();
    }

    public AppointmentException(String s) {
        super(s);
    }

    public AppointmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppointmentException(Throwable cause) {
        super(cause);
    }
}
