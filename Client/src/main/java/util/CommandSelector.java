package util;

import requestbuilders.*;
import requestbuilders.exceptions.WrongArgumentsException;
import requestbuilders.exceptions.WrongArgumentsNumberException;
import talkers.Talker;

import java.util.Arrays;
import java.util.HashMap;

public class CommandSelector {
    private final HashMap<String, RequestBuilder> argBuildHashMap = new HashMap<>();
    private final Communicator communicator;
    private final Talker talker;
    private final boolean isScript;

    public CommandSelector(Communicator communicator, Talker talker, boolean isScript) {
        this.communicator = communicator;
        this.talker = talker;
        this.isScript = isScript;
        argBuildHashMap.put("help", new Help(talker, isScript, communicator));
        argBuildHashMap.put("info", new Info(talker, isScript, communicator));
        argBuildHashMap.put("show", new Show(talker, isScript, communicator));
        argBuildHashMap.put("add", new Add(talker, isScript, communicator));
        argBuildHashMap.put("update", new Update(talker, isScript, communicator));
        argBuildHashMap.put("remove_by_id", new RemoveById(talker, isScript, communicator));
        argBuildHashMap.put("clear", new Clear(talker, this.isScript, communicator));
        argBuildHashMap.put("execute_script", new ExecuteScript(talker, isScript, communicator));
        argBuildHashMap.put("exit", new Exit(talker, isScript, communicator));
        argBuildHashMap.put("add_if_max", new AddIfMax(talker, isScript, communicator));
        argBuildHashMap.put("add_if_min", new AddIfMin(talker, isScript, communicator));
        argBuildHashMap.put("remove_greater", new RemoveGreater(talker, isScript, communicator));
        argBuildHashMap.put("sum_of_annual_turnover", new SumOfAnnualTurnover(talker, isScript, communicator));
        argBuildHashMap.put("print_descending", new PrintDescending(talker, isScript, communicator));
        argBuildHashMap.put("print_field_ascending_employees_count", new PrintFieldAscendingEmployeesCount(talker, isScript, communicator));
    }

    public void execute(String commandLine) {
        String[] commandArgs = commandLine.split("\\s+");
        String commandName = commandArgs[0];
        commandArgs = Arrays.copyOfRange(commandArgs, 1, commandArgs.length);
        if (!argBuildHashMap.containsKey(commandName)) {
            talker.println("Такой команды нет. Введите help, чтобы получить список и описание команд.");
        } else {
            try {
                argBuildHashMap.get(commandName).execute(commandArgs);
            } catch (WrongArgumentsNumberException e) {
                talker.println("Команда принимает число аргументов: " + e.getMessage());
            } catch (WrongArgumentsException e) {
                talker.println(e.getMessage());
            }
        }
    }
}
