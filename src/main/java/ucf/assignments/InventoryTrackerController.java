package ucf.assignments;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class InventoryTrackerController{
    @FXML
    private TableView<Product> ProductList;

    private SceneManager sceneManager;
    private ProductModel productModel;

    //column setup
    @FXML private TableColumn<Product, String> serialNumCol;
    @FXML private TableColumn<Product, String> productNameCol;
    @FXML private TableColumn<Product, BigDecimal> valueCol;


    public InventoryTrackerController(ProductModel productModel, SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.productModel = productModel;
    }

    @FXML
    void CloseProgramOptionClicked(ActionEvent event) {
        //terminates program
        Platform.exit();
    }

    @FXML
    void LoadListOptionClicked(ActionEvent event) {
        File file;
        Scanner fileIn = new Scanner(System.in);
        int response;
        JFileChooser chooser = new JFileChooser("C:");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        response = chooser.showOpenDialog(null);

        if(response == JFileChooser.APPROVE_OPTION){
            file = chooser.getSelectedFile();

            try {
                fileIn = new Scanner(file);

                if(file.isFile()){
                    while(fileIn.hasNext()){
                        String name = fileIn.next();
                        String serialNum = fileIn.next();
                        Double value = fileIn.nextDouble();
                        Product newProduct = new Product(name,serialNum,BigDecimal.valueOf(value));
                        productModel.getListOfProducts().add(newProduct);
                    }


                }

                else {
                    System.out.println("Not a file");
                }
                //close file
                fileIn.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void SaveAsOptionClicked(ActionEvent event) {
        System.out.println("Save as");
    }

    public void createObservableList(){

    }

    @FXML
    void AddProduct(ActionEvent event) {
        //use scenebuilder to open add item window
        Stage stage = new Stage();
        stage.setTitle("Add Product");
        stage.setScene(sceneManager.getScene("AddProduct"));
        stage.show();
    }

    @FXML
    void DeleteSelectedProduct(ActionEvent event) {

    }

    @FXML
    void EditSelectedProduct(ActionEvent event) {

    }



}
    /*  Stage stage = new Stage();
    stage.setTitle("Save As...");
    stage.setScene(sceneManager.getScene("SaveAs"));
    stage.show(); */

