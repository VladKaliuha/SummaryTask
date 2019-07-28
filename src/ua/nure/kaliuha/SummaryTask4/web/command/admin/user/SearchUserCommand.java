package ua.nure.kaliuha.SummaryTask4.web.command.admin.user;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.Path;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.db.entity.User;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.Command;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.List;

public class SearchUserCommand extends Command {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(SearchUserCommand.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        String login = request.getParameter("user_login");
        LOG.trace("Request parameter: login --> " + login);


        User user = DBManager.getInstance().findUserByLogin(login);
        LOG.trace("Found in DB: allUsers --> " + user);

        if (user!=null) {
            List<User> users = new ArrayList<>();
            users.add(user);

            request.setAttribute("users", users);
            LOG.trace("Set the request attribute: result --> " + users);
        }else {
            request.setAttribute("ex", "Cannot find user with login: "+login);
            return Path.PAGE_SEARCH;
        }
        LOG.debug("Command finished");

        return Path.PAGE_ALL_USERS;

    }
}
