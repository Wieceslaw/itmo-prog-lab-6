package exchange.request;

import elements.OrganizationType;

import java.io.Serializable;

/**
 * Класс-оболочка для полей класса {@link elements.Organization}
 */
public class FieldsWrapper implements Serializable {
    public String name; //Поле не может быть null, Строка не может быть пустой
    public Double x; //Максимальное значение поля: 76, Поле не может быть null
    public Float y; //Поле не может быть null
    public Integer annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    public int employeesCount; //Значение поля должно быть больше 0
    public OrganizationType organizationType; //Поле может быть null
    public String street; //Строка не может быть пустой, Поле может быть null
    public String zipCode; //Поле может быть null

    public FieldsWrapper(String name, Double x, Float y, Integer annualTurnover, int employeesCount, OrganizationType organizationType, String street, String zipCode) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.annualTurnover = annualTurnover;
        this.employeesCount = employeesCount;
        this.organizationType = organizationType;
        this.street = street;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "FieldsWrapper{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", annualTurnover=" + annualTurnover +
                ", employeesCount=" + employeesCount +
                ", organizationType='" + organizationType + '\'' +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
