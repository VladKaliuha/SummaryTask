package ua.nure.kaliuha.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.Path;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.db.entity.User;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.ObjectStreamClass;

public class RegistrationCommand extends Command {
    private static final long serialVersionUID = ObjectStreamClass
            .lookup(RegistrationCommand.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        DBManager manager = DBManager.getInstance();

        String forward = Path.PAGE_ERROR_PAGE;

        User insertUser = new User();

        String firstName = request.getParameter("first_name");
        LOG.trace("Request parameter: firstName --> " + firstName);
        insertUser.setFirstName(firstName);
        String lastName = request.getParameter("last_name");
        LOG.trace("Request parameter: lastName --> " + lastName);
        insertUser.setLastName(lastName);
        String login = request.getParameter("login");
        LOG.trace("Request parameter: login --> " + login);
        insertUser.setLogin(login);
        String email = request.getParameter("email");
        LOG.trace("Request parameter: email --> " + email);
        insertUser.setEmail(email);
        String password = request.getParameter("password");
        LOG.trace("Request parameter: password --> " + password);
        insertUser.setPassword(password);
        String male = request.getParameter("male");
        LOG.trace("Request parameter: male --> " + male);
        insertUser.setMale(male);

        User user = manager.findUserByLogin(login);
        LOG.trace("Found in DB: user --> " + user);
        if (user != null && login.equals(user.getLogin())) {
            throw new AppException("User already exists");
        }

        user = manager.findUserByEmail(email);
        LOG.trace("Found in DB: user --> " + user);
        if (user != null && email.equals(user.getEmail())) {
            throw new AppException("User already exists");
        }

        manager.insertUser(insertUser);
        LOG.trace("Insert into DB: user --> "+ insertUser);

        forward = Path.PAGE_LOGIN;
        LOG.debug("Command finished");

        return forward;
    }
}
