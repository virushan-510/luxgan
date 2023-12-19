package com.anjana.luxgan.models;

import java.io.Serializable;

public class ShowItemModel implements Serializable {

    String desString;
    String image;
    String name;

    int price;

    public ShowItemModel() {
    }

    public ShowItemModel(String desString, String image, String name, int price) {
        this.desString = desString;
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public String getDesString() {
        return desString;
    }

    public void setDesString(String desString) {
        this.desString = desString;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
