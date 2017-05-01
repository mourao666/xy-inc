package br.brothers.mourao.exception;

public class TypeNotExistsException extends Exception {

    public TypeNotExistsException() {
        super();
    }

    public TypeNotExistsException(String message) {
        super(message);
    }

    public TypeNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeNotExistsException(Throwable cause) {
        super(cause);
    }

    protected TypeNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
