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

public class EditQuestionCommand extends Command {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(EditQuestionCommand.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        String questionText = request.getParameter("question_text");
        LOG.trace("Request parameter question_text --> " + questionText);

        long question_id = Long.parseLong(request.getParameter("question_id"));
        LOG.trace("Request parameter question_id --> " + question_id);

        long test_id = (long) request.getSession().getAttribute("test_id");
        LOG.trace("Request parameter test_id --> " + test_id);

        String answerType = request.getParameter("answer_type");
        LOG.trace("Request parameter answer_type --> " + answerType);
        DBManager manager = DBManager.getInstance();


        Question question = new Question();
        question.setTestId(test_id);
        question.setText(questionText);
        question.setId(question_id);

        manager.updateQuestion(question);
        LOG.trace("Update into data source");

        LOG.debug("Command finished");

        return "controller?command=startEditQuestion&test_id=" + test_id;
    }
}
