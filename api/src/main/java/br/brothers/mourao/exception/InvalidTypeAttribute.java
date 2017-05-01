package br.brothers.mourao.exception;

public class InvalidTypeAttribute extends Exception {

    public InvalidTypeAttribute() {
        super();
    }

    public InvalidTypeAttribute(String message) {
        super(message);
    }

    public InvalidTypeAttribute(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTypeAttribute(Throwable cause) {
        super(cause);
    }

    protected InvalidTypeAttribute(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
