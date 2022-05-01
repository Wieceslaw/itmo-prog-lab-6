package talkers;

import java.io.Console;

/**
 * Класс-обертка для общения с пользователем через консоль
 */
public class ConsoleTalker implements Talker {
    private static ConsoleTalker consoleTalker;
    Console console;

    private ConsoleTalker() {
        this.console = System.console();
    }

    public static ConsoleTalker getInstance() {
        if (consoleTalker == null) {
            consoleTalker = new ConsoleTalker();
        }
        return consoleTalker;
    }

    @Override
    public String read() {
        return null;
    }

    @Override
    public void println(String str) {
        ;
    }

    @Override
    public void print(String str) {
        ;
    }
}
