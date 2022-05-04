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
 * Класс команды Add
 */
public class Add extends Command {
    public Add(Sender sender) {
        this.name = "add";
        this.help = "Добавить новый элемент в коллекцию";
        this.argsNumber = 0;
        this.sender = sender;
    }

    @Override
    public void execute(SocketAddress socketAddress, Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() == null) throw new IllegalFieldWrapperRequestException("отсутствует передаваемый элемент");
        Organization element = ElementAssembler.assemble(request.getFieldsWrapper());
        CollectionManager collectionManager = CollectionManager.getInstance();
        collectionManager.add(element);
        sender.send(socketAddress, new Response("Элемент успешно добавлен.", true));
    }
}
