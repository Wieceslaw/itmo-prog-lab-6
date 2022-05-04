package talkers;

import java.util.Scanner;

/**
 * Класс-обертка для общения с пользователем через сканнер {@link Scanner}
 */
public class ScannerTalker implements Talker {
    Scanner scanner;

    public ScannerTalker() {
        this.scanner = new Scanner(System.in);
    }

    public ScannerTalker(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String read() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            System.out.println("Конец ввода.");
            System.exit(0);
        }
        return null;
    }

    @Override
    public void println(String str) {
        System.out.println(str);
    }

    @Override
    public void print(String str) {
        System.out.print(str);
    }

    @Override
    public String readCommand() {
        print("Введите команду: ");
        return read();
    }
}
