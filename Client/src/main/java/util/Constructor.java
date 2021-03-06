package util;

import elements.OrganizationType;
import elements.exceptions.ElementConstructorException;
import exchange.request.FieldsWrapper;
import exceptions.WrongCommandLineArgumentsException;
import talkers.Talker;
import java.util.NoSuchElementException;

/**
 * Класс для получения и валидации полей класса {@link elements.Organization Organization}
 */
public class Constructor {
    private Talker talker;
    private boolean isScript;

    public Constructor(Talker talker, boolean isScript) {
        this.talker = talker;
        this.isScript = isScript;
    }

    /**
     * Собрать объект {@link elements.Organization организации}
     *
     * @return собранный объект {@link elements.Organization Organisation}
     * @throws ElementConstructorException
     * ошибка сборки объекта класса {@link elements.Organization Organisation}
     */
    public FieldsWrapper construct() throws ElementConstructorException {
        return new FieldsWrapper(
                getName(),
                getX(),
                getY(),
                getAnnualTurnover(),
                getEmployeesCount(),
                getType(),
                getStreet(),
                getZipCode()
        );
    }

    /**
     * Получить имя
     *
     * @return имя {@link elements.Organization Organisation}
     */
    private String getName() {
        if (!isScript) talker.print("Введите название организации (непустая, строка): ");
        String name;
        try {
            String line = talker.read().trim();
            line = line.isEmpty() ? null : line;
            name = line;
            if (name == null) {
                throw new WrongCommandLineArgumentsException();
            }
        } catch (NoSuchElementException e) {
            name = "";
            System.exit(1);
        } catch (Exception e) {
            if (!isScript) talker.println("Ошибка ввода: введите корректное значение.");
            return getName();
        }
        return name;
    }

    /**
     * Получить координату X
     *
     * @return координата X {@link elements.Coordinates Coordinates}
     */
    private Double getX() {
        if (!isScript) talker.print("Введите координату x организации (непустая, double, не больше 76): ");
        Double x;
        try {
            String line = talker.read().trim();
            line = line.isEmpty() ? null : line;
            x = Double.parseDouble(line.replace(',', '.'));
            if (x > 76) {
                throw new WrongCommandLineArgumentsException();
            }
        } catch (NoSuchElementException e) {
            x = null;
            System.exit(1);
        } catch (Exception e) {
            if (!isScript) talker.println("Ошибка ввода: введите корректное значение. ");
            return getX();
        }
        return x;
    }

    /**
     * Получить координату Y
     *
     * @return координата Y {@link elements.Coordinates Coordinates}
     */
    private Float getY() {
        if (!isScript) talker.print("Введите координату y организации (непустая, float): ");
        Float y;
        try {
            String line = talker.read().trim();
            line = line.isEmpty() ? null : line;
            y = Float.parseFloat(line.replace(',', '.'));
        } catch (NoSuchElementException e) {
            y = null;
            System.exit(1);
        } catch (Exception e) {
            if (!isScript) talker.println("Ошибка ввода: введите корректное значение.");
            return getY();
        }
        return y;
    }

    /**
     * Получить годовой оборот
     *
     * @return годовой оборот {@link elements.Organization Organisation}
     */
    private Integer getAnnualTurnover() {
        if (!isScript) talker.print("Введите годовой оборот annualTurnover организации (integer, больше 0): ");
        Integer annualTurnover;
        try {
            String line = talker.read().trim();
            line = line.isEmpty() ? null : line;
            if (line == null) {
                return null;
            }
            annualTurnover = Integer.parseInt(line);
            if (annualTurnover <= 0) {
                throw new WrongCommandLineArgumentsException();
            }
        } catch (NoSuchElementException e) {
            annualTurnover = null;
            System.exit(1);
        } catch (Exception e) {
            if (!isScript) talker.println("Ошибка ввода: введите корректное значение.");
            return getAnnualTurnover();
        }
        return annualTurnover;
    }

    /**
     * Получить количество сотрудников
     *
     * @return количество сотрудников {@link elements.Organization Organisation}
     */
    private int getEmployeesCount() {
        if (!isScript) talker.print("Введите количество сотрудников employeesCount организации (int, больше 0): ");
        int employeesCount;
        try {
            String line = talker.read().trim();
            line = line.isEmpty() ? null : line;
            employeesCount = Integer.parseInt(line);
            if (employeesCount <= 0) {
                throw new WrongCommandLineArgumentsException();
            }
        } catch (NoSuchElementException e) {
            employeesCount = 0;
            System.exit(1);
        } catch (Exception e) {
            if (!isScript) talker.println("Ошибка ввода: введите корректное значение.");
            return getEmployeesCount();
        }
        return employeesCount;
    }

    /**
     * Получить тип организации
     *
     * @return тип организации {@link OrganizationType OrganisationType}
     */
    private OrganizationType getType() {
        if (!isScript) talker.print("Введите тип организации type организации (один из: COMMERCIAL, PUBLIC, TRUST, PRIVATE_LIMITED_COMPANY, OPEN_JOINT_STOCK_COMPANY): ");
        OrganizationType type;
        try {
            String line = talker.read().trim();
            line = line.isEmpty() ? null : line;
            if (line == null) {
                return null;
            }
            type = OrganizationType.valueOf(line);
        } catch (NoSuchElementException e) {
            type = null;
            System.exit(1);
        } catch (Exception e) {
            if (!isScript) talker.println("Ошибка ввода: введите корректное значение.");
            return getType();
        }
        return type;
    }

    /**
     * Получить имя улицы
     *
     * @return имя улицы {@link elements.Address Address}
     */
    private String getStreet() {
        if (!isScript) talker.print("Введите название улицы street организации (строка): ");
        String street;
        try {
            String line = talker.read().trim();
            line = line.isEmpty() ? null : line;
            street = line;
        } catch (NoSuchElementException e) {
            street = null;
            System.exit(1);
        } catch (Exception e) {
            if (!isScript) talker.println("Ошибка ввода: введите корректное значение.");
            return getStreet();
        }
        return street;
    }

    /**
     * Получить зип код
     *
     * @return зип код {@link elements.Address Address}
     */
    private String getZipCode() {
        if (!isScript) talker.print("Введите зип код zipCode организации (строка): ");
        String zipCode;
        try {
            String line = talker.read().trim();
            line = line.isEmpty() ? null : line;
            zipCode = line;
        } catch (NoSuchElementException e) {
            zipCode = null;
            System.exit(1);
        } catch (Exception e) {
            if (!isScript) talker.println("Ошибка ввода: введите корректное значение.");
            return getZipCode();
        }
        return zipCode;
    }
}
