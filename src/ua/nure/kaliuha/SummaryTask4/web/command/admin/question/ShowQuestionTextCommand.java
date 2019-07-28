package ua.nure.kaliuha.SummaryTask4.web.command.admin.question;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.Path;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.db.entity.Answer;
import ua.nure.kaliuha.SummaryTask4.db.entity.Question;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.Command;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.ObjectStreamClass;
import java.util.List;

public class ShowQuestionTextCommand extends Command {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(ShowQuestionTextCommand.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession();
        LOG.trace("Get session: --> " + session);

        long test_id = (long) session.getAttribute("test_id");
        LOG.trace("Request parameter: test_id --> " + test_id);

        int question_num = 0;
        if (request.getParameter("question_num") != null) {
            question_num = Integer.parseInt(request.getParameter("question_num")) - 1;
            LOG.trace("Request parameter: question_num --> " + question_num);
        }

        List<Question> questionList = (List<Question>) session.getAttribute("questionList");
        LOG.trace("Session attribute: questionList --> " + questionList);


        if (questionList != null) {
            if (question_num >= questionList.size()) {
                question_num = 0;
            }
            Question question = questionList.get(question_num);

            List<Answer> answerList = DBManager.getInstance().findAnswersByQuestionId(question.getId());
            LOG.trace("Session attribute: questionList --> " + questionList);

            request.setAttribute("question_num", question_num+1);
            LOG.trace("Set the session attribute: question_num --> " + question_num+1);

            request.setAttribute("question", question);
            LOG.trace("Set the request attribute: question_text --> " + question.getText());

            request.setAttribute("answer_list", answerList);
            LOG.trace("Set the request attribute: answer_list --> " + answerList);

            LOG.debug("Command finished");

        } else {
            request.getSession().setAttribute("questionList", null);
            LOG.trace("Set the session attribute: questionList --> " + null);
        }
        return Path.PAGE_QUESTION_LIST_TO_EDIT;
    }
}
