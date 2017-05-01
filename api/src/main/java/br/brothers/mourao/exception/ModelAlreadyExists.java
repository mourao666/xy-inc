package br.brothers.mourao.exception;

public class ModelAlreadyExists extends Exception {

    public ModelAlreadyExists() {
        super();
    }

    public ModelAlreadyExists(String message) {
        super(message);
    }

    public ModelAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelAlreadyExists(Throwable cause) {
        super(cause);
    }

    protected ModelAlreadyExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
