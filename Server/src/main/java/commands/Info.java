package commands;

import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import exchange.request.Request;
import exchange.response.Response;
import util.CollectionManager;

import java.text.SimpleDateFormat;

/**
 * Класс команды Info
 */
public class Info extends Command {
    public static String name = "info";
    public static String help = "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    public static int argsNumber = 0;

    @Override
    public Response execute(Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() != null) throw new IllegalFieldWrapperRequestException("команда не принимает элемент");
        StringBuilder stringBuilder = new StringBuilder();
        CollectionManager collectionManager = CollectionManager.getInstance();
        String pattern = "dd.M.yyyy hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        stringBuilder.append("Тип: ").append(collectionManager.getCollection().getClass().getName()).append('\n');;
        stringBuilder.append("Дата инициализации: ").append(simpleDateFormat.format(collectionManager.getCreationDate())).append('\n');
        stringBuilder.append("Количество элементов: ").append(collectionManager.getSize());
        return new Response(stringBuilder.toString(), true);
    }
}