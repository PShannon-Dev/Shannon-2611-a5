package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Paul Shannon
 */
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.BigDecimalStringConverter;

import java.math.BigDecimal;

public class InventoryTrackerController{
    @FXML
    private TableView<Product> ProductList;

    private SceneManager sceneManager;
    private ProductModel productModel;

    @FXML
    private TextField searchText;

    //column setup
    @FXML private TableColumn<Product, String> serialNumCol;
    @FXML private TableColumn<Product, String> productNameCol;
    @FXML private TableColumn<Product, BigDecimal> valueCol;


    //button to refresh table after item is added
    @FXML
    void RefreshTable(ActionEvent event) {
        updateTableView();
    }

    public InventoryTrackerController(ProductModel productModel, SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.productModel = productModel;
    }

    public void updateTableView() {
        ProductList.setItems(productModel.createObservableList(productModel.getListOfProducts()));

        //updates each column cell value as well as makes the cell editable
        serialNumCol.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        serialNumCol.setCellFactory(TextFieldTableCell.forTableColumn());
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));
        valueCol.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));

        //sets table column values
        ProductList.getColumns().setAll(serialNumCol, productNameCol, valueCol);
    }
    @FXML
    void AddProduct(ActionEvent event) {
        //use scenebuilder to open add item window
        Stage stage = new Stage();
        stage.setTitle("Add Product");
        stage.setScene(sceneManager.getScene("AddProduct"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    void search(KeyEvent event) {
        FilteredList<Product> filteredSearch = new FilteredList<>(productModel.createObservableList(productModel.getListOfProducts()), b -> true);
        searchText.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredSearch.setPredicate(Product -> {
                //if empty, display all
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                //compare serial number and name to text
                String lowerCaseNewValue = newValue.toLowerCase();

                if(Product.getName().toLowerCase().indexOf(lowerCaseNewValue) != -1)
                    return true;
                else if(Product.getSerialNumber().toLowerCase().indexOf(lowerCaseNewValue) != -1)
                    return true;
                else if(Product.getValue().toString().indexOf(lowerCaseNewValue) != -1)
                    return true;
                else
                    return false;
            });
        });

        SortedList<Product> sortedList = new SortedList<>(filteredSearch);

        sortedList.comparatorProperty().bind(ProductList.comparatorProperty());

        ProductList.setItems(sortedList);
    }
    @FXML
    void CloseProgramOptionClicked(ActionEvent event) {
        //terminates program
        Platform.exit();
    }

    @FXML
    void LoadListOptionClicked(ActionEvent event) {
        //clear list already opened
        productModel.getListOfProducts().clear();
        ProductList.getItems().clear();
        //use file manager class to open item
        FileManager fileManager = new FileManager(productModel,"Select Inventory...");
        fileManager.openFile();
        updateTableView();
    }

    @FXML
    void SaveAsOptionClicked(ActionEvent event) {
        //use FileManager Class to save
        FileManager fileManager = new FileManager(productModel, "Create Inventory");
        fileManager.saveAs();
    }

    @FXML
    void DeleteSelectedProduct(ActionEvent event) {
        //removes item from list
        productModel.RemoveItem(ProductList.getSelectionModel().getSelectedIndex());
        //create new observable list for tableview, clear others before
        updateTableView();
    }

}

