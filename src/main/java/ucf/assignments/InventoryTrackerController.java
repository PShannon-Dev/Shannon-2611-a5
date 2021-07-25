package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class InventoryTrackerController implements Initializable {
    @FXML
    private TableView<Product> ProductList;

    private SceneManager sceneManager;
    private ListModel listModel;

    //column setup
    @FXML
    private TableColumn<Product, String> serialNumCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, Double> valueCol;

    //create inventorytracker instance
    //private InventoryTracker inventoryTracker = new InventoryTracker();


    public InventoryTrackerController(ListModel listModel, SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.listModel = listModel;
    }

    public void LoadListOptionClicked(ActionEvent actionEvent) {
        //create tableview
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        serialNumCol.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));

        ProductList.setItems(InventoryTracker.getProducts());

    }

    public void SaveAsOptionClicked(ActionEvent actionEvent) {
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
                        Product newProduct = new Product(name,serialNum,value);
                        listModel.getListOfProducts().add(newProduct);
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

        /*  Stage stage = new Stage();
        stage.setTitle("Save As...");
        stage.setScene(sceneManager.getScene("SaveAs"));
        stage.show(); */

    public void CloseProgramOptionClicked(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //create tableview
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        serialNumCol.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        serialNumCol.setCellFactory(TextFieldTableCell.forTableColumn());
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));
    }
}
