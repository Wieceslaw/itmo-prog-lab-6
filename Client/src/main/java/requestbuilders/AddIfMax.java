package requestbuilders;

import talkers.Talker;
import util.Constructor;
import exchange.request.Request;
import exchange.request.FieldsWrapper;
import requestbuilders.exceptions.WrongArgumentsNumberException;
import util.transceiving.Receiver;
import util.transceiving.Sender;

/**
 * Класс сборщик запроса для запроса на сервер по команде AddIfMax
 */
public class AddIfMax extends RequestBuilder {
    private final Constructor constructor;
    public AddIfMax(Talker talker, boolean isScript, Sender sender, Receiver receiver) {
        this.name = "add_if_max";
        this.argsNumber = 0;
        this.talker = talker;
        this.sender = sender;
        this.isScript = isScript;
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
