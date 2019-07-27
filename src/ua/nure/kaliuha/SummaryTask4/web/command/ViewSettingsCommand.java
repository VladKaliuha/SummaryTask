package ua.nure.kaliuha.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.Path;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectStreamClass;

public class ViewSettingsCommand extends Command {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(ViewSettingsCommand.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        if (request.getParameter("command_name") != null) {
            String command = request.getParameter("command_name");
            if ("changeLocale".equals(command)) {
                return Path.PAGE_CHANGE_LOCALE;
            }
        }

        LOG.debug("Command finished");
        return Path.PAGE_SETTINGS;
    }
}
