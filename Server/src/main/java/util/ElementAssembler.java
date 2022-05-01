package util;

import elements.*;
import exchange.request.FieldsWrapper;
public class ElementAssembler {
    /**
     * Конвертирует оболочку {@code FieldsWrapper} в класс {@code Organisation}
     *
     * @param fieldsWrapper оболочка с полями класса
     * @return элемента класса {@code Organisation}
     */
    public static Organization assemble(FieldsWrapper fieldsWrapper) {
        return new Organization(
                fieldsWrapper.name,
                new Coordinates(fieldsWrapper.x, fieldsWrapper.y),
                fieldsWrapper.annualTurnover,
                fieldsWrapper.employeesCount,
                fieldsWrapper.organizationType,
                new Address(fieldsWrapper.street, fieldsWrapper.zipCode)
                );
    }
}
