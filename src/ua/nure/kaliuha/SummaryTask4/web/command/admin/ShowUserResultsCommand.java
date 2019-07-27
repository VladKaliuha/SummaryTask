package ua.nure.kaliuha.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.Path;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.db.entity.Result;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.Command;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowUserResultsCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        List<Result> allResults = DBManager.getInstance().findAllResults();
        LOG.trace("Found in DB: subjectList --> " + allResults);

        request.setAttribute("result", allResults);
        LOG.trace("Set the request attribute: result --> " + allResults);

        LOG.debug("Command finished");

        return Path.PAGE_SUBJECT_STUDENT_RESULTS;

    }
}