package commands;

import exchange.request.Request;
import exchange.response.Response;

/**
 * Класс базовой команды
 *
 */
public abstract class Command {
    public abstract Response execute(Request request);
}