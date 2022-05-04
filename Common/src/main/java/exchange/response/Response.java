package exchange.response;

import elements.Organization;

import java.io.Serializable;
import java.util.List;

/**
 * Класс передачи данных ответа с сервера
 */
public class Response implements Serializable {
    private String text;
    private List<Organization> elements;
    private boolean success;

    public Response(String text, List<Organization> elements, boolean success) {
        this.text = text;
        this.elements = elements;
        this.success = success;
    }

    public Response(String text, boolean success) {
        this.text = text;
        this.elements = null;
        this.success = success;
    }

    @Override
    public String toString() {
        return "Response{" +
                "text='" + text + '\'' +
                ", elements=" + elements +
                '}';
    }

    public String getText() {
        return text;
    }

    public List<Organization> getElements() {
        return elements;
    }

    public boolean isSuccess() {
        return success;
    }
}
