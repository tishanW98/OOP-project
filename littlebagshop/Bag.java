package com.mycompany.littlebagshop;

import java.io.Serializable;

class Bag implements Serializable {
    private String name;
    private Category category;

    public Bag(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Bag: " + name + ", Category: " + category.getName();
    }
}