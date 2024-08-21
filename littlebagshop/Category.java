package com.mycompany.littlebagshop;

import java.io.Serializable;

class Category implements Serializable {
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Category: " + name;
    }
}

