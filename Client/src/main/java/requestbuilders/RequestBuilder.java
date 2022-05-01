package requestbuilders;

import requestbuilders.exceptions.WrongArgumentsException;
import requestbuilders.exceptions.WrongArgumentsNumberException;

/**
 * Базовый класс сборщика запроса по команде
 */
public abstract class RequestBuilder {
    abstract public void execute(String[] args) throws WrongArgumentsNumberException, WrongArgumentsException;
}
