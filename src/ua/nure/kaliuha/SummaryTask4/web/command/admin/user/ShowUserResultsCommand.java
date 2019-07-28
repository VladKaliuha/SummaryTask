package ua.nure.kaliuha.SummaryTask4.web.command.admin.user;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.Path;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.db.entity.Result;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.Command;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectStreamClass;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShowUserResultsCommand extends Command {

    private static final long serialVersionUID = ObjectStreamClass
            .lookup(ShowUserResultsCommand.class)
            .getSerialVersionUID();

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        List<Result> allResults = DBManager.getInstance().findAllResults();
        LOG.trace("Found in DB: subjectList --> " + allResults);

        Collections.sort(allResults, new Comparator<Result>() {
            public int compare(Result o1, Result o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        request.setAttribute("result", allResults);
        LOG.trace("Set the request attribute: result --> " + allResults);

        LOG.debug("Command finished");

        return Path.PAGE_STUDENT_RESULTS;

    }
}