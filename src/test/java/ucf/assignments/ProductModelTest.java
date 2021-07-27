package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductModelTest {
    //instance
    ProductModel productModel;
    @Test
    void addProduct() {
    }

    @Test
    void removeItem() {
        //create new products to add to list
        Product product1 = new Product("xbox","1234567890", BigDecimal.valueOf(300.00));
        Product product2 = new Product("PS5","1234567891", BigDecimal.valueOf(250.00));
        Product product3 = new Product("Switch","1234567892", BigDecimal.valueOf(150.00));
        //add new products to list
        productModel.AddProduct(product1);
        productModel.AddProduct(product2);
        productModel.AddProduct(product3);

        //call remove item
        productModel.RemoveItem(2);
        assertNotEquals(product3,productModel.getListOfProducts().get(2));
    }
}