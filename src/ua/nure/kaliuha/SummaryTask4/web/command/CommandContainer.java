package ua.nure.kaliuha.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.kaliuha.SummaryTask4.web.command.admin.answer.CreateAnswerCommand;
import ua.nure.kaliuha.SummaryTask4.web.command.admin.answer.DeleteAnswerCommand;
import ua.nure.kaliuha.SummaryTask4.web.command.admin.question.*;
import ua.nure.kaliuha.SummaryTask4.web.command.admin.subject.CreateSubjectCommand;
import ua.nure.kaliuha.SummaryTask4.web.command.admin.subject.DeleteSubjectCommand;
import ua.nure.kaliuha.SummaryTask4.web.command.admin.subject.EditSubjectCommand;
import ua.nure.kaliuha.SummaryTask4.web.command.admin.test.CreateTestCommand;
import ua.nure.kaliuha.SummaryTask4.web.command.admin.test.DeleteTestCommand;
import ua.nure.kaliuha.SummaryTask4.web.command.admin.test.EditTestCommand;
import ua.nure.kaliuha.SummaryTask4.web.command.admin.user.*;
import ua.nure.kaliuha.SummaryTask4.web.command.common.ForwardToCommand;
import ua.nure.kaliuha.SummaryTask4.web.command.common.SubjectsListCommand;
import ua.nure.kaliuha.SummaryTask4.web.command.common.TestsListCommand;
import ua.nure.kaliuha.SummaryTask4.web.command.student.*;

import java.util.Map;
import java.util.TreeMap;


public class CommandContainer {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("subjectsList", new SubjectsListCommand());
        commands.put("testsListBySubject", new TestsListCommand());
        commands.put("startTest", new StartTestCommand());
        commands.put("testing", new TestingCommand());
        commands.put("checkAnswer", new CheckAnswerCommand());
        commands.put("finishTest", new FinishTestCommand());
        commands.put("userCabinet", new UserCabinetCommand());
        commands.put("editSubject", new EditSubjectCommand());
        commands.put("createSubject", new CreateSubjectCommand());
        commands.put("deleteSubject", new DeleteSubjectCommand());
        commands.put("forwardTo", new ForwardToCommand());
        commands.put("createTest", new CreateTestCommand());
        commands.put("createQuestion", new CreateQuestionCommand());
        commands.put("updateTest", new EditTestCommand());
        commands.put("deleteTest", new DeleteTestCommand());
        commands.put("showQuestion", new ShowQuestionTextCommand());
        commands.put("startEditQuestion", new StartEditQuestionCommand());
        commands.put("viewSettings", new ViewSettingsCommand());
        commands.put("editQuestion", new EditQuestionCommand());
        commands.put("deleteQuestion", new DeleteQuestionCommand());
        commands.put("deleteAnswer", new DeleteAnswerCommand());
        commands.put("createAnswer", new CreateAnswerCommand());
        commands.put("showUserResults", new ShowUserResultsCommand());
        commands.put("showAllUsers", new ShowAllUsersCommand());
        commands.put("searchUser", new SearchUserCommand());
        commands.put("openUserCabinet", new OpenUserCabinetCommand());
        commands.put("changeRole", new ChangeRoleCommand());

/*      commands.put("te", new ViewSettingsCommand());
        commands.put("noCommand", new NoCommand());

        // client commands
        commands.put("listMenu", new ListMenuCommand());

        // admin commands
        commands.put("listOrders", new ListOrdersCommand());

        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());*/
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}