package talkers;

/**
 * Интерфейс-обертка для общения с пользователем
 */
public interface Talker {
    /**
     * Считать строку
     *
     * @return считанная строка
     */
    String read();
    /**
     * Напечатать строку с переносом
     */
    void println(String str);
    /**
     * Напечатать строку без переноса
     */
    void print(String str);
    /**
     * Считать команду с префиксом
     *
     * @return Считанная командная строка
     */
    String readCommand();
}
