package commands;

import exchange.request.Request;
import exchange.response.Response;
import util.transceiving.Receiver;
import util.transceiving.Sender;

import java.net.SocketAddress;

/**
 * Класс базовой команды
 *
 */
public abstract class Command {
    protected String name;
    protected String help;
    protected int argsNumber;
    protected Sender sender;
    public abstract void execute(SocketAddress socketAddress, Request request);
}