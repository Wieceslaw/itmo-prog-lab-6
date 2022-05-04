package util.transceiving;

import exchange.response.Response;
import talkers.Talker;
import util.Convertor;

import java.io.IOException;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Класс для получения ответов от сервра
 */
public class Receiver {
    private final DatagramChannel datagramChannel;
    private final ByteBuffer buffer = ByteBuffer.allocate(65507);
    private final Talker talker;

    public Receiver(DatagramChannel datagramChannel, Talker talker) {
        this.datagramChannel = datagramChannel;
        this.talker = talker;
    }

    /**
     * Получить ответ сервера и вывести пользователю
     *
     * <p>
     *     Если ответ от сервера запаздывает, но подключение есть, существует timeout в 2 секунды ожидания ответа.
     *     Если подключение отсутствует, то будет выкинута ошибка {@link PortUnreachableException}.
     * </p>
     */
    public void receive() {
        try {
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
                Response response = null;
                try {
                    response = (Response) Convertor.convertBytesToObject(buffer.array());
                } catch (IOException | ClassNotFoundException e) {
                    talker.println("[ОШИБКА]: Ошибка сериализации ответа.");
                }
                if (response == null) {
                    talker.println("[ОШИБКА]: Пустой ответ сервера.");
                } else {
                    show(response);
                }
            }
        } catch (PortUnreachableException e) {
            talker.println("[ОШИБКА]: Сервер недоступен, попробуйте снова.");
        } catch (IOException e) {
            talker.println("[ОШИБКА]: Проблема соединения с сервером..");
        }
    }

    /**
     * Вывести ответ сервера в вывод пользователя
     *
     * @param response {@link Response ответ} сервера
     */
    private void show(Response response) {
        if (response != null) {
            if (!response.isSuccess())
                talker.println("[ОШИБКА]: " + (response.getText() == null ? "Не удалось распознать ответ сервера." : response.getText()));
            else if (response.getText() != null) {
                talker.println(response.getText());
            }
            if (response.getElements() != null)
                response.getElements().stream().forEach(x -> talker.println(x.toString()));
        }
    }
}