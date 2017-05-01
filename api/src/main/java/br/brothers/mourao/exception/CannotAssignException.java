package br.brothers.mourao.exception;

public class CannotAssignException extends Exception {

    public CannotAssignException() {
        super();
    }

    public CannotAssignException(String message) {
        super(message);
    }

    public CannotAssignException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotAssignException(Throwable cause) {
        super(cause);
    }

    protected CannotAssignException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
