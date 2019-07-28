package ua.nure.kaliuha.SummaryTask4.web.command.admin.answer;

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
import java.io.ObjectStreamClass;
import java.util.List;

public class CreateAnswerCommand extends Command {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(CreateAnswerCommand.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        long questionId = Long.parseLong(request.getParameter("question_id"));
        LOG.trace("Request parameter question_id --> " + questionId);

        int questionNum = Integer.parseInt(request.getParameter("question_num"));
        LOG.trace("Request parameter question_id --> " + questionId);

        String answerText = request.getParameter("answer_text");
        LOG.trace("Request parameter answer_text --> " + answerText);

        String correct = request.getParameter("correct");
        LOG.trace("Request parameter correct --> " + correct);

        boolean answerCorrect;
        switch (correct) {
            case "+":
                answerCorrect = true;
                break;
            default:
                answerCorrect = false;
        }

        Answer answer = new Answer();
        answer.setCorrect(answerCorrect);
        answer.setText(answerText);
        answer.setQuestionId(questionId);

        DBManager manager = DBManager.getInstance();
        manager.insertAnswer(answer);
        LOG.trace("Insert new answer into data source");


        List<Answer> answerList = manager.findAnswersByQuestionId(questionId);
        List<Answer> trueAnswerList = manager.findAnswersByQuestionId(questionId, true);

        int answerType = 1;
        if (answerList.size() == 1) {
            answerType = 3;
        } else if (answerList.size() > 1 && trueAnswerList.size() == 1) {
            answerType = 1;
        } else if (answerList.size() > 2 && trueAnswerList.size() > 1) {
            answerType = 2;
        }

        manager.updateQuestion(answerType, questionId);

        return "controller?command=showQuestion&question_num="+questionNum;
    }
}
