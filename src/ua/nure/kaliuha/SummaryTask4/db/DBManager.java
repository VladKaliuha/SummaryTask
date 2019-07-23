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

    private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM user WHERE email=?";

    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM user WHERE id=?";

    private static final String SQL_FIND_TEST_BY_ID = "SELECT * FROM test WHERE id=?";

    private static final String SQL_FIND_ALL_SUBJECTS = "SELECT * FROM subject";

    private static final String SQL_FIND_TEST_BY_SUBJECT_ID = "SELECT * FROM test WHERE subject_id=?";

    private static final String SQL_FIND_QUESTION_BY_TEST_ID = "SELECT * FROM question WHERE test_id=?";

    private static final String SQL_FIND_ANSWER_BY_QUESTION_ID = "SELECT * FROM answer WHERE question_id=?";

    private static final String SQL_FIND_TRUE_ANSWER_BY_QUESTION_ID = "SELECT * FROM answer WHERE question_id=? AND is_right=1";


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
        answer.setRight(rs.getBoolean(Fields.ANSWER_IS_RIGHT));


        return answer;
    }

    public List<Answer> findTrueAnswersByQuestionId(long question_id) throws DBException {
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
}
