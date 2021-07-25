package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.awt.*;
import java.math.BigDecimal;

public class AddProductController {

    private SceneManager sceneManager;
    private ProductModel productModel;
    private AddProduct addProduct;

    //textfields
    /*
    @FXML private TextField SerialNumField;
    @FXML private TextField ValueField;
    @FXML private TextField NameField;
*/
    public AddProductController(ProductModel productModel, SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.productModel = productModel;
    }

    @FXML
    void CreateProduct(ActionEvent event) {
        //convert text field values to their actual type
        //call addProduct java class to create product pass textfield values through
        //call productmodel java class to add product to the arraylist
    }
}
