package commands;

import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import exchange.request.Request;
import exchange.response.Response;
import util.file.Saver;

/**
 * Класс команды Save
 */
public class Save extends Command {
    public static String name = "save";
    public static String help = "Сохранить коллекцию в файл";
    public static int argsNumber = 0;

    @Override
    public Response execute(Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() != null) throw new IllegalFieldWrapperRequestException("команда не принимает элемент");
        Saver.save();
        return new Response("Коллекция успешно сохранена.", true);
    }
}
