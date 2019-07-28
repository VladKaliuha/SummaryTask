package ua.nure.kaliuha.SummaryTask4.web.command.admin.subject;

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

public class EditSubjectCommand extends Command {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(EditSubjectCommand.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        int subjectId = Integer.parseInt(request.getParameter("subject_id"));
        LOG.trace("Request parameter subject_id --> " + subjectId);

        String newSubjectName = request.getParameter("new_subject_name");
        LOG.trace("Request parameter new_subject_name --> " + newSubjectName);

        DBManager manager = DBManager.getInstance();
        manager.updateSubjectName(subjectId, newSubjectName);
        LOG.trace("");

        LOG.debug("Command finished");

        return "controller?command=subjectsList";
    }
}
