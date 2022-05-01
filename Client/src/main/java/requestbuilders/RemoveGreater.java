package requestbuilders;

import exchange.request.FieldsWrapper;
import exchange.request.Request;
import requestbuilders.exceptions.WrongArgumentsNumberException;
import util.Communicator;
import talkers.Talker;
import util.Constructor;

public class RemoveGreater extends RequestBuilder {
    public static String name = "remove_greater";
    public static int argsNumber = 0;
    private final Talker talker;
    private final boolean isScript;
    private final Communicator communicator;

    public RemoveGreater(Talker talker, boolean isScript, Communicator communicator) {
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