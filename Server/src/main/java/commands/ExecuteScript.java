package commands;

import exchange.request.Request;
import exchange.response.Response;

/**
 * Класс команды ExecuteScript
 */
public class ExecuteScript extends Command {
    public static String name = "execute_script";
    public static String help = "Считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит";
    public static int argsNumber = 0;

    @Override
    public Response execute(Request request) {
        // TODO: Сделать что-то
        return null;
    }
}
