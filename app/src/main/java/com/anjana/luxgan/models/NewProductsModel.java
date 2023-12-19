package com.anjana.luxgan.models;

import java.io.Serializable;

public class NewProductsModel implements Serializable {

    String description;
    String name;
    String rating;
    int price;
    String img_url;

    public NewProductsModel(){


    }

    public NewProductsModel(String description, String name, String rating, int price, String img_url) {
        this.description = description;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.img_url = img_url;
    }

public String getDescription(){
        return description;
}
    public String getImg_url() {
        return img_url;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

   public int getPrice() {
       return price;}

}
