package ucf.assignments;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
