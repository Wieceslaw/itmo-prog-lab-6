package requestbuilders;

import requestbuilders.exceptions.WrongArgumentsException;
import exchange.request.FieldsWrapper;
import exchange.request.Request;
import requestbuilders.exceptions.WrongArgumentsNumberException;
import util.Communicator;
import talkers.Talker;
import util.Constructor;

public class Update extends RequestBuilder {
    public static String name = "update";
    public static int argsNumber = 1;
    private final Talker talker;
    private final boolean isScript;
    private final Communicator communicator;

    public Update(Talker talker, boolean isScript, Communicator communicator) {
        this.talker = talker;
        this.isScript = isScript;
        this.communicator = communicator;
    }

    @Override
    public void execute(String[] args) throws WrongArgumentsNumberException, WrongArgumentsException {
        if (args.length != argsNumber) throw new WrongArgumentsNumberException(String.valueOf(argsNumber));
        try {
            Long id = Long.parseLong(args[0]);
            if (id < 0) throw new WrongArgumentsException("Команда не принимает отрицательный ID.");
        } catch (NumberFormatException e) {
            throw new WrongArgumentsException("Неверный формат ID");
        }
        FieldsWrapper fieldsWrapper = Constructor.construct(talker, isScript);
        communicator.execute(new Request(name, args, fieldsWrapper));
    }
}
