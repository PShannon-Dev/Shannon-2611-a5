package ucf.assignments;

import javafx.fxml.Initializable;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class SaveAsController{
    private ListModel listModel;
    private SceneManager sceneManager;



    //contructor for scene manager
    public SaveAsController(ListModel listModel, SceneManager sceneManager) {
        this.listModel = listModel;
        this.sceneManager = sceneManager;
    }


    public void openFileView() {
        File file;
        Scanner fileIn = new Scanner(System.in);
        int response;
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        response = chooser.showOpenDialog(null);
    }
}
