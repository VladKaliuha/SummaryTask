package ua.nure.kaliuha.SummaryTask4.web.command.admin.question;

import org.apache.log4j.Logger;
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
import java.util.List;

public class StartEditQuestionCommand extends Command {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(StartEditQuestionCommand.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");


        long test_id;
        if (request.getSession().getAttribute("test_id") != null) {
            test_id = (long) request.getSession().getAttribute("test_id");
        } else {
            test_id = Long.parseLong(request.getParameter("test_id"));
            LOG.trace("Request parameter: test_id --> " + test_id);
        }
        Test test = DBManager.getInstance().findTestById(test_id);
        LOG.trace("Found in DB: test --> " + test);

        request.getSession().setAttribute("test_size", test.getSize());
        LOG.trace("Set the session attribute: testSize --> " + test.getSize());

        List<Integer> listUserAnswers = new ArrayList<>(test.getSize());
        for (int i = 0; i < test.getSize(); i++) {
            listUserAnswers.add(0);
        }

        request.getSession().setAttribute("listUserAnswers", listUserAnswers);
        LOG.trace("Set the session attribute: listUserAnswers --> " + listUserAnswers);


        if (test.getSize() != 0) {
            List<Question> questionList = DBManager.getInstance().findQuestionByTestId(test_id);
            LOG.trace("Found in DB: questionList --> " + questionList);

            request.getSession().setAttribute("questionList", questionList);
            LOG.trace("Set the session attribute: questionList --> " + questionList);

            request.setAttribute("question_num", 0);
            LOG.trace("Set the session attribute: question_num --> " + 0);
        }

        request.getSession().setAttribute("test_id", test.getId());
        LOG.trace("Set the session attribute: testId --> " + test.getId());

        LOG.debug("Command finished");
        return "controller?command=showQuestion";
    }
}
