package io.github.gokhanbarisaker.ecommerce.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Catalog {

    // == Variables ================================================================================

    @SerializedName("items")
    @Expose
    private List<Product> items = new ArrayList<Product>();


    // == Factory methods ==========================================================================

    public static Catalog random() {
        Catalog catalog = new Catalog();

        for (int i = 0; i < 5; i++) {
            catalog.getItems().add(Product.random());
        }

        return catalog;
    }


    // == Accessors ================================================================================

    /**
     * @return The items
     */
    public List<Product> getItems() {
        return items;
    }

    /**
     * @param items The items
     */
    public void setItems(List<Product> items) {
        this.items = items;
    }

}
