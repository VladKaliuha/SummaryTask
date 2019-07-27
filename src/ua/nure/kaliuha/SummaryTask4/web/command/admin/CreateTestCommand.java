package ua.nure.kaliuha.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.db.entity.Test;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.Command;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CreateTestCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        String newTestName = request.getParameter("new_test_name");
        LOG.trace("Request parameter new_subject_name --> " + newTestName);

        int subjectId = (int) request.getSession().getAttribute("subject_id");
        LOG.trace("Request parameter subject_id --> " + subjectId);

        int complexity = Integer.parseInt(request.getParameter("complexity"));
        LOG.trace("Request parameter subject_id --> " + subjectId);

        int time = Integer.parseInt(request.getParameter("test_time"));
        LOG.trace("Request parameter subject_id --> " + subjectId);

        Test test = new Test();

        test.setSubject_id(subjectId);
        test.setComplexity(complexity);
        test.setTime(time);
        test.setName(newTestName);

        DBManager manager = DBManager.getInstance();
        manager.insertTest(test);
        LOG.trace("Insert new test into data source");

        LOG.debug("Command finished");

        return "controller?command=testsListBySubject&subject_id=" + subjectId;
    }
}
