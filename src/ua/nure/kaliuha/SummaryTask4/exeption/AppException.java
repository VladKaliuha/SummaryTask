package ua.nure.kaliuha.SummaryTask4.exeption;

import java.io.ObjectStreamClass;

public class AppException extends Exception {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(AppException.class)
            .getSerialVersionUID();

    public AppException() {
        super();
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }
}
