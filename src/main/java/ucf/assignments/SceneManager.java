package ucf.assignments;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private Map<String, Scene> scenes = new HashMap<>();

    void load(){
        ListModel listModel = new ListModel();

        InventoryTrackerController inventoryTrackerController = new InventoryTrackerController(listModel,this);
        SaveAsController saveAsController = new SaveAsController(listModel, this);

        Parent root;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("InventoryTracker.fxml"));
        loader.setController(inventoryTrackerController);

        try{
            root = loader.load();
            scenes.put("InventoryTracker", new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
/*
        loader = new FXMLLoader(getClass().getResource("SaveAs.fxml"));
        loader.setController(saveAsController);
        try{
            root = loader.load();
            scenes.put("SaveAs", new Scene(root));
            saveAsController.openFileView();
        }catch (IOException e){
            e.printStackTrace();
        }
*/

    }

    //accessor to take in name, look up name, and return for use
    public Scene getScene(String name){
        return scenes.get(name);
    }
}
