package ua.nure.kaliuha.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.Path;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.db.Role;
import ua.nure.kaliuha.SummaryTask4.db.entity.User;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.ObjectStreamClass;

public class LoginCommand extends Command {
    private static final long serialVersionUID = ObjectStreamClass
            .lookup(LoginCommand.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession();
        String errorString;

        String forward = Path.PAGE_LOGIN;

        DBManager manager = DBManager.getInstance();

        String login = request.getParameter("login");
        LOG.trace("Request parameter: loging --> " + login);

        String password = request.getParameter("password");

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorString = "Login/password cannot be empty";
            request.setAttribute("errorString", errorString);
        } else {
            User user = manager.findUserByLogin(login);
            LOG.trace("Found in DB: user --> " + user);

            if (user == null || !password.equals(user.getPassword())) {
                errorString = "Please check your login/password";
                request.setAttribute("errorString", errorString);
            } else {
                Role userRole = Role.getRole(user);
                System.out.println(userRole);
                LOG.trace("userRole --> " + userRole);

                if (userRole == Role.ADMIN) {
                    forward = Path.COMMAND_ADMIN_LIST_TESTS;
                }

                if (userRole == Role.STUDENT) {
                    forward = Path.COMMAND_STUDENT_LIST_TESTS;
                }

                session.setAttribute("user", user);
                LOG.trace("Set the session attribute: user --> " + user);
                session.setAttribute("userRole", userRole);
                LOG.trace("Set the session attribute: userRole --> " + userRole);

                LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());
            }
        }

        LOG.debug("Command finished");
        return forward;
    }


}
