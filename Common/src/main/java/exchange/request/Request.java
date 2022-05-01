package exchange.request;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Класс передачи данных о запросах с клиента
 */
public class Request implements Serializable {
    private final String command;
    private final String[] args;
    private final FieldsWrapper fieldsWrapper;
    private boolean isSilent;

    public Request(String command, String[] args, FieldsWrapper fieldsWrapper) {
        this.command = command;
        this.args = args;
        this.fieldsWrapper = fieldsWrapper;
        this.isSilent = false;
    }

    @Override
    public String toString() {
        return "Request{" +
                "command='" + command + '\'' +
                ", args=" + Arrays.toString(args) +
                ", fieldsWrapper=" + fieldsWrapper +
                ", isSilent=" + isSilent +
                '}';
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }

    public FieldsWrapper getFieldsWrapper() {
        return fieldsWrapper;
    }

    public boolean isSilent() {
        return isSilent;
    }

    public void setSilent(boolean silent) {
        isSilent = silent;
    }
}
