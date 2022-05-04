package commands.exceptions;

/**
 * Базовое исключение при работе с запросами {@link exchange.request.Request} клиента
 */
public class IllegalRequestException extends IllegalArgumentException {
    public IllegalRequestException() {
    }

    public IllegalRequestException(String s) {
        super(s);
    }

    public IllegalRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalRequestException(Throwable cause) {
        super(cause);
    }
}
