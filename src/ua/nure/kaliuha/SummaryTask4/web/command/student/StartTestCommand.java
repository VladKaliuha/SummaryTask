package ua.nure.kaliuha.SummaryTask4.web.command.student;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.Path;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.db.entity.Question;
import ua.nure.kaliuha.SummaryTask4.db.entity.Test;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.Command;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StartTestCommand extends Command {
    private static final long serialVersionUID = ObjectStreamClass
            .lookup(StartTestCommand.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        int test_id = Integer.parseInt(request.getParameter("test_id"));
        LOG.trace("Request parameter: test_id --> " + test_id);

        Test test = DBManager.getInstance().findTestById(test_id);
        LOG.trace("Found in DB: test --> " + test);

        List<Integer> listUserAnswers = new ArrayList<>(test.getSize());
        for (int i = 0; i < test.getSize(); i++) {
            listUserAnswers.add(0);
        }


        List<Question> questionList = DBManager.getInstance().findQuestionByTestId(test_id);
        LOG.trace("Found in DB: questionList --> " + questionList);
        if (questionList.isEmpty()){
            request.setAttribute("is_empty", "true");
            LOG.trace("Set the request attribute: is_empty --> " + true);
        }else {
            request.setAttribute("is_empty", "false");
            LOG.trace("Set the request attribute: is_empty --> " + false);
        }




        request.getSession().setAttribute("test_size", test.getSize());
        LOG.trace("Set the session attribute: testSize --> " + test.getSize());

        final long ONE_MINUTE_IN_MILLIS=60000;//millisecs

        Calendar date = Calendar.getInstance();
        long time= date.getTimeInMillis()+(test.getTime()*ONE_MINUTE_IN_MILLIS);

        request.getSession().setAttribute("test_time", time);
        LOG.trace("Set the session attribute: test_time --> " + time);

        request.getSession().setAttribute("test_id", test.getId());
        LOG.trace("Set the session attribute: testId --> " + test.getId());

        request.setAttribute("test_name", test.getName());
        LOG.trace("Set the request attribute: testName --> " + test.getName());

        request.setAttribute("test_time", test.getTime());
        LOG.trace("Set the request attribute: testTime --> " + test.getTime());

        request.getSession().setAttribute("listUserAnswers", listUserAnswers);
        LOG.trace("Set the session attribute: listAnswers --> " + listUserAnswers);

        request.getSession().setAttribute("question_num", 0);
        LOG.trace("Set the session attribute: question_num --> " + 0);

        request.getSession().setAttribute("questionList", questionList);
        LOG.trace("Set the session attribute: questionList --> " + questionList);

        LOG.debug("Command finished");
        return Path.PAGE_START_TEST;
    }
}
