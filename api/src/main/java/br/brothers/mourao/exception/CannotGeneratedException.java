package br.brothers.mourao.exception;

public class CannotGeneratedException extends Exception {

    public CannotGeneratedException() {
        super();
    }

    public CannotGeneratedException(String message) {
        super(message);
    }

    public CannotGeneratedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotGeneratedException(Throwable cause) {
        super(cause);
    }

    protected CannotGeneratedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
