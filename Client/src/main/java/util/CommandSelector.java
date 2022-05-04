package util;

import requestbuilders.*;
import requestbuilders.exceptions.WrongArgumentsException;
import requestbuilders.exceptions.WrongArgumentsNumberException;
import talkers.Talker;
import util.transceiving.Receiver;
import util.transceiving.Sender;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Класс для выборки и исполнения команд
 */
public class CommandSelector {
    private final HashMap<String, RequestBuilder> requestBuilderHashMap = new HashMap<>();
    private final Talker talker;

    public CommandSelector(Sender sender, Receiver receiver, Talker talker, boolean isScript) {
        this.talker = talker;
        requestBuilderHashMap.put("help", new Help(talker, isScript, sender, receiver));
        requestBuilderHashMap.put("info", new Info(talker, isScript, sender, receiver));
        requestBuilderHashMap.put("show", new Show(talker, isScript, sender, receiver));
        requestBuilderHashMap.put("add", new Add(talker, isScript, sender, receiver));
        requestBuilderHashMap.put("update", new Update(talker, isScript, sender, receiver));
        requestBuilderHashMap.put("remove_by_id", new RemoveById(talker, isScript, sender, receiver));
        requestBuilderHashMap.put("clear", new Clear(talker, isScript, sender, receiver));
        requestBuilderHashMap.put("execute_script", new ExecuteScript(talker, isScript, sender, receiver));
        requestBuilderHashMap.put("exit", new Exit(talker, isScript, sender, receiver));
        requestBuilderHashMap.put("add_if_max", new AddIfMax(talker, isScript, sender, receiver));
        requestBuilderHashMap.put("add_if_min", new AddIfMin(talker, isScript, sender, receiver));
        requestBuilderHashMap.put("remove_greater", new RemoveGreater(talker, isScript, sender, receiver));
        requestBuilderHashMap.put("sum_of_annual_turnover", new SumOfAnnualTurnover(talker, isScript, sender, receiver));
        requestBuilderHashMap.put("print_descending", new PrintDescending(talker, isScript, sender, receiver));
        requestBuilderHashMap.put("print_field_ascending_employees_count", new PrintFieldAscendingEmployeesCount(talker, isScript, sender, receiver));
    }

    /**
     * Вызывает метод {@link RequestBuilder#execute(String[]) execute} у {@link RequestBuilder}
     *
     * @param commandLine командная строка с именем команды и ее аргументами
     */
    public void execute(String commandLine) {
        String[] commandArgs = commandLine.split("\\s+");
        String commandName = commandArgs[0];
        commandArgs = Arrays.copyOfRange(commandArgs, 1, commandArgs.length);
        if (!requestBuilderHashMap.containsKey(commandName)) {
            talker.println("Такой команды нет. Введите help, чтобы получить список и описание команд.");
        } else {
            try {
                requestBuilderHashMap.get(commandName).execute(commandArgs);
            } catch (WrongArgumentsNumberException e) {
                talker.println("Команда принимает число аргументов: " + e.getMessage());
            } catch (WrongArgumentsException e) {
                talker.println(e.getMessage());
            }
        }
    }
}
