package requestbuilders;

import talkers.Talker;
import util.Constructor;
import exchange.request.Request;
import exchange.request.FieldsWrapper;
import requestbuilders.exceptions.WrongArgumentsNumberException;
import util.transceiving.Receiver;
import util.transceiving.Sender;

/**
 * Класс сборщик запроса для запроса на сервер по команде Add
 */
public class Add extends RequestBuilder {
    private final Constructor constructor;

    public Add(Talker talker, boolean isScript, Sender sender, Receiver receiver) {
        this.name = "add";
        this.argsNumber = 0;
        this.talker = talker;
        this.isScript = isScript;
        this.sender = sender;
        this.constructor = new Constructor(talker, isScript);
        this.receiver = receiver;
    }

    @Override
    public void execute(String[] args) throws WrongArgumentsNumberException {
        if (args.length != argsNumber) throw new WrongArgumentsNumberException(String.valueOf(argsNumber));
        FieldsWrapper fieldsWrapper = constructor.construct();
        if (sender.send(new Request(name, args, fieldsWrapper))) receiver.receive();
    }
}
