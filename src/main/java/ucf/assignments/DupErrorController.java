package ucf.assignments;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

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
