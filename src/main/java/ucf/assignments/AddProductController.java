package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class AddProductController {

    private SceneManager sceneManager;
    private ProductModel productModel;

    @FXML private Button AddProductButton;

    //textfields
    @FXML private TextField SerialNumField;
    @FXML private TextField ValueField;
    @FXML private TextField NameField;

    public AddProductController(ProductModel productModel, SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.productModel = productModel;
    }

    @FXML
    void CreateProduct(ActionEvent event) {
        //convert text field values to their actual type
        String newSerialNum = SerialNumField.getText();
        String newName = NameField.getText();
        BigDecimal newValue = new BigDecimal(ValueField.getText());

        //add product
        AddProduct addProduct = new AddProduct(productModel, sceneManager);
        Product tempProduct = addProduct.CreateNewProduct(newName, newSerialNum, newValue);

        //if null don't add, else add
        if(tempProduct == null)
            ;
        else
            addProduct.addNewProduct(tempProduct);

        //clear textfields
        SerialNumField.clear();
        NameField.clear();
        ValueField.clear();

        //close window after product added
        Stage stage = (Stage) AddProductButton.getScene().getWindow();
        stage.close();
    }
}
