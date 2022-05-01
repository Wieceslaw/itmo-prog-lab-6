package requestbuilders;

import util.Communicator;
import talkers.Talker;
import util.Constructor;
import exchange.request.Request;
import exchange.request.FieldsWrapper;
import requestbuilders.exceptions.WrongArgumentsNumberException;

public class AddIfMax extends RequestBuilder {
    public static String name = "add_if_max";
    public static int argsNumber = 0;

    private final Talker talker;
    private final boolean isScript;
    private final Communicator communicator;

    public AddIfMax(Talker talker, boolean isScript, Communicator communicator) {
        this.talker = talker;
        this.communicator = communicator;
        this.isScript = isScript;
    }

    @Override
    public void execute(String[] args) throws WrongArgumentsNumberException {
        if (args.length != argsNumber) throw new WrongArgumentsNumberException(String.valueOf(argsNumber));
        FieldsWrapper fieldsWrapper = Constructor.construct(talker, isScript);
        communicator.execute(new Request(name, args, fieldsWrapper));
    }
}
