package ua.nure.kaliuha.SummaryTask4.web.command.admin.question;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.db.entity.Question;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.Command;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectStreamClass;
import java.util.List;

public class CreateQuestionCommand extends Command {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(CreateQuestionCommand.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        String questionText = request.getParameter("question_text");
        LOG.trace("Request parameter question_text --> " + questionText);

        long test_id = (long) request.getSession().getAttribute("test_id");
        LOG.trace("Request parameter test_id --> " + test_id);



        Question question = new Question();
        question.setTestId(test_id);
        question.setText(questionText);

        DBManager manager = DBManager.getInstance();
        manager.insertQuestion(question);
        LOG.trace("Insert new question into data source");

        List<Question> questionList = DBManager.getInstance().findQuestionByTestId(test_id);
        LOG.trace("Found in DB: questionList --> " + questionList);

        request.getSession().setAttribute("questionList", questionList);
        LOG.trace("Set the session attribute: questionList --> " + questionList);
        LOG.debug("Command finished");

        return "controller?command=startEditQuestion&test_id=" + test_id;
    }
}
