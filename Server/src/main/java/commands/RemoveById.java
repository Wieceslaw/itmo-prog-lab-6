package commands;

import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import elements.Organization;
import exchange.request.Request;
import exchange.response.Response;
import util.CollectionManager;
import util.transceiving.Sender;

import java.net.SocketAddress;

/**
 * Класс команды RemoveById
 */
public class RemoveById extends Command {
    public RemoveById(Sender sender) {
        this.name = "remove_by_id";
        this.help = "Удалить элемент из коллекции по его id";
        this.argsNumber = 1;
        this.sender = sender;
    }

    @Override
    public void execute(SocketAddress socketAddress, Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() != null) throw new IllegalFieldWrapperRequestException("команда не принимает элемент");
        CollectionManager collectionManager = CollectionManager.getInstance();
        long id = Long.parseLong(request.getArgs()[0]);
        Organization element = collectionManager.getElementById(id);
        if (element == null) throw new NullPointerException("Элемент с таким ID не найден.");
        collectionManager.remove(element);
        sender.send(socketAddress, new Response("Элемент удален.", true));
    }
}