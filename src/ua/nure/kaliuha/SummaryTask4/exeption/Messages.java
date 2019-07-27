package ua.nure.kaliuha.SummaryTask4.exeption;

public class Messages {


    public static final String ERR_CANNOT_OBTAIN_USER_BY_EMAIL = "Cannot obtain a user by its email";
    public static final String ERR_CANNOT_INSERT_USER = "Cannot insert a user into data base";
    public static final String ERR_CANNOT_OBTAIN_SUBJECT = "Cannot obtain subject from data source";
    public static final String ERR_CANNOT_OBTAIN_ANSWER = "Cannot obtain answer from data source";
    public static final String ERR_CANNOT_INSERT_RESULT = "Cannot insert a result into data base";
    public static final String ERR_CANNOT_UPDATE_SUBJECT_NAME = "Cannot update subject name into data base";
    public static final String ERR_CANNOT_INSERT_SUBJECT = "Cannot insert a subject into data base";
    public static final String ERR_CANNOT_DELETE_SUBJECT = "Cannot delete a subject from data base";
    public static final String ERR_CANNOT_DELETE_TEST = "Cannot delete a test from data base";
    public static final String ERR_CANNOT_INSERT_TEST =  "Cannot insert a test into data base";
    public static final String ERR_CANNOT_UPDATE_TEST = "Cannot update a test into data base";
    public static final String ERR_CANNOT_INSERT_QUESTION = "Cannot insert a question into data base";
    public static final String ERR_CANNOT_UPDATE_QUESTION = "Cannot update a question into data base";
    public static final String ERR_CANNOT_DELETE_QUESTION = "Cannot delete a question from data base";
    public static final String ERR_CANNOT_CHANGE_ANSWER_TYPE_OF_QUESTION_ON_USER_ANSWER = "You must have just one answer in this question and it must be true" ;
    public static final String ERR_CANNOT_CHANGE_ANSWER_TYPE_OF_QUESTION_ON_ONE_TRUE = "You must have more then one answer in this question and just one true answer";
    public static final String ERR_CANNOT_CHANGE_ANSWER_TYPE_OF_QUESTION_ON_MANY_TRUE =  "You must have more then two answer in this question and more then one true answer";
    public static final String ERR_CANNOT_CHANGE_ANSWER_TYPE = "Cannot change answer type of this question" ;
    public static final String ERR_CANNOT_DELETE_ANSWER = "Cannot delete a answer from data base";
    public static final String ERR_CANNOT_INSERT_ANSWER = "Cannot insert a answer into data base";
    public static final String ERR_CANNOT_OBTAIN_RESULT = "Cannot obtain result from data source";

    private Messages() {
        //--------
    }

    public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";

    public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = "Cannot obtain a user by its id";

    public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";

    public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";

    public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";

    public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";

    public static final String ERR_CANNOT_OBTAIN_USER_BY_LOGIN = "Cannot obtain a user by its login";


}
