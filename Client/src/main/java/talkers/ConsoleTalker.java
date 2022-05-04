package talkers;

import java.io.Console;

/**
 * Класс-обертка для общения с пользователем через {@link Console}
 */
public class ConsoleTalker implements Talker {
    Console console;

    /**
     * Конструктор класса с вызовом системной консоли
     */
    public ConsoleTalker() {
        this.console = System.console();
    }

    @Override
    public String read() {
        return console.readLine();
    }

    @Override
    public void println(String str) {
        console.printf(str + '\n');
    }

    @Override
    public void print(String str) {
        console.printf(str);
    }

    @Override
    public String readCommand() {
        print("Введите команду: ");
        return read();
    }
}
