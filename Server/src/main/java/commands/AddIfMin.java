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
 * Класс команды AddIfMin
 */
public class AddIfMin extends Command {
    public AddIfMin(Sender sender) {
        this.name = "add_if_min";
        this.help = "Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
        this.argsNumber = 0;
        this.sender = sender;
    }

    @Override
    public void execute(SocketAddress socketAddress, Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() == null) throw new IllegalFieldWrapperRequestException("отсутствует передаваемый элемент");
        Organization elem = ElementAssembler.assemble(request.getFieldsWrapper());
        CollectionManager collectionManager = CollectionManager.getInstance();
        Organization min = collectionManager.getMin();
        if (min == null || elem.compareTo(min) < 0) {
            collectionManager.add(elem);
            sender.send(socketAddress, new Response("Элемент добавлен", true));
        } else {
            sender.send(socketAddress, new Response("Элемент не был добавлен.", true));
        }
    }
}
