package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Paul Shannon
 */
import java.math.BigDecimal;

public class Product {
    private String name;
    private String serialNumber;
    private BigDecimal value;

    public Product(String name, String serialNumber, BigDecimal value) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public BigDecimal getValue() {
        return value;
    }
}
