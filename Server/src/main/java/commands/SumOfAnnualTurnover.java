package commands;

import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import elements.Organization;
import exchange.request.Request;
import exchange.response.Response;
import util.CollectionManager;

/**
 * Класс команды SumOfAnnualTurnover
 */
public class SumOfAnnualTurnover extends Command {
    public static String name = "sum_of_annual_turnover";
    public static String help = "Вывести сумму значений поля annualTurnover для всех элементов коллекции";
    public static int argsNumber = 0;

    @Override
    public Response execute(Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() != null) throw new IllegalFieldWrapperRequestException("команда не принимает элемент");
        CollectionManager collectionManager = CollectionManager.getInstance();
        int sum = collectionManager.getCollection().stream()
                .map(Organization::getAnnualTurnover)
                .reduce(0, Integer::sum);
        return new Response(String.valueOf(sum), true);
    }
}
