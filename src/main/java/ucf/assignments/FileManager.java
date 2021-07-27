package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Paul Shannon
 */
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private ProductModel productModel;
    private String title;
    FileChooser fileChooser = new FileChooser();

    public FileManager(ProductModel productModel, String title) {
        this.productModel = productModel;
        this.title = title;
    }

    private Stage stage = new Stage();
    public void saveAs(){
        fileChooser.setTitle(title);
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
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("<html>");

                //write table
                writer.write("<table style ='width:100%'>");

                //create header
                writer.write("<tr> <th>Serial Number</th><th>Product Name</th><th>Value</th><tr>");

                //table rows
                for(int i = 0; i < productModel.getListOfProducts().size(); i++){
                    writer.write("<tr>");
                    writer.write("<td>" + productModel.getListOfProducts().get(i).getSerialNumber() + "</td>");
                    writer.write("<td>" + productModel.getListOfProducts().get(i).getName() + "</td>");
                    writer.write("<td>" + productModel.getListOfProducts().get(i).getValue().toString() + "</td>");
                    writer.write("</tr>");
                }
                writer.write("</table>");
                writer.write("</html>");
                writer.close();
            } catch (IOException e) {
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
                for(int i = 0; i < productModel.getListOfProducts().size(); i++) {
                    filew.write(productModel.getListOfProducts().get(i).getSerialNumber() + "\t" +
                    productModel.getListOfProducts().get(i).getName() + "\t" +productModel.getListOfProducts().get(i).getValue() + "\n");
                }
                filew.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void openFile(){
        fileChooser.setTitle(title);

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
            //use stringbuilder to append each new item to the string
            StringBuilder productInfo = new StringBuilder();

            if(file != null){
                try{
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String nextValue;

                    //read in the html doc as a string to parse
                    while((nextValue=br.readLine())!= null){
                        productInfo.append(nextValue);
                    }
                    br.close();
                    String htmlString = productInfo.toString();

                    //parse string
                    Document doc = Jsoup.parse(htmlString);

                    //create element instances using tags
                    Element table = doc.select("table").get(0);
                    Elements rows = table.select("tr");

                    ArrayList<String> elementText = new ArrayList<>(100);

                    for(int i = 1; i < rows.size(); i++){
                        Element row = rows.get(i);
                        Elements columns = row.select("td");
                        elementText.add(columns.text());
                    }

                    for(int j = 1; j < elementText.size(); j++) {
                        //split array into subarray using split method
                        String[] splitString = elementText.get(j).split(" ");

                        String serialNum= splitString[0];
                        String name = splitString[1];
                        BigDecimal value = BigDecimal.valueOf(Double.valueOf(splitString[2]));
                        productModel.AddProduct(new Product(name,serialNum,value));
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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

