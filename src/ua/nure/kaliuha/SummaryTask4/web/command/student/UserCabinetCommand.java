package ua.nure.kaliuha.SummaryTask4.web.command.student;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.ObjectStreamClass;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserCabinetCommand extends Command {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(UserCabinetCommand.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();
        LOG.trace("Get session: --> " + session);

        User user = (User) session.getAttribute("user");
        LOG.trace("Session attribute: user --> " + user);

        DBManager manager = DBManager.getInstance();

        List<Result> result = manager.findUserResult(user.getId());
        Collections.sort(result, new Comparator<Result>() {
            public int compare(Result o1, Result o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        request.setAttribute("result", result);

        return Path.PAGE_STUDENT_CABINET;
    }
}
