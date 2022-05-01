package requestbuilders;

import exchange.request.Request;
import requestbuilders.exceptions.WrongArgumentsNumberException;
import util.Communicator;
import talkers.Talker;

public class Info extends RequestBuilder {
    public static String name = "info";
    public static int argsNumber = 0;
    private final Talker talker;
    private final boolean isScript;
    private final Communicator communicator;

    public Info(Talker talker, boolean isScript, Communicator communicator) {
        this.talker = talker;
        this.isScript = isScript;
        this.communicator = communicator;
    }

    @Override
    public void execute(String[] args) throws WrongArgumentsNumberException {
        if (args.length != argsNumber) throw new WrongArgumentsNumberException(String.valueOf(argsNumber));
        communicator.execute(new Request(name, args, null));
    }
}
