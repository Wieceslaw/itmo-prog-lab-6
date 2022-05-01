package util;

import elements.OrganizationType;
import elements.exceptions.ElementConstructorException;
import exchange.request.FieldsWrapper;
import exceptions.WrongCommandLineArgumentsException;
import talkers.Talker;
import java.util.NoSuchElementException;


public class Constructor {
    public static FieldsWrapper construct(Talker talker, boolean isScript) throws ElementConstructorException {
        return new FieldsWrapper(
                getName(talker, isScript),
                getX(talker, isScript),
                getY(talker, isScript),
                getAnnualTurnover(talker, isScript),
                getEmployeesCount(talker, isScript),
                getType(talker, isScript),
                getStreet(talker, isScript),
                getZipCode(talker, isScript)
        );
    }

    private static String getName(Talker talker, boolean isScript) {
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
            return getName(talker, isScript);
        }
        return name;
    }

    private static Double getX(Talker talker, boolean isScript) {
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
            if (!isScript) talker.println("Ошибка ввода: введите корректное значение. " + e);
            return getX(talker, isScript);
        }
        return x;
    }

    private static Float getY(Talker talker, boolean isScript) {
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
            return getY(talker, isScript);
        }
        return y;
    }

    private static Integer getAnnualTurnover(Talker talker, boolean isScript) {
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
            return getAnnualTurnover(talker, isScript);
        }
        return annualTurnover;
    }

    private static int getEmployeesCount(Talker talker, boolean isScript) {
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
            return getEmployeesCount(talker, isScript);
        }
        return employeesCount;
    }

    private static OrganizationType getType(Talker talker, boolean isScript) {
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
            return getType(talker, isScript);
        }
        return type;
    }

    private static String getStreet(Talker talker, boolean isScript) {
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
            return getStreet(talker, isScript);
        }
        return street;
    }

    private static String getZipCode(Talker talker, boolean isScript) {
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
            return getZipCode(talker, isScript);
        }
        return zipCode;
    }
}
