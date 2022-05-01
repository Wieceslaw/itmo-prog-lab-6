package requestbuilders;

import util.CommandSelector;
import util.Communicator;
import requestbuilders.exceptions.WrongArgumentsNumberException;
import talkers.ScannerTalker;
import talkers.Talker;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

public class ExecuteScript extends RequestBuilder {
    public static Stack<String> scriptsStack = new Stack<String>();
    public static String name = "execute_script";
    public static int argsNumber = 1;
    private final Talker talker;
    private final boolean isScript;
    private final Communicator communicator;

    public ExecuteScript(Talker talker, boolean isScript, Communicator communicator) {
        this.talker = talker;
        this.isScript = isScript;
        this.communicator = communicator;
    }

    @Override
    public void execute(String[] args) throws WrongArgumentsNumberException {
        if (args.length != argsNumber) throw new WrongArgumentsNumberException(String.valueOf(argsNumber));
        String fileName = args[0];
        if (scriptsStack.contains(fileName)) {
            talker.println("Скрипт " + Paths.get(fileName).getFileName() + " уже вызван.");
        } else {
            scriptsStack.push(fileName);
            try {
                Scanner newScanner = new Scanner(new File(fileName));
                Talker scannerTalker = new ScannerTalker(newScanner);
//                Invoker invoker = Invoker.getInstance();
                CommandSelector commandSelector = new CommandSelector(communicator, scannerTalker, true);
                while (newScanner.hasNextLine()) {
                    String str = newScanner.nextLine().trim();
                    commandSelector.execute(str);
//                    invoker.execute(str, true, scannerTalker);
                }
                talker.println("Файл " + Paths.get(fileName).getFileName() + " успешно исполнен.");
            } catch (NullPointerException | FileNotFoundException e) {
                talker.println("Нет указанного файла " + e.getMessage());
            }
            scriptsStack.pop();
        }
    }
}
