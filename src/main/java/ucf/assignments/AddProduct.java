package ucf.assignments;

import java.math.BigDecimal;

public class AddProduct {

    public Product CreateNewProduct(String name, String serialNum, BigDecimal value){
        //get values from text fields
        //textfield serial num -> string
        //if conditional
        //if nonexisting continue
        //else (duplicate) open error message and restart
        //textfield name -> string
        //textfield value -> BigDecimal

        //pass through to product constructor
        Product tempProduct = new Product(name, serialNum, value);
        return tempProduct;
    }
}
