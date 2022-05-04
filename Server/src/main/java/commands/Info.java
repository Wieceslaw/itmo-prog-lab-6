package commands;

import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import exchange.request.Request;
import exchange.response.Response;
import util.CollectionManager;
import util.transceiving.Sender;

import java.net.SocketAddress;
import java.text.SimpleDateFormat;

/**
 * Класс команды Info
 */
public class Info extends Command {
    public Info(Sender sender) {
        this.name = "info";
        this.help = "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
        this.argsNumber = 0;
        this.sender = sender;
    }

    @Override
    public void execute(SocketAddress socketAddress, Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() != null) throw new IllegalFieldWrapperRequestException("команда не принимает элемент");
        StringBuilder stringBuilder = new StringBuilder();
        CollectionManager collectionManager = CollectionManager.getInstance();
        String pattern = "dd.M.yyyy hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        stringBuilder.append("Тип: ").append(collectionManager.getCollection().getClass().getName()).append('\n');;
        stringBuilder.append("Дата инициализации: ").append(simpleDateFormat.format(collectionManager.getCreationDate())).append('\n');
        stringBuilder.append("Количество элементов: ").append(collectionManager.getSize());
        sender.send(socketAddress, new Response(stringBuilder.toString(), true));
    }
}