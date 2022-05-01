package commands;

import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import elements.Organization;
import exchange.request.Request;
import exchange.response.Response;
import util.CollectionManager;

import java.util.Collections;
import java.util.List;

/**
 * Класс команды PrintDescending
 */
public class PrintDescending extends Command {
    public static String name = "print_descending";
    public static String help = "Вывести элементы коллекции в порядке убывания";
    public static int argsNumber = 0;

    @Override
    public Response execute(Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() != null) throw new IllegalFieldWrapperRequestException("команда не принимает элемент");
        CollectionManager collectionManager = CollectionManager.getInstance();
        List<Organization> list = collectionManager.getSortedList();
        Collections.reverse(list);
        return new Response(list.isEmpty() ? "Коллекция пуста." : null, list, true);
    }
}
