package requestbuilders;

import util.Communicator;
import talkers.Talker;
import util.Constructor;
import exchange.request.Request;
import exchange.request.FieldsWrapper;
import requestbuilders.exceptions.WrongArgumentsNumberException;

/**
 * Класс сборщик запроса для запроса на сервер по команде Add
 */
public class Add extends RequestBuilder {
    public static String name = "add";
    public static int argsNumber = 0;
    private final Talker talker;
    private final boolean isScript;
    private final Communicator communicator;

    public Add(Talker talker, boolean isScript, Communicator communicator) {
        this.talker = talker;
        this.isScript = isScript;
        this.communicator = communicator;
    }

    @Override
    public void execute(String[] args) throws WrongArgumentsNumberException {
        if (args.length != argsNumber) throw new WrongArgumentsNumberException(String.valueOf(argsNumber));
        FieldsWrapper fieldsWrapper = Constructor.construct(talker, isScript);
        communicator.execute(new Request(name, args, fieldsWrapper));
    }
}
