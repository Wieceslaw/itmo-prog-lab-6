package util;

import exchange.request.Request;
import exchange.response.Response;
import talkers.Talker;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Communicator {
    private final DatagramChannel datagramChannel;
    private final InetSocketAddress inetSocketAddress;
    private final ByteBuffer buffer = ByteBuffer.allocate(65507);
    private final Talker talker;

    public Communicator(String hostName, int port, Talker talker) throws IOException {
        this.talker = talker;
        this.inetSocketAddress = new InetSocketAddress(hostName, port);
        this.datagramChannel = DatagramChannel.open();
        this.datagramChannel.configureBlocking(false);
    }

    public void send(Request request) throws IOException {
        datagramChannel.send(ByteBuffer.wrap(Convertor.convertObjectToBytes(request)), inetSocketAddress);
    }

    public Response receive() throws IOException {
        long timeout = 2000;
        SocketAddress address = null;
        long startTime = System.currentTimeMillis();
        buffer.clear();
        while (address == null && (System.currentTimeMillis() - startTime) < timeout) {
            address = datagramChannel.receive(buffer);
        }
        if ((System.currentTimeMillis() - startTime) >= timeout) {
            talker.println("[ОШИБКА]: Превышено время ожидания ответа сервера.");
        } else {
            try {
                Response response = (Response) Convertor.convertBytesToObject(buffer.array());
                if (response == null) {
                    talker.println("[ОШИБКА]: Пустой ответ сервера.");
                } else {
                    return response;
                }
            } catch (ClassNotFoundException e ) {
                talker.println("[ОШИБКА]: Не удалось распознать ответ сервера.");
            }
        }
        return null;
    }

    public void show(Response response) {
        if (response != null) {
            if (!response.isSuccess())
                talker.println("[ОШИБКА]: " + (response.getText() == null ? "Не удалось распознать ответ сервера." : response.getText()));
            else if (response.getText() != null) talker.println(response.getText());
            if (response.getElements() != null)
                response.getElements().stream().forEach(x -> talker.println(x.toString()));
        }
    }

    public void execute(Request request) {
        try {
            send(request);
            Response response = receive();
            if (request.getCommand().equals("exit") && response.isSuccess()) {
                talker.println("Коллекция успешно сохранена.");
                talker.println("Завершение программы.");
                System.exit(0);
            }
            show(response);
        } catch (IOException e) {
            talker.println("[ОШИБКА]: ");
        }
    }
}
