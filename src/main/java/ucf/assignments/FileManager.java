package ucf.assignments;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.processor.ObjectRowProcessor;
import com.univocity.parsers.conversions.Conversions;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;


import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {

    private ProductModel productModel;
    FileChooser fileChooser = new FileChooser();

    public FileManager(ProductModel productModel) {
        this.productModel = productModel;
    }
    private Stage stage = new Stage();
    public void saveAs(){
        fileChooser.setTitle("Save As...");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON", "*.json"),
                new FileChooser.ExtensionFilter("HTML","*.html"),
                new FileChooser.ExtensionFilter("TSV", "*.txt"));

        File file = fileChooser.showSaveDialog(stage);
        fileChooser.setInitialDirectory(file.getParentFile());

        //saving as json
        if(FilenameUtils.getExtension(file.getName()).equals("json")) {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                FileWriter filew = new FileWriter(file);
                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < productModel.getListOfProducts().size(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("serialNumber", productModel.getListOfProducts().get(i).getSerialNumber());
                    jsonObject.put("name", productModel.getListOfProducts().get(i).getName());
                    jsonObject.put("value", productModel.getListOfProducts().get(i).getValue());
                    jsonArray.put(jsonObject);
                }
                filew.write(jsonArray.toString());
                filew.close();

            } catch (IOException f) {
                f.printStackTrace();
            }
        }

        /*
        //saving as html
        else if(FilenameUtils.getExtension(file.getName()).equals("html"))
        {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                FileWriter filew = new FileWriter(file);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        //saving as tsv
        else
        {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                FileWriter filew = new FileWriter(file);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

         */
    }

    public void openFile(){
        fileChooser.setTitle("File Selection...");

        File file = fileChooser.showOpenDialog(stage);

        //reads file if extension is json
        if(FilenameUtils.getExtension(file.getName()).equals("json")) {

            try {
                if (file.isFile()) {
                    JsonElement fileElement = JsonParser.parseReader(new FileReader(file));
                    JsonArray fileArray = fileElement.getAsJsonArray();

                    for (int i = 0; i < fileArray.size(); i++) {
                        //get each object from JSON array
                        JsonObject fileObject = fileArray.get(i).getAsJsonObject();
                        //get values from each json object
                        String serialNum = fileObject.get("serialNumber").getAsString();
                        String name = fileObject.get("name").getAsString();
                        BigDecimal value = fileObject.get("value").getAsBigDecimal();

                        //create product and add to list
                        Product newProduct = new Product(name, serialNum, value);
                        productModel.AddProduct(newProduct);
                    }

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }


        //reads file if extension is html
        else if(FilenameUtils.getExtension(file.getName()).equals("html")) {
        }

        //reads file if extension is txt (tsv)
        else{
            if (file != null) {
                try {
                    //create tsv parser settings for it to go to next line after \n
                    TsvParserSettings settings = new TsvParserSettings();
                    settings.getFormat().setLineSeparator("\n");

                    //create parser from settings
                    TsvParser parser = new TsvParser(settings);

                    //parse all lines
                    List<String[]> all = parser.parseAll(new FileReader(file));
                    //outer array for each String[]
                    for(int i = 0; i < all.size(); i++){
                        //inner array to loop within
                        String serialNum = null;
                        String name = null;
                        BigDecimal value = BigDecimal.valueOf(0);
                        for(int j = 0; j < all.get(i).length; j+=3) {
                            serialNum = all.get(i)[j].toString();
                            name = all.get(i)[j+1].toString();
                            value = BigDecimal.valueOf(Double.valueOf(all.get(i)[j+2].toString()));
                        }
                        Product newProduct = new Product(name,serialNum,value);
                        productModel.AddProduct(newProduct);
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
    }
}

