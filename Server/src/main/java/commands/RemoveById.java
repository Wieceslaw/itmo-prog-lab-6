package commands;

import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import elements.Organization;
import exchange.request.Request;
import exchange.response.Response;
import util.CollectionManager;

/**
 * Класс команды RemoveById
 */
public class RemoveById extends Command {
    public static String name = "remove_by_id";
    public static String help = "Удалить элемент из коллекции по его id";
    public static int argsNumber = 1;

    @Override
    public Response execute(Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() != null) throw new IllegalFieldWrapperRequestException("команда не принимает элемент");
        CollectionManager collectionManager = CollectionManager.getInstance();
        long id = Long.parseLong(request.getArgs()[0]);
        Organization element = collectionManager.getElementById(id);
        if (element == null) throw new NullPointerException("Элемент с таким ID не найден.");
        collectionManager.remove(element);
        return new Response("Элемент удален.", true);
    }
}