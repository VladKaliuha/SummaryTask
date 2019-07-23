package ua.nure.kaliuha.SummaryTask4.web.command.student;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.Path;
import ua.nure.kaliuha.SummaryTask4.db.AnswerType;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.db.entity.Answer;
import ua.nure.kaliuha.SummaryTask4.db.entity.Question;
import ua.nure.kaliuha.SummaryTask4.db.entity.Test;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.Command;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TestingCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();
        LOG.trace("Get session: --> " + session);

        long test_id = (long) session.getAttribute("test_id");
        LOG.trace("Request parameter: test_id --> " + test_id);

        int question_num = (int) session.getAttribute("question_num");
        LOG.trace("Request parameter: question_num --> " + question_num);

        Test test = DBManager.getInstance().findTestById(test_id);
        LOG.trace("Found in DB: test --> " + test);

        List<Question> questionList = (List<Question>) session.getAttribute("questionList");
        LOG.trace("Session attribute: questionList --> " + questionList);


        Question question = questionList.get(question_num);

        List<Answer> answerList = DBManager.getInstance().findAnswersByQuestionId(question.getId());
        LOG.trace("Session attribute: questionList --> " + questionList);

        AnswerType answerType = AnswerType.getAnswerType(question);

        request.setAttribute("question_text", question.getText());
        LOG.trace("Set the request attribute: question_text --> " + question.getText());

        session.setAttribute("question_num", question_num);
        LOG.trace("Set the session attribute: question_num --> " + question_num);

        session.setAttribute("question_id", question.getId());
        LOG.trace("Set the session attribute: question_id --> " + question.getId());

        request.setAttribute("answer_list", answerList);
        LOG.trace("Set the request attribute: answer_list --> " + answerList);

        LOG.debug("Command finished");
        switch (answerType) {
            case oneTrue:
                return Path.PAGE_TESTING_ONE_RIGHT;

            case manyTrue:
                return Path.PAGE_TESTING_MANY_RIGHT;

            case yourAnswer:
                return Path.PAGE_TESTING_YOUR_ANSWER;

            default:
                return Path.PAGE_ERROR_PAGE;
        }
    }
}
