package ua.nure.kaliuha.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.db.entity.Answer;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.Command;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteAnswerCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        long test_id = (long) request.getSession().getAttribute("test_id");
        LOG.trace("Request parameter test_id --> " + test_id);

        long questionId = Long.parseLong(request.getParameter("question_id"));
        LOG.trace("Request parameter question_id --> " + questionId);

        int answerId = Integer.parseInt(request.getParameter("answer_id"));
        LOG.trace("Request parameter answer_id --> " + answerId);

        DBManager manager = DBManager.getInstance();
        manager.deleteAnswer(answerId);
        LOG.trace("Delete answer from data source");

        List<Answer> answerList = manager.findAnswersByQuestionId(questionId);
        List<Answer> trueAnswerList = manager.findAnswersByQuestionId(questionId, true);

        int answerType = 1;
        if (answerList.size() == 1) {
            answerType = 3;
        } else if (answerList.size() > 2 && trueAnswerList.size() > 1) {
            answerType = 2;
        } else if (answerList.size() > 1 && trueAnswerList.size() == 1) {
            answerType = 1;
        }

        manager.updateQuestion(answerType, questionId);
        LOG.debug("Command finished");

        return "controller?command=startEditQuestion&test_id=" + test_id;
    }
}
