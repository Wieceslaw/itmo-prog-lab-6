package util;

import exchange.request.Request;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.*;
import commands.exceptions.IllegalArgsNumberRequestException;
import commands.exceptions.IllegalFieldWrapperRequestException;
import elements.exceptions.ElementConstructorException;
import exchange.response.Response;
import util.exceptions.UniqueElementException;

/**
 * Класс обрабатывающий запросы
 */
public class RequestHandler {
    private static final Logger logger = LogManager.getLogger();
    private final HashMap<String, Command> commands = new HashMap<>();

    /**
     * Конструктор класса
     */
    public RequestHandler() {
        commands.put("help", new Help());
        commands.put("info", new Info());
        commands.put("show", new Show());
        commands.put("add", new Add());
        commands.put("update", new Update());
        commands.put("remove_by_id", new RemoveById());
        commands.put("clear", new Clear());
        commands.put("save", new Save());
        commands.put("execute_script", new ExecuteScript());
        commands.put("exit", new Exit());
        commands.put("add_if_max", new AddIfMax());
        commands.put("add_if_min", new AddIfMin());
        commands.put("remove_greater", new RemoveGreater());
        commands.put("sum_of_annual_turnover", new SumOfAnnualTurnover());
        commands.put("print_descending", new PrintDescending());
        commands.put("print_field_ascending_employees_count", new PrintFieldAscendingEmployeesCount());
    }

    /**
     * Обработать запрос
     *
     * @param request запрос с клиента {@link Request}
     * @return {@link Response} - ответ сервера
     */
    public Response executeRequest(Request request) {
        if (request == null) {
            logger.warn("Невозможно распознать команду");
            return new Response("Невозможно распознать команду.", false);
        }
        if (!commands.containsKey(request.getCommand())){
            logger.warn("Несуществующая команда в запросе");
            return new Response("Такой команды нет. Введите help, чтобы получить список и описание команд.", false);
        }
        try {
            return commands.get(request.getCommand()).execute(request);
        } catch (IllegalArgsNumberRequestException e) {
            logger.error("Request error", e);
            return new Response("Невереное число аргументов. Команда принимает " + e.getMessage() + " аргументов.", false);
        } catch (ElementConstructorException e) {
            logger.error("Request error", e);
            return new Response("Неверное поле " + e.getMessage() + "в переданном элементе.", false);
        } catch (IllegalFieldWrapperRequestException | UniqueElementException | NullPointerException e) {
            logger.error("Request error", e);
            return new Response(e.getMessage(), false);
        } catch (NumberFormatException e) {
            logger.error("Request error", e);
            return new Response("Неверный формат ID.", false);
        }
    }
}
