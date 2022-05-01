package commands;

import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import exchange.request.Request;
import exchange.response.Response;

/**
 * Класс команды Help
 */
public class Help extends Command {
    public static String name = "help";
    public static String help = "Вывести справку по доступным командам";
    public static int argsNumber = 0;

    @Override
    public Response execute(Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() != null) throw new IllegalFieldWrapperRequestException("команда не принимает элемент");
        String helpDescription = Help.name + ": " + Help.help + '\n' +
                Info.name + ": " + Info.help + '\n' +
                Show.name + ": " + Show.help + '\n' +
                Add.name + ": " + Add.help + '\n' +
                Update.name + ": " + Update.help + '\n' +
                RemoveById.name + ": " + RemoveById.help + '\n' +
                Clear.name + ": " + Clear.help + '\n' +
                Exit.name + ": " + Exit.help + '\n' +
                ExecuteScript.name + ": " + ExecuteScript.help + '\n' +
                AddIfMax.name + ": " + AddIfMax.help + '\n' +
                AddIfMin.name + ": " + AddIfMin.help + '\n' +
                RemoveGreater.name + ": " + RemoveGreater.help + '\n' +
                SumOfAnnualTurnover.name + ": " + SumOfAnnualTurnover.help + '\n' +
                PrintDescending.name + ": " + PrintDescending.help + '\n' +
                PrintFieldAscendingEmployeesCount.name + ": " + PrintFieldAscendingEmployeesCount.help;
        return new Response(helpDescription, true);
    }
}
