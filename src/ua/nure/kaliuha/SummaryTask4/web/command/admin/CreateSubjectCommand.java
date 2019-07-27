package ua.nure.kaliuha.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.Command;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateSubjectCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        String newSubjectName = request.getParameter("new_subject_name");
        LOG.trace("Request parameter new_subject_name --> " + newSubjectName);

        DBManager manager = DBManager.getInstance();
        manager.insertSubject(newSubjectName);
        LOG.trace("Insert new subject into data source");

        LOG.debug("Command finished");

        return "controller?command=subjectsList";
    }
}
