package ucf.assignments;

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
import javafx.scene.input.InputMethodEvent;
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



    public InventoryTrackerController(ProductModel productModel, SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.productModel = productModel;
    }

    public void updateTableView(){
        ProductList.setItems(productModel.createObservableList(productModel.getListOfProducts()));

        serialNumCol.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        serialNumCol.setCellFactory(TextFieldTableCell.forTableColumn());
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));
        valueCol.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));

        ProductList.getColumns().setAll(serialNumCol,productNameCol,valueCol);
    }

    @FXML
    void search(InputMethodEvent event) {
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
        ProductList.getItems().clear();
        FileManager fileManager = new FileManager(productModel);
        fileManager.openFile();
        updateTableView();
    }

    @FXML
    void SaveAsOptionClicked(ActionEvent event) {
        FileManager fileManager = new FileManager(productModel);
        fileManager.saveAs();
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
    void DeleteSelectedProduct(ActionEvent event) {
        productModel.RemoveItem(ProductList.getSelectionModel().getSelectedIndex());
    }


}

