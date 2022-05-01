package commands;

import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import elements.Organization;
import exchange.request.Request;
import exchange.response.Response;
import util.CollectionManager;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс команды Show
 */
public class Show extends Command {
    public static String name = "show";
    public static String help = "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    public static int argsNumber = 0;

    @Override
    public Response execute(Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() != null) throw new IllegalFieldWrapperRequestException("команда не принимает элемент");
        CollectionManager collectionManager = CollectionManager.getInstance();
        List<Organization> sortedByNameList = collectionManager.getCollection().stream().sorted((x1, x2) -> x1.getName().compareTo(x2.getName())).collect(Collectors.toList());
        return new Response(collectionManager.isEmpty() ? "Коллекция пуста." : null, sortedByNameList, true);
    }
}
