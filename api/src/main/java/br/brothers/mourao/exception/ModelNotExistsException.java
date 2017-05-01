package br.brothers.mourao.exception;

public class ModelNotExistsException extends Exception {

    public ModelNotExistsException() {
        super();
    }

    public ModelNotExistsException(String message) {
        super(message);
    }

    public ModelNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelNotExistsException(Throwable cause) {
        super(cause);
    }

    protected ModelNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
