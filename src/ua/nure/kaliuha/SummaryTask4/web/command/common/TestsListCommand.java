package ua.nure.kaliuha.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.Path;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.db.Role;
import ua.nure.kaliuha.SummaryTask4.db.entity.Test;
import ua.nure.kaliuha.SummaryTask4.exeption.AppException;
import ua.nure.kaliuha.SummaryTask4.web.command.Command;
import ua.nure.kaliuha.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestsListCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

        Role role = (Role) request.getSession().getAttribute("userRole");

        int subject_id = Integer.parseInt(request.getParameter("subject_id"));
        LOG.trace("Request parameter: subject_id --> " + subject_id);

        String subject_name = request.getParameter("subject_name");
        LOG.trace("Request parameter: subject_name --> " + subject_name);

        String sortBy = request.getParameter("sort_by");
        LOG.trace("Request parameter: sort_by --> " + sortBy);

        // get menu items list
        List<Test> testsList = DBManager.getInstance().findTestBySubjectId(subject_id);
        LOG.trace("Found in DB: testsList --> " + testsList);

        request.getSession().setAttribute("doneSortByName", "false");
        request.getSession().setAttribute("doneSortByComplexity", "false");
        request.getSession().setAttribute("doneSortBySize", "false");

        if (sortBy != null && !sortBy.isEmpty()) {
            switch (sortBy) {
                case "A->Z":
//                  sort tests by name from A to Z
                    request.getSession().setAttribute("doneSortByName", "true");
                    Collections.sort(testsList, new Comparator<Test>() {
                        public int compare(Test o1, Test o2) {
                            return o1.getName().compareTo(o2.getName());
                        }
                    });
                    break;
                case "Z->A":
//                  sort tests by name from A to Z
                    request.getSession().setAttribute("doneSortByName", "false");
                    Collections.sort(testsList, new Comparator<Test>() {
                        public int compare(Test o1, Test o2) {
                            return o2.getName().compareTo(o1.getName());
                        }
                    });
                    break;
                case "E->H":
                    request.getSession().setAttribute("doneSortByComplexity", "false");
//                  sort tests by complexity from easy to hard
                    Collections.sort(testsList, new Comparator<Test>() {
                        public int compare(Test o1, Test o2) {
                            return o1.getComplexity() - o2.getComplexity();
                        }
                    });
                    break;
                case "H->E":
                    request.getSession().setAttribute("doneSortByComplexity", "true");
//                  sort tests by complexity from hard to easy
                    Collections.sort(testsList, new Comparator<Test>() {
                        public int compare(Test o1, Test o2) {
                            return o2.getComplexity() - o1.getComplexity();
                        }
                    });
                    break;

                case "S->B":
//                  sort tests by test size from small number of questions to big
                    request.getSession().setAttribute("doneSortBySize", "true");
                    Collections.sort(testsList, new Comparator<Test>() {
                        public int compare(Test o1, Test o2) {
                            return o1.getSize() - o2.getSize();
                        }
                    });
                    break;
                case "B->S":
//               sort tests by test size from big number of questions to small
                    request.getSession().setAttribute("doneSortBySize", "false");
                    Collections.sort(testsList, new Comparator<Test>() {
                        public int compare(Test o1, Test o2) {
                            return o2.getSize() - o1.getSize();
                        }
                    });
                    break;
                default:
                    break;
            }
        }

        request.getSession().setAttribute("subject_id", subject_id);
        request.setAttribute("subject_name", subject_name);
        // put menu items list to the request
        request.setAttribute("testsList", testsList);
        LOG.trace("Set the request attribute: testsList --> " + testsList);

        LOG.debug("Command finished");
        switch (role) {
            case ADMIN:
                return Path.PAGE_TEST_LIST_TO_EDIT;
            case STUDENT:
                return Path.PAGE_TEST_LIST;
            default:
                return Path.PAGE_ERROR_PAGE;
        }
    }
}
