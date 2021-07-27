package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Paul Shannon
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DupErrorController {
    private ProductModel productModel;
    private SceneManager sceneManager;

    @FXML
    private Button closeButton;

    public DupErrorController(ProductModel productModel, SceneManager sceneManager) {
        this.productModel = productModel;
        this.sceneManager = sceneManager;
    }

    @FXML
    void CloseError(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }
}
