package br.brothers.mourao.exception;

public class CannotInstantiateException extends Exception {

    public CannotInstantiateException() {
        super();
    }

    public CannotInstantiateException(String message) {
        super(message);
    }

    public CannotInstantiateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotInstantiateException(Throwable cause) {
        super(cause);
    }

    protected CannotInstantiateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
