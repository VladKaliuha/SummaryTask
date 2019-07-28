package ua.nure.kaliuha.SummaryTask4.web;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectStreamClass;


public class Controller extends HttpServlet {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(Controller.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(Controller.class);

    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            process(req, resp, "get");
        } catch (AppException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            process(req, resp, "post");
        } catch (AppException e) {
            e.printStackTrace();
        }
    }

    private void process(HttpServletRequest req, HttpServletResponse resp, String method)
            throws IOException, ServletException, AppException {
        String command = req.getParameter("command");

        LOG.debug("Executing command " + command);
        String path = "/" + CommandContainer.get(command).execute(req, resp);

        if (method.equals("post")) {
            LOG.debug("Redirecting to " + path);
            resp.sendRedirect(req.getContextPath() + path);
        } else {
            LOG.debug("Forward to " + path);
            req.getRequestDispatcher(path).forward(req, resp);
        }
    }

}