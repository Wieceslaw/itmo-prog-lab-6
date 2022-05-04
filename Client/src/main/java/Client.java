import talkers.ConsoleTalker;
import util.CommandSelector;
import talkers.ScannerTalker;
import talkers.Talker;
import util.transceiving.Receiver;
import util.transceiving.Sender;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

public class Client {
    static String hostName = "localhost";
    static int port = 2000;
    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(hostName, port);
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        datagramChannel.connect(inetSocketAddress);
        Talker talker = new ScannerTalker();
        Receiver receiver = new Receiver(datagramChannel, talker);
        Sender sender = new Sender(datagramChannel, talker, inetSocketAddress);
        CommandSelector commandSelector = new CommandSelector(sender, receiver, talker, false);
        String str;
        while (true) {
            str = talker.readCommand();
            if (str != null) commandSelector.execute(str);
            else talker.println("");
        }
    }
}