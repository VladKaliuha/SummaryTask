package ua.nure.kaliuha.SummaryTask4.web.command.admin.test;

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

public class EditTestCommand extends Command {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(EditTestCommand.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        int subjectId = (int) request.getSession().getAttribute("subject_id");
        LOG.trace("Request parameter subject_id --> " + subjectId);

        String newTestName = request.getParameter("new_test_name");
        LOG.trace("Request parameter new_subject_name --> " + newTestName);

        int complexity = Integer.parseInt(request.getParameter("complexity"));
        LOG.trace("Request parameter complexity --> " + complexity);

        int time = Integer.parseInt(request.getParameter("test_time"));
        LOG.trace("Request parameter test_time --> " + time);

        int testId = Integer.parseInt(request.getParameter("test_id"));

        DBManager manager = DBManager.getInstance();
        manager.updateTest(newTestName, complexity, time, testId);
        LOG.trace("");

        LOG.debug("Command finished");

        return "controller?command=testsListBySubject";
    }
}
