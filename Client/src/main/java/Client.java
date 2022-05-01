import util.CommandSelector;
import util.Communicator;
import talkers.ScannerTalker;
import talkers.Talker;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        Talker talker = new ScannerTalker();
        Communicator communicator = new Communicator("localhost", 2000, talker);
        CommandSelector commandSelector = new CommandSelector(communicator, talker, false);
        String str;
        while (true) {
            str = talker.read();
            commandSelector.execute(str);
        }
    }
}