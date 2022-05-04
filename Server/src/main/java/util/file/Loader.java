package util.file;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import elements.*;
import elements.exceptions.ElementConstructorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.CollectionManager;

import java.io.*;
import java.util.Objects;

/**
 * Класс загружающий файл csv в коллекцию
 */
public class Loader {
    private static final Logger logger = LogManager.getLogger();
    /**
     * Загрузить данные в коллекцию из файла
     *
     * @param filePath путь к файлу, с которого ведется загрузка
     */
    public static void load(String filePath) {
        CollectionManager collectionManager = CollectionManager.getInstance();
        if (!Objects.equals(filePath.split("\\.")[filePath.split("\\.").length - 1], "csv")) {
            logger.error("Неверный формат файла. Используйте формат csv.");
            System.exit(-1);
        }
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
             CSVReader csvReader = new CSVReaderBuilder(fileReader).withCSVParser(new CSVParserBuilder().build()).build()) {
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                Organization organization = parseCsvLine(line);
                if (organization != null) {
                    collectionManager.add(organization);
                } else {
                    logger.warn("Не удалось считать строку.");
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("Ошибка. Файл не найден.");
            System.exit(-1);
        } catch (CsvValidationException | IOException e) {
            logger.error("Ошибка чтения файла.");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Парсер строки полей класса из csv-файла
     *
     * @param line массив строк - полей создаваемого класса
     * @return объект класса {@code Organisation}, с заданными полями, или {@code null}, если не удалось создать объект
     */
    private static Organization parseCsvLine(String[] line) throws ElementConstructorException {
        if (line.length == 9) {
            Long id = Long.parseLong(line[0]);
            String name = line[1];
            Coordinates coordinates = new Coordinates(Double.parseDouble(line[2].replace(',', '.')), Float.parseFloat(line[3].replace(',', '.')));
            Integer annualTurnover = Objects.equals(line[4], "") ? null : Integer.parseInt(line[4]);
            int employeesCount = Integer.parseInt(line[5]);
            OrganizationType type = line[6].equals("") ? null : OrganizationType.valueOf(line[6]);
            String street = line[7].equals("") ? null : line[7];
            String zipCode = line[8].equals("") ? null : line[8];
            Address address = new Address(street, zipCode);
            return new Organization(id, name, coordinates, annualTurnover, employeesCount, type, address);
        } else {
            return null;
        }
    }
}
