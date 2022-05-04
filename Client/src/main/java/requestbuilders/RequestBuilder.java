package requestbuilders;

import requestbuilders.exceptions.WrongArgumentsException;
import requestbuilders.exceptions.WrongArgumentsNumberException;
import talkers.Talker;
import util.transceiving.Receiver;
import util.transceiving.Sender;

/**
 * Базовый класс сборщика запроса по команде
 */
public abstract class RequestBuilder {
    protected String name;
    protected int argsNumber;
    protected Talker talker;
    protected boolean isScript;
    protected Sender sender;
    protected Receiver receiver;

    abstract public void execute(String[] args) throws WrongArgumentsNumberException, WrongArgumentsException;
}
