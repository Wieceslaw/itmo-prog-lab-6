package util.file;

import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.CollectionManager;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс сохраняющий коллекцию в csv файл
 */
public class Saver {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Сохранить коллекцию в файл по пути "res/saves/save.csv"
     */
    public static void save() {
        CollectionManager collectionManager = CollectionManager.getInstance();
        String filePath = "res/saves/save.csv";
        try (FileWriter fw = new FileWriter(filePath); CSVWriter writer = new CSVWriter(fw)) {
            collectionManager.getCollection().stream()
                    .map(element -> new String[]{
                            String.valueOf(element.getId()),
                            element.getName(),
                            String.valueOf(element.getCoordinates().getX()),
                            String.valueOf(element.getCoordinates().getY()),
                            element.getAnnualTurnover() == null ? null : String.valueOf(element.getAnnualTurnover()),
                            String.valueOf(element.getEmployeesCount()),
                            element.getType() == null ? null : String.valueOf(element.getType()),
                            element.getOfficialAddress().getStreet(),
                            element.getOfficialAddress().getZipCode()})
                    .forEach(writer::writeNext);
            logger.info("Коллекция сохранена.");
        } catch (IOException e) {
            logger.error("Ошибка при работе с сохраняемым файлом");
        }
    }
}
