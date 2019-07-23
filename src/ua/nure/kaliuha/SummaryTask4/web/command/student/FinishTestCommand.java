package ua.nure.kaliuha.SummaryTask4.web.command.student;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.Path;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.db.entity.Test;
import ua.nure.kaliuha.SummaryTask4.db.entity.User;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.Command;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FinishTestCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();
        LOG.trace("Get session: --> " + session);

        User user = (User) session.getAttribute("user");
        LOG.trace("Session attribute: user --> " + user);

        List<Integer> listUserAnswers = (List<Integer>) session.getAttribute("listUserAnswers");
        LOG.trace("Session attribute: listUserAnswers --> " + listUserAnswers);

        float result = 0;
        for (Integer answer : listUserAnswers) {
            if (answer == 1) {
                result++;
            }
        }

        long testId = (long) session.getAttribute("test_id");

        Test test = DBManager.getInstance().findTestById(testId);

        result = (result / test.getSize()) * 100;

        int finalResult = (int) result;

        DBManager.getInstance().insertResult(user, testId, finalResult);
        LOG.trace("Insert into DB: result --> " + finalResult + testId + user);

        request.setAttribute("test_name", test.getName());
        LOG.trace("Set the request attribute: test_name --> " + test.getName());

        request.setAttribute("result", finalResult);
        LOG.trace("Set the request attribute: result --> " + finalResult);

        LOG.debug("Command finished");
        return Path.PAGE_FINISH_TEST;
    }
}
