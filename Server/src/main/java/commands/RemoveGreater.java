package commands;

import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import elements.Organization;
import exchange.request.Request;
import exchange.response.Response;
import util.CollectionManager;
import util.ElementAssembler;
import util.transceiving.Sender;

import java.net.SocketAddress;

/**
 * Класс команды RemoveGrater
 */
public class RemoveGreater extends Command {
    public RemoveGreater(Sender sender) {
        this.name = "remove_greater";
        this.help = "Удалить из коллекции все элементы, превышающие заданный";
        this.argsNumber = 0;
        this.sender = sender;
    }

    @Override
    public void execute(SocketAddress socketAddress, Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() == null) throw new IllegalFieldWrapperRequestException("отсутствует передаваемый элемент");
        CollectionManager collectionManager = CollectionManager.getInstance();
        if (collectionManager.isEmpty()) sender.send(socketAddress, new Response("Коллекция пуста.", true));
        int count = collectionManager.getSize();
        Organization elem = ElementAssembler.assemble(request.getFieldsWrapper());
        collectionManager.getList().stream().filter(s -> (s.compareTo(elem) > 0)).forEach(collectionManager::remove);
        if (count == collectionManager.getSize()) {
            sender.send(socketAddress, new Response("Элементов больше данного не было найдено.", true));
        } else {
            sender.send(socketAddress, new Response("Удалено элементов " + (count - collectionManager.getSize()), true));
        }
    }
}