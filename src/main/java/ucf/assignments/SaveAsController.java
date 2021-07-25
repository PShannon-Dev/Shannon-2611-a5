package ucf.assignments;

import java.io.*;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class SaveAsController{
    private ProductModel productModel;
    private SceneManager sceneManager;



    //contructor for scene manager
    public SaveAsController(ProductModel productModel, SceneManager sceneManager) {
        this.productModel = productModel;
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
