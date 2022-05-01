package commands;

import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import elements.Organization;
import exchange.request.Request;
import exchange.response.Response;
import util.CollectionManager;
import util.ElementAssembler;

/**
 * Класс команды RemoveGrater
 */
public class RemoveGreater extends Command {
    public static String name = "remove_greater";
    public static String help = "Удалить из коллекции все элементы, превышающие заданный";
    public static int argsNumber = 0;

    @Override
    public Response execute(Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() == null) throw new IllegalFieldWrapperRequestException("отсутствует передаваемый элемент");
        CollectionManager collectionManager = CollectionManager.getInstance();
        if (collectionManager.isEmpty()) return new Response("Коллекция пуста.", true);
        int count = collectionManager.getSize();
        Organization elem = ElementAssembler.assemble(request.getFieldsWrapper());
        collectionManager.getList().stream().filter(s -> (s.compareTo(elem) > 0)).forEach(collectionManager::remove);
        if (count == collectionManager.getSize()) {
            return new Response("Элементов больше данного не было найдено.", true);
        } else {
            return new Response("Удалено элементов " + (count - collectionManager.getSize()), true);
        }
    }
}