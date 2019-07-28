package ua.nure.kaliuha.SummaryTask4.web.command;

import org.junit.Test;
import ua.nure.kaliuha.SummaryTask4.db.DBManager;
import ua.nure.kaliuha.SummaryTask4.db.entity.User;
import ua.nure.kaliuha.SummaryTask4.exeption.DBException;
import ua.nure.kaliuha.SummaryTask4.exeption.Messages;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;

public class LoginCommandTest {

    private final DBManager db_manager = DBManager.getInstance();

    private final String name = "Jem";
    private final String surname = "Finch";
    private final String email = "jem.finch@gmail.com";
    private final String password = "pass123word";
    private final String male = "male";
    private final String login = "login";


    public LoginCommandTest() throws DBException {
    }

    @Test
    public void registerNewUser() {

        User user = null;
        try {
            user.setMale(male);
            user.setPassword(password);
            user.setEmail(email);
            user.setLogin(login);
            user.setLastName(surname);
            user.setFirstName(name);
            db_manager.insertUser(user);
            fail("Failed to register new user");

        } catch (DBException e) {
            assertEquals("Wrong error type", Messages.ERR_CANNOT_INSERT_USER, e.getMessage());
        }

    }
}