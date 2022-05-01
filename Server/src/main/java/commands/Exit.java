package commands;

import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import exchange.request.Request;
import exchange.response.Response;

/**
 * Класс команды Exit
 */
public class Exit extends Command {
    public static String name = "exit";
    public static String help = "Завершить программу (без сохранения в файл)";
    public static int argsNumber = 0;

    @Override
    public Response execute(Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() != null) throw new IllegalFieldWrapperRequestException("команда не принимает элемент");
        return new Save().execute(new Request(null, new String[0], null));
    }
}
