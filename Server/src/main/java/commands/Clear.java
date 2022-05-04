package commands;

import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import exchange.request.Request;
import exchange.response.Response;
import util.CollectionManager;
import util.transceiving.Sender;

import java.net.SocketAddress;

/**
 * Класс команды Clear
 */
public class Clear extends Command {
    public Clear(Sender sender) {
        this.name = "clear";
        this.help = "Очистить коллекцию";
        this.argsNumber = 0;
        this.sender = sender;
    }

    @Override
    public void execute(SocketAddress socketAddress, Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() != null) throw new IllegalFieldWrapperRequestException("команда не принимает элемент");
        CollectionManager collectionManager = CollectionManager.getInstance();
        collectionManager.clear();
        sender.send(socketAddress, new Response("Коллекция успешно очищена.", true));
    }
}
