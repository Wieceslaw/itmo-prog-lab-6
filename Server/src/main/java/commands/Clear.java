package commands;

import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import exchange.request.Request;
import exchange.response.Response;
import util.CollectionManager;

/**
 * Класс команды Clear
 */
public class Clear extends Command {
    public static String name = "clear";
    public static String help = "Очистить коллекцию";
    public static int argsNumber = 0;

    @Override
    public Response execute(Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() != null) throw new IllegalFieldWrapperRequestException("команда не принимает элемент");
        CollectionManager collectionManager = CollectionManager.getInstance();
        collectionManager.clear();
        return new Response("Коллекция очищена.", true);
    }
}
