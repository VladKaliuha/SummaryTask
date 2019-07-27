package ua.nure.kaliuha.SummaryTask4.db;


import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.db.entity.*;
import ua.nure.kaliuha.SummaryTask4.exeption.DBException;
import ua.nure.kaliuha.SummaryTask4.exeption.Messages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);


    private static DBManager instance;

    public static synchronized DBManager getInstance() throws DBException {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() throws DBException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            // ST4DB - the name of data source
            ds = (DataSource) envContext.lookup("jdbc/ST4DB");
            LOG.trace("Data source ==> " + ds);
        } catch (NamingException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }

    private DataSource ds;

    public Connection getConnection() throws DBException {
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = ds.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
        }
        return con;
    }

    private void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
            }
        }
    }

    /**
     * Closes a statement object.
     */
    private void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
            }
        }
    }

    /**
     * Closes a result set object.
     */
    private void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
            }
        }
    }

    /**
     * Closes resources.
     */
    private void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }

    /**
     * Rollbacks a connection.
     * <p>
     * Connection to be rollbacked.
     */
    private void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }

    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login=?";

    private static final String SQL_INSERT_INTO_USER = "INSERT INTO user(first_name, last_name, login, email, password, male) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_INSERT_INTO_SUBJECT = "INSERT INTO subject(name) VALUES (?)";

    private static final String SQL_INSERT_INTO_TEST = "INSERT INTO test(name, subject_id, complexity, time) VALUES (?, ?, ?, ?)";

    private static final String SQL_INSERT_INTO_RESULT = "INSERT INTO result(user_id, test_id, result, date, test_name) VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM user WHERE email=?";

    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM user WHERE id=?";

    private static final String SQL_FIND_RESULT_BY_USER_ID = "SELECT * FROM result WHERE user_id=?";

    private static final String SQL_FIND_TEST_BY_ID = "SELECT * FROM test WHERE id=?";

    private static final String SQL_FIND_ALL_SUBJECTS = "SELECT * FROM subject";

    private static final String SQL_FIND_TEST_BY_SUBJECT_ID = "SELECT * FROM test WHERE subject_id=?";

    private static final String SQL_FIND_QUESTION_BY_TEST_ID = "SELECT * FROM question WHERE test_id=?";

    private static final String SQL_FIND_ANSWER_BY_QUESTION_ID = "SELECT * FROM answer WHERE question_id=?";

    private static final String SQL_FIND_TRUE_ANSWER_BY_QUESTION_ID = "SELECT * FROM answer WHERE question_id=? AND is_right=1";

    public static final String SQL_UPDATE_SUBJECT_NAME = "UPDATE subject SET name=? WHERE id=?";

    private static final String SQL_UPDATE_TEST = "UPDATE test SET name=?, complexity=?, time=? WHERE id=?";

    private static final String SQL_UPDATE_QUESTION = "UPDATE question SET test_id=?, text=? WHERE id=?";

    private static final String SQL_UPDATE_QUESTION_ANSWER_TYPE = "UPDATE question SET answer_type_id=? WHERE id=?";

    private static final String SQL_DELETE_SUBJECT = "DELETE FROM subject where id=?";

    private static final String SQL_DELETE_TEST = "DELETE FROM test where id=?";

    private static final String SQL_DELETE_QUESTION = "DELETE FROM question where id=?";

    private static final String SQL_DELETE_ANSWER = "DELETE FROM answer where id=?";

    private static final String SQL_FIND_ALL_RESULTS = "SELECT * from result";


    private static final String SQL_INSERT_INTO_QUESTION = "INSERT INTO question(test_id, text) VALUES (?, ?)";

    private static final String SQL_INSERT_INTO_ANSWER = "INSERT INTO answer(text, is_right, question_id) VALUES (?, ?, ?)";


    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(Fields.ENTITY_ID));
        user.setLogin(rs.getString(Fields.USER_LOGIN));
        user.setPassword(rs.getString(Fields.USER_PASSWORD));
        user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
        user.setLastName(rs.getString(Fields.USER_LAST_NAME));
        user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
        user.setEmail(rs.getString(Fields.USER_EMAIL));
        user.setMale(rs.getString(Fields.USER_MALE));
        return user;
    }


    public User findUser(long id) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return user;
    }

    public User findUserByLogin(String login) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return user;
    }

    public User findUserByEmail(String email) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_EMAIL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_EMAIL, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return user;
    }

    public boolean insertUser(User user) throws DBException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_INTO_USER);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getLogin());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPassword());
            pstmt.setString(6, user.getMale());
            if (pstmt.executeUpdate() > 0) {
                con.commit();
                return true;
            }
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_INSERT_USER, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return false;
    }

    public List<Subject> findSubjects() throws DBException {

        List<Subject> subjectList = new ArrayList<Subject>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_SUBJECTS);
            while (rs.next()) {
                subjectList.add(extractSubject(rs));
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_SUBJECT, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_SUBJECT, ex);
        } finally {
            close(con, stmt, rs);
        }
        return subjectList;

    }

    private Subject extractSubject(ResultSet rs) throws SQLException {
        Subject subject = new Subject();
        subject.setName(rs.getString(Fields.SUBJECT_NAME));
        subject.setId(rs.getLong(Fields.ENTITY_ID));
        return subject;
    }

    public List<Test> findTestBySubjectId(long subjectId) throws DBException {

        List<Test> testsList = new ArrayList<Test>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_TEST_BY_SUBJECT_ID);
            pstmt.setLong(1, subjectId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                testsList.add(extractTest(rs));
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_SUBJECT, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_SUBJECT, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return testsList;

    }

    private Test extractTest(ResultSet rs) throws SQLException {
        Test test = new Test();

        test.setId(rs.getLong(Fields.ENTITY_ID));
        test.setName(rs.getString(Fields.TEST_NAME));
        test.setSize();
        test.setComplexity(rs.getInt(Fields.TEST_COMPLEXITY));
        test.setSubject_id(rs.getInt(Fields.TEST_SUBJECT_ID));
        test.setTime(rs.getInt(Fields.TEST_TIME));

        return test;
    }

    public List<Question> findQuestionByTestId(long testId) throws DBException {
        List<Question> questionList = new ArrayList<Question>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_QUESTION_BY_TEST_ID);
            pstmt.setLong(1, testId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                questionList.add(extractQuestion(rs));
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_SUBJECT, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_SUBJECT, ex);
        } finally {
            close(con, pstmt, rs);
        }

        return questionList;

    }

    private Question extractQuestion(ResultSet rs) throws SQLException {
        Question question = new Question();

        question.setAnswerTypeId(rs.getInt(Fields.QUESTION_ANSWER_TYPE_ID));
        question.setText(rs.getString(Fields.QUESTION_TEXT));
        question.setTestId(rs.getInt(Fields.QUESTION_TEST_ID));
        question.setId(rs.getLong(Fields.ENTITY_ID));

        return question;
    }

    public Test findTestById(long test_id) throws DBException {
        Test test = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_TEST_BY_ID);
            pstmt.setLong(1, test_id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                test = extractTest(rs);
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return test;
    }

    public List<Answer> findAnswersByQuestionId(Long id) throws DBException {
        List<Answer> answerList = new ArrayList<Answer>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ANSWER_BY_QUESTION_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                answerList.add(extractAnswer(rs));
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_SUBJECT, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ANSWER, ex);
        } finally {
            close(con, pstmt, rs);
        }

        return answerList;
    }

    private Answer extractAnswer(ResultSet rs) throws SQLException {
        Answer answer = new Answer();

        answer.setId(rs.getLong(Fields.ENTITY_ID));
        answer.setQuestionId(rs.getInt(Fields.ANSWER_QUESTION_ID));
        answer.setText(rs.getString(Fields.ANSWER_TEXT));
        answer.setCorrect(rs.getBoolean(Fields.ANSWER_IS_RIGHT));


        return answer;
    }

    public List<Answer> findAnswersByQuestionId(long question_id, boolean isRight) throws DBException {
        List<Answer> trueAnswerList = new ArrayList<Answer>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_TRUE_ANSWER_BY_QUESTION_ID);
            pstmt.setLong(1, question_id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                trueAnswerList.add(extractAnswer(rs));
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_SUBJECT, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ANSWER, ex);
        } finally {
            close(con, pstmt, rs);
        }

        return trueAnswerList;
    }

    public boolean insertResult(Result result) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_INTO_RESULT);
            pstmt.setLong(1, result.getUserId());
            pstmt.setLong(2, result.getTestId());
            pstmt.setInt(3, result.getResult());
            pstmt.setString(4, result.getDate());
            pstmt.setString(5, result.getTestName());
            if (pstmt.executeUpdate() > 0) {
                con.commit();
                return true;
            }
        } catch (SQLException | DBException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_INSERT_RESULT, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return false;
    }

    public List<Result> findUserResult(Long id) throws DBException {
        List<Result> resultList = new ArrayList<Result>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_RESULT_BY_USER_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultList.add(extractResult(rs));
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_SUBJECT, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ANSWER, ex);
        } finally {
            close(con, pstmt, rs);
        }

        return resultList;
    }

    private Result extractResult(ResultSet rs) throws SQLException {
        Result result = new Result();

        result.setId(rs.getLong(Fields.ENTITY_ID));
        result.setTestName(rs.getString(Fields.RESULT_TEST_NAME));
        result.setDate(rs.getString(Fields.RESULT_DATE));
        result.setTestId(rs.getLong(Fields.RESULT_TEST_ID));
        result.setResult(rs.getInt(Fields.RESULT_RESULT));
        result.setUserId(rs.getLong(Fields.RESULT_USER_ID));

        return result;
    }

    public void updateSubjectName(int subjectId, String newSubjectName) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_SUBJECT_NAME);
            pstmt.setString(1, newSubjectName);
            pstmt.setLong(2, subjectId);
            if (pstmt.executeUpdate() > 0) {
                con.commit();
            }
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_UPDATE_SUBJECT_NAME, ex);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_SUBJECT_NAME, ex);
        } finally {
            close(con);
            close(pstmt);
        }
    }

    public boolean insertSubject(String newSubjectName) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_INTO_SUBJECT);
            pstmt.setString(1, newSubjectName);
            if (pstmt.executeUpdate() > 0) {
                con.commit();
                return true;
            }
        } catch (SQLException | DBException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_INSERT_SUBJECT, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return false;
    }

    public boolean deleteSubject(int subjectId) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_SUBJECT);
            pstmt.setLong(1, subjectId);
            if (pstmt.executeUpdate() > 0) {
                con.commit();
                return true;
            }
        } catch (SQLException | DBException e) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_DELETE_SUBJECT, e);
            throw new DBException(Messages.ERR_CANNOT_DELETE_SUBJECT, e);
        } finally {
            close(con);
            close(pstmt);
        }
        return false;
    }

    public boolean deleteTest(int testId) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_TEST);
            pstmt.setLong(1, testId);
            if (pstmt.executeUpdate() > 0) {
                con.commit();
                return true;
            }
        } catch (SQLException | DBException e) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_DELETE_TEST, e);
            throw new DBException(Messages.ERR_CANNOT_DELETE_TEST, e);
        } finally {
            close(con);
            close(pstmt);
        }
        return false;
    }

    public boolean insertTest(Test test) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_INTO_TEST);
            pstmt.setString(1, test.getName());
            pstmt.setInt(2, test.getSubjectId());
            pstmt.setInt(3, test.getComplexity());
            pstmt.setInt(4, test.getTime());
            if (pstmt.executeUpdate() > 0) {
                con.commit();
                return true;
            }
        } catch (SQLException | DBException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_INSERT_TEST, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return false;
    }

    public void updateTest(String newTestName, int complexity, int time, int testId) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_TEST);
            pstmt.setString(1, newTestName);
            pstmt.setInt(2, complexity);
            pstmt.setInt(3, time);
            pstmt.setInt(4, testId);
            if (pstmt.executeUpdate() > 0) {
                con.commit();
            }
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_UPDATE_TEST, ex);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_TEST, ex);
        } finally {
            close(con);
            close(pstmt);
        }
    }

    public boolean insertQuestion(Question question) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_INTO_QUESTION);
            pstmt.setLong(1, question.getTestId());
            pstmt.setString(2, question.getText());
            if (pstmt.executeUpdate() > 0) {
                con.commit();
                return true;
            }
        } catch (SQLException | DBException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_INSERT_QUESTION, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return false;
    }

    public void updateQuestion(int answerType, long questionId) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_QUESTION_ANSWER_TYPE);
            pstmt.setInt(1, answerType);
            pstmt.setLong(2, questionId);
            if (pstmt.executeUpdate() > 0) {
                con.commit();
            }
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_UPDATE_QUESTION, ex);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_QUESTION, ex);
        } finally {
            close(con);
            close(pstmt);
        }
    }

    public void updateQuestion(Question question) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_QUESTION);
            pstmt.setLong(1, question.getTestId());
            pstmt.setString(2, question.getText());
            pstmt.setLong(3, question.getId());
            if (pstmt.executeUpdate() > 0) {
                con.commit();
            }
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_UPDATE_QUESTION, ex);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_QUESTION, ex);
        } finally {
            close(con);
            close(pstmt);
        }
    }

    public boolean deleteQuestion(int questionId) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_QUESTION);
            pstmt.setLong(1, questionId);
            if (pstmt.executeUpdate() > 0) {
                con.commit();
                return true;
            }
        } catch (SQLException | DBException e) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_DELETE_QUESTION, e);
            throw new DBException(Messages.ERR_CANNOT_DELETE_QUESTION, e);
        } finally {
            close(con);
            close(pstmt);
        }
        return false;
    }

    public boolean deleteAnswer(int answerId) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_ANSWER);
            pstmt.setLong(1, answerId);
            if (pstmt.executeUpdate() > 0) {
                con.commit();
                return true;
            }
        } catch (SQLException | DBException e) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_DELETE_ANSWER, e);
            throw new DBException(Messages.ERR_CANNOT_DELETE_ANSWER, e);
        } finally {
            close(con);
            close(pstmt);
        }
        return false;
    }

    public boolean insertAnswer(Answer answer) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_INTO_ANSWER);
            pstmt.setString(1, answer.getText());
            pstmt.setBoolean(2, answer.getCorrect());
            pstmt.setLong(3, answer.getQuestionId());
            if (pstmt.executeUpdate() > 0) {
                con.commit();
                return true;
            }
        } catch (SQLException | DBException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_INSERT_ANSWER, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return false;
    }

    public List<Result> findAllResults() throws DBException {
        List<Result> resultList = new ArrayList<Result>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ALL_RESULTS);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultList.add(extractResult(rs));
            }
            con.commit();
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_RESULT, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_RESULT, ex);
        } finally {
            close(con, pstmt, rs);
        }

        return resultList;
    }
}
