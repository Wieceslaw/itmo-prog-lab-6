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
 * Класс команды AddIfMax
 */
public class AddIfMax extends Command {
    public AddIfMax(Sender sender) {
        this.name = "add_if_max";
        this.help = "Добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
        this.argsNumber = 0;
        this.sender = sender;
    }

    @Override
    public void execute(SocketAddress socketAddress, Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() == null) throw new IllegalFieldWrapperRequestException("отсутствует передаваемый элемент");
        Organization elem = ElementAssembler.assemble(request.getFieldsWrapper());
        CollectionManager collectionManager = CollectionManager.getInstance();
        Organization max = collectionManager.getMax();
        if (max == null || elem.compareTo(max) > 0) {
            collectionManager.add(elem);
            sender.send(socketAddress, new Response("Элемент добавлен", true));
        } else {
            sender.send(socketAddress, new Response("Элемент не был добавлен.", true));
        }
    }
}
