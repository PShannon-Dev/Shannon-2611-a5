package ucf.assignments;

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
        SaveAsController saveAsController = new SaveAsController(productModel,this);

        Parent root;

        //main app window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InventoryTracker.fxml"));
        loader.setController(inventoryTrackerController);

        try{
            root = loader.load();
            scenes.put("InventoryTracker", new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }

        //add product window
        loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        loader.setController(addProductController);
        try{
            root = loader.load();
            scenes.put("AddProduct", new Scene(root));
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

        //Save As window
        loader = new FXMLLoader(getClass().getResource("SaveAs.fxml"));
        loader.setController(saveAsController);
        try {
            root = loader.load();
            scenes.put("SaveAs", new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //accessor to take in name, look up name, and return for use
    public Scene getScene(String name){
        return scenes.get(name);
    }
}
