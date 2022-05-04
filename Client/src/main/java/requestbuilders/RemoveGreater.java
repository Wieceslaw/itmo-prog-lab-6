package requestbuilders;

import exchange.request.FieldsWrapper;
import exchange.request.Request;
import requestbuilders.exceptions.WrongArgumentsNumberException;
import talkers.Talker;
import util.Constructor;
import util.transceiving.Receiver;
import util.transceiving.Sender;

/**
 * Класс сборщик запроса для запроса на сервер по команде RemoveGreater
 */
public class RemoveGreater extends RequestBuilder {
    private final Constructor constructor;
    public RemoveGreater(Talker talker, boolean isScript, Sender sender, Receiver receiver) {
        this.name = "remove_greater";
        this.argsNumber = 0;
        this.talker = talker;
        this.isScript = isScript;
        this.sender = sender;
        this.receiver = receiver;
        this.constructor = new Constructor(talker, isScript);
    }

    @Override
    public void execute(String[] args) throws WrongArgumentsNumberException {
        if (args.length != argsNumber) throw new WrongArgumentsNumberException(String.valueOf(argsNumber));
        FieldsWrapper fieldsWrapper = constructor.construct();
        if (sender.send(new Request(name, args, fieldsWrapper))) receiver.receive();
    }
}
