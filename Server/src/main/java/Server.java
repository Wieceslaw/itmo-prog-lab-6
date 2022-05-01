import exchange.request.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Convertor;
import exchange.response.Response;
import util.RequestHandler;
import util.file.Loader;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    private final byte[] buffer = new byte[65507];
    private final DatagramSocket datagramSocket;
    DatagramPacket datagramPacket;
    private boolean running;
    private static final Logger logger = LogManager.getLogger();

    public Server(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
        this.datagramPacket = new DatagramPacket(buffer, buffer.length);
    }

    private DatagramPacket receive() throws IOException {
        datagramSocket.receive(datagramPacket);
        return datagramPacket;
    }

    private void send(DatagramPacket datagramPacket, byte[] data) throws IOException {
        InetAddress inetAddress = datagramPacket.getAddress();
        int port = datagramPacket.getPort();
        datagramPacket = new DatagramPacket(data, data.length, inetAddress, port);
        datagramSocket.send(datagramPacket);
    }

    public void run() {
        running = true;
        RequestHandler requestHandler = new RequestHandler();
        while (running) {
            try {
                receive();
                byte[] data = datagramPacket.getData();
                Request request = (Request) Convertor.convertBytesToObject(data);
                Response response = requestHandler.executeRequest(request);
                send(datagramPacket, Convertor.convertObjectToBytes(response));
                logger.info("Client request:" + request.toString());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            logger.error("Неверное число аргументов. Можно ввести только один аргумент - название файла.");
            System.exit(-1);
        } else if (args.length == 1) {
            String fileName = args[0];
            Loader.load(fileName);
        }

        DatagramSocket ds = new DatagramSocket(2000);
        Server server = new Server(ds);
        server.run();
    }
}