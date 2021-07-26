package ucf.assignments;

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

    }
}
