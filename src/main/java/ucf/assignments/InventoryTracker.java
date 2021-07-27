package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Paul Shannon
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class InventoryTracker extends Application {


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        SceneManager sceneManager = new SceneManager();
        sceneManager.load();

        //starts inital scene that will control future movement of app
        Scene scene = sceneManager.getScene("InventoryTracker");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Inventory Tracker");
        primaryStage.show();
    }

}

