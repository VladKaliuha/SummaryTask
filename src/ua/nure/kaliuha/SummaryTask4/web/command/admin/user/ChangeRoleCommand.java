package ua.nure.kaliuha.SummaryTask4.web.command.admin.user;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.Command;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectStreamClass;

public class ChangeRoleCommand extends Command {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(ChangeRoleCommand.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        int userId = Integer.parseInt(request.getParameter("user_id"));
        LOG.trace("Request parameter user_id --> " + userId);

        String role = request.getParameter("role");
        int roleId = 1;

        switch (role) {
            case "Student":
                break;
            case "Admin":
                roleId = 2;
                break;
            case "Blocked":
                roleId = 3;
                break;
        }

        DBManager manager = DBManager.getInstance();
        manager.changeUserRole(userId, roleId);
        LOG.trace("");

        LOG.debug("Command finished");

        return "controller?command=openUserCabinet&user_id=" + userId;
    }
}
