package util;

import commands.*;
import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import elements.exceptions.ElementConstructorException;
import exchange.request.Request;
import exchange.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.exceptions.UniqueElementException;
import util.transceiving.Sender;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandSelector {
    private static final Logger logger = LogManager.getLogger();
    private final HashMap<String, Command> commands = new HashMap<>();
    private final Sender sender;

    public CommandSelector(Sender sender) {
        this.sender = sender;
        commands.put("help", new Help(sender));
        commands.put("info", new Info(sender));
        commands.put("show", new Show(sender));
        commands.put("add", new Add(sender));
        commands.put("update", new Update(sender));
        commands.put("remove_by_id", new RemoveById(sender));
        commands.put("clear", new Clear(sender));
        commands.put("execute_script", new ExecuteScript(sender));
        commands.put("add_if_max", new AddIfMax(sender));
        commands.put("add_if_min", new AddIfMin(sender));
        commands.put("remove_greater", new RemoveGreater(sender));
        commands.put("sum_of_annual_turnover", new SumOfAnnualTurnover(sender));
        commands.put("print_descending", new PrintDescending(sender));
        commands.put("print_field_ascending_employees_count", new PrintFieldAscendingEmployeesCount(sender));
    }

    public List<Command> getCommands() {
        return new ArrayList<>(commands.values());
    }

    /**
     * Исполнить запрос клиента
     *
     * @param request запрос от клиента {@link Request}
     */
    public void executeRequest(SocketAddress socketAddress, Request request) {
        if (request == null) {
            logger.warn("Невозможно распознать команду");
            sender.send(socketAddress, new Response("Невозможно распознать команду.", false));
        }
        if (!commands.containsKey(request.getCommand())){
            logger.warn("Несуществующая команда в запросе");
            sender.send(socketAddress, new Response("Такой команды нет. Введите help, чтобы получить список и описание команд.", false));
        }
        try {
            commands.get(request.getCommand()).execute(socketAddress, request);
        } catch (IllegalArgsNumberRequestException e) {
            logger.error("Request error", e);
            sender.send(socketAddress, new Response("Невереное число аргументов. Команда принимает " + e.getMessage() + " аргументов.", false));
        } catch (ElementConstructorException e) {
            logger.error("Request error", e);
            sender.send(socketAddress, new Response("Неверное поле " + e.getMessage() + "в переданном элементе.", false));
        } catch (IllegalFieldWrapperRequestException | UniqueElementException | NullPointerException e) {
            logger.error("Request error", e);
            sender.send(socketAddress, new Response(e.getMessage(), false));
        } catch (NumberFormatException e) {
            logger.error("Request error", e);
            sender.send(socketAddress, new Response("Неверный формат ID.", false));
        }
    }
}
