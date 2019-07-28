package ua.nure.kaliuha.SummaryTask4.web.command.admin.question;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.Command;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectStreamClass;

public class DeleteQuestionCommand extends Command {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(DeleteQuestionCommand.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        long test_id = (long) request.getSession().getAttribute("test_id");
        LOG.trace("Request parameter test_id --> " + test_id);

        int questionId = Integer.parseInt(request.getParameter("question_id"));
        LOG.trace("Request parameter question_id --> " + questionId);

        DBManager manager = DBManager.getInstance();
        manager.deleteQuestion(questionId);
        LOG.trace("Delete question from data source");

        LOG.debug("Command finished");

        return "controller?command=startEditQuestion&test_id=" + test_id;
    }
}
