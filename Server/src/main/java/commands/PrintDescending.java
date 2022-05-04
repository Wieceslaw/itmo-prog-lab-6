package commands;

import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import elements.Organization;
import exchange.request.Request;
import exchange.response.Response;
import util.CollectionManager;
import util.transceiving.Sender;

import java.net.SocketAddress;
import java.util.Collections;
import java.util.List;

/**
 * Класс команды PrintDescending
 */
public class PrintDescending extends Command {
    public PrintDescending(Sender sender) {
        this.name = "print_descending";
        this.help = "Вывести элементы коллекции в порядке убывания";
        this.argsNumber = 0;
        this.sender = sender;
    }

    @Override
    public void execute(SocketAddress socketAddress, Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() != null) throw new IllegalFieldWrapperRequestException("команда не принимает элемент");
        CollectionManager collectionManager = CollectionManager.getInstance();
        List<Organization> list = collectionManager.getSortedList();
        Collections.reverse(list);
        sender.send(socketAddress, new Response(list.isEmpty() ? "Коллекция пуста." : null, list, true));
    }
}
