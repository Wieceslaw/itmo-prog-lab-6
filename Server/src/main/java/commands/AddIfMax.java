package commands;

import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import elements.Organization;
import exchange.request.Request;
import exchange.response.Response;
import util.CollectionManager;
import util.ElementAssembler;

/**
 * Класс команды AddIfMax
 */
public class AddIfMax extends Command {
    public static String name = "add_if_max";
    public static String help = "Добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    public static int argsNumber = 0;

    @Override
    public Response execute(Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() == null) throw new IllegalFieldWrapperRequestException("отсутствует передаваемый элемент");
        Organization elem = ElementAssembler.assemble(request.getFieldsWrapper());
        CollectionManager collectionManager = CollectionManager.getInstance();
        Organization max = collectionManager.getMax();
        if (max == null || elem.compareTo(max) > 0) {
            collectionManager.add(elem);
            return new Response("Элемент добавлен", true);
        } else {
            return new Response("Элемент не был добавлен.", true);
        }
    }
}
