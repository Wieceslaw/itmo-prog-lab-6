package requestbuilders;

import requestbuilders.exceptions.WrongArgumentsException;
import exchange.request.FieldsWrapper;
import exchange.request.Request;
import requestbuilders.exceptions.WrongArgumentsNumberException;
import talkers.Talker;
import util.Constructor;
import util.transceiving.Receiver;
import util.transceiving.Sender;

/**
 * Класс сборщик запроса для запроса на сервер по команде Update
 */
public class Update extends RequestBuilder {
    private final Constructor constructor;
    public Update(Talker talker, boolean isScript, Sender sender, Receiver receiver) {
        this.name = "update";
        this.argsNumber = 1;
        this.talker = talker;
        this.isScript = isScript;
        this.sender = sender;
        this.constructor = new Constructor(talker, isScript);
        this.receiver = receiver;
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
        if (sender.send(new Request(name, args, constructor.construct()))) receiver.receive();
    }
}
