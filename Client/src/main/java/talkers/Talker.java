package talkers;

/**
 * Интерфейс-обертка для общения с пользователем
 */
public interface Talker {
    /**
     * Прочитать строку
     */
    String read();
    /**
     * Вывести строку с переносом
     */
    void println(String str);
    /**
     * Вывести строку без переноса
     */
    void print(String str);
}
