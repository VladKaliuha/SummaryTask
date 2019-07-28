package ua.nure.kaliuha.SummaryTask4.web.command;

import ua.nure.kaliuha.SummaryTask4.exeption.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectStreamClass;
import java.io.Serializable;

public abstract class Command implements Serializable {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(Command.class)
            .getSerialVersionUID();


    public abstract String execute(HttpServletRequest request,
                                   HttpServletResponse response)
            throws IOException, ServletException, AppException;

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
