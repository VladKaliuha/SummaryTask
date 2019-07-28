package ua.nure.kaliuha.SummaryTask4;

public final class Path {

    public static final String PAGE_LOGIN = "/login.jsp";

    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";

    public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";

    /*
     * Admin
     */

    public static final String PAGE_EDIT_TEST = "/WEB-INF/jsp/admin/test/edit_test.jsp";

    /*
     * Student
     */

    public static final String PAGE_START_TEST = "/WEB-INF/jsp/student/test/start_test.jsp";

    public static final String PAGE_FINISH_TEST = "/WEB-INF/jsp/student/test/finish_test.jsp";


    /*
     * Command
     */

    public static final String COMMAND_ADMIN_LIST_TESTS = "controller?command=subjectsList";


    public static final String COMMAND_STUDENT_LIST_TESTS = "controller?command=subjectsList";


    public static final String PAGE_SUBJECT_LIST = "/WEB-INF/jsp/student/subject/subjects_list.jsp";

    public static final String PAGE_TEST_LIST = "/WEB-INF/jsp/student/test/tests_list_by_subject.jsp";

    public static final String PAGE_TESTING_ONE_RIGHT = "/WEB-INF/jsp/student/test/testing_one_right.jsp";
    public static final String PAGE_TESTING_MANY_RIGHT = "/WEB-INF/jsp/student/test/testing_many_right.jsp";
    public static final String PAGE_TESTING_YOUR_ANSWER = "/WEB-INF/jsp/student/test/testing_your_answer.jsp";
    public static final String COMMAND_TESTING = "controller?command=testing";
    public static final String PAGE_USER_CABINET = "WEB-INF/jsp/admin/user/user_cabinet.jsp";
    public static final String PAGE_SUBJECT_LIST_TO_EDIT = "WEB-INF/jsp/admin/subject/subject_list_to_edit.jsp";
    public static final String PAGE_EDIT_SUBJECT = "WEB-INF/jsp/admin/subject/edit_subject.jsp";
    public static final String PAGE_CREATE_SUBJECT = "WEB-INF/jsp/admin/subject/create_subject.jsp";
    public static final String PAGE_TEST_LIST_TO_EDIT = "WEB-INF/jsp/admin/test/test_list_to_edit.jsp";
    public static final String PAGE_CREATE_TEST = "WEB-INF/jsp/admin/test/create_test.jsp";
    public static final String PAGE_QUESTION_LIST_TO_EDIT = "WEB-INF/jsp/admin/question/question_list_to_edit.jsp";
    public static final String PAGE_CREATE_QUESTION = "WEB-INF/jsp/admin/question/create_question.jsp";
    public static final String PAGE_CHANGE_LOCALE = "WEB-INF/jsp/changeLocale.jsp";
    public static final String PAGE_EDIT_QUESTION = "WEB-INF/jsp/admin/question/edit_question.jsp";
    public static final String PAGE_CREATE_ANSWER = "WEB-INF/jsp/admin/answer/create_answer.jsp";
    public static final String PAGE_STUDENT_RESULTS = "WEB-INF/jsp/admin/user/student_results.jsp";
    public static final String PAGE_ALL_USERS = "WEB-INF/jsp/admin/user/users_list.jsp";
    public static final String PAGE_SEARCH = "WEB-INF/jsp/admin/user/search_user.jsp";
    public static final String PAGE_STUDENT_CABINET = "/WEB-INF/jsp/student/user/student_cabinet.jsp";
    public static final String PAGE_CHANGE_ROLE = "WEB-INF/jsp/admin/user/change_role.jsp";
}
