package ucf.assignments;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;

/*
    1.  The user shall be able to store at least 100 inventory items
        Each inventory item shall have a value representing its monetary value in US dollars
        Each inventory item shall have a unique serial number in the format of XXXXXXXXXX where X can be either a letter or digit
            The application shall display an error message if the user enters an existing serial number for the new item
            The user shall be able to edit the serial number of an existing inventory item
            The application shall prevent the user from duplicating the serial number
        Each inventory item shall have a name between 2 and 256 characters in length (inclusive)
    2.  The user shall be able to add a new inventory item
    3.  The user shall be able to remove an existing inventory item
    4.  The user shall be able to edit the value of an existing inventory item
    5.  The user shall be able to edit the name of an existing inventory item
    6.  Sorting
            The user shall be able to sort the inventory items by value
            The user shall be able to sort inventory items by serial number
            The user shall be able to sort inventory items by name
    8.  Searching
            The user shall be able to search for an inventory item by serial number
            The user shall be able to search for an inventory item by name
    9.  The user shall be able to save their inventory items to a file
            The user shall be able to select the file format from among the following set of options: TSV (tab-separated value), HTML, JSON
            TSV files shall list one inventory item per line, separate each field within an inventory item using a tab character, and end with the extension .txt
            HTML files shall contain valid HTML and end with the extension .html
            The list of inventory items must appear as a table when the HTML file is rendered.
            JSON files shall contain valid JSON and end with the extension .json
            The user shall provide the file name and file location of the file to save
    10. The user shall be able to load inventory items from a file that was previously created by the application.
            The user shall provide the file name and file location of the file to load
 */
public class InventoryTracker extends Application {


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        SceneManager sceneManager = new SceneManager();
        sceneManager.load();

        Scene scene = sceneManager.getScene("InventoryTracker");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Inventory Tracker");
        primaryStage.show();
    }

    //Get products for list
    public static ObservableList<Product> getProducts(){
        ObservableList<Product> listOfProducts = FXCollections.observableArrayList();
        listOfProducts.add(new Product("xbox","1234567890", BigDecimal.valueOf(430.00)));
        listOfProducts.add(new Product("dfs","1534241134",BigDecimal.valueOf(23.00)));
        listOfProducts.add(new Product("sdf","123451234",BigDecimal.valueOf(40.00)));
        return listOfProducts;
    }
}
