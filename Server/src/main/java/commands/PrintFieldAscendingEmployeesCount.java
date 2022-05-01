package commands;

import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import elements.Organization;
import exchange.request.Request;
import exchange.response.Response;
import util.CollectionManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Класс команды PrintFieldAscendingEmployeesCount
 */
public class PrintFieldAscendingEmployeesCount extends Command {
    public static String name = "print_field_ascending_employees_count";
    public static String help = "Вывести значения поля employeesCount всех элементов в порядке возрастания";
    public static int argsNumber = 0;

    @Override
    public Response execute(Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() != null) throw new IllegalFieldWrapperRequestException("команда не принимает элемент");
        CollectionManager collectionManager = CollectionManager.getInstance();
        List<Organization> list = new ArrayList<>(collectionManager.getCollection());
        list.sort(Comparator.comparingInt(Organization::getEmployeesCount));
        return new Response(list.isEmpty() ? "Коллекция пуста" : null, list, true);
    }
}
