package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Paul Shannon
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ProductModel {
    private ArrayList<Product> ListOfProducts = new ArrayList<>(100);

    public ArrayList<Product> getListOfProducts() {
        return ListOfProducts;
    }

    public void AddProduct(Product newProduct){
        //add item to the array list
        ListOfProducts.add(newProduct);
    }

    public void RemoveItem(int Index){
        //remove item from arraylist
        ListOfProducts.remove(Index);
    }

    public ObservableList<Product> createObservableList(ArrayList<Product> list){
        ObservableList<Product> obsListofProducts = FXCollections.observableArrayList();
        obsListofProducts.addAll(list);
        return obsListofProducts;
    }
}
