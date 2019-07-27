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

public class DeleteTestCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        int testId = Integer.parseInt(request.getParameter("test_id"));
        LOG.trace("Request parameter test_id --> " + testId);

        DBManager manager = DBManager.getInstance();
        manager.deleteTest(testId);
        LOG.trace("Delete test from data source");

        LOG.debug("Command finished");

        return "controller?command=subjectsList";
    }
}
