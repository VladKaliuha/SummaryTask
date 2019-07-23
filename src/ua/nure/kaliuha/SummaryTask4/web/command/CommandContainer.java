package ua.nure.kaliuha.SummaryTask4.web.command;

import org.apache.log4j.Logger;
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
        commands.put("checkAnswer", new ChechAnswerCommand());
        commands.put("finishTest", new FinishTestCommand());
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