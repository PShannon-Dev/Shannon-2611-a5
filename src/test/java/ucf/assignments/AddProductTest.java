package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AddProductTest {
    ProductModel productModel;
    SceneManager sceneManager;

    @Test
    void createNewProduct() {
        AddProduct addProduct = new AddProduct(productModel,sceneManager);
        String serialNum = "1234567890";
        String name = "expectedName";
        BigDecimal number = BigDecimal.valueOf(300.20);
        Product actualProduct = addProduct.CreateNewProduct(serialNum,name,number);

        assertEquals("1234567890", actualProduct.getSerialNumber());
        assertEquals("expectedName", actualProduct.getName());
        assertEquals(BigDecimal.valueOf(300.20), actualProduct.getValue());
    }
}