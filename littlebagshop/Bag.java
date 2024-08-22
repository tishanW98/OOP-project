package com.mycompany.littlebagshop;

import java.io.Serializable;

public class Bag implements Serializable {
    private String name;
    private String category;
    private double price;

    public Bag(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + "," + category + "," + price;
    }
}
