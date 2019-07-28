package ua.nure.kaliuha.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.Path;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.Command;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectStreamClass;

public class ForwardToCommand extends Command {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(ForwardToCommand.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        String command = request.getParameter("command_name");

        switch (command) {
            case "update_subject": {
                int subjectId = Integer.parseInt(request.getParameter("subject_id"));
                request.setAttribute("subject_id", subjectId);
                return Path.PAGE_EDIT_SUBJECT;
            }
            case "create_subject": {
                return Path.PAGE_CREATE_SUBJECT;
            }
            case "update_test": {
                int testId = Integer.parseInt(request.getParameter("test_id"));
                request.setAttribute("test_id", testId);
                return Path.PAGE_EDIT_TEST;
            }
            case "create_test": {
                return Path.PAGE_CREATE_TEST;
            }
            case "create_question": {
                return Path.PAGE_CREATE_QUESTION;
            }
            case "editQuestion": {
                request.setAttribute("question_id", request.getParameter("question_id"));
                return Path.PAGE_EDIT_QUESTION;
            }
            case "create_answer": {
                request.setAttribute("question_id", request.getParameter("question_id"));
                request.setAttribute("question_num", request.getParameter("question_num"));
                return Path.PAGE_CREATE_ANSWER;
            }
            case "find_user": {
                request.setAttribute("ex", null);
                return Path.PAGE_SEARCH;
            }
            case "changeRole": {
                request.setAttribute("user_id", request.getParameter("user_id"));
                return Path.PAGE_CHANGE_ROLE;
            }

        }

        LOG.debug("Command finished");

        return Path.PAGE_ERROR_PAGE;

    }
}
