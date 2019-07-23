package ua.nure.kaliuha.SummaryTask4.exeption;

import java.io.ObjectStreamClass;

public class DBException extends AppException {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(DBException.class)
            .getSerialVersionUID();

    public DBException() {
        super();
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

}
