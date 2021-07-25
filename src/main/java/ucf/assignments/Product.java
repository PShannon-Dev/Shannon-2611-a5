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
}
