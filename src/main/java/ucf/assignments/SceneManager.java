package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Paul Shannon
 */
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private Map<String, Scene> scenes = new HashMap<>();

    void load(){
        ProductModel productModel = new ProductModel();

        InventoryTrackerController inventoryTrackerController = new InventoryTrackerController(productModel,this);
        AddProductController addProductController = new AddProductController(productModel, this);
        DupErrorController dupErrorController = new DupErrorController(productModel,this);

        Parent root;

        //main app window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InventoryTracker.fxml"));
        loader.setController(inventoryTrackerController);

        try{
            root = loader.load();
            scenes.put("InventoryTracker", new Scene(root));
            inventoryTrackerController.updateTableView();
        }catch (IOException e){
            e.printStackTrace();
        }

        //add product window
        loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        loader.setController(addProductController);
        try{
            root = loader.load();
            scenes.put("AddProduct", new Scene(root));
            inventoryTrackerController.updateTableView();
        }catch (IOException e){
            e.printStackTrace();
        }

        //duplicate error window
        loader = new FXMLLoader(getClass().getResource("DupSerialNumError.fxml"));
        loader.setController(dupErrorController);
        try {
            root = loader.load();
            scenes.put("DupError", new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //accessor to take in name, look up name, and return for use
    public Scene getScene(String name){
        return scenes.get(name);
    }
}
