package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Paul Shannon
 */
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class AddProduct {
    private ProductModel productModel;
    private SceneManager sceneManager;

    public AddProduct(ProductModel productModel, SceneManager sceneManager) {
        this.productModel = productModel;
        this.sceneManager = sceneManager;
    }

    public Product CreateNewProduct(String name, String serialNum, BigDecimal value){
        //boolean for error
        Boolean errorFound = false;
        //if conditional
        if (productModel.getListOfProducts().size() == 0)
            return new Product(name, serialNum, value);
        else {
            for(int i = 0; i < productModel.getListOfProducts().size(); i++) {
                //if serial number is duplicate open error message
                if (serialNum.equals(productModel.getListOfProducts().iterator().next().getSerialNumber())) {
                    Stage stage = new Stage();
                    stage.setTitle("Duplicate Serial Number Error");
                    stage.setScene(sceneManager.getScene("DupError"));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                    errorFound = true;
                    break;
                }
            }
        }
        if (errorFound.equals(false)) {
            return new Product(name, serialNum, value);
        } else {
            return null;
        }
    }
}
