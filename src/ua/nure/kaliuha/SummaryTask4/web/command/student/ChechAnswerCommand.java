package ua.nure.kaliuha.SummaryTask4.web.command.student;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.Path;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.db.entity.Answer;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.Command;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ChechAnswerCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");


        HttpSession session = request.getSession();
        LOG.trace("Get session: --> " + session);
        int questionNum;

        if (request.getParameter("question_num") != null) {
            questionNum = Integer.parseInt(request.getParameter("question_num"));
            LOG.trace("Request parameter: user_answer --> " + questionNum);

            session.setAttribute("question_num", questionNum - 1);
            LOG.trace("Set the session attribute: question_num --> " + questionNum);

            return Path.COMMAND_TESTING;
        }


        List<Integer> listUserAnswers = (List<Integer>) session.getAttribute("listUserAnswers");
        LOG.trace("Session attribute: listUserAnswers --> " + listUserAnswers);

        long questionId = (long) session.getAttribute("question_id");
        LOG.trace("Session attribute: question_id --> " + questionId);

        questionNum = ((int) session.getAttribute("question_num"));
        LOG.trace("Session attribute: question_num --> " + questionNum);

        List<Answer> trueAnswerList = DBManager.getInstance().findTrueAnswersByQuestionId(questionId);
        LOG.trace("Found in DB: trueAnswerList --> " + trueAnswerList);

        String userAnswer = request.getParameter("user_answer");
        LOG.trace("Request parameter: user_answer --> " + userAnswer);

        int testSize = (int) session.getAttribute("test_size");
        LOG.trace("Session attribute: testSize --> " + testSize);


        int checkTrueAnswer = 0;
        for (Answer answer : trueAnswerList) {
            if (answer.getText().equals(userAnswer)) {
                checkTrueAnswer++;
            }
        }
        if (checkTrueAnswer == trueAnswerList.size()) {
            listUserAnswers.set(questionNum, 1);
        } else {
            listUserAnswers.set(questionNum, -1);
        }

        if (questionNum >= testSize - 1) {
            return "controller?command=finishTest";
        }

        session.setAttribute("question_num", (questionNum + 1));
        LOG.trace("Set the session attribute: question_num --> " + questionNum);

        LOG.debug("Command finished");
        return Path.COMMAND_TESTING;
    }
}
