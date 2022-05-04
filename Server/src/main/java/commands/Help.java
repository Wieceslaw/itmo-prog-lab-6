package commands;

import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import exchange.request.Request;
import exchange.response.Response;
import util.CommandSelector;
import util.transceiving.Sender;

import java.net.SocketAddress;

/**
 * Класс команды Help
 */
public class Help extends Command {
    public Help(Sender sender) {
        this.name = "help";
        this.help = "Вывести справку по доступным командам";
        this.argsNumber = 0;
        this.sender = sender;
    }

    @Override
    public void execute(SocketAddress socketAddress, Request request) {
        if (request.getArgs().length != argsNumber) throw new IllegalArgsNumberRequestException(String.valueOf(argsNumber));
        if (request.getFieldsWrapper() != null) throw new IllegalFieldWrapperRequestException("команда не принимает элемент");
        CommandSelector commandSelector = new CommandSelector(sender);
        StringBuilder helpDesc = new StringBuilder();
        for(Command command : commandSelector.getCommands()) {
            if (command.name != "save") helpDesc.append(command.name).append(": ").append(command.help).append('\n');
        }
        helpDesc.deleteCharAt(helpDesc.length() - 1);
        sender.send(socketAddress, new Response(helpDesc.toString(), true));
    }
}
