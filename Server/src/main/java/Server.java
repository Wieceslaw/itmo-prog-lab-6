import exchange.request.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.CommandSelector;
import util.Convertor;
import util.file.Saver;
import util.transceiving.Receiver;
import util.transceiving.Sender;
import util.file.Loader;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

public class Server {
    private static final Logger logger = LogManager.getLogger();

    public static void load(String[] args) {
        if (args.length > 1) {
            logger.error("Неверное число аргументов. Можно ввести только один аргумент - название файла.");
            System.exit(-1);
        } else if (args.length == 1) {
            String fileName = args[0];
            Loader.load(fileName);
        }
    }

    public void run() {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(2000);
        } catch (IOException e) {
            logger.error("Ошибка при открытии сокета.", e);
            System.exit(-1);
        }
        Receiver receiver = new Receiver(socket);
        Sender sender = new Sender(socket);
        CommandSelector commandSelector = new CommandSelector(sender);
        DatagramPacket datagramPacket;
        byte[] data;
        SocketAddress socketAddress;
        Request request = null;
        Saver saver = new Saver();
        saver.start();

        while (true) {
            datagramPacket = receiver.receive();
            data = datagramPacket.getData();
            socketAddress = datagramPacket.getSocketAddress();
            try {
                request = (Request) Convertor.convertBytesToObject(data);
            } catch (IOException | ClassNotFoundException e) {
                logger.error("Ошибка сериализации");
            }
            if (request != null) {
                logger.info("Запрос клиента=" + request);
                commandSelector.executeRequest(socketAddress, request);
            }
        }
    }

    public static void main(String[] args) {
        load(args);
        Server server = new Server();
        server.run();
    }
}