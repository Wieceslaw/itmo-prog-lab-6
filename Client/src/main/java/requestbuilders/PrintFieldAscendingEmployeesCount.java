package requestbuilders;

import exchange.request.Request;
import requestbuilders.exceptions.WrongArgumentsNumberException;
import talkers.Talker;
import util.transceiving.Receiver;
import util.transceiving.Sender;

/**
 * Класс сборщик запроса для запроса на сервер по команде PrintFieldAscendingEmployeesCount
 */
public class PrintFieldAscendingEmployeesCount extends RequestBuilder {
    public PrintFieldAscendingEmployeesCount(Talker talker, boolean isScript, Sender sender, Receiver receiver) {
        this.name = "print_field_ascending_employees_count";
        this.argsNumber = 0;
        this.talker = talker;
        this.isScript = isScript;
        this.sender = sender;
        this.receiver = receiver;
    }

    @Override
    public void execute(String[] args) throws WrongArgumentsNumberException {
        if (args.length != argsNumber) throw new WrongArgumentsNumberException(String.valueOf(argsNumber));
        if (sender.send(new Request(name, args, null))) receiver.receive();
    }
}
