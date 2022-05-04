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
 * Класс команды Update
 */
public class Update extends Command {
    public Update(Sender sender) {
        this.name = "update";
        this.help = "Обновить значение элемента коллекции, id которого равен заданному";
        this.argsNumber = 1;
        this.sender = sender;
    }

    @Override
    public void execute(SocketAddress socketAddress, Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() == null) throw new IllegalFieldWrapperRequestException("отсутствует передаваемый элемент");
        CollectionManager collectionManager = CollectionManager.getInstance();
        Long id = Long.parseLong(request.getArgs()[0]);
        Organization element = collectionManager.getElementById(id);
        if (element == null) throw new NullPointerException("Элемента с таким ID не найдено.");
        collectionManager.remove(element);
        Organization organization = ElementAssembler.assemble(request.getFieldsWrapper());
        organization.setId(id);
        collectionManager.add(organization);
        sender.send(socketAddress, new Response("Элемент обновлен.", true));
    }
}
