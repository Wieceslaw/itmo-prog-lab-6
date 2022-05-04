package util.transceiving;

import exchange.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Convertor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

/**
 * Класс для отправки ответов сервера
 */
public class Sender {
    private final DatagramSocket datagramSocket;
    private static final Logger logger = LogManager.getLogger();

    public Sender(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    /**
     * Отправить {@link Response ответ} сервера клиенту
     *
     * @param socketAddress адрес получателя
     * @param response ответ сервера
     */
    public void send(SocketAddress socketAddress, Response response) {
        try {
            byte[] data = Convertor.convertObjectToBytes(response);
            if (data != null) {
                datagramSocket.send(new DatagramPacket(data, data.length, socketAddress));
            }
        } catch (IOException e) {
            logger.error("Ошибка отправки запроса.", e);
        }
    }
}
