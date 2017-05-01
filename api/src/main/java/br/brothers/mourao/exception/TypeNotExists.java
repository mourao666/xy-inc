package br.brothers.mourao.exception;

public class TypeNotExists extends Exception {

    public TypeNotExists() {
        super();
    }

    public TypeNotExists(String message) {
        super(message);
    }

    public TypeNotExists(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeNotExists(Throwable cause) {
        super(cause);
    }

    protected TypeNotExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
