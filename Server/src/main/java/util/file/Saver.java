package util.file;

import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.CollectionManager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Saver extends Thread {
    private final Scanner scanner;
    private static final Logger logger = LogManager.getLogger();
    private final String filePath = "res/saves/save.csv";
    public Saver() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        String str;
        while (true) {
            str = scanner.nextLine().trim();
            if (str.equals("save")) {
                save();
            } else if (str.equals("exit")) {
                save();
                logger.info("Завершение программы.");
                System.exit(0);
            } else {
                logger.warn("Такой команды нету. Доступные команды: save и exit.");
            }
        }
    }

    public void save() {
        CollectionManager collectionManager = CollectionManager.getInstance();
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
