package ua.nure.kaliuha.SummaryTask4.web.command.admin.user;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.Path;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.db.entity.Result;
import ua.nure.kaliuha.SummaryTask4.db.entity.User;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.Command;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectStreamClass;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OpenUserCabinetCommand extends Command {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(OpenUserCabinetCommand.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");
        DBManager manager = DBManager.getInstance();

        long userId = Long.parseLong(request.getParameter("user_id"));

        User user = manager.findUser(userId);
        LOG.trace("Find into data source: user --> " + user);
        request.setAttribute("user_id", userId);
        request.setAttribute("useR", user);

        List<Result> result = manager.findUserResult(user.getId());
        if (result != null) {
            Collections.sort(result, new Comparator<Result>() {
                public int compare(Result o1, Result o2) {
                    return o2.getDate().compareTo(o1.getDate());
                }
            });

            request.setAttribute("result", result);
        } else {
            request.setAttribute("result", null);
        }
        return Path.PAGE_USER_CABINET;
    }
}
