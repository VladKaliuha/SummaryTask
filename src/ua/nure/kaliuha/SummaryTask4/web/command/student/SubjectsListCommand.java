package ua.nure.kaliuha.SummaryTask4.web.command.student;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.Path;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.db.entity.Subject;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.Command;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SubjectsListCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        List<Subject> subjectList = DBManager.getInstance().findSubjects();
        LOG.trace("Found in DB: subjectList --> " + subjectList);

        request.setAttribute("subjectList", subjectList);
        LOG.trace("Set the request attribute: subjectList --> " + subjectList);

        LOG.debug("Command finished");
        return Path.PAGE_SUBJECT_LIST;
    }
}
